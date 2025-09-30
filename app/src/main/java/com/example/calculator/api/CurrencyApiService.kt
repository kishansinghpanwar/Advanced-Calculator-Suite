package com.example.calculator.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApiService {
    @GET("latest/{base}")
    suspend fun getExchangeRates(@Path("base") baseCurrency: String): Response<ExchangeRateResponse>
}

data class ExchangeRateResponse(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
