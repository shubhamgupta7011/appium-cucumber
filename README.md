# Appium-Cucumber
Sample Appium test automation using Cucumber-JVM

## Pre-requisites
* Appium 1.7 and more
    * Appium server or appium desktop
* Java 8 and more
* Maven
* IDE with Cucumber Plugin installed( Intellij )
* Android Studio
* Xcode
* Android/iphone or Emulator/simulator
* java
* nodeJS
* Vysor for devise projection
* More info on [Appium Setup instructions](http://appium.io/slate/en/master/?ruby#running-appium-on-mac-os-x)

## Project Structure

* Configuration package - Hooks and API Processor
* Runners.java - Cucumber Runner Test
* Screens - Page Classes with Page Actions defined
* Step Definitions - Screen and its Steps Defs.
* Features - cucumber features
* Appium.properity file - contains capabilities

## Dependencies
* Appium Java Client
* Cucumber JVM
* Cucumber Java
* Rest Assured - API processing

## Required Tools

$ `mvn clean install`
$ `mvn clean verify`