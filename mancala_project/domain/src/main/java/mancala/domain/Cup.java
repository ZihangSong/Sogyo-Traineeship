package mancala.domain;

public class Cup {
    protected int stones;
    protected int player;
    protected int floatingstones;
    protected Cup neighbor;
    protected int totalAmountOfNormalCupsPerPlayer = 5;
    protected Cup oppositeneighbor;
    protected boolean landedupon = false;

    public int getStones(){
        return this.stones;
    }

    public Cup getNeighbor(){
        return this.neighbor;
    }

    public Cup getCup(int CupNumber){
        if (CupNumber == 0){
            return this;
        }else{
            return this.neighbor.getCup(CupNumber - 1);
        }
    }

    public int getFloatingstones(){
        return this.floatingstones;
    }

    public int getPlayer(){
        return this.player;
    }

    public void passStonesToNeighbor(){
        Cup neighbor = this.neighbor;
        neighbor.floatingstones = this.stones;
    }

    public void emptySelf(){
        this.stones = 0;
    }

    public void takeFromFloat(int playerside){
        int originalstones = this.getStones();
        if (this.floatingstones > 0){
            this.stones++;
            this.floatingstones--;          
        }
        if (this.getFloatingstones() == 0 && originalstones == 0){
            if (!opponentCheck(playerside)){
                this.stealFromOppositeNeighbor();
                if (!this.kalahaCheck()){
                    ((NormalCup) this).moveOwnContentToKalaha();
                }
            }
        }
    }

    public boolean kalahaCheck(){
        return false;
    }

    public void passFloatThrough(){
        Cup neighbor = this.getNeighbor();
        if (this.floatingstones > 0){
            neighbor.floatingstones = this.floatingstones;
            emptyOwnFloatingStones();
        }
    }

    public void emptyOwnFloatingStones(){
        this.floatingstones = 0;
    }

    public void linkOppositeNeighbors(Player OppositePlayer){
        Cup player1cups = new Cup();
        for (int i = 0; i < 6; i++){
            int linktarget = 5 - i;
            player1cups = this.getCup(i);
            player1cups.oppositeneighbor = OppositePlayer.board.getCup(linktarget);
        }
    }

    public Cup getOppositeNeighbor(){
        return this.oppositeneighbor;
    }

    public void linkKalahaToOppositeplayerCup0(Player OppositePlayer){
        Cup player1Kalaha = new Cup();
        Cup player2Cup0 = new Cup();
        player1Kalaha = this.getCup(6);
        player2Cup0 = OppositePlayer.board.getCup(0);
        player1Kalaha.neighbor = player2Cup0;
    }


    public boolean moveContentValidity(){
        int content = this.getStones();
        if (content <= 0){
            return false;
        }else{
            return true;
        }
    }

    public void passFloatWithoutTaking(){
        this.neighbor.floatingstones = this.floatingstones;
        this.floatingstones = 0;
    }

    public boolean playerTurnValidity(boolean turn){
        if (turn){
            return true;
        }else{
            return false;
        }
    }

    public boolean moveValidityCheck(boolean turn){
        if(this.moveContentValidity() && this.playerTurnValidity(turn)){
            return true;
        }else{
            return false;
        }

    }

    public void completeMove(int CupNumber, int playerside, boolean turn){
        Cup cupToMakeAMove = this.getCup(CupNumber);
        int content = cupToMakeAMove.getStones();
        boolean validitycheck = cupToMakeAMove.moveValidityCheck(turn);
        if (validitycheck){
            cupToMakeAMove.passStonesToNeighbor();
            cupToMakeAMove.emptySelf();
            for (int i = 0; i <= content ; content--){
                cupToMakeAMove.tellNeighborToTakeAndPassFloat(playerside);
                cupToMakeAMove = cupToMakeAMove.neighbor;
            }         
        }
    }

    public void tellNeighborToTakeAndPassFloat(int playerside){
        this.neighbor.takeFromFloat(playerside);
        this.neighbor.passFloatThrough();
    }

    public boolean opponentCheck(int playerside){
        if (this.player == playerside){
            return false;
        }else{
            return true;
        }
    }

    public void stealFromOppositeNeighbor(){
        int opponentstones = this.oppositeneighbor.getStones();
        this.stones += opponentstones;
        this.oppositeneighbor.youGotStolen();
    }

    public void youGotStolen(){
        this.emptySelf();
    }

    public boolean didILandOnAKalaha(){
        return landedupon;
    }

    public void resetLandStatus(){
        landedupon = false;
    }

    public boolean checkWhetherAllOwnNeighborsAreEmpty(int playerside){
        Cup cuptocheck;
        int cupschecked = 0;
        int totalcups = 6;
        cuptocheck = this.neighbor;
        while(cupschecked <= totalcups){
            if (cuptocheck.getPlayer() == playerside){
                if (cuptocheck.checkWhetherIAmEmpty()){
                    cupschecked++;
                }else{
                    return false;
                }
                cuptocheck = cuptocheck.neighbor;
            }else{
                cuptocheck = cuptocheck.neighbor;
            }
        }
        return true;   
    }

    public boolean checkWhetherIAmEmpty(){
        if(this.stones == 0){
            return true;
        }else{
            return false;
        }
    }

    public Cup getKalaha(){
        return this.getCup(6);
    }

    public int kalahaEndContent(){
        return this.getKalaha().getStones();
    }
}
