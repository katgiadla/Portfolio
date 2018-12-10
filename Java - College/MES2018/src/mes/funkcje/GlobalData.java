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
        this.path = path;
        this.extractDataFromFile(path);
        //this.setAllDataFromFIle();
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

    }
}
