package mancala.domain;

public class NormalCup extends Cup{

    public NormalCup(int Player){
        this.stones = 4;
        this.neighbor = new NormalCup(totalAmountOfNormalCupsPerPlayer, Player);
        this.player = Player;
    }

    public NormalCup(int totalAmountOfNormalCupsPerPlayer, int Player){
        if (totalAmountOfNormalCupsPerPlayer > 0){
            totalAmountOfNormalCupsPerPlayer--;
            this.stones = 4;
            this.player = Player;
            if (totalAmountOfNormalCupsPerPlayer == 0){
                this.neighbor = new Kalaha(Player);
            }else{
                this.neighbor = new NormalCup(totalAmountOfNormalCupsPerPlayer, Player);
            }
        }
    }


    public void moveOwnContentToKalaha(){
        this.floatingstones = this.stones;
        this.passFloatWithoutTaking();
        this.tellNeighborToPassWithoutTaking();
        this.emptySelf();
    }

    public void tellNeighborToPassWithoutTaking(){
        this.neighbor.passFloatWithoutTaking();
        if (!this.neighbor.kalahaCheck()){
            ((NormalCup) this.neighbor).tellNeighborToPassWithoutTaking();
        }
    }

    public void passFloatWithoutTaking(){
        this.neighbor.floatingstones = this.floatingstones;
        this.floatingstones = 0;
    }

    public void moveWholeSideToKalaha(){
        this.moveOwnContentToKalaha();
        this.emptySelf();
        if (!this.neighbor.kalahaCheck()){
            ((NormalCup) this.neighbor).moveWholeSideToKalaha();
        }
    }

    public void tellNeighborToMoveToKalaha(){
        ((NormalCup) this.neighbor).moveOwnContentToKalaha();
    }
}
