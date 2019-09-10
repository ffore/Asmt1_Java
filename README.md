# CISE4930 Continuous Integration and Software Testing Assignment 1

## Naming and Organizational Conventions

The language we used in this assignment is Java, and as a result, we've decided to use JUnit5 as our testing framework. Therefore,
a majority of our naming conventions follow that of Java's best practices. We've also tried to modularize this assignment so that
each class created has a single responsibility.


### Naming Conventions

- File Names start with a Capital Letter, should be a noun, and should not be an acronym
- Methods should be in Camel Case, where the first letter in the first word is lowercase and subsequent words have their first letter capitalized
- Variables should also follow the Camel Case convention
- Constants should be written in all capitals

The above naming convention follows the "official" [Java Naming Convention](https://www.javatpoint.com/java-naming-conventions)

### Testing Organizational Structure

- Each Source File will have its own Test File, meaning if a source file called Retirement.java is made, there us a RetirementTest.java file
made specifically for it
- Each @Test will function as a Unit Test and is described by the function's name
- Tests with numbers in the name should be written out, i.e., Twenty instead of 20
- Test names follow the Camel Case convention with the first word *always* being 'test' followed by the feature being tested
```
testSayHello(), testIfTwentyIsValidAge(), etc.
```

The above test naming convention was influenced by [popular JUnit conventions](https://dzone.com/articles/7-popular-unit-test-naming)

### Project Organizational Structure

- The MainMenu file serves as the starting point of the application and therefore contains our main function
- The Main function will loop until the user chooses the exit option
- Each of the functions are numbered, and the user must choose an option between 1-5 (5 being exit)
- Once a function has been chosen, MainMenu calls 'acceptInput()', which is a function that exists in each of the 4 possible options
- Each class file uses acceptInput() as its own 'main' loop that deals with error handling / input validation before performing its core functionality
- Once valid input has been provided, the calculation will be performed, and control will be returned to MainMenu so that the user can choose other functions
