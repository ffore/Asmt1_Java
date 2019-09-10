# CISE4930 Continuous Integration and Software Testing Assignment 1

## System Requirements

Due to the language and framework of choice, our project can only be run on a **Windows 10 Machine**. Do **not** follow these setup instructions on a Linux Machine as they will not work.

## How to Setup the Project

1) Install the latest version of IntelliJ Community Version from [here](https://www.jetbrains.com/idea/download/#section=windows)
2) After downloading your version, run the downloaded executable and follow the instructions to set IntelliJ up
3) Once IntelliJ has been installed, you can choose your User Interface color scheme preference.
4) Other than the color scheme preference, accept all the default options by clicking on the **Accept Defaults** button in the bottom left corner
5) After IntelliJ has been installed, make sure to download the latest version of the Java SE, specifically JDK 12.0.2, from [here](https://www.oracle.com/technetwork/java/javase/downloads/jdk12-downloads-5295952.html)
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
9) Once you are inside the folder / directory that you want the project to be saved, you'll need to clone our repository containing all our code using the command `git clone https://github.com/ffore/Asmt1_Java.git`. This will clone our repository to the current directory. Afterwards, make sure you also run `git pull origin master` to get the latest changes to the project
10) Now that we have cloned our repository, Open up IntelliJ.
    - On the right-hand side, you'll have 4 different options. Select **Import Project**, which will open a file explorer
    - Navigate to the folder you cloned the GitHub Repository, select tat folder, and then click **Import**
    - Back in IntelliJ, continue to click next on all the options as you won't need to make any changes.
    - IntelliJ will then open the project if everything has been done correctly
11) From here, you'll be able to run the program, any and all tests, and check our code coverage.

## Execution Instructions

### How to Run the Tests

Now that the project is open in IntelliJ, on the left-hand side you will find the file system / file structure of our project. One of the folders should be highlighted green and labeled **tests**. Right click on the green folder labeled tests and select the option "Run All Tests". This option should have a small green play button next to it.

If you'd like to see an exact breakdown of our code coverage as well, you can Right click on the green folder labeled tests again and this time select the option "Run All Tests with Coverage".

### How to Run the Program

Because IntelliJ is an IDE, it has its own built-in command line. We will be using this to run our application. On the left-hand side in the file system / file structure of our project, there should be a blue folder labeled **src**. Open that folder by Left clicking on it.

Opening the src folder will produce a list of files with different names. The only one of importance to run the application is **MainMenu**. Right-click on MainMenu and select the option "Run MainMenu.main()". This option should also have a green play button next to it. 

Once you've selected that option, the built-in command line will appear from the bottom of the page. You will interact with the application through this. The main menu of the application will be displayed and is waiting for your input. From here, you'll be able to play with the 4 different functions we've chosen or simply exit the app.
