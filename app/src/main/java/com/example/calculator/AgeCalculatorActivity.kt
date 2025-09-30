package com.example.calculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class AgeCalculatorActivity : AppCompatActivity() {
    
    private lateinit var birthDate: EditText
    private lateinit var currentDate: EditText
    private lateinit var resultText: TextView
    private lateinit var calculateButton: TextView
    private lateinit var backButton: ImageButton
    private lateinit var useCurrentDateButton: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_calculator)
        
        initializeViews()
        setupClickListeners()
        setCurrentDate()
    }
    
    private fun initializeViews() {
        birthDate = findViewById(R.id.birthDate)
        currentDate = findViewById(R.id.currentDate)
        resultText = findViewById(R.id.resultText)
        calculateButton = findViewById(R.id.calculateButton)
        backButton = findViewById(R.id.backButton)
        useCurrentDateButton = findViewById(R.id.useCurrentDateButton)
    }
    
    private fun setupClickListeners() {
        calculateButton.setOnClickListener {
            calculateAge()
        }
        
        backButton.setOnClickListener {
            finish()
        }
        
        useCurrentDateButton.setOnClickListener {
            setCurrentDate()
        }
    }
    
    private fun setCurrentDate() {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val today = Date()
        currentDate.setText(sdf.format(today))
    }
    
    private fun calculateAge() {
        try {
            val birthDateStr = birthDate.text.toString()
            val currentDateStr = currentDate.text.toString()
            
            if (birthDateStr.isEmpty() || currentDateStr.isEmpty()) {
                resultText.text = "Please enter both dates"
                return
            }
            
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val birth = sdf.parse(birthDateStr)
            val current = sdf.parse(currentDateStr)
            
            if (birth == null || current == null) {
                resultText.text = "Please enter valid dates (YYYY-MM-DD)"
                return
            }
            
            if (birth.after(current)) {
                resultText.text = "Birth date cannot be after current date"
                return
            }
            
            val calendarBirth = Calendar.getInstance()
            val calendarCurrent = Calendar.getInstance()
            calendarBirth.time = birth
            calendarCurrent.time = current
            
            var years = calendarCurrent.get(Calendar.YEAR) - calendarBirth.get(Calendar.YEAR)
            var months = calendarCurrent.get(Calendar.MONTH) - calendarBirth.get(Calendar.MONTH)
            var days = calendarCurrent.get(Calendar.DAY_OF_MONTH) - calendarBirth.get(Calendar.DAY_OF_MONTH)
            
            if (days < 0) {
                months--
                val daysInPreviousMonth = calendarCurrent.getActualMaximum(Calendar.DAY_OF_MONTH)
                days += daysInPreviousMonth
            }
            
            if (months < 0) {
                years--
                months += 12
            }
            
            val totalDays = ((current.time - birth.time) / (1000 * 60 * 60 * 24)).toInt()
            val totalWeeks = totalDays / 7
            val totalMonths = years * 12 + months
            
            resultText.text = """
                Age: $years years, $months months, $days days
                Total Days: $totalDays
                Total Weeks: $totalWeeks
                Total Months: $totalMonths
            """.trimIndent()
            
        } catch (e: Exception) {
            resultText.text = "Error calculating age. Please use YYYY-MM-DD format"
        }
    }
}
