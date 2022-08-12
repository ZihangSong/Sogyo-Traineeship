package QuoteOfTheDay;
import java.time.LocalDate;
import java.time.Month;

public class QuoteOfTheDay {
    static String[][] quotes = {
        {"galileo", "eppur si muove"},
        {"archimedes", "eureka!"},
        {"erasmus", "in regione caecorum rex est luscus"},
        {"socrates", "I know nothing except the fact of my ignorance"},
        {"rené descartes", "cogito, ergo sum"},
        {"sir isaac newton", "if I have seen further it is by standing on the shoulders of giants"}
    };
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        int dayofyearnow = now.getDayOfYear();
        Month month = now.getMonth();
        String currentmonth = month.name();
        int dayofmonth = now.getDayOfMonth();
        message(dayofmonth,currentmonth);
        uppercases();
        punctation();
        quotemark();
        quote(dayofyearnow);
    }
    static void message(int dayofmonth,String currentmonth){
        if (dayofmonth > 2){
            System.out.println("Quote \u001B[35mfor\u001B[30m " + dayofmonth + "th of " + 
            currentmonth.substring(0,1) + currentmonth.substring(1).toLowerCase() + ":");
        }else if(dayofmonth == 1){
            System.out.println("Quote \u001B[35mfor\u001B[30m " + dayofmonth + "st of " + 
            currentmonth.substring(0,1) + currentmonth.substring(1).toLowerCase() + ":");
        }else{
            System.out.println("Quote \u001B[35mfor\u001B[30m " + dayofmonth + "nd of " + 
            currentmonth.substring(0,1) + currentmonth.substring(1).toLowerCase() + ":");
        }
    }
    static void quote(int dayofyearnow){
        String fquote1 = "\u001B[32m" + quotes[0][1] + "\u001B[0m" +  " -- " + quotes[0][0];
        String fquote2 = "\u001B[32m" + quotes[1][1] + "\u001B[0m" + " -- " + quotes[1][0];
        String fquote3 = "\u001B[32m" + quotes[2][1] + "\u001B[0m" + " -- " + quotes[2][0];
        String fquote4 = "\u001B[32m" + quotes[3][1] + "\u001B[0m" + " -- " + quotes[3][0];
        String fquote5 = "\u001B[32m" + quotes[4][1] + "\u001B[0m" + " -- " + quotes[4][0];
        String fquote6 = "\u001B[32m" + quotes[5][1] + "\u001B[0m" + " -- " + quotes[5][0];
        String[] quotelist = {fquote1,fquote2,fquote3,fquote4,fquote5,fquote6};
        int quotenum = (dayofyearnow-1) % 6;
        System.out.println(quotelist[quotenum]);
    }
    static void uppercases(){
        int rows = 6;
        int columns = 2;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                quotes[i][j] = quotes[i][j].substring(0,1).toUpperCase() + quotes[i][j].substring(1);
            }
        }
    }
    static void punctation(){
        int rows = 6;
        for (int i = 0; i < rows; i++){
            if (quotes[i][1].contains(".") || quotes[i][1].contains("!" ) || quotes[i][1].contains("?" )){
            }else{
                quotes[i][1] = quotes[i][1] + ".";
            }
        }
    }
    static void quotemark(){
        int rows = 6;
        for (int i = 0; i < rows; i++){
            quotes[i][1] = "\"" + quotes[i][1] + "\"";
        }
    }
}