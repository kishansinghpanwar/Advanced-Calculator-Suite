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
 * Normal Calculator UI tests with actual result validation
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class NormalCalculatorTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun disableAnimations() {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.executeShellCommand("settings put global window_animation_scale 0")
        device.executeShellCommand("settings put global transition_animation_scale 0")
        device.executeShellCommand("settings put global animator_duration_scale 0")
    }

    @Test
    fun normalCalculator_shouldLaunchSuccessfully() {
        Thread.sleep(1000)
        // Verify display is visible
        onView(withId(R.id.tvResult)).check(matches(isDisplayed()))
    }

    @Test
    fun normalCalculator_shouldHaveBasicButtons() {
        // Verify number buttons using actual IDs
        onView(withId(R.id.btn0)).check(matches(isDisplayed()))
        onView(withId(R.id.btn1)).check(matches(isDisplayed()))
        onView(withId(R.id.btn2)).check(matches(isDisplayed()))
        onView(withId(R.id.btn3)).check(matches(isDisplayed()))
        onView(withId(R.id.btn4)).check(matches(isDisplayed()))
        onView(withId(R.id.btn5)).check(matches(isDisplayed()))
        onView(withId(R.id.btn6)).check(matches(isDisplayed()))
        onView(withId(R.id.btn7)).check(matches(isDisplayed()))
        onView(withId(R.id.btn8)).check(matches(isDisplayed()))
        onView(withId(R.id.btn9)).check(matches(isDisplayed()))
    }

    @Test
    fun normalCalculator_shouldHaveOperationButtons() {
        // Verify operation buttons using actual IDs
        onView(withId(R.id.btnAdd)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSubtract)).check(matches(isDisplayed()))
        onView(withId(R.id.btnMultiply)).check(matches(isDisplayed()))
        onView(withId(R.id.btnDivide)).check(matches(isDisplayed()))
        onView(withId(R.id.btnEquals)).check(matches(isDisplayed()))
    }

    @Test
    fun normalCalculator_shouldHaveUtilityButtons() {
        // Verify utility buttons using actual IDs
        onView(withId(R.id.btnClearAll)).check(matches(isDisplayed())) // Clear
        onView(withId(R.id.btnDelete)).check(matches(isDisplayed())) // Delete
        onView(withId(R.id.btnDecimal)).check(matches(isDisplayed())) // Decimal
    }

    @Test
    fun normalCalculator_shouldPerformAdditionWithResultValidation() {
        // Clear first
        onView(withId(R.id.btnClearAll)).perform(click())
        Thread.sleep(300)
        
        // Test: 2 + 3 = 5
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.btnAdd)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        Thread.sleep(500)
        
        // Validate result
        onView(withId(R.id.tvResult)).check(matches(withText("5")))
    }

    @Test
    fun normalCalculator_shouldPerformSubtractionWithResultValidation() {
        // Clear first
        onView(withId(R.id.btnClearAll)).perform(click())
        Thread.sleep(300)
        
        // Test: 10 - 3 = 7
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.btn0)).perform(click())
        onView(withId(R.id.btnSubtract)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        Thread.sleep(500)
        
        // Validate result
        onView(withId(R.id.tvResult)).check(matches(withText("7")))
    }

    @Test
    fun normalCalculator_shouldPerformMultiplicationWithResultValidation() {
        // Clear first
        onView(withId(R.id.btnClearAll)).perform(click())
        Thread.sleep(300)
        
        // Test: 4 × 5 = 20
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.btnMultiply)).perform(click())
        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        Thread.sleep(500)
        
        // Validate result
        onView(withId(R.id.tvResult)).check(matches(withText("20")))
    }

    @Test
    fun normalCalculator_shouldPerformDivisionWithResultValidation() {
        // Clear first
        onView(withId(R.id.btnClearAll)).perform(click())
        Thread.sleep(300)
        
        // Test: 15 ÷ 3 = 5
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.btnDivide)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        Thread.sleep(500)
        
        // Validate result
        onView(withId(R.id.tvResult)).check(matches(withText("5")))
    }

    @Test
    fun normalCalculator_shouldHandleDecimalOperations() {
        // Clear first
        onView(withId(R.id.btnClearAll)).perform(click())
        Thread.sleep(300)
        
        // Test: 2.5 + 1.5 = 4
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.btnDecimal)).perform(click())
        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.btnAdd)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.btnDecimal)).perform(click())
        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        Thread.sleep(500)
        
        // Validate result (should be 4 or 4.0)
        onView(withId(R.id.tvResult)).check(matches(withText("4")))
    }

    @Test
    fun normalCalculator_shouldHandleClearFunctionality() {
        // Enter some numbers
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        Thread.sleep(300)
        
        // Clear
        onView(withId(R.id.btnClearAll)).perform(click())
        Thread.sleep(300)
        
        // Validate display is cleared (should show 0)
        onView(withId(R.id.tvResult)).check(matches(withText("0")))
    }

    @Test
    fun normalCalculator_shouldHandleDeleteFunctionality() {
        // Clear first
        onView(withId(R.id.btnClearAll)).perform(click())
        Thread.sleep(300)
        
        // Enter 123
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        Thread.sleep(300)
        
        // Delete last digit
        onView(withId(R.id.btnDelete)).perform(click())
        Thread.sleep(300)
        
        // Should show 12
        onView(withId(R.id.tvResult)).check(matches(withText("12")))
    }
}
