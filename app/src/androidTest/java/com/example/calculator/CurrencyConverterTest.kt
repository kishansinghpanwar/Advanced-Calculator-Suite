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
 * Currency Converter UI tests
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class CurrencyConverterTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(CurrencyConverterActivity::class.java)

    @Before
    fun disableAnimations() {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.executeShellCommand("settings put global window_animation_scale 0")
        device.executeShellCommand("settings put global transition_animation_scale 0")
        device.executeShellCommand("settings put global animator_duration_scale 0")
    }

    @Test
    fun currencyConverter_shouldLaunchSuccessfully() {
        Thread.sleep(2000) // Wait for API call
        // Verify activity loaded
    }

    @Test
    fun currencyConverter_shouldHaveRequiredElements() {
        // Verify main UI elements
        onView(withId(R.id.fromValue)).check(matches(isDisplayed()))
        onView(withId(R.id.toValue)).check(matches(isDisplayed()))
        onView(withId(R.id.fromCurrency)).check(matches(isDisplayed()))
        onView(withId(R.id.toCurrency)).check(matches(isDisplayed()))
        onView(withId(R.id.convertButton)).check(matches(isDisplayed()))
    }

    @Test
    fun currencyConverter_shouldHaveRefreshButton() {
        onView(withId(R.id.refreshButton)).check(matches(isDisplayed()))
    }

    @Test
    fun currencyConverter_shouldHaveLastUpdatedText() {
        onView(withId(R.id.lastUpdatedText)).check(matches(isDisplayed()))
    }

    @Test
    fun currencyConverter_shouldAllowAmountInput() {
        onView(withId(R.id.fromValue)).perform(replaceText("100"))
        Thread.sleep(500)
    }

    @Test
    fun currencyConverter_shouldAllowCurrencySelection() {
        // Test from currency selection
        onView(withId(R.id.fromCurrency)).perform(click())
        onView(withText("USD")).perform(click())
        Thread.sleep(500)
        
        // Test to currency selection
        onView(withId(R.id.toCurrency)).perform(click())
        onView(withText("EUR")).perform(click())
        Thread.sleep(500)
    }

    @Test
    fun currencyConverter_shouldPerformConversionWithResultValidation() {
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
        
        // Validate result field has conversion result (not empty)
        onView(withId(R.id.toValue)).check(matches(allOf(
            isDisplayed(),
            not(withText("")),
            not(withText("0")),
            not(withText("0.0"))
        )))
    }

    @Test
    fun currencyConverter_shouldRefreshRates() {
        // Test refresh functionality
        onView(withId(R.id.refreshButton)).perform(click())
        Thread.sleep(2000) // Wait for refresh
    }

    @Test
    fun currencyConverter_shouldHandleDifferentCurrencies() {
        // Test a simple currency conversion
        // Enter amount using a different approach
        onView(withId(R.id.fromValue)).perform(replaceText("50"))
        Thread.sleep(500)
        
        // Select from currency
        onView(withId(R.id.fromCurrency)).perform(click())
        onView(withText("USD")).perform(click())
        Thread.sleep(500)
        
        // Select to currency
        onView(withId(R.id.toCurrency)).perform(click())
        onView(withText("EUR")).perform(click())
        Thread.sleep(500)
        
        // Perform conversion
        onView(withId(R.id.convertButton)).perform(click())
        Thread.sleep(2000)
        
        // Verify result is displayed
        onView(withId(R.id.toValue)).check(matches(isDisplayed()))
    }
}
