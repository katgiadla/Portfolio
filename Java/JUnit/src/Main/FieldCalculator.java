package Main;

import java.lang.IllegalArgumentException;

public class FieldCalculator {
    public double calculateSquare(double a){
        if(a<0)throw new IllegalArgumentException("field negative");
        else return Math.pow(a,2);
    }
    public double calculateCircle(double r){
        if(r<0) throw new IllegalArgumentException("field negative");
        else return Math.pow(r,2)*Math.PI;
    }
    public double calculateTriangle(double a, double h){
        if(a<0 || h<0) throw new IllegalArgumentException("field negative");
        else return (a*h)/2.0;
    }
    public double calculateRectangle(double a, double b){
        if(a<0 || b<0) throw new IllegalArgumentException("field negative");
        else return a*b;
    }
}
