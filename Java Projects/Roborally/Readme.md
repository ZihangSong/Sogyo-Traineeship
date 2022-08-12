Roborally (required)
=
### Imagine a simple robot. This robot moves through a factory based on simple commands like “turn left”, “turn right”, “forward”, “backward”. It moves in a grid and each turn is 90 degrees. Assume the robot is the same size as the grid.
#
Create a class for this type of robot. The robot has a position and a facing. By default these are (0,0) and “North”.

Not every robot will start at the same position and facing, so we want to be able to change its starting state. This can be done with another constructor that receives three parameters for initializing the robot with a position and facing.<br>
Now we can create robots with different positions like:<br>
Robot myFirstRobot = new Robot(0, 1, "East")<br>
Robot mySecondRobot = new Robot(1, 0, "West")<br>
Try printing their positions and see if it works.<br>

Now try to implement the turnLeft() method.

**1. Robot myFirstRobot = new Robot(0, 1, "East");<br>
2. Robot mySecondRobot = new Robot(1, 0, "West");<br>
3. myFirstRobot.turnLeft();<br>
4. myFirstRobot.printState(); // Now facing "North"<br>
5. mySecondRobot.printState(); // Still facing "West"<br>**
Implement the turnRight() method in the same way.<br>

Now try to implement the forward and backward method. We assume north means +1 to the y coordinate, south ­1 to the y coordinate, etc…<br>

**1. Robot myFirstRobot = new Robot(0, 1, "East");<br>
2. Robot mySecondRobot = new Robot(1, 0, "West");<br>
3. myFirstRobot.turnLeft();<br>
4. myFirstRobot.forward();<br>
5. mySecondRobot.backward();<br>
6. myFirstRobot.printState(); // Now facing "North" at (0,2)<br>
7. mySecondRobot.printState(); // Now facing "West" at (2,0)<br>**
Robots can move faster forward than the single square the forward function did. Allow the forward function to specify a speed. This speed should be between 1 and 3. The default when no speed is specified should still be one.<br>

Until now the robot performed the command directly, but that is not how robots normally work. Change the turn and movement functions so they do not alter the state, but are stored in a list. When you call goLeft(), goRight(), etc, the robot will store these as commands chronologically. Also create an execute() function that performs all commands in the list.<br>

**1. Robot myFirstRobot = new Robot(1, 0, "West");<br>
2. myFirstRobot.turnLeft();<br>
3. myFirstRobot.forward();<br>
4. myFirstRobot.backward();<br>
5. myFirstRobot.execute();<br>**

Learning goals<br>
-
Working with objects<br>
Creating a functional api on an object<br>
Store methods as data<br>
