# CIS4930 Continuous Integration and Software Testing Assignment 1

## System Requirements

Due to the language and framework of choice, our project can only be run on a **Windows 10 Machine**. Do **not** follow these setup instructions on a Linux Machine as they will not work.

## How to Setup the Project

1) Install the latest version of IntelliJ Community Version from [here](https://www.jetbrains.com/idea/download/#section=windows)
2) After downloading your version, run the downloaded executable and follow the instructions to set IntelliJ up
3) Once IntelliJ has been installed, you can choose your User Interface color scheme preference.
4) Other than the color scheme preference, accept all the default options by clicking on the **Accept Defaults** button in the bottom left corner
5) After IntelliJ has been installed, make sure to download the latest version of the Java SE, specifically JDK 12.0.2, from [here](https://www.oracle.com/technetwork/java/javase/downloads/jdk12-downloads-5295953.html)
    - You'll need to accept the license agreement before you can start the download.
    - Make sure to choose the **.exe** download option and follow the steps on the screen
6) The last piece of software that needs installation is Git. Download Git for Windows, version 2.23.0.windows.1, from [here](https://git-scm.com/download)
    - Accept all the defaults, except for one. The only change we made while developing was we changed the default editor from **Vim** to **Nano** (our personal preference).
    - NOTE: If you change the location of where you are saving Git, remember that **exact location**. We recommend **not** changing the default location so that it matches our setup
7) Once Git Bash has been installed, open it and login with your GitHub Account Credentials if prompted. If you do not have a GitHub Account, you'll need to create one before moving on to the next step
8) Now you can navigate to a location in your file system that you'd like to save our project.
    - If you want to choose a new location, you can use the `cd` command to change directories, `ls` to print out the contents of the current directory, and `mkdir` to create a new directory.
    - If you'd rather avoid the extra work, you can simply create a new directory in your current location without moving anything. Create a new directory and enter it like so:
    ```
    mkdir Assignment_1
    cd Assignment_1
    ```
9) Once you are inside the folder / directory that you want the project to be saved, you'll need to clone our repository containing all our code using the command `git clone https://github.com/ffore/Asmt1_Java.git`. This will clone our repository to the current directory and save everything in a folder called 'Asmt1_Java'.
10) Now that we have cloned our repository, Open up IntelliJ.
    - On the right-hand side, you'll have 4 different options. Select **Import Project**, which will open a file explorer
    - Navigate to the folder you cloned the GitHub Repository, select the folder named 'Asmt1_Java', and then click **Import**
    - Back in IntelliJ, click next when prompted. There will be a warning that the chosen folder is not empty, and that is ok. You can overwrite it. 
    - Click Next 3 more times. You will be prompted again to overwrite another file. Choose the Reuse option.
    - You'll now be prompted to choose your Java JDK, which we downloaded earlier. On the left-hand side, there should be a folder with the number 12. Left click that folder and hit next.
    - Press Finish
    - IntelliJ will then open the project if everything has been done correctly
11) Now you need to add the JUnit framework to the project. Do so by:
    - Selected File in the top left corner
    - Choose Project Structure, which is the 6th option in the dropdown.
    - Select the Modules tab on the left-hand side
    - In the main window, you should see 3 different tabs labeled Sources, Paths, and Dependencies respectively. Select the Dependencies tab
    - On the right-hand side of the main window, there is a small grey plus sign next to Scope. Press the plus sign.
    - Choose the second option of 'Library...'
    - Choose the second option of the new dropdown 'From Maven...'
    - A search box will now appear. Copy and paste 'org.junit.jupiter:junit-jupiter:5.4.2' in the search box.
    - Click OK
    - A new popup will appear asking you to configure the library. Simply press OK.
    - Click Apply on the bottom right of the window, and then OK
12) From here, you'll be able to run the program, any and all tests, and check our code coverage.

## Execution Instructions
### How to Run the Tests

Now that the project is open in IntelliJ, on the left-hand side you will find the file system / file structure of our project. One of the folders should be highlighted green and labeled **tests**. Right click on the green folder labeled tests and select the option "Run All Tests". This option should have a small green play button next to it.

If you'd like to see an exact breakdown of our code coverage as well, you can Right click on the green folder labeled tests again and this time select the option "Run All Tests with Coverage".

### How to Run the Program

