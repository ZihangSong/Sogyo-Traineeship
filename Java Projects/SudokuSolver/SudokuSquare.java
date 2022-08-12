package SudokuSolver;

public class SudokuSquare {
    int x_cordinate;
    int y_cordinate;
    int number;
    int squarenum;
    
    public SudokuSquare(int x, int y, int z, int s){
        this.x_cordinate = x;
        this.y_cordinate = y;
        this.number = z;
        this.squarenum = s;
    }

    public SudokuSquare(){
        this.x_cordinate = 0;
        this.y_cordinate = 0;
        this.number = 0;
    }
}
