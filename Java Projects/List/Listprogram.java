package List;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Listprogram {
//List generating
    /**
     * @param args
     */
    public static void main(String[] args){
        List<Integer> nlist = new ArrayList<Integer>();
        Random rand = new Random();
        int upperbound = 101;
        for (int i = 0; i < 11; i++){
            int int_random = rand.nextInt(upperbound);
            nlist.add(int_random);
        }
        System.out.println(nlist);
        int n = nlist.size();
//Choice menu
        Scanner input = new Scanner(System.in);
        System.out.print("Please select from: 'The highest', 'Lowest two', 'Even','Split','Sort':");
        String userinput = input.nextLine();

        int max = nlist.get(0);
        int min1 = nlist.get(0);
//Max value
        if(userinput.equals("The highest")){
             for (int j = 1; j < n; j++){
                if (nlist.get(j) > max){
                    max = nlist.get(j);
                }
            }
            System.out.println("The highest value is " + max);
        }
      
//2 Min value addition
        else if(userinput.equals("Lowest two")){
            for(int k = 1; k < n; k++){
                if(nlist.get(k) < min1){
                    min1 = nlist.get(k);
                }
            }
            nlist.remove(Integer.valueOf(min1));
            int min2 = nlist.get(0);
            for(int k = 1; k < 9; k++){
                if(nlist.get(k) < min2){
                    min2 = nlist.get(k);
                }
            }
            int min3 = min1 + min2;
            System.out.println("The addition of the two lowest values is "+ min3);        
        }
//Even numbers
        else if(userinput.equals("Even")){
            List<Integer> even = new ArrayList<>();
            for(int i = 0; i < n; i++){
                if(nlist.get(i) > 0 && nlist.get(i) % 2 ==0){
                    even.add(nlist.get(i));
                }
            }
            System.out.println(even);
        }
//Splitted list
        else if(userinput.equals("Split")){
            List<Integer> bytwo = new ArrayList<>();
            for(int i = 0; i < n; i++){
                if  (nlist.get(i) > 0 && nlist.get(i) % 2 ==0){
                    bytwo.add(nlist.get(i));
                }
            }
            List<Integer> bythree = new ArrayList<>();
            for(int i = 0; i < n; i++){
                if  (nlist.get(i) > 0 && nlist.get(i) % 3 ==0){
                    bythree.add(nlist.get(i));
                }
            }
            List<Integer> byfive = new ArrayList<>();
            for(int i = 0; i < n; i++){
                if (nlist.get(i) > 0 && nlist.get(i) % 5 ==0){
                    byfive.add(nlist.get(i));
                }
            }
            System.out.println("The following number can be divided by 2: " + bytwo);
            System.out.println("The following number can be divided by 3: " + bythree);
            System.out.println("The following number can be divided by 5: " + byfive);
        }
//Sorted list
        else if(userinput.equals("Sort")){  
            int temp = 0;  
            for(int i=0; i < n; i++){  
                 for(int j=1; j < (n-i); j++){  
                    if(nlist.get(j-1) > nlist.get(j)){  
                    //swap elements  
                        temp = nlist.get(j-1);  
                        nlist.set(j-1, nlist.get(j));  
                        nlist.set(j, temp);  
                    }  
                }  
            }
            System.out.println("The list has been sorted: " + nlist);
        }
        else{
            System.out.println("Error, Invalid input!");
        }
        input.close();
    }
}