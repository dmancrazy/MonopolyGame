# Monopoly Game
This project was inspired by the hasbro game Monopoly.
The project can be found in this repository.
https://github.com/dmancrazy/MonopolyGame

# Download
Download the practice folder within the repository (Including the Test classes) and into a directory (I suggest desktop). also make sure that this file has a copy of hamcrest-core-1.3.jar and junit-4.12.jar (which it does).

# Test Files
```
SquareTest.java
PlayerTest.java
JailTest.java
BankerTest.java
```

# Junit Testing
After changing directories to practice using the command prompt; run the following commands in the practice.
```
javac -cp .;hamcrest-core-1.3.jar;junit-4.12.jar *.java
java -cp ;hamcrest-core-1.3.jar;junit-4.12.jar org.junit.runner.JUnitCore SquareTest
or
java -cp ;hamcrest-core-1.3.jar;junit-4.12.jar org.junit.runner.JUnitCore PlayerTest
or
java -cp ;hamcrest-core-1.3.jar;junit-4.12.jar org.junit.runner.JUnitCore JailTest
or 
java -cp ;hamcrest-core-1.3.jar;junit-4.12.jar org.junit.runner.JUnitCore BankerTest.java
```

# Running the Game
There should be 3 folders in practice containing .java files called Actions, Configuration and GamePieces. Leave those folders as they are, you don't need to pull the files out. It is important that you REMOVE the 4 Test files from this folder and delete their respective classesif you have already complied them, because it won't compile if you are trying to run the game.

# Text-Based Game
After using the command prompt to change directories to the practice and run the following commands in that directory.
```
javac *.java
java TextBasedGame
```

# GUI Game
After using the command prompt to change directories to the practice and run the following commands in that directory.
```
javac *.java
java MonopolyApp
```
Then choose the number of players in the first scene and press ok. Then assign names to all of the players and press the start game button.

# Citations and References

Verwaal, N. CPSC 219 - Introduction to Computer Science for Multidisciplinary Studies II (Winter 2019 Class Notes).

Hasbro, Inc. Monopoly.

ObjectAid UML Explorer: https://www.objectaid.com/home

Oracle Docs: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/Background.html

StackOverFlow: https://stackoverflow.com/questions/14370554/how-to-programmatically-set-the-color-or-texture-of-a-tab-label-in-javafx
               https://stackoverflow.com/questions/37689441/javafx-text-control-setting-the-fill-color
               https://stackoverflow.com/questions/53644508/setting-background-color-in-javafx-not-working
             
Monopoly Rents: http://www.falstad.com/monopoly.html

YouTube: https://youtu.be/7LxWQIDOzyE

https://www.amazon.com/Hasbro-Monopoly-Replacement-Board/dp/B017MNUCXC:
The source of our main background image. We do not in any way have ownership of this image and this project will not be used to make any money. The Monopoly board is liscened and owned by Hasbro and all credit for the invention of the game goes to them.

Piskel Sprite Art and Editor: https://www.piskelapp.com/

