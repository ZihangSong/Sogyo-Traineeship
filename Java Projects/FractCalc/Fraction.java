package FractCalc;

public class Fraction {
    int numerator;
    int denominator;
    double decimalnot;
    Fraction fraction;
    String string;
    
    public Fraction(int i,int j){
        this.numerator = i;
        this.denominator = j;
    }

    public double toDecimalNotation(){
        double num = (double)this.numerator;
        double denom = (double)this.denominator;
        this.decimalnot = num/denom;
        System.out.printf("%.5f %n", (float)this.decimalnot);
        return this.decimalnot;
    }

    public String toString(){
        this.string = String.valueOf(this.numerator) + "/" + String.valueOf(this.denominator);
        System.out.println("\"" + this.string + "\"");
        return this.string;
    }

    public Fraction add(int number){
        int newnum = number * this.numerator;
        Fraction addwholeresult = new Fraction(newnum, this.denominator); 
        printfract(addwholeresult);
        return addwholeresult.fraction;
    }

    public Fraction add(Fraction fraction){
        int secondnum = fraction.numerator;
        int seconddenom = fraction.denominator;
        int newnum = this.numerator * seconddenom + this.denominator * secondnum;
        int newdenom = this.denominator * seconddenom;
        int biggestdivider = findbiggestdivider(newnum, newdenom);
        newnum = newnum / biggestdivider;
        newdenom = newdenom / biggestdivider;
        Fraction addfractresult = new Fraction(newnum, newdenom); 
        printfract(addfractresult);
        return addfractresult.fraction;
    }

    public Fraction substract(int number){
        int tosubstract = this.denominator*number;
        int newnum = 0;
        if(this.numerator > tosubstract){
            newnum = this.numerator - tosubstract;
        }else{
            System.out.println("Error!, number is to big for substraction");
        }
        Fraction subwholeres = new Fraction(newnum, this.denominator);
        printfract(subwholeres);
        return(subwholeres);
    }

    public Fraction substract(Fraction fraction){
        int secondnum = fraction.numerator;
        int seconddenom = fraction.denominator;
        int newnum = this.numerator * seconddenom - secondnum * this.denominator;
        int newdenom = this.denominator * seconddenom;
        int biggestdivider = findbiggestdivider(newnum, newdenom);
        newnum = newnum/biggestdivider;
        newdenom = newdenom/biggestdivider;
        Fraction subfractres = new Fraction(newnum, newdenom);
        printfract(subfractres);
        return(subfractres);
    }

    public Fraction multiply(int number){
        int newnum = this.numerator * number;
        Fraction multres = new Fraction (newnum,this.denominator);
        printfract(multres);
        return(multres);
    }

    public Fraction multiply(Fraction fraction){
        int secondnum = fraction.numerator;
        int seconddenom = fraction.denominator;
        int newnum = this.numerator * secondnum + secondnum * this.denominator;
        int newdenom = this.denominator * seconddenom;
        int biggestdivider = findbiggestdivider(newnum, newdenom);
        newnum = newnum / biggestdivider;
        newdenom = newdenom / biggestdivider;
        Fraction multfractres = new Fraction(newnum, newdenom);
        printfract(multfractres);
        return multfractres;
    }


    public Fraction divide(int number){
        int newdenom = this.denominator * number;
        int biggestdivider = findbiggestdivider(this.numerator, newdenom);
        int newnum = this.numerator / biggestdivider;
        newdenom = newdenom / biggestdivider;
        Fraction divwholeres = new Fraction(newnum, newdenom); 
        printfract(divwholeres);
        return divwholeres.fraction;
    }

    public Fraction divide(Fraction fraction){
        int newnum = this.numerator * fraction.denominator;
        int newdenom = this.denominator * fraction.numerator;
        int biggestdivider = findbiggestdivider(newnum, newdenom);
        newnum = newnum / biggestdivider;
        newdenom = newdenom / biggestdivider;
        Fraction divfractres = new Fraction(newnum, newdenom); 
        printfract(divfractres);
        return divfractres.fraction;
    }
    
    public int findbiggestdivider(int newnum, int newdenom){
        if (newdenom == 0) {
            return newnum;
        }
        return findbiggestdivider(newdenom,newnum % newdenom);
    }

    public void printfract(Fraction fraction){
        fraction.string = String.valueOf(fraction.numerator) + "/" + String.valueOf(fraction.denominator);
        System.out.println(fraction.string);
    };

} 

