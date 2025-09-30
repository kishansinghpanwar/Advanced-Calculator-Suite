package com.example.calculator.calculators

import org.junit.Test
import org.junit.Assert.*
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Simple unit tests for calculator logic
 */
class SimpleCalculatorTest {

    @Test
    fun `addition should work correctly`() {
        // Given
        val a = 10.0
        val b = 5.0
        
        // When
        val result = add(a, b)
        
        // Then
        assertEquals(15.0, result, 0.01)
    }

    @Test
    fun `subtraction should work correctly`() {
        // Given
        val a = 10.0
        val b = 5.0
        
        // When
        val result = subtract(a, b)
        
        // Then
        assertEquals(5.0, result, 0.01)
    }

    @Test
    fun `multiplication should work correctly`() {
        // Given
        val a = 10.0
        val b = 5.0
        
        // When
        val result = multiply(a, b)
        
        // Then
        assertEquals(50.0, result, 0.01)
    }

    @Test
    fun `division should work correctly`() {
        // Given
        val a = 10.0
        val b = 5.0
        
        // When
        val result = divide(a, b)
        
        // Then
        assertEquals(2.0, result, 0.01)
    }

    @Test
    fun `division by zero should return infinity`() {
        // Given
        val a = 10.0
        val b = 0.0
        
        // When
        val result = divide(a, b)
        
        // Then
        assertTrue(result.isInfinite())
    }

    @Test
    fun `percentage calculation should work correctly`() {
        // Given
        val value = 100.0
        val percentage = 20.0
        
        // When
        val result = calculatePercentage(value, percentage)
        
        // Then
        assertEquals(20.0, result, 0.01)
    }

    @Test
    fun `square root calculation should work correctly`() {
        // Given
        val value = 16.0
        
        // When
        val result = squareRoot(value)
        
        // Then
        assertEquals(4.0, result, 0.01)
    }

    @Test
    fun `power calculation should work correctly`() {
        // Given
        val base = 2.0
        val exponent = 3.0
        
        // When
        val result = power(base, exponent)
        
        // Then
        assertEquals(8.0, result, 0.01)
    }

    // Simple calculator functions for testing
    private fun add(a: Double, b: Double): Double = a + b
    private fun subtract(a: Double, b: Double): Double = a - b
    private fun multiply(a: Double, b: Double): Double = a * b
    private fun divide(a: Double, b: Double): Double = a / b
    private fun calculatePercentage(value: Double, percentage: Double): Double = value * percentage / 100
    private fun squareRoot(value: Double): Double = sqrt(value)
    private fun power(base: Double, exponent: Double): Double = base.pow(exponent)
}
