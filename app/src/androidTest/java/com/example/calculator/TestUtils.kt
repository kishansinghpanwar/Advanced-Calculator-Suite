package com.example.calculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import org.hamcrest.Matchers
import java.util.concurrent.TimeUnit

/**
 * Utility functions for testing
 */
object TestUtils {

    /**
     * Wait for a view to be displayed
     */
    fun waitForView(viewInteraction: ViewInteraction, timeoutSeconds: Long = 10): ViewInteraction {
        return viewInteraction.check(ViewAssertions.matches(
            ViewMatchers.isDisplayed()
        ))
    }

    /**
     * Perform a click and wait for the action to complete
     */
    fun clickAndWait(viewInteraction: ViewInteraction, waitMillis: Long = 500): ViewInteraction {
        viewInteraction.perform(ViewActions.click())
        Thread.sleep(waitMillis)
        return viewInteraction
    }

    /**
     * Type text and close keyboard
     */
    fun typeTextAndCloseKeyboard(viewInteraction: ViewInteraction, text: String): ViewInteraction {
        return viewInteraction.perform(
            ViewActions.typeText(text),
            ViewActions.closeSoftKeyboard()
        )
    }

    /**
     * Clear text field and type new text
     */
    fun clearAndTypeText(viewInteraction: ViewInteraction, text: String): ViewInteraction {
        return viewInteraction.perform(
            ViewActions.clearText(),
            ViewActions.typeText(text),
            ViewActions.closeSoftKeyboard()
        )
    }

    /**
     * Wait for network operations to complete
     */
    fun waitForNetworkOperations(timeoutSeconds: Long = 30) {
        try {
            Thread.sleep(timeoutSeconds * 1000)
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }

    /**
     * Wait for a specific condition to be true
     */
    fun waitForCondition(condition: () -> Boolean, timeoutSeconds: Long = 10): Boolean {
        val startTime = System.currentTimeMillis()
        val timeoutMillis = timeoutSeconds * 1000
        
        while (System.currentTimeMillis() - startTime < timeoutMillis) {
            if (condition()) {
                return true
            }
            try {
                Thread.sleep(100)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                return false
            }
        }
        return false
    }

    /**
     * Verify that a view contains specific text
     */
    fun verifyTextContains(viewInteraction: ViewInteraction, expectedText: String) {
        viewInteraction.check(ViewAssertions.matches(
            ViewMatchers.withText(Matchers.containsString(expectedText))
        ))
    }

    /**
     * Verify that a view is enabled
     */
    fun verifyEnabled(viewInteraction: ViewInteraction) {
        viewInteraction.check(ViewAssertions.matches(
            ViewMatchers.isEnabled()
        ))
    }

    /**
     * Verify that a view is disabled
     */
    fun verifyDisabled(viewInteraction: ViewInteraction) {
        viewInteraction.check(ViewAssertions.matches(
            ViewMatchers.isNotEnabled()
        ))
    }

    /**
     * Perform a series of calculator button clicks
     */
    fun performCalculatorSequence(vararg buttonIds: Int) {
        buttonIds.forEach { buttonId ->
            Espresso.onView(ViewMatchers.withId(buttonId))
                .perform(ViewActions.click())
            Thread.sleep(100) // Small delay between clicks
        }
    }

    /**
     * Verify calculator display shows expected result
     */
    fun verifyCalculatorResult(expectedResult: String) {
        // Note: This would need the actual display ID from your layout
        // For now, we'll use a generic text matcher
        Espresso.onView(ViewMatchers.withText(expectedResult))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /**
     * Select item from spinner
     */
    fun selectSpinnerItem(spinnerId: Int, itemText: String) {
        Espresso.onView(ViewMatchers.withId(spinnerId))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText(itemText))
            .perform(ViewActions.click())
    }

    /**
     * Wait for activity to be idle
     */
    fun waitForIdle() {
        Espresso.onIdle()
    }

    /**
     * Take screenshot (useful for debugging)
     */
    fun takeScreenshot(testName: String) {
        // This would require additional setup for screenshot functionality
        // For now, just log the test name
        println("Screenshot would be taken for test: $testName")
    }
}
