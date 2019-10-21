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
    - Navigate to the folder you cloned the GitHub Repository, select the folder named `Asmt1_Java`, and then click **OK**
    - Back in IntelliJ, click next 2 times. There will be a warning that the chosen folder is not empty, and that is ok. You can overwrite it. 
    - Click Next 3 more times. You will be prompted again to overwrite another file. Choose the Reuse option.
    - You'll now be prompted to choose your Java JDK, which we downloaded earlier. On the left-hand side, there should be a folder with the number 12. Left click that folder and hit next.
    - Press Finish
    - IntelliJ will then open the project if everything has been done correctly
11) Now you need to add the JUnit framework to the project. Do so by:
    - Selecting File in the top left corner
    - Choose Project Structure, which is the 6th option in the dropdown.
    - Select the Modules tab on the left-hand side
    - In the main window, you should see 3 different tabs labeled Sources, Paths, and Dependencies respectively. Select the Dependencies tab
    - On the right-hand side of the main window, there is a small grey plus sign next to Scope. Press the plus sign.
    - Choose the second option of `Library...`
    - Choose the second option of the new dropdown `From Maven...`
    - A search box will now appear. Copy and paste `org.junit.jupiter:junit-jupiter:5.4.2` in the search box.
    - Click OK
    - A new popup will appear asking you to configure the library. Simply press OK.
    - Click Apply on the bottom right of the window, and then OK
12) From here, you'll be able to run the program, any and all tests, and check our code coverage.

## Execution Instructions
### How to Run the Tests

Now that the project is open in IntelliJ, on the left-hand side you will find the file system / file structure of our project. One of the folders should be highlighted green and labeled **tests**. Right click on the green folder labeled tests and select the option "Run All Tests". This option should have a small green play button next to it.

If you'd like to see an exact breakdown of our code coverage as well, you can Right click on the green folder labeled tests again and this time select the option "Run All Tests with Coverage".

#### PPA#2 Changes
PPA#2 does not have a required coverage report that we need to meet. We therefore focused on making sure the desired functionality was achieved and wrote the required tests only. All the additional mock tests and Http mock tests will be run with the existing unit tests by following the above directions. A live database instance is not required to run the tests (new and old).

### Running the JenkinsCI on Docker

