package SudokuSolver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
 //000820090500000000308040007100000040006402503000090010093004000004035200000700900
public class SudokuSolver{
    public static ArrayList<Integer> board = new ArrayList<>();
    public static ArrayList<String> statecheck = new ArrayList<>();
    public static ArrayList<SudokuSquare> coordinatedsquares = new ArrayList<>();
    public static ArrayList<SudokuSquare> flushlist = new ArrayList<>();
    public static ArrayList<Integer> possiblenumbers = new ArrayList<>();
    public static ArrayList<Integer> rowpossibilitycollection = new ArrayList<>();
    public static ArrayList<Integer> columnpossibilitycollection = new ArrayList<>();
    public static ArrayList<Integer> squarepossibilitycollection = new ArrayList<>();
    public static Map<Integer, Integer> frequencymap = new HashMap<>();
    public static ArrayList<Integer> numberstofill = new ArrayList<>();
    public static String boardinput;
    public static Long starttime;


    public static void main(String[] args){
        Initiate();
        Registerboard();
        CreateSudokuSquareObjects();
        Printinitialstate();
        Solveboard();
        Printfinalstate();
        Terminate();
    }

    public static void Initiate(){
        String start = "Please enter the sudokuboard:";
        System.out.println(start);
//        Scanner scan = new Scanner(System.in);
//        boardinput = scan.nextLine();
//        scan.close();
        boardinput = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        starttime = System.nanoTime();
    }

    public static void Terminate(){
        Long endtime = System.nanoTime();
        Long time = (endtime - starttime);
        Double duration = (double)time/1000000000;
        System.out.print("Solved in ");
        System.out.printf("%.2f", duration);
        System.out.println(" seconds!");
    }

    public static void Registerboard(){
        String digit[] = boardinput.split("(?<=\\d)(?=\\d)");
        for (int i = 0; i < 81; i++){
            board.add(Integer.valueOf(digit[i]));
        }
    }

    public static void CreateSudokuSquareObjects(){
        int squarenumber = 0;
        int rows = 9;
        int columns = 9;
        for (int row = 0; row < rows; row++){
            int squareassign = 0;
            if (row <= 2){
                squareassign = 1;
            }else if (row > 2 && row <= 5){
                squareassign = 4;
            }else{
                squareassign = 7;
            }
            for (int column = 0; column < columns; column++){
                SudokuSquare square = new SudokuSquare(row, column, board.get(squarenumber), squareassign);
                coordinatedsquares.add(square);
                squarenumber++;
                if (column == 2 || column == 5){
                    squareassign++;
                }
           }
        }
    }

    public static void Printinitialstate(){
        String message = "\nInitial state:";
        System.out.println(message);
        printceiling();
        convertzerotoblank();
        printnumbers();
        printfloor();     
    }

    public static void printceiling(){
        System.out.println();
        System.out.print("\u001b[36;1m╔");
        for (int i = 0; i < 8; i++){
            System.out.print("═══╦");
        }
        System.out.println("═══╗");
    }

    public static void printmiddleceiling(){
        System.out.println();
        System.out.print("╠═══");
        for (int k = 0; k < 8; k++){
            System.out.print("╬═══");
        }
        System.out.println("╣");
    }

    public static void printfloor(){
        System.out.println();
        System.out.print("╚");
        for (int i = 0; i < 8; i++){
            System.out.print("═══╩");
        }
        System.out.println("═══╝\u001b[0m");
    }

    public static void printnumbers(){
        int wordcount = 0;
        for (int i = 0; i < 9; i++){
            System.out.print("║");
            for (int j = 0; j < 9; j++){
                    System.out.print("\u001b[37;1m " + statecheck.get(wordcount) + "\u001b[36;1m ║");
                    wordcount++;
            }
            if(wordcount == 81){
                break;
            }else{
                printmiddleceiling();
            }
        }
    }

    public static void Solveboard(){
        while (!Solvedcheck()){
            Findandfilluniqueinrow();
            Findandfilluniqueincolumn();
            Findandfilluniqueinsquare();
        }
    }

    public static void compareuniquelistandfill(int row, int column){
        if (numberstofill.size() > 0){
            if (possiblenumbers.contains(numberstofill.get(0))){
                int position = Converttoboardposition(row, column);
                board.set(position, numberstofill.get(0));
                possiblenumbers.remove(Integer.valueOf(numberstofill.get(0)));
                numberstofill.remove(0);
                updateboardstatus();
            }
        }
    }

