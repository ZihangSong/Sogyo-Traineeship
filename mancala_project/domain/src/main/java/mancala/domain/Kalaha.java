package mancala.domain;

public class Kalaha extends Cup{

    public Kalaha(int Player){
        this.stones = 0;
        this.player = Player;
    }


    @Override
    public void completeMove(int cupToMakeAMove, int playerside, boolean turn){
    }

    @Override
    public void takeFromFloat(int playerside){
        boolean opponentcheck = this.opponentCheck(playerside);
        if(opponentcheck == false){
            if (this.floatingstones > 0){
                this.stones++;
                this.floatingstones--;
                if (this.floatingstones == 0){
                    landedupon = true;   
                }           
            }
        }
    }

    @Override
    public void passFloatWithoutTaking(){
        this.stones = this.getStones() + this.floatingstones;
        this.floatingstones = 0;
    }

    @Override
    public boolean checkWhetherIAmEmpty(){
        return true;
    }

    public boolean kalahaCheck(){
        return true;
    }
}
