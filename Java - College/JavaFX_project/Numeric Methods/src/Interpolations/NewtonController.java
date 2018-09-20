package Interpolations;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class NewtonController {

    @FXML
    private TextArea newtonResultArea;

    @FXML
    private Button newtonArgButton;

    @FXML
    private Button newtonCalcButton;

    @FXML
    private Button valCalcButton;

    @FXML
    private TextArea newArgArea;

    @FXML
    private TextArea newInputArea;

    @FXML
    private TextArea newValArea;

    private double [] argArray;
    private double [] valArray ;
    private double resultArg = 0;
    double inputResultArgNumb = -99999999;
    private double [][] newtonArray;

    @FXML
    void calcResult(ActionEvent event) {
        try {
            inputResultArgNumb = Double.parseDouble(newInputArea.getText());
        }
        catch (NumberFormatException number){
            newInputArea.setText("error!");

        }

        double result = 0;
        if(argArray.length!=valArray.length || inputResultArgNumb ==-99999999){
            newValArea.setText("ilosc argumentow "+Integer.toString(valArray.length));
            newArgArea.setText("ilosc argumentow "+Integer.toString(argArray.length));
            newInputArea.setText(Double.toString(inputResultArgNumb));
            return;
        }
        else{
            newtonArray = new double[argArray.length+1][];
            newtonArray[0] = argArray; // ARGUEMENTS
            newtonArray[1]= valArray; //VALUES



            for(int i =2;i<argArray.length+1;i++){
                newtonArray[i] = new double[argArray.length-i+1];
                for(int rowNumber = 0; rowNumber<argArray.length-i+1;rowNumber++){
                    newtonArray[i][rowNumber]=((newtonArray[i-1][rowNumber+1] - newtonArray[i-1][rowNumber])/(newtonArray[0][rowNumber+i-1]-newtonArray[0][rowNumber]));
                }
            }

            //inputResultArgNumb


            for(int indexColumn = 1;indexColumn <argArray.length+1;indexColumn++){
                double multipliedValue = 1;
                for(int multiplication =0;multiplication<indexColumn-1;multiplication++){
                    multipliedValue *=(inputResultArgNumb-newtonArray[0][multiplication]);
                }
                multipliedValue *= newtonArray[indexColumn][0];
                result+=multipliedValue;
            }


        }

        newtonResultArea.setText("Value  : "+inputResultArgNumb +" is "+result);


    }

    @FXML
    void inpArgToArray(ActionEvent event) {
        String[] argSplit = newArgArea.getText().split("/");
        argArray = new double[argSplit.length];
        for(int i = 0;i<argSplit.length;i++){
            argArray[i]=Double.parseDouble(argSplit[i]);
        }
    }

    @FXML
    void inpValToArray(ActionEvent event) {
        String[] argSplit = newValArea.getText().split("/");
        valArray = new double[argSplit.length];
        for(int i = 0;i<argSplit.length;i++){
            valArray[i]=Double.parseDouble(argSplit[i]);
        }
    }

}
