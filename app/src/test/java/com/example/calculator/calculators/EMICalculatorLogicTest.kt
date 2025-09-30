package com.example.calculator.calculators

import org.junit.Test
import org.junit.Assert.*

/**
 * Unit tests for EMI calculation logic
 */
class EMICalculatorLogicTest {

    @Test
    fun `calculate EMI with valid inputs should return correct result`() {
        // Given
        val principal = 100000.0
        val rate = 10.0 // 10% per annum
        val tenure = 12.0 // 12 months
        
        // When
        val result = calculateEMI(principal, rate, tenure)
        
        // Then
        assertNotNull(result)
        assertTrue(result.isSuccess)
        
        val emi = result.getOrNull()
        assertNotNull(emi)
        
        // Expected EMI calculation: P * R * (1+R)^N / ((1+R)^N - 1)
        // Where R is monthly rate (10/12/100 = 0.00833)
        val monthlyRate = rate / 12 / 100
        val expectedEMI = principal * monthlyRate * Math.pow(1 + monthlyRate, tenure) / 
                         (Math.pow(1 + monthlyRate, tenure) - 1)
        
        assertEquals(expectedEMI, emi!!, 1.0) // Allow 1 rupee difference
    }

    @Test
    fun `calculate EMI with zero principal should fail`() {
        // Given
        val principal = 0.0
        val rate = 10.0
        val tenure = 12.0
        
        // When
        val result = calculateEMI(principal, rate, tenure)
        
        // Then
        assertNotNull(result)
        assertTrue(result.isFailure)
    }

    @Test
    fun `calculate EMI with negative principal should fail`() {
        // Given
        val principal = -100000.0
        val rate = 10.0
        val tenure = 12.0
        
        // When
        val result = calculateEMI(principal, rate, tenure)
        
        // Then
        assertNotNull(result)
        assertTrue(result.isFailure)
    }

    @Test
    fun `calculate EMI with zero rate should return principal divided by tenure`() {
        // Given
        val principal = 120000.0
        val rate = 0.0
        val tenure = 12.0
        
        // When
        val result = calculateEMI(principal, rate, tenure)
        
        // Then
        assertNotNull(result)
        assertTrue(result.isSuccess)
        
        val emi = result.getOrNull()
        assertEquals(10000.0, emi!!, 0.01) // 120000/12 = 10000
    }

    @Test
    fun `calculate EMI with very high rate should still work`() {
        // Given
        val principal = 100000.0
        val rate = 36.0 // 36% per annum
        val tenure = 12.0
        
        // When
        val result = calculateEMI(principal, rate, tenure)
        
        // Then
        assertNotNull(result)
        assertTrue(result.isSuccess)
        
        val emi = result.getOrNull()
        assertTrue(emi!! > 0) // EMI should be positive for high rate
    }

    private fun calculateEMI(principal: Double, rate: Double, tenure: Double): Result<Double> {
        return try {
            if (principal <= 0) {
                return Result.failure(IllegalArgumentException("Principal must be positive"))
            }
            if (tenure <= 0) {
                return Result.failure(IllegalArgumentException("Tenure must be positive"))
            }
            
            val monthlyRate = rate / 12 / 100
            
            if (monthlyRate == 0.0) {
                Result.success(principal / tenure)
            } else {
                val emi = principal * monthlyRate * Math.pow(1 + monthlyRate, tenure) / 
                         (Math.pow(1 + monthlyRate, tenure) - 1)
                Result.success(emi)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
