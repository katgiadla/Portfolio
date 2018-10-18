package programs;

import data.Functions;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainApp {


    public static void main (String [] args) throws Exception {
        Functions functions = new Functions();
         Random random = new Random();

        double W1, b1, W2, b2;
        List<Double> valuesCollection = new LinkedList<>();
        List<Double> randomArgumentsCollection = new LinkedList<>();

        randomArgumentsCollection = functions.randomArguments();

        valuesCollection = functions.collectValues(randomArgumentsCollection);

        W1 = random.nextDouble()*6.0-3.0;
        b1 = random.nextDouble()*6.0-3.0;

        W2 = random.nextDouble()*6.0-3.0;
        b2 = random.nextDouble()*6.0-3.0;
        List<Double> z = (List<Double>) functions.NN(randomArgumentsCollection,W1,b1);

        double mse = functions.MSE(valuesCollection,z);
        System.out.println(mse);

        List<Double> z2 = functions.NN(randomArgumentsCollection,2,0.5);
        double mse2 = functions.MSE(valuesCollection,z2);

        System.out.println(mse2);

        List<Double> z3 = functions.NN2(randomArgumentsCollection,W1,W2,b1,b2);
        double mse3 = functions.MSE(valuesCollection,z3);
        System.out.println(mse3);
    }
}