    public static void Filluniquerow(int row){
        if (numberstofill.size() > 0){
            for (int column = 0; column < 9; column++){
                if (Grabsquarevalue(row, column) == 0){
                    Initializepossiblenumberslist();
                    Checkcolumn(row, column);
                    Checkrow(row, column);
                    CheckSquare(row, column);
                    compareuniquelistandfill(row, column);
                }                
            }
        }
    }

    public static void Filluniquecolumn(int column){
        if (numberstofill.size() > 0){
            for (int row = 0; row < 9; row++){
                if (Grabsquarevalue(row, column) == 0){
                    Initializepossiblenumberslist();
                    Checkcolumn(row, column);
                    Checkrow(row, column);
                    CheckSquare(row, column);
                    compareuniquelistandfill(row, column);
                }                
            }
        }
    }

    public static void Filluniquesquare(int squarenum){
        if (numberstofill.size() > 0){
            for (int i = 0; i < 9; i++){
                for (int j = 0; j < 9; j++){
                    if (Identifysquarenum(i, j) == squarenum && Grabsquarevalue(i, j) == 0){
                        Initializepossiblenumberslist();
                        Checkcolumn(i, j);
                        Checkrow(i, j);
                        CheckSquare(i, j);
                        compareuniquelistandfill(i, j);
                    }
                }                
            }
        }
    }

    public static void Findelementoccuronce(ArrayList<Integer> collection, int size){
        numberstofill.clear();
        for (int i : collection){
            frequencymap.merge(i, 1, Integer::sum);
        }
        for (Map.Entry<Integer, Integer> intToFrequency : frequencymap.entrySet()){
            if (intToFrequency.getValue() == 1){
                numberstofill.add(intToFrequency.getKey());
            }
        }
        frequencymap.clear();
    }

