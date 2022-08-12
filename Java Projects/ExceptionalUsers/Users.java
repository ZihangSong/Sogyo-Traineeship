package ExceptionalUsers;

import java.util.ArrayList;
import java.util.Scanner;

public class Users {
    String username;
    String password;
    public static ArrayList<Users> registeredusers = new ArrayList<>();

    public static void main(String[] args){
        registration();
    }

    public static void registration(){
        String initiateprocess = "Enter a username:";
        String askpassword = "Enter a password:";
        System.out.println(initiateprocess);
        Scanner input = new Scanner(System.in);
        String userinput = input.nextLine();
        Users newuser = new Users();
        newuser.username = userinput;
        System.out.println(askpassword);
        boolean registrated = false;
        while(!registrated){  
            userinput = input.nextLine();
            try {
                boolean check = passreqcheck(userinput);
                if(check){ 
                    String registrationcomplete = "Registered user " + newuser.username + ".";
                    System.out.println(registrationcomplete);
                    registrated = true;
                };
            } catch (Passwordexception e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter a stronger password!");
            }
        }
        input.close();
    }

    public static boolean passreqcheck(String tocheck) throws Passwordexception{
        boolean containsupp = false;
        boolean containslow = false;
        boolean containsnum = false;
        char[] password = tocheck.toCharArray();
        for (char c : password){
            if (Character.isDigit(c)){
                containsnum = true;
            }
            if (Character.isUpperCase(c)){
                containsupp = true;
            }
            if (Character.isLowerCase(c)){
                containslow = true;
            }
        }
        if (!containsupp){
            throw new Passwordexception("Password does not countain an uppercase letter!");
        }else if (!containslow){
            throw new Passwordexception("Password does not contain a lowercase letter!");
        }else if (!containsnum){
            throw new Passwordexception("Password does not contain a number!");
        }else{
            return true;
        }
    }


}
