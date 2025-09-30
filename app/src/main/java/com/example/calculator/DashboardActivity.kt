package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    
    private lateinit var calculatorGrid: GridView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        
        setupCalculatorGrid()
    }
    
    private fun setupCalculatorGrid() {
        calculatorGrid = findViewById(R.id.calculatorGrid)
        
        val calculatorTypes = listOf(
            CalculatorType("Normal Calculator", "Basic arithmetic operations", R.drawable.ic_normal_calc, CalculatorTypeEnum.NORMAL),
            CalculatorType("Scientific Calculator", "Advanced mathematical functions", R.drawable. ic_scientific_calc, CalculatorTypeEnum.SCIENTIFIC),
            CalculatorType("EMI Calculator", "Calculate loan EMI", R.drawable.ic_emi_calc, CalculatorTypeEnum.EMI),
            CalculatorType("Finance Calculator", "Financial calculations", R.drawable.ic_finance_calc, CalculatorTypeEnum.FINANCE),
            CalculatorType("Unit Converter", "Convert between units", R.drawable.ic_unit_conv, CalculatorTypeEnum.UNIT),
            CalculatorType("Currency Converter", "Convert currencies", R.drawable.ic_currency_conv, CalculatorTypeEnum.CURRENCY),
            CalculatorType("Percentage Calculator", "Calculate percentages", R.drawable.ic_percentage_calc, CalculatorTypeEnum.PERCENTAGE),
            CalculatorType("Tip Calculator", "Calculate tips and splits", R.drawable.ic_tip_calc, CalculatorTypeEnum.TIP),
            CalculatorType("Age Calculator", "Calculate age and dates", R.drawable.ic_age_calc, CalculatorTypeEnum.AGE),
            CalculatorType("BMI Calculator", "Calculate body mass index", R.drawable.ic_bmi_calc, CalculatorTypeEnum.BMI)
        )
        
        val adapter = CalculatorGridAdapter(this, calculatorTypes) { calculatorType ->
            when (calculatorType.type) {
                CalculatorTypeEnum.NORMAL -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                CalculatorTypeEnum.SCIENTIFIC -> {
                    val intent = Intent(this, ScientificCalculatorActivity::class.java)
                    startActivity(intent)
                }
                CalculatorTypeEnum.EMI -> {
                    val intent = Intent(this, EMICalculatorActivity::class.java)
                    startActivity(intent)
                }
                CalculatorTypeEnum.FINANCE -> {
                    val intent = Intent(this, FinanceCalculatorActivity::class.java)
                    startActivity(intent)
                }
                CalculatorTypeEnum.UNIT -> {
                    val intent = Intent(this, UnitConverterActivity::class.java)
                    startActivity(intent)
                }
                CalculatorTypeEnum.CURRENCY -> {
                    val intent = Intent(this, CurrencyConverterActivity::class.java)
                    startActivity(intent)
                }
                CalculatorTypeEnum.PERCENTAGE -> {
                    val intent = Intent(this, PercentageCalculatorActivity::class.java)
                    startActivity(intent)
                }
                CalculatorTypeEnum.TIP -> {
                    val intent = Intent(this, TipCalculatorActivity::class.java)
                    startActivity(intent)
                }
                CalculatorTypeEnum.AGE -> {
                    val intent = Intent(this, AgeCalculatorActivity::class.java)
                    startActivity(intent)
                }
                CalculatorTypeEnum.BMI -> {
                    val intent = Intent(this, BMICalculatorActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        
        calculatorGrid.adapter = adapter
    }
}

data class CalculatorType(
    val name: String,
    val description: String,
    val icon: Int,
    val type: CalculatorTypeEnum
)

enum class CalculatorTypeEnum {
    NORMAL, SCIENTIFIC, EMI, FINANCE, UNIT, CURRENCY, PERCENTAGE, TIP, AGE, BMI
}
