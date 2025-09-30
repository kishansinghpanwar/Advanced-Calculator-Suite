#!/bin/bash

# 🚀 UI Automation Test Runner for Multi-Calculator App
# This script runs automated UI tests on Android devices/emulators

echo "🧪 Starting UI Automation Tests for Multi-Calculator App"
echo "========================================================"

# Fix JAVA_HOME if it's pointing to invalid directory
if [ ! -d "$JAVA_HOME" ]; then
    echo "🔧 Fixing JAVA_HOME..."
    if [ -d "/usr/lib/jvm/java-21-openjdk-amd64" ]; then
        export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
        echo "✅ JAVA_HOME set to: $JAVA_HOME"
    elif [ -d "/usr/lib/jvm/default-java" ]; then
        export JAVA_HOME=/usr/lib/jvm/default-java
        echo "✅ JAVA_HOME set to: $JAVA_HOME"
    else
        echo "❌ Could not find valid Java installation"
        exit 1
    fi
fi

# Check if Android SDK is available
if ! command -v adb &> /dev/null; then
    echo "❌ Android SDK not found. Please install Android SDK and add to PATH"
    exit 1
fi

# Check if device/emulator is connected
echo "📱 Checking for connected devices..."
adb devices

DEVICE_COUNT=$(adb devices | grep -v "List of devices" | grep -v "^$" | wc -l)
if [ $DEVICE_COUNT -eq 0 ]; then
    echo "❌ No Android devices or emulators found!"
    echo "Please connect a device or start an emulator"
    exit 1
fi

echo "✅ Found $DEVICE_COUNT device(s)"

# Clean and build the project
echo "🔨 Building project..."
./gradlew clean assembleDebug assembleDebugAndroidTest

if [ $? -ne 0 ]; then
    echo "❌ Build failed!"
    exit 1
fi

echo "✅ Build successful"

# Install the app on device
echo "📦 Installing app on device..."
adb install -r app/build/outputs/apk/debug/app-debug.apk

if [ $? -ne 0 ]; then
    echo "❌ App installation failed!"
    exit 1
fi

echo "✅ App installed successfully"

# Run unit tests first
echo "🧪 Running Unit Tests with Coverage..."
./gradlew testDebugUnitTest jacocoTestReport

if [ $? -ne 0 ]; then
    echo "❌ Unit tests failed!"
    exit 1
fi

echo "✅ Unit tests passed"
echo "📊 Coverage report generated at: app/build/reports/jacoco/jacocoTestReport/html/index.html"

# Run UI automation tests
echo "🎭 Running UI Automation Tests..."
echo "This will test the actual UI interactions on the device"

# Disable animations for stable testing
echo "🔧 Disabling animations for stable testing..."
adb shell settings put global window_animation_scale 0
adb shell settings put global transition_animation_scale 0
adb shell settings put global animator_duration_scale 0

# Run all UI tests
./gradlew connectedDebugAndroidTest

if [ $? -ne 0 ]; then
    echo "❌ UI tests failed!"
    echo "📊 Test results available in: app/build/reports/androidTests/"
    exit 1
fi

echo "✅ All UI automation tests passed!"

# Generate test report
echo "📊 Generating test report..."
./gradlew jacocoTestReport

echo "🎉 UI Automation Testing Complete!"
echo "=================================="
echo "📊 Test reports available in:"
echo "   - Unit tests: app/build/reports/tests/testDebugUnitTest/"
echo "   - UI tests: app/build/reports/androidTests/"
echo "   - Coverage: app/build/reports/jacoco/testDebugUnitTest/html/"

# Optional: Take screenshots of test results
echo "📸 Taking final screenshots..."
adb shell screencap -p /sdcard/test_complete.png
adb pull /sdcard/test_complete.png ./test_screenshots/
echo "📸 Screenshots saved to ./test_screenshots/"

echo "🚀 UI Automation Testing Complete!"
