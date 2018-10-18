package program;
import data.Grid;

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
        mainApp.loadFile("C:\\Users\\MTchorek\\IdeaProjects\\MES_01\\src\\data\\dane.txt");
        Grid grid = new Grid(Integer.parseInt(mainApp.getFileData().get("L")),Integer.parseInt(mainApp.getFileData().get("H")),Integer.parseInt(mainApp.getFileData().get("NL")),Integer.parseInt(mainApp.getFileData().get("NH")));
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