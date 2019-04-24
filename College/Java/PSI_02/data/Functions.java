package data;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Functions {
    private Random random = new Random();
    private int randomArgumentsAmount;

    public Functions( int randomArgumentsAmount) {
        this.randomArgumentsAmount = randomArgumentsAmount;
    }

    public Functions() {
        this.randomArgumentsAmount = -1;
    }

    public List<Double> randomArguments() {
        List<Double> resultArguments = new LinkedList<>();
        if (randomArgumentsAmount == -1)
            randomArgumentsAmount = 100;

        while (randomArgumentsAmount > 0) {
            double randomNumber = random.nextDouble() * 2.0 + 0.0;
            random = new Random();

            resultArguments.add(randomNumber);
            randomArgumentsAmount--;
        }
        return resultArguments;
    }

    public List<Double> collectValues(List<Double> inputrandomArgumentsCollection) {
        List<Double> returnValuesCollection = new LinkedList<>();
        for (double randomedNumber : inputrandomArgumentsCollection)
            returnValuesCollection.add(2.0 * randomedNumber + 0.5);
        return returnValuesCollection;
    }

    public List<Double> NN(List<Double> a, double inW, double inB) {
        List<Double> resultCollection = new LinkedList<>();
        for (double element : a)
            resultCollection.add((element * inW + inB));
        return resultCollection;
    }

    public double MSE(List<Double> a, List<Double> b) throws Exception {
        double sum = 0;
        if (a.size() != b.size())
            throw new Exception("collections size aren't equal");
        for (int index = 0; index < a.size(); index++)
            sum += (a.get(index) - b.get(index)) * (a.get(index) - b.get(index)) * 0.5;

        return ((double) sum / a.size());
    }

    public List<Double> relu(List<Double> a) {
        List<Double> result = new LinkedList<>();

        for (double element : a) {
            if (element > 0)
                result.add(element);
            else
                result.add(0.);
        }
        return result;
    }

    public List<Double> NN2(List<Double> inputrandomArgumentsCollection, double W1, double W2, double b1, double b2) {
        return this.NN(relu(this.NN(inputrandomArgumentsCollection, W1, b1)), W2, b2);
    }

    public double calculateDeltaW(double W1, List<Double> x, double b1, List<Double> y) {
        double deltaW = 0;
        int index = 0;
        while (index < x.size()) {
            deltaW += (W1 * x.get(index) + b1 - y.get(index)) * x.get(index);
            index++;
        }
        return deltaW / x.size();
    }

    public double calculateDeltaB(double W1, List<Double> x, double b1, List<Double> y) {
        double deltaB = 0;
        int index = 0;
        while (index < x.size()) {
            deltaB += W1 * x.get(index) + b1 - y.get(index);
            index++;
        }
        return deltaB / x.size();
    }

    public double changeCoeffecient(double element, double deltaElement, double E) {
        return element - E * deltaElement;
    }

    public void doSimulation(List<Double> x, List<Double> y, double W1, double B1, double E) throws Exception {
        double deltaW = calculateDeltaW(W1, x, B1, y);
        double deltaB = calculateDeltaB(W1, x, B1, y);
        List<Double> z = x;
        System.out.println("---------------SIMULATION---------------");
        for (int i = 0; i < 100; i++) {
            System.out.println(i + "\t" + MSE(NN(z, W1, B1), y));
            W1 = changeCoeffecient(W1, deltaW, E);
            B1 = changeCoeffecient(B1, deltaB, E);
            deltaW = calculateDeltaW(W1, x, B1, y);
            deltaB = calculateDeltaB(W1, x, B1, y);
        }

        for (int i = 0; i < 100; i++) {
            random = new Random();
            double N = random.nextDouble() * 0.2 - 0.1;
            double Y2 = 3 * x.get(i) * +4 + N;
            double Y3 = (x.get(i)) * (x.get(i));
            System.out.println("random number: \t" + N + " Y2: \t" + Y2 + " Y3: \t" + Y3);
        }
    }
}