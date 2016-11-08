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
3. Launch command prompt and type 'mvn clean test' and hit Enter
4. Test execution starts
5. Run logs created under ./results/logs/TestLog.txt
6. Test execution video created under ./results/videos
