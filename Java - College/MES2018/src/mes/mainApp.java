package mes;

import mes.funkcje.*;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class mainApp {
    private HashMap<String,String> fileData;

    public HashMap<String, String> getFileData() {
        return fileData;
    }

    public static void main(String[] args){
        mainApp mainApp = new mainApp();
        mainApp.loadFile("C:\\AGH\\Portfolio\\Java - College\\MES2018\\src\\mes\\data\\dane.txt");
        GlobalData globalData = new GlobalData("C:\\AGH\\Portfolio\\Java - College\\MES2018\\src\\mes\\data\\dane.txt");
        //mainApp.loadFile("C:\\AGH\\Portfolio\\Java - College\\MES2018\\src\\mes\\data\\dane.txt");
        Grid grid = new Grid(Integer.parseInt(mainApp.getFileData().get("L")),Integer.parseInt(mainApp.getFileData().get("H")),Integer.parseInt(mainApp.getFileData().get("NL")),Integer.parseInt(mainApp.getFileData().get("NH")));

        Jacobian jakobian_2d = new Jacobian(grid.getElementsCollection());
        MatrixH matrixH = new MatrixH(grid.getElementsCollection(),jakobian_2d.getReversedDetJ(),jakobian_2d.getDetJ(),
                jakobian_2d.getDeltaNdeltaKsiArray(),jakobian_2d.getDeltaNdeltaEtaArray(),jakobian_2d.getJakobian());
        MatrixC matrixC = new MatrixC(matrixH.getDetJ(),matrixH.getJacobian(),matrixH.getReversedJacobian(),jakobian_2d.getN1(),jakobian_2d.getN2(),jakobian_2d.getN3(),jakobian_2d.getN4());
    }

    public mainApp() {
        this.fileData = new HashMap<>();
    }

    public void loadFile(String path){
        try {
            FileReader fileReader = new FileReader(path);
            String splitter = "=";
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine())!=null)
                this.fileData.put(line.split(splitter)[0],line.split(splitter)[1]);

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}