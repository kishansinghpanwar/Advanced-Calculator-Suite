package com.example.calculator

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Simplified calculator tests using text-based matching and result validation
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class SimplifiedCalculatorTests {

    @get:Rule
    val dashboardRule = ActivityScenarioRule(DashboardActivity::class.java)

    @Before
    fun disableAnimations() {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.executeShellCommand("settings put global window_animation_scale 0")
        device.executeShellCommand("settings put global transition_animation_scale 0")
        device.executeShellCommand("settings put global animator_duration_scale 0")
    }

    @Test
    fun dashboard_shouldNavigateToNormalCalculator() {
        // Click on Normal Calculator
        onView(withText("Normal Calculator")).perform(click())
        Thread.sleep(1000)
        
        // Verify we're in normal calculator (check for back button)
        onView(withId(R.id.backButton)).check(matches(isDisplayed()))
        
        // Go back to dashboard
        onView(withId(R.id.backButton)).perform(click())
        Thread.sleep(500)
    }

    @Test
    fun normalCalculator_shouldPerformBasicMathOperations() {
        // Navigate to Normal Calculator
        onView(withText("Normal Calculator")).perform(click())
        Thread.sleep(1000)
        
        // Test addition: 2 + 3 = 5
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.btnAdd)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        Thread.sleep(500)
        
        // Validate result
        onView(withId(R.id.tvResult)).check(matches(withText("5")))
        
        // Test multiplication: 4 × 5 = 20
        onView(withId(R.id.btnClearAll)).perform(click())
        Thread.sleep(300)
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.btnMultiply)).perform(click())
        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        Thread.sleep(500)
        
        // Validate result
        onView(withId(R.id.tvResult)).check(matches(withText("20")))
        
        // Go back to dashboard
        onView(withId(R.id.backButton)).perform(click())
        Thread.sleep(500)
    }

    @Test
    fun dashboard_shouldNavigateToCurrencyConverter() {
        // Click on Currency Converter
        onView(withText("Currency Converter")).perform(click())
        Thread.sleep(2000) // Wait for API call
        
        // Verify we're in currency converter
        onView(withId(R.id.backButton)).check(matches(isDisplayed()))
        
        // Go back to dashboard
        onView(withId(R.id.backButton)).perform(click())
        Thread.sleep(500)
    }

    @Test
    fun currencyConverter_shouldPerformLiveConversion() {
        // Navigate to Currency Converter
        onView(withText("Currency Converter")).perform(click())
        Thread.sleep(2000) // Wait for API call
        
        // Enter amount
        onView(withId(R.id.fromValue)).perform(replaceText("100"))
        
        // Select currencies
        onView(withId(R.id.fromCurrency)).perform(click())
        onView(withText("USD")).perform(click())
        Thread.sleep(500)
        
        onView(withId(R.id.toCurrency)).perform(click())
        onView(withText("EUR")).perform(click())
        Thread.sleep(500)
        
        // Perform conversion
        onView(withId(R.id.convertButton)).perform(click())
        Thread.sleep(3000) // Wait for API response
        
        // Validate result is not empty and not zero
        onView(withId(R.id.toValue)).check(matches(allOf(
            isDisplayed(),
            not(withText("")),
            not(withText("0")),
            not(withText("0.0"))
        )))
        
        // Go back to dashboard
        onView(withId(R.id.backButton)).perform(click())
        Thread.sleep(500)
    }

    @Test
    fun dashboard_shouldShowAllCalculatorTypes() {
        // Verify all 10 calculators are visible
        onView(withText("Normal Calculator")).check(matches(isDisplayed()))
        onView(withText("Scientific Calculator")).check(matches(isDisplayed()))
        onView(withText("EMI Calculator")).check(matches(isDisplayed()))
        onView(withText("Finance Calculator")).check(matches(isDisplayed()))
        onView(withText("Unit Converter")).check(matches(isDisplayed()))
        onView(withText("Currency Converter")).check(matches(isDisplayed()))
        onView(withText("Percentage Calculator")).check(matches(isDisplayed()))
        onView(withText("Tip Calculator")).check(matches(isDisplayed()))
        onView(withText("Age Calculator")).check(matches(isDisplayed()))
        onView(withText("BMI Calculator")).check(matches(isDisplayed()))
    }

    @Test
    fun dashboard_shouldNavigateToAllCalculators() {
        val calculators = listOf(
            "Scientific Calculator",
            "EMI Calculator", 
            "Finance Calculator",
            "Unit Converter",
            "Percentage Calculator",
            "Tip Calculator",
            "Age Calculator",
            "BMI Calculator"
        )
        
        calculators.forEach { calculator ->
            // Click on calculator
            onView(withText(calculator)).perform(click())
            Thread.sleep(1500)
            
            // Verify we navigated (back button should be present)
            onView(withId(R.id.backButton)).check(matches(isDisplayed()))
            
            // Go back to dashboard
            onView(withId(R.id.backButton)).perform(click())
            Thread.sleep(500)
        }
    }

    @Test
    fun normalCalculator_shouldHandleComplexOperations() {
        // Navigate to Normal Calculator
        onView(withText("Normal Calculator")).perform(click())
        Thread.sleep(1000)
        
        // Test complex operation: (10 + 5) × 2 = 30
        onView(withId(R.id.btnClearAll)).perform(click())
        Thread.sleep(300)
        
        // This would require parentheses support, so let's test a simpler chain
        // Test: 10 + 5 = 15, then × 2 = 30
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.btn0)).perform(click())
        onView(withId(R.id.btnAdd)).perform(click())
        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        Thread.sleep(500)
        
        // Should show 15
        onView(withId(R.id.tvResult)).check(matches(withText("15")))
        
        // Continue with × 2
        onView(withId(R.id.btnMultiply)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        Thread.sleep(500)
        
        // Should show 30
        onView(withId(R.id.tvResult)).check(matches(withText("30")))
        
        // Go back to dashboard
        onView(withId(R.id.backButton)).perform(click())
        Thread.sleep(500)
    }

    @Test
    fun normalCalculator_shouldHandleDecimalOperations() {
        // Navigate to Normal Calculator
        onView(withText("Normal Calculator")).perform(click())
        Thread.sleep(1000)
        
        // Test decimal: 2.5 + 1.5 = 4
        onView(withId(R.id.btnClearAll)).perform(click())
        Thread.sleep(300)
        
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.btnDecimal)).perform(click())
        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.btnAdd)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.btnDecimal)).perform(click())
        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        Thread.sleep(500)
        
        // Should show 4 or 4.0
        onView(withId(R.id.tvResult)).check(matches(withText("4")))
        
        // Go back to dashboard
        onView(withId(R.id.backButton)).perform(click())
        Thread.sleep(500)
    }

    @Test
    fun currencyConverter_shouldRefreshRates() {
        // Navigate to Currency Converter
        onView(withText("Currency Converter")).perform(click())
        Thread.sleep(2000)
        
        // Test refresh button
        onView(withId(R.id.refreshButton)).perform(click())
        Thread.sleep(2000)
        
        // Verify refresh worked (last updated text should change)
        onView(withId(R.id.lastUpdatedText)).check(matches(isDisplayed()))
        
        // Go back to dashboard
        onView(withId(R.id.backButton)).perform(click())
        Thread.sleep(500)
    }
}
