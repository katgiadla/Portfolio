package data;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Functions {
    private Random random = new Random();
    private static final int AMOUNT = 100 ;
    private int randomArgumentsAmount;

    private List<Double> valuesCollection;
    private List<Double> randomArgumentsCollection;

    public Functions(Random randomArgument, int randomArgumentsAmount, List<Double> valuesCollection, List<Double> randomArgumentsCollection) {
        this.randomArgumentsAmount = randomArgumentsAmount;
        this.randomArgumentsCollection = randomArgumentsCollection;
        this.valuesCollection = valuesCollection;
    }

    public Functions() {
        this.randomArgumentsAmount = -1;
        this.valuesCollection = new LinkedList<>();
        this.randomArgumentsCollection = new LinkedList<>();

    }

    public List<Double> randomArguments(){
        List<Double> resultArguments = new LinkedList<>();
        if(randomArgumentsAmount == -1)
            randomArgumentsAmount = 100;

        while(randomArgumentsAmount>0){
            random = new Random();
            double randomNumber = random.nextDouble()*2.0 +0.0;

            resultArguments.add(randomNumber);
            randomArgumentsAmount--;
        }
        return resultArguments;
    }

    public List<Double> collectValues(List<Double> inputrandomArgumentsCollection){
        List<Double> returnValuesCollection = new LinkedList<>();
        for(double randomedNumber: inputrandomArgumentsCollection)
            returnValuesCollection.add(2.0 * randomedNumber + 0.5);
        return returnValuesCollection;
    }

    private void MES2(){ }

    public List<Double> NN(List<Double> a, double inW, double inB){
        List<Double> resultCollection = new LinkedList<>();
        for(double element: a) {
            resultCollection.add((element * inW + inB));
        }
        return resultCollection;
    }

    public double MSE(List<Double> a, List<Double> b) throws Exception {
        double sum = 0;
        if(a.size()!=b.size())
            throw new Exception("collections size aren't equal");
        for(int index = 0;index<a.size() ;index++)
            sum+=(a.get(index)-b.get(index))*(a.get(index)-b.get(index)) ;

        return ((double)sum/a.size());
    }

    public List<Double> relu(List<Double> a){
        List<Double> result = new LinkedList<>();

        for(double element: a){
            if(element>0)
                result.add(element);
            else
                result.add(0.);
        }
        return result;
    }

    public List<Double> NN2(List<Double> inputrandomArgumentsCollection, double W1, double W2, double b1, double b2){
        return this.NN(relu(this.NN(inputrandomArgumentsCollection,W1,b1)),W2,b2);
    }
}