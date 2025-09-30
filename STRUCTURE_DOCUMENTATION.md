# Calculator App - Structured Code Documentation

## Overview
This document outlines the restructured codebase of the Calculator App, which has been organized for better maintainability, scalability, and code reusability.

## Project Structure

### 1. Base Classes (`com.example.calculator.base`)
- **`BaseCalculatorActivity`**: Common functionality for all calculator activities
- **`BaseInputCalculatorActivity`**: Extended base class for input-based calculators

### 2. Utils (`com.example.calculator.utils`)
- **`CalculatorUtils`**: Common mathematical operations and formatting
- **`ValidationUtils`**: Input validation utilities

### 3. Constants (`com.example.calculator.constants`)
- **`CalculatorConstants`**: All app constants and configuration values

### 4. Managers (`com.example.calculator.managers`)
- **`ThemeManager`**: Theme management and persistence
- **`HistoryManager`**: Calculation history management
- **`MemoryManager`**: Calculator memory operations

### 5. Calculators (`com.example.calculator.calculators`)
- **`EMICalculator`**: EMI calculation logic
- **`BMICalculator`**: BMI calculation logic
- **`PercentageCalculator`**: Percentage calculation logic
- **`TipCalculator`**: Tip calculation logic
- **`AgeCalculator`**: Age calculation logic

### 6. Activities (`com.example.calculator.activities`)
- **`RefactoredMainActivity`**: Normal calculator using new structure
- **`RefactoredEMICalculatorActivity`**: EMI calculator using new structure

### 7. Config (`com.example.calculator.config`)
- **`AppConfig`**: Application configuration and settings

## Key Benefits

### 1. **Separation of Concerns**
- Business logic separated from UI logic
- Calculator logic isolated in dedicated classes
- Manager classes handle specific functionalities

### 2. **Code Reusability**
- Base classes provide common functionality
- Utility classes can be used across different calculators
- Manager classes handle shared operations

### 3. **Maintainability**
- Clear structure makes it easy to locate and modify code
- Changes to one calculator don't affect others
- Centralized configuration and constants

### 4. **Testability**
- Calculator logic can be unit tested independently
- Manager classes can be mocked for testing
- Clear interfaces make testing easier

### 5. **Scalability**
- Easy to add new calculators
- Easy to add new features
- Easy to modify existing functionality

## Usage Examples

### Adding a New Calculator

1. **Create Calculator Class**:
```kotlin
object NewCalculator {
    fun calculate(input: Double): CalculationResult {
        // Calculation logic
    }
}
```

2. **Create Activity**:
```kotlin
class NewCalculatorActivity : BaseInputCalculatorActivity() {
    override fun getLayoutResource(): Int = R.layout.activity_new_calculator
    
    override fun onCalculateClick() {
        // Validation and calculation logic
    }
}
```

3. **Add to Dashboard**:
```kotlin
// Add to calculator list in DashboardActivity
```

### Using Managers

```kotlin
// Theme Management
val themeManager = ThemeManager.getInstance(this)
themeManager.applyLightTheme()

// History Management
val historyManager = HistoryManager.getInstance(this)
historyManager.saveCalculation("2 + 2", "4")

// Memory Management
val memoryManager = MemoryManager.getInstance(this)
memoryManager.setMemory(100.0)
```

### Using Utilities

```kotlin
// Validation
val isValid = ValidationUtils.isPositiveNumber(editText)

// Formatting
val formatted = CalculatorUtils.formatCurrency(1234.56)

// Calculations
val percentage = CalculatorUtils.calculatePercentage(100.0, 10.0)
```

## Migration Guide

### From Old Structure to New Structure

1. **Replace Activity Inheritance**:
   - Old: `AppCompatActivity`
   - New: `BaseCalculatorActivity` or `BaseInputCalculatorActivity`

2. **Use Managers Instead of Direct Operations**:
   - Old: Direct SharedPreferences access
   - New: Use appropriate Manager classes

3. **Extract Calculator Logic**:
   - Old: Logic in Activity
   - New: Separate Calculator classes

4. **Use Utilities for Common Operations**:
   - Old: Duplicate code across activities
   - New: Use utility classes

## Best Practices

### 1. **Naming Conventions**
- Classes: PascalCase (e.g., `EMICalculator`)
- Functions: camelCase (e.g., `calculateEMI`)
- Constants: UPPER_SNAKE_CASE (e.g., `CALCULATOR_EMI`)

### 2. **Error Handling**
- Use Result classes for calculations
- Provide meaningful error messages
- Handle edge cases gracefully

### 3. **Resource Management**
- Use organized string resources
- Follow consistent naming patterns
- Group related resources together

### 4. **Code Organization**
- Keep related functionality together
- Use appropriate access modifiers
- Document public APIs

## Future Enhancements

### 1. **Dependency Injection**
- Use Dagger/Hilt for dependency injection
- Improve testability and maintainability

### 2. **Repository Pattern**
- Abstract data access layer
- Support different data sources

### 3. **MVVM Architecture**
- Implement ViewModel classes
- Use LiveData for reactive programming

### 4. **Unit Testing**
- Add comprehensive unit tests
- Test calculator logic independently

### 5. **Integration Testing**
- Test complete user flows
- Ensure UI works correctly

## Conclusion

The restructured codebase provides a solid foundation for the Calculator App with improved maintainability, scalability, and code organization. The modular approach makes it easy to add new features and maintain existing functionality while following Android development best practices.

