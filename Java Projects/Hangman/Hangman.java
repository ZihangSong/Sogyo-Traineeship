package Hangman;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.Random;


public class Hangman {
    public static void main(String[] args){
        System.out.println("Welcome to a game of hangman, please guess the letters of the word correctly to win the game.");
        List<String> wordlist = new ArrayList<String>(
            Arrays.asList("fancy","pause","meek","versed","reading","impulse","grumpy","quarrelsome","unite","clover","bake",
            "callous","wipe","winter","treatment","closer","unnatural","silk","art","wave")
        );
 //       System.out.println(wordlist);
        Random rand = new Random();
        int wordnumber = rand.nextInt(19);
        String theword = wordlist.get(wordnumber);
        int wordlength = theword.length();
//        System.out.println(theword);
        int lives = 10;
        String tempword = "";
        String rtword = tempword;
        for (int i = 0; i < wordlength; i++){
            tempword += "" + i;
            System.out.print('_');
        }
        List<String> miss = new ArrayList<String>();
        Scanner input = new Scanner(System.in);
        while (wordlength != 0){
            boolean lifecheck = lives > 0;
            if(lifecheck){ 
                System.out.print("\nYou have " + lives + " attempts left!");
                System.out.print("\nPlease make a letter guess:");
                String userinput = input.nextLine();
                int index = theword.indexOf(userinput);
                boolean check = theword.contains(userinput);
                if(check){ 
                    while (index >= 0){
                        String tempindex = String.valueOf(index);
                        tempword = tempword.replaceFirst(tempindex,userinput);
                        theword = theword.replaceFirst(userinput,"-");
                        rtword = tempword.replaceAll("[0-9]","_");
                        index = theword.indexOf(userinput);
                        check = theword.contains(userinput);
                        wordlength = wordlength -1;
                        System.out.println("You have missed: " + miss);
                    }
                }else{
                    miss.add(userinput);
                    lives = lives - 1;
                    System.out.println("Wrong!");
                    System.out.println("You have missed: " + miss);
                    }
                rtword = tempword.replaceAll("[0-9]","_");
                System.out.println("\n"+ rtword);
            }else{
                System.out.println("\nGame over, you lost!");
                System.exit(0);
            }
        }
        input.close();
        System.out.println("Game finished, you have won!");  
    }
}
