package Roborally;

public class Roborally {
    
    public static void main(String[] args){
        Robot myFirstRobot = new Robot(0,1,"East");
        Robot mySecondRobot = new Robot(1,0,"West");
        myFirstRobot.printstate();
        myFirstRobot.turnleft();
        myFirstRobot.turnleft();
        myFirstRobot.printstate();
        myFirstRobot.forward();
        myFirstRobot.printstate();
        myFirstRobot.forward(3);
        myFirstRobot.printstate();
        myFirstRobot.turnaround();
        myFirstRobot.printstate();
        myFirstRobot.execute();
        mySecondRobot.forward(3);
        mySecondRobot.forward(2);
        mySecondRobot.turnright();
        mySecondRobot.forward(3);
        mySecondRobot.printstate();
        mySecondRobot.execute();
    }
}
    


