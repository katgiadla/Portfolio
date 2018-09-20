package Interpolations;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class LagrangeController {

    private double [] argArray;
    private double [] valArray ;
    private double resultArg = 0;

    @FXML
    private Button argButtn;

    @FXML
    private Button valButton;

    @FXML
    private TextArea argArea;

    @FXML
    private TextArea valArea;

    @FXML
    private TextArea resultArea;

    @FXML
    private Button calculateButton;

    @FXML
    private TextArea inputResultArguement;

    @FXML
    void enterArguments(ActionEvent event) {
        String[] argSplit = argArea.getText().split("/");
        argArray = new double[argSplit.length];
        for(int i = 0;i<argSplit.length;i++){
            argArray[i]=Double.parseDouble(argSplit[i]);
        }
    }

    double inputResultArgNumb = -99999999;
    @FXML
    void enterResultArg(ActionEvent event) {
        resultArea.setEditable(false);
        try {
            inputResultArgNumb = Double.parseDouble(inputResultArguement.getText());
        }
        catch (NumberFormatException number){
            inputResultArguement.setText("error!");

        }

        double result = 0;
        if(argArray.length!=valArray.length || inputResultArgNumb ==-99999999){
            valArea.setText("ilosc argumentow "+Integer.toString(valArray.length));
            argArea.setText("ilosc argumentow "+Integer.toString(argArray.length));
            inputResultArguement.setText(Double.toString(inputResultArgNumb));
            return;
        }
        else{
            for(int i =0;i<valArray.length;i++){
                double tmp = 1.0;
                for(int j = 0;j<valArray.length;j++){
                    if(i!=j){
                        tmp=tmp*((inputResultArgNumb-argArray[j])/(argArray[i]-argArray[j]));
                    }
                }
                result+=tmp*valArray[i];
            }

        }

        resultArea.setText("Point: "+inputResultArgNumb +" value: "+result);
    }

    @FXML
    void enterValues(ActionEvent event) {
        String[] argSplit = valArea.getText().split("/");
        valArray = new double[argSplit.length];
        for(int i = 0;i<argSplit.length;i++){
            valArray[i]=Double.parseDouble(argSplit[i]);
        }
    }

}
