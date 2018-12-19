package mes.funkcje;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class GlobalData {
    private HashMap<String,String> fileData;
    private String path,fileName;
    int nH,nB;
    private double initialTemperature,simulationTime,simulationStepTime,environmentTemperature, alfa    ,   H   ,   B   ,   individualHeat,conductivity,density;
    private final String PATH = "C:\\AGH\\Portfolio\\Java - College\\MES2018\\src\\mes\\data\\";
    Scanner scanner;

    public GlobalData() {
        scanner = new Scanner(System.in);
        fileName = scanner.next();
        scanner.close();

        fileData = new HashMap<>();
        this.path = PATH+fileName;
        this.extractDataFromFile(path);
        this.setAllDataFromFIle();
    }

    private void extractDataFromFile(String inputPath){
        try {
            FileReader fileReader = new FileReader(inputPath);
            String splitter = "=";
            String line;
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine())!=null)
                this.fileData.put(line.split(splitter)[0],line.split(splitter)[1]);

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAllDataFromFIle(){
        initialTemperature = Double.parseDouble(fileData.get("initialTemp"));
        simulationTime = Double.parseDouble(fileData.get("simTime"));
        simulationStepTime = Double.parseDouble(fileData.get("simStepTime"));
        environmentTemperature= Double.parseDouble(fileData.get("envTemp"));
        alfa= Double.parseDouble(fileData.get("alfa"));
        B =Double.parseDouble(fileData.get("B"));
        H= Double.parseDouble(fileData.get("H"));
        nB =Integer.parseInt(fileData.get("NB"));
        nH =Integer.parseInt(fileData.get("NH"));
        individualHeat= Double.parseDouble(fileData.get("specHeat"));
        conductivity= Double.parseDouble(fileData.get("conductivity"));
        density= Double.parseDouble(fileData.get("density"));
    }

    public int getnH() {
        return nH;
    }

    public int getnB() {
        return nB;
    }

    public double getInitialTemperature() {
        return initialTemperature;
    }

    public double getEnvironmentTemperature() {
        return environmentTemperature;
    }

    public double getAlfa() {
        return alfa;
    }

    public double getH() {
        return H;
    }

    public double getB() {
        return B;
    }

    public double getConductivity() {
        return conductivity;
    }

    public double getSimulationTime() {
        return simulationTime;
    }

    public double getSimulationStepTime() {
        return simulationStepTime;
    }

    public double getIndividualHeat() {
        return individualHeat;
    }

    public double getDensity() {
        return density;
    }

}
