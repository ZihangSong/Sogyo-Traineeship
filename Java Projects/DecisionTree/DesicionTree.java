package DecisionTree;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;



public class DesicionTree {
    public static ArrayList<String> treedata = new ArrayList<String>();
    public static ArrayList<Node> nodes = new ArrayList<>();
    public static ArrayList<Edge> edges = new ArrayList<>();
    public static ArrayList<String> allnodes = new ArrayList<>();
    public static ArrayList<String> allnodeswithmessage = new ArrayList<>();
    public static ArrayList<String> finalnodes = new ArrayList<>();
    public static int filelines;
    public static String beginnode;
    public static String currentnode;

    public static void main(String[] args){
        readfile("decision-tree-data.txt");
        splitdata();
        findbeginnode();
        findfinalnodes();
        printbegin();
        playloop();
    }

    public static void readfile(String filename){
        String currentline;
        filelines = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            int i = 0;
            while ((currentline = br.readLine()) != null){
                    treedata.add(i, currentline);
                    i = i + 1;
                    filelines = filelines + 1;
            }
        }catch (IOException e){
                e.printStackTrace();          
       }
    }

    public static void splitdata(){
        for (int i = 0; i < filelines; i++){
            String singlelines = treedata.get(i);
            String[] splitlines = singlelines.split(", ");
            if (splitlines.length == 2){
                    Node newnode = new Node();
                    newnode.identity = splitlines[0]; 
                    newnode.message = splitlines[1];
                    nodes.add(newnode);
                    allnodes.add(splitlines[0]);
                    allnodeswithmessage.add(singlelines);
            }else if(splitlines.length == 3){
                    Edge newedge = new Edge();
                    newedge.begin = splitlines[0];
                    newedge.destination = splitlines[1];
                    newedge.message = splitlines[2];
                    edges.add(newedge);
            }else{
                System.out.print("Error in splitting data!");
            }
        }
    }
    
    public static void findbeginnode(){
        for (int i = 0; i < edges.size(); i++){
            Edge edge = edges.get(i);
            String edgedestinations = edge.destination;
            allnodes.remove(edgedestinations);
        }
        beginnode = allnodes.get(0);
    } 

    public static void printbegin(){
        for (int i = 0; i < nodes.size(); i++){
            Node node =  nodes.get(i);
            String tempnode = node.identity;
            String message = node.message;
            if (tempnode.equals(beginnode)){
                System.out.println(message);
            }
        }
    }

    public static void printmessage(String destnode){
        for (int i = 0; i < nodes.size(); i++){
            Node node =  nodes.get(i);
            String tempnode = node.identity;
            String message = node.message;
            if (tempnode.equals(destnode)){
                System.out.println(message);
            }
        }
    }

    public static void playloop(){
        System.out.println("Antwoord met Ja of Nee:");
        try (Scanner input = new Scanner(System.in)) {
            int cyclelife = 1;
            currentnode = beginnode;
            while(cyclelife != 0){    
                String userinput = input.nextLine();
                userinput = userinput.toLowerCase();
                userinput = userinput.substring(0,1).toUpperCase() + userinput.substring(1);
                for (int i = 0; i < edges.size(); i++){
                    Edge currentedge = edges.get(i);
                    if (userinput.equals(currentedge.message) && currentnode.equals(currentedge.begin)){
                        printmessage(currentedge.destination);
                        currentnode = currentedge.destination;
                        break;
                    }
                }
                if (checkfinal(currentnode)){
                    cyclelife = 0;
                    
                }
            }
        }
    }

    public static void findfinalnodes(){
        ArrayList<String> alldestinations = new ArrayList<>();
        for (int j = 0; j < edges.size(); j++){ 
            Edge tempedge = edges.get(j);    
            alldestinations.add(tempedge.begin);           
            }
        for (int i = 0; i < nodes.size(); i++){
            Node tempnode = nodes.get(i);
            if (!alldestinations.contains(tempnode.identity)){
                finalnodes.add(tempnode.identity);
            }             
        }
    }

    public static boolean checkfinal(String node){
        boolean check = false;
        for (int i = 0; i < finalnodes.size(); i++){
            if (finalnodes.contains(node)){
                check = true;
                break;
            }else{
                check = false;
            }
        }
        return check;
    }
}