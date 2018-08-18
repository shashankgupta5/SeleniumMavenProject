# SeleniumMavenProject

## Description

Wedriver framework implemented using Java, Maven and TestNG. Object oriented architecture framework made with page factory design pattern and running tests via selenium grid.

## Test Application

Application Under Test is toolsQA (http://toolsqa.com/).

## Project Structure

The project follows the standard Maven structure, all test related configuration are read from default_config.properties placed under root directory. All modification goes there.

All tests go in the src\test\java folder. Tests should be placed under com\SeleniumMavenProject\Tests package and inherit from the TestRunner class.

For every test there should be a corresponding PageObject class under com\SeleniumMavenProject\Pages package, hence each page is an object. These objects are instantiated under corresponding tests, making tests and UiObjects reside under different packages.

## Geting Started

1. Clone repository
2. Copy driver exe from ./src/resources/Driver/ to C:/Windows
3. Launch command prompt and type 'mvn clean test site' and hit Enter
4. Test execution starts
5. Run logs created under ./results/logs/TestLog.txt
6. Run command mvn allure:report to generate the allure reports at ./target/site/allure-maven-plugin/index.html

## What's New

v0.5-Updates in allure reporting, selenium and lombok.

v0.4-Added support for lombok, re-worked on base page objects. If your IDE gives errors, do the following-
1. For IntelliJ, install lombok plugin from plugin manager and enable annotation processing under settings.
2. For Eclipse, insatall using lombok.jar and add the jar to project build path.

v0.3-Added support for running tests on PhantomJS (a headless browser with scriptable WebKit in JavaScript) where WebDriver protocol is implemented using GhostDriver.

v0.2-Added support for running test in distributed environment using selenium grid (creating a hub followed by attaching nodes to it). For more information visit http://www.seleniumhq.org/docs/07_selenium_grid.jsp. Removed logic for creating test execution videos as it would not be useful in distributed environment.

Added a switch in default_config.properties file to toggle remote or local test execution. If value is set to true then test will be executed using selenium grid server, else locally if set to false. 

Steps before executing tests-

1. Go to src/resources/Grid
2. Modify the node.json file as per requirement
3. Launch run_grid.bat for running server
4. From now on testSuite.xml will be controlling test execution for browsers i.e., for running tests make a entry with browser-name and platform. Refer testSuite.xml for more info 
5. Finally, run test via testSuite.xml or mvn command
