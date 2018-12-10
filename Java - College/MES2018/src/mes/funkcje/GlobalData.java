package mes.funkcje;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class GlobalData {
    private HashMap<String,String> fileData;
    private String path;
    int nH,nB;
    private double initialTemperature,simulationTime,simulationStepTime,environmentTemperature, alfa    ,   H   ,   B   ,   individualHeat,conductivity,density;
    //                  100*C             500 s         50s                 1200*C          300W/m^2K       0,1m    0,1m    700 J/kg*C      25 W/m*C    7800 kg/m^3

    public GlobalData(String path) {
        fileData = new HashMap<>();
        this.path = path;
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
        initialTemperature = Integer.parseInt(fileData.get("initialTemp"));
        simulationTime = Integer.parseInt(fileData.get("simTime"));
        simulationStepTime = Integer.parseInt(fileData.get("simStepTime"));
        environmentTemperature= Integer.parseInt(fileData.get("envTemp"));
        alfa= Integer.parseInt(fileData.get("alfa"));
        B =Integer.parseInt(fileData.get("B"));
        H= Integer.parseInt(fileData.get("H"));
        nB =Integer.parseInt(fileData.get("NB"));
        nH =Integer.parseInt(fileData.get("NH"));
        individualHeat= Integer.parseInt(fileData.get("specHeat"));
        conductivity= Integer.parseInt(fileData.get("conductivity"));
        density= Integer.parseInt(fileData.get("density"));
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


    public void setnH(int nH) {
        this.nH = nH;
    }

    public void setnB(int nB) {
        this.nB = nB;
    }

    public void setInitialTemperature(double initialTemperature) {
        this.initialTemperature = initialTemperature;
    }

    public void setSimulationTime(double simulationTime) {
        this.simulationTime = simulationTime;
    }

    public void setSimulationStepTime(double simulationStepTime) {
        this.simulationStepTime = simulationStepTime;
    }

    public void setEnvironmentTemperature(double environmentTemperature) {
        this.environmentTemperature = environmentTemperature;
    }

    public void setAlfa(double alfa) {
        this.alfa = alfa;
    }

    public void setH(double h) {
        H = h;
    }

    public void setB(double b) {
        B = b;
    }

    public void setIndividualHeat(double individualHeat) {
        this.individualHeat = individualHeat;
    }

    public void setConductivity(double conductivity) {
        this.conductivity = conductivity;
    }

    public void setDensity(double density) {
        this.density = density;
    }
}
