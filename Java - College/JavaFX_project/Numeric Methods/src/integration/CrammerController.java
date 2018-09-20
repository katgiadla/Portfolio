package integration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CrammerController {


    @FXML
    private TextField frirstTxt;

    @FXML
    private TextField secondTxt;

    @FXML
    private TextField thirdTxt;

    @FXML
    private Button reultBtn;

    @FXML
    private TextArea resultTxt;

    double xTab[] = new double[3], yTab[] = new double[3], zTab[] = new double[3], resTab[] = new double[3];

    @FXML
    void showResult(ActionEvent event) {
        cramer();
    }

    private void initialization(){
        String tmp = frirstTxt.getText();
        String firstParts[] = tmp.split(",");
        tmp = secondTxt.getText();
        String secondParts[] = tmp.split(",");
        tmp  = thirdTxt.getText();
        String thirdParts[] = tmp.split(",");

        xTab[0] = Double.parseDouble(firstParts[0]);
        xTab[1] = Double.parseDouble(secondParts[0]);
        xTab[2] = Double.parseDouble(thirdParts[0]);

        yTab[0] = Double.parseDouble(firstParts[1]);
        yTab[1] = Double.parseDouble(secondParts[1]);
        yTab[2] = Double.parseDouble(thirdParts[1]);

        zTab[0] = Double.parseDouble(firstParts[2]);
        zTab[1] = Double.parseDouble(secondParts[2]);
        zTab[2] = Double.parseDouble(thirdParts[2]);

        resTab[0] = Double.parseDouble(firstParts[3]);
        resTab[1] = Double.parseDouble(secondParts[3]);
        resTab[2] = Double.parseDouble(thirdParts[3]);


    }

    private double [][] fulfilment(String wsp){

        if(wsp == "x"){
            double mtxX[][] = {
                {resTab[0], yTab[0], zTab[0]},
                {resTab[1], yTab[1], zTab[1]},
                {resTab[2], yTab[2], zTab[2]},
            };
            return mtxX;
        }
        else if(wsp == "y"){
            double mtxY[][] = {
                    {xTab[0],resTab[0],zTab[0]},
                    {xTab[1],resTab[1],zTab[1]},
                    {xTab[2],resTab[2],zTab[2]},
            };
            return mtxY;
        }
        else if(wsp == "z"){
            double mtxZ[][] = {
                    {xTab[0], yTab[0],resTab[0]},
                    {xTab[1], yTab[1],resTab[1]},
                    {xTab[2], yTab[2],resTab[2]},
            };
            return mtxZ;
        }
        else if(wsp == "d"){
            double mtx[][] = {
                    {xTab[0], yTab[0], zTab[0]},
                    {xTab[1], yTab[1], zTab[1]},
                    {xTab[2], yTab[2], zTab[2]},
            };
            return mtx;
        }

        double mtx[][] = {
                {0,0,0},
                {0,0,0},
                {0,0,0},
        };
        return mtx;

    }


    private void cramer() {
        double matrix[][] = new double[3][3];
        initialization();

        double wspx, wspy, wspz, wsp;
        matrix = fulfilment("d");
        wsp = MatrixOperations.matrixDeterminant(matrix);
        matrix = fulfilment("x");
        wspx = MatrixOperations.matrixDeterminant(matrix);
        matrix = fulfilment("y");
        wspy = MatrixOperations.matrixDeterminant(matrix);
        matrix = fulfilment("z");
        wspz = MatrixOperations.matrixDeterminant(matrix);

        if (wsp == 0 && wspx == 0 && wspy == 0 && wspz == 0) {
            resultTxt.setText("Układ  nieoznaczony");
        }
        else if(wsp == 0 && (wspx == 0 || wspy == 0 || wspz == 0)){
            resultTxt.setText("Układ sprzeczny");
        }
        else {
            double resX = 0, resY = 0, resZ = 0;
            resX = (wspx/wsp);
            resY = (wspy/wsp);
            resZ = (wspz/wsp);
            resultTxt.setText("Rozwiązania to: x = " + resX + ", y = " + resY + ", z = " + resZ + ".");
        }
    }
}
