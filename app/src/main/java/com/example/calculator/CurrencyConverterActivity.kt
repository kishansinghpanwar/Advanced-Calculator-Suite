package com.example.calculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.calculator.repository.CurrencyRepository
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class CurrencyConverterActivity : AppCompatActivity() {
    
    private lateinit var fromValue: EditText
    private lateinit var toValue: TextView
    private lateinit var fromCurrency: Spinner
    private lateinit var toCurrency: Spinner
    private lateinit var convertButton: TextView
    private lateinit var backButton: ImageButton
    private lateinit var refreshButton: ImageButton
    private lateinit var lastUpdatedText: TextView
    private lateinit var progressBar: ProgressBar
    
    private val decimalFormat = DecimalFormat("#,##0.00")
    private lateinit var currencyRepository: CurrencyRepository
    private var exchangeRates: Map<String, Double> = emptyMap()
    
    private val currencies = listOf(
        "USD", "EUR", "GBP", "JPY", "INR", "CAD", "AUD", "CHF", "CNY", "RUB",
        "KRW", "SGD", "HKD", "NZD", "MXN", "BRL", "ZAR", "TRY", "AED", "SAR"
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_converter)
        
        currencyRepository = CurrencyRepository(this)
        
        initializeViews()
        setupClickListeners()
        setupSpinners()
        loadExchangeRates()
    }
    
    private fun initializeViews() {
        fromValue = findViewById(R.id.fromValue)
        toValue = findViewById(R.id.toValue)
        fromCurrency = findViewById(R.id.fromCurrency)
        toCurrency = findViewById(R.id.toCurrency)
        convertButton = findViewById(R.id.convertButton)
        backButton = findViewById(R.id.backButton)
        refreshButton = findViewById(R.id.refreshButton)
        lastUpdatedText = findViewById(R.id.lastUpdatedText)
        progressBar = findViewById(R.id.progressBar)
    }
    
    private fun setupClickListeners() {
        convertButton.setOnClickListener {
            convertCurrency()
        }
        
        backButton.setOnClickListener {
            finish()
        }
        
        refreshButton.setOnClickListener {
            loadExchangeRates()
        }
    }
    
    private fun setupSpinners() {
        val currencyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fromCurrency.adapter = currencyAdapter
        toCurrency.adapter = currencyAdapter
        
        // Set default currencies
        val defaultFromIndex = currencies.indexOf("USD")
        val defaultToIndex = currencies.indexOf("EUR")
        if (defaultFromIndex >= 0) fromCurrency.setSelection(defaultFromIndex)
        if (defaultToIndex >= 0) toCurrency.setSelection(defaultToIndex)
    }
    
    private fun loadExchangeRates() {
        showLoading(true)
        
        lifecycleScope.launch {
            try {
                // Load USD-based rates as default (most comprehensive)
                val result = currencyRepository.getExchangeRates("USD")
                result.fold(
                    onSuccess = { rates ->
                        exchangeRates = rates
                        showLoading(false)
                        updateLastUpdatedText()
                        convertCurrency() // Auto-convert with new rates
                    },
                    onFailure = { error ->
                        // Use fallback rates
                        exchangeRates = currencyRepository.getFallbackRates()
                        showLoading(false)
                        toValue.text = "Using offline rates - ${error.message}"
                        updateLastUpdatedText()
                    }
                )
            } catch (e: Exception) {
                exchangeRates = currencyRepository.getFallbackRates()
                showLoading(false)
                toValue.text = "Using offline rates - ${e.message}"
                updateLastUpdatedText()
            }
        }
    }
    
    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) android.view.View.VISIBLE else android.view.View.GONE
        convertButton.isEnabled = !show
        refreshButton.isEnabled = !show
    }
    
    private fun updateLastUpdatedText() {
        lastUpdatedText.text = "Last updated: ${java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault()).format(java.util.Date())}"
    }
    
    private fun convertCurrency() {
        try {
            val value = fromValue.text.toString().toDoubleOrNull() ?: 0.0
            val fromCurr = fromCurrency.selectedItem.toString()
            val toCurr = toCurrency.selectedItem.toString()
            
            if (value <= 0) {
                toValue.text = "Please enter a positive value"
                return
            }
            
            if (fromCurr == toCurr) {
                toValue.text = "${decimalFormat.format(value)} $toCurr"
                return
            }
            
            lifecycleScope.launch {
                try {
                    val result = currencyRepository.convertCurrency(value, fromCurr, toCurr)
                    result.fold(
                        onSuccess = { convertedValue ->
                            toValue.text = "${decimalFormat.format(convertedValue)} $toCurr"
                        },
                        onFailure = { error ->
                            // Fallback to simple USD-based conversion
                            if (exchangeRates.isNotEmpty()) {
                                val fromRate = exchangeRates[fromCurr] ?: 1.0
                                val toRate = exchangeRates[toCurr] ?: 1.0
                                val usdValue = value / fromRate
                                val fallbackResult = usdValue * toRate
                                toValue.text = "${decimalFormat.format(fallbackResult)} $toCurr (USD-based)"
                            } else {
                                toValue.text = "Error: ${error.message}"
                            }
                        }
                    )
                } catch (e: Exception) {
                    toValue.text = "Error converting currency: ${e.message}"
                }
            }
            
        } catch (e: Exception) {
            toValue.text = "Error converting currency: ${e.message}"
        }
    }
}
