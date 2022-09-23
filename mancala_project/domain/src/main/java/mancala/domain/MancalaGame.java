package mancala.domain;

public class MancalaGame implements Playable{
    String nameOfPlayer1;
    String nameOfPlayer2;
    Player player1 = new Player(1);
    Player player2 = new Player(2);

    public MancalaGame(String name1, String name2){
        this.nameOfPlayer1 = name1;
        this.nameOfPlayer2 = name2;
        this.player1.startAGameAgainst(player2);
    }

    public String getNameOfPlayerOne(){
        return this.nameOfPlayer1;
    };

    public String getNameOfPlayerTwo(){
        return this.nameOfPlayer2;
    };

    public boolean isPlayersTurn(String name){
        boolean turnCheck;
        Player playerToCheck = playerCheck(name);
        turnCheck = playerToCheck.getTurn();
        return turnCheck;
    };

    public int[] playPit(int index){
        int gameState[] = new int[15];
        gameState = gameStateCheck(gameState);
        if (index <= 5){
            player1.makeAMoveOnCup(index);
        }else if (index > 6 && index < 13){
            int player2Cup = index - 7;
            player2.makeAMoveOnCup(player2Cup);
        }
        return gameState;
    };

    public int getStonesForPit(int index){
        if (index <= 6){
            int stones = player1.board.getCup(index).getStones();
            return stones;
        }else{
            int player2Cup = index - 7;
            int stones = player2.board.getCup(player2Cup).getStones();
            return stones;
        }
    };

    public boolean isEndOfGame(){
        if (player1.board.checkWhetherAllOwnNeighborsAreEmpty(1) || 
        player2.board.checkWhetherAllOwnNeighborsAreEmpty(2)){
            return true;
        }else{
            return false;
        }
    };

    public Winner getWinner(){
        if (isEndOfGame()){
            if (player1.didIWinBoolean()){
                return Playable.Winner.PLAYER_1;
            }else if(player2.didIWinBoolean()){
                return Playable.Winner.PLAYER_2;
            }else{
                return Playable.Winner.DRAW;
            }
        }else{
            return Playable.Winner.NO_ONE;
        }
    };

    private Player playerCheck(String name){
        if (name.equals(nameOfPlayer1)){
            return player1;
        }else{
            return player2;
        }
    }

    private int[] gameStateCheck(int[] gameStateArray){
        gameStateArray = fillPlayer1GameStateArray(gameStateArray);
        gameStateArray[6] = player1.board.getKalaha().getStones();
        gameStateArray = fillPlayer2GameStateArray(gameStateArray);
        gameStateArray[13]= player2.board.getKalaha().getStones();
        gameStateArray[14]= gameStatePlayerTurnCheck();
        return gameStateArray;
    }

    private int gameStatePlayerTurnCheck(){
        if (player1.getTurn()){
            return 2;
        }else{
            return 1;
        }
    }

    private int[] fillPlayer1GameStateArray(int [] gameStateArray){
        int[] player1BoardState = getPlayer1BoardState();
        for (int i = 0; i < player1BoardState.length; i++){
            gameStateArray[i] = player1BoardState[i];
        }
        return gameStateArray;
    }

    private int[] fillPlayer2GameStateArray(int [] gameStateArray){
        int[] player2BoardState = getPlayer2BoardState();
        for (int i = 0; i < player2BoardState.length; i++){
            int tempArrayIndex = i + 7;
            gameStateArray[tempArrayIndex] = player2BoardState[i];
        }
        return gameStateArray;
    }

    private int[] getPlayer1BoardState(){
        int[] player1BoardState = new int[6];
        for (int i = 0; i < player1BoardState.length; i++){
            player1BoardState[i] = player1.board.getCup(i).getStones();
        }
        return player1BoardState;
    }

    private int[] getPlayer2BoardState(){
        int[] player2BoardState = new int[6];
        for (int i = 0; i < player2BoardState.length; i++){
            player2BoardState[i] = player2.board.getCup(i).getStones();
        }
        return player2BoardState;
    }
}
