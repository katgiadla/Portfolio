package Main;

import java.lang.IllegalArgumentException;
public class BasicCalculator {
    public double calculateSum(double a, double b){return a+b;}
    public double calculateDiffirence(double a, double b) {return a-b;}
    public double calculateMultiplication(double a, double b){return a*b;}
    public double calculatePow(double a, double b){
        if(a==0 && b ==0)throw new IllegalArgumentException("symbol nieoznaczony");
        if(a<0 && (b<1 && b > 0)) throw new IllegalArgumentException ("negative square");
        return Math.pow(a,b);}
    public double calculateDivision(double a, double b) {
        if(b==0) throw new IllegalArgumentException("divide by 0");
        else return a/b;
    }
    public double calculateSqrt(double a){
        if(a<0)throw new IllegalArgumentException("negative value of sqrt");
        return Math.sqrt(a);
    }
}
