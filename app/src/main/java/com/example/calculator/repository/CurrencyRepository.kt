package com.example.calculator.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.calculator.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class CurrencyRepository(private val context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences("currency_cache", Context.MODE_PRIVATE)
    private val apiService = ApiClient.currencyApiService
    
    companion object {
        private const val CACHE_KEY = "exchange_rates_cache"
        private const val CACHE_TIMESTAMP_KEY = "cache_timestamp"
        private const val CACHE_DURATION_HOURS = 1L // Cache for 1 hour
    }
    
    suspend fun getExchangeRates(baseCurrency: String = "USD"): Result<Map<String, Double>> = withContext(Dispatchers.IO) {
        try {
            // Check if we have valid cached data for this base currency
            val cachedRates = getCachedRates(baseCurrency)
            if (cachedRates != null && !isCacheExpired()) {
                return@withContext Result.success(cachedRates)
            }
            
            // Fetch fresh data from API
            val response = apiService.getExchangeRates(baseCurrency)
            if (response.isSuccessful && response.body() != null) {
                val rates = response.body()!!.rates
                cacheRates(rates, baseCurrency)
                Result.success(rates)
            } else {
                // Fallback to cached data if available
                val fallbackRates = getCachedRates(baseCurrency)
                if (fallbackRates != null) {
                    Result.success(fallbackRates)
                } else {
                    Result.failure(Exception("Failed to fetch exchange rates: ${response.message()}"))
                }
            }
        } catch (e: Exception) {
            // Fallback to cached data if available
            val fallbackRates = getCachedRates(baseCurrency)
            if (fallbackRates != null) {
                Result.success(fallbackRates)
            } else {
                Result.failure(e)
            }
        }
    }
    
    suspend fun convertCurrency(amount: Double, fromCurrency: String, toCurrency: String): Result<Double> = withContext(Dispatchers.IO) {
        try {
            if (fromCurrency == toCurrency) {
                return@withContext Result.success(amount)
            }
            
            // Try to get rates with fromCurrency as base first
            val directRates = getExchangeRates(fromCurrency)
            if (directRates.isSuccess && directRates.getOrNull()?.containsKey(toCurrency) == true) {
                val rate = directRates.getOrNull()!![toCurrency]!!
                return@withContext Result.success(amount * rate)
            }
            
            // If direct conversion not available, use USD as intermediate
            val usdRates = getExchangeRates("USD")
            if (usdRates.isSuccess) {
                val rates = usdRates.getOrNull()!!
                val fromRate = rates[fromCurrency] ?: 1.0
                val toRate = rates[toCurrency] ?: 1.0
                
                // Convert: fromCurrency -> USD -> toCurrency
                val usdValue = amount / fromRate
                val result = usdValue * toRate
                return@withContext Result.success(result)
            }
            
            Result.failure(Exception("Unable to convert between $fromCurrency and $toCurrency"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private fun getCachedRates(baseCurrency: String): Map<String, Double>? {
        val cacheKey = "${CACHE_KEY}_$baseCurrency"
        val cachedData = prefs.getString(cacheKey, null)
        return if (cachedData != null) {
            try {
                cachedData.split(",").associate { pair ->
                    val (key, value) = pair.split(":")
                    key to value.toDouble()
                }
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }
    
    private fun cacheRates(rates: Map<String, Double>, baseCurrency: String) {
        val cacheKey = "${CACHE_KEY}_$baseCurrency"
        val cacheData = rates.map { "${it.key}:${it.value}" }.joinToString(",")
        prefs.edit()
            .putString(cacheKey, cacheData)
            .putLong(CACHE_TIMESTAMP_KEY, System.currentTimeMillis())
            .apply()
    }
    
    private fun isCacheExpired(): Boolean {
        val cacheTime = prefs.getLong(CACHE_TIMESTAMP_KEY, 0L)
        val currentTime = System.currentTimeMillis()
        return (currentTime - cacheTime) > TimeUnit.HOURS.toMillis(CACHE_DURATION_HOURS)
    }
    
    // Fallback rates for when API is unavailable
    fun getFallbackRates(): Map<String, Double> = mapOf(
        "USD" to 1.0,
        "EUR" to 0.85,
        "GBP" to 0.73,
        "JPY" to 110.0,
        "INR" to 75.0,
        "CAD" to 1.25,
        "AUD" to 1.35,
        "CHF" to 0.92,
        "CNY" to 6.45,
        "RUB" to 75.0,
        "KRW" to 1180.0,
        "SGD" to 1.35,
        "HKD" to 7.8,
        "NZD" to 1.45,
        "MXN" to 20.0,
        "BRL" to 5.2,
        "ZAR" to 15.0,
        "TRY" to 8.5,
        "AED" to 3.67,
        "SAR" to 3.75
    )
}