    public static void Findandfilluniqueinrow(){
        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){
                Findpossiblenumbers(row, column);
                rowpossibilitycollection.addAll(possiblenumbers);
            }
            int size = rowpossibilitycollection.size();
            Findelementoccuronce(rowpossibilitycollection, size);
            Filluniquerow(row);
            Cleanuprowcollection();
        }
    }

    public static void Findandfilluniqueincolumn(){
        for (int column = 0; column < 9; column++){
            for (int row = 0; row < 9; row++){
                Findpossiblenumbers(row, column);
                columnpossibilitycollection.addAll(possiblenumbers);
            }
            int size = columnpossibilitycollection.size();
            Findelementoccuronce(columnpossibilitycollection, size);
            Filluniquecolumn(column);
            Cleanupcolumncollection();        
        }
    }

    public static void Findandfilluniqueinsquare(){
        int squarenum = 1;
        int cyclecount = 1;
        while (squarenum < 10){
            for (int row = 0; row < 9; row++){
                for (int column = 0; column < 9; column++){
                    int identifiedsquarenum = Identifysquarenum(row, column);
                    if (identifiedsquarenum == squarenum){
                        Findpossiblenumbers(row, column);
                        squarepossibilitycollection.addAll(possiblenumbers);
                        cyclecount++;
                        if (cyclecount == 10){
                            int size = squarepossibilitycollection.size();
                            Findelementoccuronce(squarepossibilitycollection, size);
                            Filluniquesquare(squarenum);
                            Cleanupsquarecollection();
                            cyclecount = 1;
                            break; 
                        }
                    }                 
                }
            }
            squarenum++;
        }
    }

    public static void Findpossiblenumbers(int row, int column){
        Initializepossiblenumberslist();
        if (Grabsquarevalue(row, column) == 0){
            Initializepossiblenumberslist();
            Checkcolumn(row, column);
            Checkrow(row, column);
            CheckSquare(row, column);
            int amountpossiblenum = possiblenumbers.size();
            if (amountpossiblenum == 1){
                int position = Converttoboardposition(row, column);
                board.set(position, possiblenumbers.get(0));
                updateboardstatus();
            }
        }else{
            possiblenumbers.clear();
        }
    }

    public static int Converttoboardposition(int row, int column){
        String positionstring;
        int position;
        positionstring = String.valueOf(row) + String.valueOf(column);
        position = Integer.valueOf(positionstring) - row;
        return position;
    }

    public static void Cleanuprowcollection(){
        rowpossibilitycollection.clear();
    }

    public static void Cleanupcolumncollection(){
        columnpossibilitycollection.clear();
    }

    public static void Cleanupsquarecollection(){
        squarepossibilitycollection.clear();
    }

    public static void Initializepossiblenumberslist(){
        possiblenumbers.clear();
        for (int i = 1; i <= 9; i++){
            possiblenumbers.add(i);
        }
    }

    public static void createflushlist(){
        int listsize = coordinatedsquares.size();
        for (int i = 0; i < listsize; i++){
            flushlist.add(coordinatedsquares.get(i));
        } 
    }

    public static int Grabsquarevalue(int row, int column){
        createflushlist();
        SudokuSquare square = new SudokuSquare();
        flushlist.removeIf(p -> p.x_cordinate != row);
        flushlist.removeIf(p -> p.y_cordinate != column);
        square = flushlist.get(0);
        return square.number;
    }

    public static void Checkcolumn(int row, int column){
        createflushlist();
        SudokuSquare currentsquare = new SudokuSquare();
        flushlist.removeIf(p -> p.y_cordinate != column);
        flushlist.removeIf(p -> p.number == 0);
        int flushlistsize = flushlist.size();
        for (int i = 0; i < flushlistsize; i++){
            currentsquare = flushlist.get(i);
            possiblenumbers.remove(Integer.valueOf(currentsquare.number));
        }
    }

    public static void Checkrow(int row, int column){
        createflushlist();
        SudokuSquare currentsquare = new SudokuSquare();
        flushlist.removeIf(p -> p.x_cordinate != row);
        flushlist.removeIf(p -> p.number == 0);
        int flushlistsize = flushlist.size();
        for (int i = 0; i < flushlistsize; i++){
            currentsquare = flushlist.get(i);
            possiblenumbers.remove(Integer.valueOf(currentsquare.number));
        }
    }

    public static void CheckSquare(int row, int column){
        createflushlist();
        SudokuSquare currentsquare = new SudokuSquare();
        int squarenum = Identifysquarenum(row, column);
        flushlist.removeIf(p -> p.squarenum != squarenum);
        flushlist.removeIf(p -> p.number == 0);
        int flushlistsize = flushlist.size();
        for (int i = 0; i < flushlistsize; i++){
            currentsquare = flushlist.get(i);
            possiblenumbers.remove(Integer.valueOf(currentsquare.number));
        }
    }

    public static int Identifysquarenum(int row, int column){
        if (row < 3){
            if (column < 3){
                return 1;
            }else if (column < 6){
                return 2;
            }else{
                return 3;
            }
        }else if(row >= 3 && row < 6){
            if (column < 3){
                return 4;
            }else if (column < 6){
                return 5;
            }else{
                return 6;
            }
        }else if(row >= 6 && row < 9){
            if (column < 3){
                return 7;
            }else if (column < 6){
                return 8;
            }else{
                return 9;
            }
        }else{
            return 0;
        }
    }

    public static void updateboardstatus(){
        coordinatedsquares.clear();
        CreateSudokuSquareObjects();
    }

    public static void convertzerotoblank(){
        for (int i = 0; i < board.size(); i++){
            statecheck.add(String.valueOf(board.get(i)));
        }
        for (int i = 0; i < statecheck.size(); i++){
            int toreplace = 0;
            if (statecheck.get(i).equals("0")){
                toreplace = i;
                statecheck.set(toreplace, " ");
            }
        }
    }

    public static void Printfinalstate(){
        String message = "\nSolved:";
        System.out.println(message);
        printceiling();
        int wordcount = 0;
        for (int i = 0; i < 9; i++){
            System.out.print("║");
            for (int j = 0; j < 9; j++){
                    System.out.print("\u001b[37;1m " + board.get(wordcount) + "\u001b[36;1m ║");
                    wordcount++;
            }
            if(wordcount == 81){
                break;
            }else{
                printmiddleceiling();
            }
        }
        printfloor(); 
    }

    public static boolean Solvedcheck(){
        if(!board.contains(0)){
            return true;
        }else{
            return false;
        }
    }
}