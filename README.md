# SeleniumMavenProject

##Description

Wedriver framework implemented using Java, Maven and TestNG. Object oriented architecture framework made with page factory design pattern.

##Test Application

Application Under Test is toolsQA (http://toolsqa.com/).

##Project Structure

The project follows the standard Maven structure, all test related configuration are read from default_config.properties placed under root directory. All modification goes there.

All tests go in the src\test\java folder. Tests should be placed under com\SeleniumMavenProject\Tests package and inherit from the TestRunner class.

For every test there should be a corresponding PageObject class under com\SeleniumMavenProject\Pages package, hence each page is an object. These objects are instantiated under corresponding tests, making tests and UiObjects reside under different packages.

##Geting Started

1. Clone repository
2. Copy driver exe from ./src/resources/Driver/ to C:/Windows
3. Launch command prompt and type 'mvn clean test site' and hit Enter
4. Test execution starts
5. Run logs created under ./results/logs/TestLog.txt
6. Allure reports created at default path ./target/site/allure-maven-plugin/index.html

## What's New

Added support for running test in distributed environment using selenium grid. For more information visit http://www.seleniumhq.org/docs/07_selenium_grid.jsp. Removed logic for creating test execution videos as it would not be useful in distributed environment.

Steps before executing tests-

1. Go to src/resources/Grid
2. Download selenium-server-standalone.jar and place it under src/resources/Grid/
3. Modify the node.json file as per requirement
4. Launch run_grid.bat for running server
5. From now on testSuite.xml will be controlling test execution for browsers
6. Finally, run test via testSuite.xml or mvn command
