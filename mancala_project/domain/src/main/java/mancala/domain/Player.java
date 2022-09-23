package mancala.domain;

public class Player {
    private Player opponent;
    private int playerside;
    public Cup board;
    private boolean turn;


    public Player(int playernumber){
        this.board = new NormalCup(playernumber);
        this.playerside = playernumber;
    }

    public int getPlayer(){
        return this.playerside;
    }

    public boolean getTurn(){
        return this.turn;
    }

    public void makeAMoveOnCup(int CupNumber){
        Cup setcup = this.board.getCup(CupNumber);
        int original = setcup.getStones();
        this.board.completeMove(CupNumber, this.getPlayer(), this.getTurn());
        if (checkWhetherMyMoveChangedThings(CupNumber, original)){
            if (setcup.checkWhetherAllOwnNeighborsAreEmpty(this.getPlayer())|| 
            setcup.oppositeneighbor.checkWhetherAllOwnNeighborsAreEmpty(this.opponent.getPlayer())){
                ((NormalCup) this.board).moveWholeSideToKalaha();
                this.tellOpponentToMoveWholeSideToKalaha();
                System.out.println("Game Ended!");
                String wincheck = this.didIWin();
                System.out.println(wincheck);
//                System.exit(0);
            }         
            if(checkWhetherILandedOnAKalaha() == false){
                    this.switchTurn();
                    this.tellKalahaToResetLandStatus();
                }else{
                    this.tellKalahaToResetLandStatus();
            }
        }
    }

    public void linkOpponent(Player OppositePlayer){
        this.board.linkKalahaToOppositeplayerCup0(OppositePlayer);
        this.board.linkOppositeNeighbors(OppositePlayer);
        this.opponent = OppositePlayer;
        OppositePlayer.board.linkKalahaToOppositeplayerCup0(this);
        OppositePlayer.board.linkOppositeNeighbors(this);
        OppositePlayer.opponent = this;
    }

    public void startAGameAgainst(Player OppositePlayer){
        this.linkOpponent(OppositePlayer);
        this.turn = true;
        OppositePlayer.turn = false;
    }

    public void switchTurn(){
        this.turn = false;
        this.opponent.turn = true;
    }

    public boolean checkWhetherMyMoveChangedThings(int CupNumber, int originalstones){
        if (this.board.getCup(CupNumber).getStones() != originalstones){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkWhetherILandedOnAKalaha(){
        if (this.board.getCup(6).didILandOnAKalaha()){
            return true;
        }else{
            return false;
        }
    }

    public void tellKalahaToResetLandStatus(){
        this.board.getCup(6).resetLandStatus();
    }

    public String didIWin(){
        int ownkalaha = this.board.kalahaEndContent();
        int enemykalaha = this.opponent.board.kalahaEndContent();
        if (ownkalaha > enemykalaha){
            return "Yes i have won!";
        }else if(ownkalaha == enemykalaha){
            return "No, it's a tie!";
        }else{
            return "No i lost.";
        }
    }

    public Boolean didIWinBoolean(){
        int ownkalaha = this.board.kalahaEndContent();
        int enemykalaha = this.opponent.board.kalahaEndContent();
        if (ownkalaha > enemykalaha){
            return true;
        }else{
            return false;
        }
    }

    public void tellOpponentToMoveWholeSideToKalaha(){
        ((NormalCup) this.opponent.board).moveWholeSideToKalaha();
    }

}