Because IntelliJ is an IDE, it has its own built-in command line. We will be using this to run our application. On the left-hand side in the file system / file structure of our project, there should be a blue folder labeled **src**. Open that folder by Left clicking on it.

Opening the src folder will produce a list of files with different names. The only one of importance to run the application is **MainMenu**. Right-click on MainMenu and select the option "Run MainMenu.main()". This option should also have a green play button next to it. 

Once you've selected that option, the built-in command line will appear from the bottom of the page. You will interact with the application through this. The main menu of the application will be displayed and is waiting for your input. From here, you'll be able to play with the 4 different functions we've chosen or simply exit the app.

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

## Screenshots
### All Tests
![All tests passing](/pics/all_tests_green.png)

### Body Mass Index Tests
<img src="/pics/bmi%20test.png" width="300">

### Retirement Tests
<img src="/pics/retirement%20test.png" width="300">

### Shortest Distance Tests
<img src="/pics/distance%20test.png" width="300">

### Tip Splitter Tests
<img src="/pics/tip%20splitter%20test.png" width="300">

### Main Menu Tests
<img src="/pics/main%20menu%20test.png" width="300">

### Input Validation Class Tests
<img src="/pics/input%20validation%20test.png" width="300">

### Code Coverage Tests
<img src="/pics/coverage%20test.png" width="300">

## Screencasts
### All Functions
![all functions](/pics/main.gif)

### RGR Example 1
![rgr example 1](/pics/RGR_Example_1.gif)

### RGR Example 2
![rgr example 2](/pics/tdd.gif)

## Our Experience
### Describe your unit testing & TDD experience. What do you think of unit testing & TDD? Do you think it's useful for a real project? Discuss the Benefits & Drawbacks to TDD

### Bryan

I found myself always following the TDD methodology while working on this assignment. Even for simple functions like getters and setters on instance variables, I ended up writing a failing test for them first, and then wrote the source code for it. Most of these functions ended up being one line, but after spending so much time on this assignment, I had developed the habit. I've followed Test Driven Development while interning at different companies before so this process wasn't entirely new. However, based on the projects I had worked on, I didn't find immediate results from TDD. However, because of the scope of this project and the requirements for each function, I found that writing a test I thought would pass easily ended up being more difficult that I realized. TDD forced me to give each method a single responsibility and to keep them as small as possible to make testing easier. There were multiple times with floating precision errors where I had written a test first, then written the source code to pass at, and still found the test failing. If I had simply written the source code without having any tests, I surely would have missed multiple bugs.

I do think TDD can be useful in a real project. Having passing tests gives you the confidence to make changes elsewhere in the source code without breaking anything existing. In my opinion, the most useful part of TDD is that it forces the programmer to think about the design of the application. This means that a developer will be coding with efficiency in mind. Classes and methods are made smaller and are written to have a single responsibility in mind that way its easier to pass the failing tests. TDD leads to a cleaner code base if followed properly.

However, there are some drawbacks that I've found while working on this assignment. There were points in time that it felt like nothing but test cases were being written. The core functionality was being put on hold to make sure all the input validation, which comes before the calculations so that they run successfully, was working properly. While this did feel like my development time was being stretched, I think it was for the right reasons. If I hadn't done all that testing, there's no guarantee the program would work as expected otherwise.


### Daniel

Throughout the assignment I kept reflecting as to how my usual development process is. I normally write code with many console logs, and verify the output visually. Then once it’s up to my standards, I comment out the console logs and continue. There is no way for me to know if my new changes affect my old “tested” code. I usually add code, test the new functionality, and then go back and test the old functionality I had previously verified. This was always a tedious and daunting process for me. I knew of test driven development but thought the process would just add more work on my end, since I’d have to write code and then also write tests.

I was motivated to take this class to force myself to use the TDD methodology. I noticed how much less stressful the development process was throughout this assignment. Adding new changes was a lot easier when I would see precisely which new changes would break specific old pieces of code. Writing tests added to my confidence and breaking down the program into smaller units made the process at first seem longer, but still easier to conceptualize. Despite the initial drawbacks of putting in more time to set up the environment for testing and getting used to writing unit tests,  I think TDD is very useful in aiding the development process. I’m interested in how to apply this testing to a web application, specifically the front end that is integrated with a front end framework. 


## More Information

If you'd like to learn more about the project like different details about the structure, our testing habits, proof we followed TDD, and more, select the Wiki tab along the top of the page. You'll find different pages on the Wiki containing additional information you might find useful.
