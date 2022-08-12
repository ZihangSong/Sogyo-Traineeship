package Roborally;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    int x_cordinate;
    int y_cordinate;
    Direction direction;
    Boolean runnable = false;

    private List<Runnable> Commandlist = new ArrayList<Runnable>();

    
    public static enum Direction{
        NORTH,
        EAST,
        SOUTH,
        WEST;
        public Direction turnRight(){
            return Direction.values()[(this.ordinal()+1) % Direction.values().length];
        }
        public Direction turnLeft(){
            return Direction.values()[(this.ordinal()+3) % Direction.values().length];
        }
        public Direction turnAround(){
            return Direction.values()[(this.ordinal()+2) % Direction.values().length];
        }
    }

    public Robot(int x, int y, String direc){
        this.x_cordinate = x;
        this.y_cordinate = y;
        this.direction = getDirectionEnumForString(direc);
    }

    private Direction getDirectionEnumForString(String direction){
        switch(direction){
            case "North":
                return Direction.NORTH;
            case "East":
                return Direction.EAST;
            case "South":
                return Direction.SOUTH;
            case "West":
                return Direction.WEST;
            default:
                return Direction.NORTH;
        }
    }

    public Robot(){
        this.x_cordinate = 0;
        this.y_cordinate = 0;
        this.direction = Direction.NORTH;  
    }

    public void printstate(){
        if (runnable){
        System.out.println("(" + this.x_cordinate + "." + this.y_cordinate + "), " + "\"" + this.direction + "\"");
        }else{
            Commandlist.add(()->printstate());
        }
    }

    public void turnleft(){
        if (runnable){
        this.direction = this.direction.turnLeft();
        }else{
            Commandlist.add(()->turnleft());
        }
    }

    public void turnright(){
        if (runnable){
        this.direction = this.direction.turnRight();
        }else{
            Commandlist.add(()->turnright());
        }
    }

    public void turnaround(){
        if (runnable){
        this.direction = this.direction.turnAround();
        }else{
            Commandlist.add(()->turnaround());
        }
    }

    public void forward(){
        if (runnable){
            if(this.direction.ordinal() == 0 | this.direction.ordinal() == 2){
                this.y_cordinate = this.y_cordinate + 1;
            }else{
                this.x_cordinate = this.x_cordinate + 1;
            }
        }else{
            Commandlist.add(()->forward());
        }
    }

    public void forward(int i){
        if (runnable){
            if(i > 3 || i < 0){
                System.out.println("Your Robot cannot move like that!");
            }
            if(this.direction.ordinal() == 0 | this.direction.ordinal() == 2){
                this.y_cordinate = this.y_cordinate + i;
            }else{
                this.x_cordinate = this.x_cordinate + i;}
        }else if(i == 1){
            Commandlist.add(()->forward(1));
        }else if(i == 2){
            Commandlist.add(()->forward(2));
        }else{
            Commandlist.add(()->forward(3));
        }
    }

    public void backward(){
        if (runnable){
            if(this.direction.ordinal() == 0 | this.direction.ordinal() == 2){
                this.y_cordinate = this.y_cordinate - 1;
            }else{
                this.x_cordinate = this.x_cordinate - 1;
            }
        }else{
            Commandlist.add(()->backward());
        }
    }

    public void execute(){
        runnable = true;
        for (Runnable runnable : Commandlist){
            runnable.run();
        }
    }
}