To run our pipeline, open a docker terminal and paste the following to create the required container:
```
docker run \
  --rm \
  -u root \
  -p 8080:8080 \
  -v jenkins-data:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v "$HOME":/home \
  jenkinsci/blueocean
```
Follow the remainder of [this](https://jenkins.io/doc/tutorials/build-a-java-app-with-maven/) but instead of creating a new Jenkinsfile, you will use the one we have created. Our Jenkinsfile has 4 separate stages, 1 for Build and 3 for Testing. If you have not already made a User, you will have to create one.

### How to Run the Program

#### PPA2 Changes
To run the application successfully, you must first have Docker installed. The databsae we have decided to use is MySQL, and the specific image is maintained by docker. To have a docker container running that the application can interact with, run the following command in a Docker Terminal:
```
docker run --detach -p 3306:3306 --name=pensive_buck --env="MYSQL_ROOT_PASSWORD=password" mysql
```
Once the command has been executed, wait a few seconds for the database to spin up properly. Once the container is up, when the application is run, it will connect to the container, generate the database and its tables appropriately, or it will use the previously generated database and table from a previous execution of the application (meaning, we have persistent data from one execution to another).

Because IntelliJ is an IDE, it has its own built-in command line. We will be using this to run our application. On the left-hand side in the file system / file structure of our project, there should be a blue folder labeled **src**. Open that folder by Left clicking on it.

Opening the src folder will produce a list of files with different names. The only one of importance to run the application is **main.java.MainMenu**. Right-click on main.java.MainMenu and select the option "Run main.java.MainMenu.main()". This option should also have a green play button next to it. 

Once you've selected that option, the built-in command line will appear from the bottom of the page. You will interact with the application through this. The main menu of the application will be displayed and is waiting for your input. From here, you'll be able to play with the 4 different functions we've chosen or simply exit the app.

#### Using Postman with the Running Application
The application now has added functionality to read and write to a database. The 2 functions we have decided to rewrite are **Shortest Distance** and **Body Mass Index**. When you choose either of these functions, with the database docker image running detached, you will be able to see the current contents of the respective tables. After you run through the function, we write that input and result, along with a timestamp, to the database. The next time you run either function, you can see the updated table.

When accessing the running application with **Postman**, we have a specific format you **must** adhere to for the application to work. For **GET** requests, the only valid URLs that the application will respond to are:
```
localhost:5000/distance
localhost:5000/bmi
```
When you send these GET requests with Postman, the application will respond with a JSON array. It will contain all the table's respective contents and can be viewed as well from a Google Chrome tab using the same URls.

To send a **POST** request with Postman to our application, the format is as follows. For **Distance**, the url is the same as the GET request, but now you are sending parameters. On Postman, using a POST request, add 4 rows to the Params tab. The **Keys** will be x1, y1, x2, and y2. The **Values** will be whatever input you want for each, so long as they are on the same row. An example URL of a POST request for the Shortest Distance function looks like:
```
localhost:5000/distance?x1=1.23333&y1=1&x2=2&y2=2
```

For **Body Mass Index**, you will have the same link as the GET request, but with params like Shortest Distance. This time, there will be 2 rows you add to the Params tab. The **Keys** will be height and weight. The **Values** will be whatever input you want to test. The input for both functions have the same constraints we have applied when using the application via the command line. An example URL of a POST request for the Body Mass Index function looks like:
```
localhost:5000/bmi?height=5'4"&weight=110
```
Although ' and " are special characters that will get encoded once the request is sent, we handle that conversion within the application.

## Naming and Organizational Conventions

The language we used in this assignment is Java, and as a result, we've decided to use JUnit5 as our testing framework. Therefore,
a majority of our naming conventions follow that of Java's best practices. We've also tried to modularize this assignment so that
each class created has a single responsibility.

#### PPA#2 Changes
For PPA#2, we've decided to use Mockito as our mocking framework. This works with Junit5 so all the existing tests still work as well. To help distinguish which are the required tests for this assignment, we have created new test files for the specified phases. For Phase 2, the Mock Tests are stored in files inside the test directory that follow the convention "Mock....Test". Although only 4 unit tests are required for each of the 2 functions being used, we've also added some tests for the MainMenu to work properly.

### Naming Conventions

- File Names start with a Capital Letter, should be a noun, and should not be an acronym
- Methods should be in Camel Case, where the first letter in the first word is lowercase and subsequent words have their first letter capitalized
- Variables should also follow the Camel Case convention
- Constants should be written in all capitals

The above naming convention follows the "official" [Java Naming Convention](https://www.javatpoint.com/java-naming-conventions)

#### PPA#2 Changes
- Mock files for Phase 2 start with "Mock" and end with "Test", following a similar convention as before
- The HTTP tests for Phase 3 are located in ServerTest. This adheres to the existing convention and makes it easier to find.

### Testing Organizational Structure

- Each Source File will have its own Test File, meaning if a source file called main.java.Retirement.java is made, there us a RetirementTest.java file
made specifically for it
- Each @Test will function as a Unit Test and is described by the function's name
- Tests with numbers in the name should be written out, i.e., Twenty instead of 20
- Test names follow the Camel Case convention with the first word *always* being 'test' followed by the feature being tested
```
testSayHello(), testIfTwentyIsValidAge(), etc.
```

The above test naming convention was influenced by [popular JUnit conventions](https://dzone.com/articles/7-popular-unit-test-naming)

#### PPA#2 Changes
- Phase 2 has its own test files identified by the name "Mock" and then the function/file being tested
- Phase 2 has its own test file called ServerTest

### Project Organizational Structure

- The main.java.MainMenu file serves as the starting point of the application and therefore contains our main function
- The Main function will loop until the user chooses the exit option
- Each of the functions are numbered, and the user must choose an option between 1-5 (5 being exit)
- Once a function has been chosen, main.java.MainMenu calls 'acceptInput()', which is a function that exists in each of the 4 possible options
- Each class file uses acceptInput() as its own 'main' loop that deals with error handling / input validation before performing its core functionality
- Once valid input has been provided, the calculation will be performed, and control will be returned to main.java.MainMenu so that the user can choose other functions

## Screencasts
### Phase 1
![Phase 1](/pics/phase1.gif)

### Phase 2
![Phase 2](/pics/phase2.gif)

### Phase 3
![Phase 3](/pics/phase3.gif)

### Phase 4
![Phase 4](/pics/phase4.gif)

## More Information

If you'd like to learn more about the project like different details about the structure, our testing habits, proof we followed TDD, the README from PPA#1 and more, select the Wiki tab along the top of the page. You'll find different pages on the Wiki containing additional information you might find useful.
