# Multi-Calculator App

A comprehensive Android calculator application built with Kotlin, featuring 10 different specialized calculators in one unified dashboard. Showcasing modern Android development practices and diverse mathematical capabilities.

## 🚀 Features

### 🧮 Calculator Types (10 Specialized Calculators)

#### 1. **Normal Calculator**
- **Standard Operations**: Addition, subtraction, multiplication, division
- **Advanced Functions**: Percentage, plus/minus toggle, decimal support
- **Memory Management**: Clear all, delete last digit
- **Clean UI**: Modern Material Design with smooth animations

#### 2. **Scientific Calculator**
- **Trigonometric Functions**: sin, cos, tan, asin, acos, atan
- **Logarithmic Functions**: log (base 10), ln (natural log)
- **Power & Root Functions**: Square, cube, square root, cube root, power (x^y)
- **Special Functions**: Factorial, absolute value, inverse (1/x)
- **Mathematical Constants**: π (pi), e (Euler's number)
- **Memory Functions**: MC, MR, MS, M+, M-

#### 3. **EMI Calculator**
- **Loan Calculations**: Monthly EMI calculation
- **Interest Rate Support**: Annual and monthly interest rates
- **Loan Tenure**: Flexible time period calculations
- **Principal Amount**: Loan amount calculations

#### 4. **Finance Calculator**
- **Financial Calculations**: Present value, future value calculations
- **Interest Rate Calculations**: Compound interest calculations
- **Time Period Calculations**: Investment duration calculations
- **Investment Planning**: Financial planning tools

#### 5. **Unit Converter**
- **Length**: Meter, Kilometer, Centimeter, Millimeter, Inch, Foot, Yard, Mile
- **Weight**: Kilogram, Gram, Pound, Ounce, Ton, Stone
- **Temperature**: Celsius, Fahrenheit, Kelvin
- **Area**: Square Meter, Square Kilometer, Square Foot, Square Inch, Acre, Hectare
- **Volume**: Liter, Milliliter, Gallon, Quart, Pint, Cup, Fluid Ounce
- **Time**: Second, Minute, Hour, Day, Week, Month, Year

#### 6. **Currency Converter**
- **Live Exchange Rates**: Real-time currency conversion using ExchangeRate-API
- **Multiple Currencies**: Support for 20+ major world currencies
- **Auto-refresh**: Automatic rate updates with manual refresh option
- **Offline Support**: Cached rates for offline use with fallback rates
- **Progress Indicators**: Loading states and error handling

#### 7. **Percentage Calculator**
- **Percentage of**: Calculate percentage of a number
- **Percentage Change**: Calculate percentage increase/decrease
- **Percentage Increase**: Calculate increase percentage
- **Percentage Decrease**: Calculate decrease percentage

#### 8. **Tip Calculator**
- **Tip Calculations**: Calculate tips based on percentage
- **Bill Splitting**: Split bills among multiple people
- **Custom Tip Rates**: Set custom tip percentages
- **Total Amount**: Calculate total including tip

#### 9. **Age Calculator**
- **Age Calculation**: Calculate exact age in years, months, days
- **Date Difference**: Calculate time between dates
- **Leap Year Support**: Accurate leap year calculations
- **Multiple Formats**: Various date format support

#### 10. **BMI Calculator**
- **BMI Calculation**: Body Mass Index calculation
- **Height/Weight Input**: Metric and imperial units
- **BMI Categories**: Underweight, Normal, Overweight, Obese
- **Health Recommendations**: BMI-based health insights

### 🎨 UI/UX Features
- **Dashboard Interface**: Unified grid-based dashboard for all calculators
- **Modern Design**: Material Design principles throughout
- **Responsive Layout**: Adapts to different screen sizes
- **Smooth Animations**: Button press feedback and screen transitions
- **Intuitive Navigation**: Easy switching between calculator types
- **Consistent Theming**: Unified visual experience across all calculators

## 🛠 Technical Implementation

### Architecture
- **Language**: Kotlin
- **UI Framework**: Android Views with XML layouts
- **Architecture Pattern**: Modular MVC with Base Classes
- **Code Organization**: Structured packages for maintainability
- **Animations**: Property Animators and View Animations

### 📁 Project Structure
```
app/src/main/java/com/example/calculator/
├── DashboardActivity.kt              # Main dashboard with grid of calculators
├── MainActivity.kt                   # Normal calculator implementation
├── ScientificCalculatorActivity.kt   # Scientific calculator
├── EMICalculatorActivity.kt          # EMI calculation
├── FinanceCalculatorActivity.kt      # Financial calculations
├── UnitConverterActivity.kt          # Unit conversion
├── CurrencyConverterActivity.kt      # Currency conversion
├── PercentageCalculatorActivity.kt   # Percentage calculations
├── TipCalculatorActivity.kt          # Tip calculations
├── AgeCalculatorActivity.kt          # Age calculations
├── BMICalculatorActivity.kt          # BMI calculations
├── CalculatorGridAdapter.kt          # Dashboard grid adapter
├── base/                            # Base classes for code reuse
│   ├── BaseCalculatorActivity.kt     # Common calculator functionality
│   └── BaseInputCalculatorActivity.kt # Input-based calculator base
├── calculators/                     # Calculator logic classes
│   ├── EMICalculator.kt
│   ├── BMICalculator.kt
│   ├── PercentageCalculator.kt
│   ├── TipCalculator.kt
│   └── AgeCalculator.kt
├── managers/                        # Manager classes
│   ├── ThemeManager.kt              # Theme management
│   ├── HistoryManager.kt            # Calculation history
│   └── MemoryManager.kt             # Memory operations
├── utils/                          # Utility classes
│   ├── CalculatorUtils.kt           # Common calculations
│   └── ValidationUtils.kt           # Input validation
├── constants/                      # App constants
│   └── CalculatorConstants.kt       # All app constants
└── activities/                     # Refactored activities
    ├── RefactoredMainActivity.kt
    └── RefactoredEMICalculatorActivity.kt
```

### Key Components
- **DashboardActivity**: Main entry point with calculator grid
- **Base Classes**: Reusable functionality across all calculators
- **Calculator Classes**: Dedicated logic for each calculator type
- **Manager Classes**: Handle themes, history, and memory operations
- **Utility Classes**: Common mathematical operations and validation
- **Constants**: Centralized configuration and string resources

### Mathematical Functions
- **Precision**: High-precision decimal calculations
- **Error Handling**: Division by zero, invalid inputs, edge cases
- **Scientific Notation**: Automatic formatting for large/small numbers
- **Memory Management**: Persistent memory storage across sessions
- **Input Validation**: Comprehensive validation for all calculator types

## 📱 App Interface

### Dashboard
- **Grid Layout**: 10 calculator types in organized grid
- **Visual Icons**: Each calculator has distinct iconography
- **Quick Access**: One-tap access to any calculator
- **Modern Design**: Clean, intuitive interface

### Individual Calculators
- **Specialized Interfaces**: Each calculator optimized for its purpose
- **Consistent Design**: Unified design language across all calculators
- **Input Validation**: Real-time validation and error handling
- **Results Display**: Clear, formatted result presentation

### Navigation
- **Back Navigation**: Easy return to dashboard from any calculator
- **Activity Management**: Proper activity lifecycle management
- **Memory Persistence**: Maintains state across navigation

## 🎯 Target Audience

This multi-calculator app is designed for:
- **Students**: Scientific calculations, unit conversions, and academic needs
- **Professionals**: Financial calculations, EMI planning, and business metrics
- **General Users**: Daily calculations, tip calculations, and personal finance
- **Health-Conscious Users**: BMI calculations and health tracking
- **Travelers**: Currency conversion and international calculations
- **Developers**: Comprehensive showcase of Android development skills and architecture

## 🔧 Installation

1. Clone the repository
   ```bash
   git clone <repository-url>
   cd CalculatorApp
   ```
2. Open in Android Studio
3. Build and run on Android device/emulator
4. Minimum SDK: API 24 (Android 7.0)

## 📋 Requirements

- **Android**: 7.0 (API 24) or higher
- **Kotlin**: Version 1.8+ support
- **Gradle**: Android Gradle Plugin 8.0+
- **Dependencies**: Material Design components, AndroidX libraries
- **Memory**: 50MB storage space
- **Permissions**: Internet (for currency converter)

## 🚀 Future Enhancements

### 📊 Advanced Features
- [ ] **Graphing Calculator**: Plot functions and equations
- [ ] **Expression Parser**: Complex mathematical expression evaluation
- [ ] **Custom Functions**: User-defined mathematical functions
- [ ] **Calculation History**: Persistent history across all calculators
- [ ] **Export Features**: Export calculations to PDF/Excel
- [ ] **Voice Input**: Voice-to-calculation functionality

### 🌐 Enhanced Functionality
- [ ] **Real-time Currency**: Live exchange rate updates
- [ ] **Offline Mode**: Core calculators work without internet
- [ ] **Dark Theme**: Comprehensive dark mode support
- [ ] **Multi-language**: Internationalization support
- [ ] **Widgets**: Home screen calculator widgets
- [ ] **Backup/Sync**: Cloud backup of calculations and preferences

### 🎨 UI/UX Improvements
- [ ] **Customizable Dashboard**: Reorder calculator grid
- [ ] **Favorites**: Pin frequently used calculators
- [ ] **Recent Calculations**: Quick access to recent results
- [ ] **Themes**: Additional color themes and customization
- [ ] **Accessibility**: Enhanced accessibility features
- [ ] **Tablet Support**: Optimized layouts for tablets

## 👨‍💻 Developer

Built by an experienced developer showcasing:
- **Modern Android Development**: Latest Android development practices and patterns
- **Clean Architecture**: Modular, maintainable, and scalable code structure
- **Advanced UI/UX Design**: Material Design principles and responsive layouts
- **Comprehensive Feature Set**: 10 specialized calculators in one unified app
- **Professional Code Quality**: Well-organized packages, base classes, and utilities
- **Best Practices**: Proper separation of concerns, error handling, and validation

## 🏗️ Code Architecture Highlights

- **Modular Design**: Separated calculator logic from UI components
- **Base Classes**: Reusable functionality across all calculator types
- **Manager Classes**: Centralized handling of themes, history, and memory
- **Utility Classes**: Common mathematical operations and validation
- **Constants Management**: Centralized configuration and resources
- **Activity Lifecycle**: Proper Android activity management

## 📊 Project Statistics

- **10 Calculator Types**: Comprehensive calculation coverage
- **Modular Structure**: 6 main package categories
- **Base Classes**: 2 reusable base activity classes
- **Manager Classes**: 3 specialized manager classes
- **Utility Classes**: 2 utility classes for common operations
- **Activities**: 10+ specialized activity implementations

## 📄 License

This project is open source and available under the MIT License.

---

**Note**: This multi-calculator app demonstrates advanced Android development skills, clean architecture principles, and comprehensive feature implementation. It serves as an excellent showcase project for professional portfolios and demonstrates expertise in modern Android development practices.