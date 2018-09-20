package differentials;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import static java.lang.Math.*;

public class Rk4Controller {

    @FXML
    private TextArea Rk4ResultArea;

    @FXML
    private TextArea x0by0hArea;

    @FXML
    private Button x0y0bhButton;

    @FXML
    private Button fxySend;

    @FXML
    private TextArea xyArea;

    @FXML
    private Button calculateButton;

    private double valuesArray[];
    private double arguementsArray[];

    private double x0,xk,y0,h,n;

    double wartoscFunkcji(double x,double y) {

        double wynik = 0.0;

        if(valuesArray != null) {
            for (int i = 0; i < valuesArray.length; i++) {
                wynik += valuesArray[i] * pow(y, i);
            }
        }

        if (arguementsArray != null) {
            for (int i = 0; i < arguementsArray.length; i++) {
                wynik += arguementsArray[i] * pow(x, i);
            }
        }

        return wynik;
    }

    @FXML
    void calculatingRk4(ActionEvent event) {
        for(int i =0;i<n;i++) {
            double k1 = h*wartoscFunkcji(x0,y0);
            double k2 = h*wartoscFunkcji(x0+0.5*h,y0+0.5*k1);
            double k3 = h*wartoscFunkcji(x0+0.5*h,y0+0.5*k2);
            double k4 = h*wartoscFunkcji(x0+h,y0+k3);

            y0 = y0 + (k1+2*k2+2*k3+k4)/6;
            System.out.println(x0+ " "+y0);
            x0 = x0+h;

            Rk4ResultArea.appendText(x0+" "+y0+"\n");
        }


    }

    @FXML
    void sendingX0XKY0H(ActionEvent event) {
        String[] argSplit = x0by0hArea.getText().split(",");
        y0 = Double.parseDouble(argSplit[2]);
        xk = Double.parseDouble(argSplit[1]);
        h = Double.parseDouble(argSplit[3]);
        x0 = Double.parseDouble(argSplit[0]);
        n = (xk-x0)/h;
    }

    @FXML
    void sendingXYString(ActionEvent event) {
        String[] argSplit = xyArea.getText().split("/");
        String[] xSplit = argSplit[0].split(","); //argsplit 0 x, 1 to y
        String[] ySplit = argSplit[1].split(",");

        if(argSplit[0].equals("-") && argSplit[1].equals("-") ){
            xyArea.setText("x and y null");
            return;
        }

        else {
            if (argSplit[0].equals("-") && !argSplit[1].equals("-")) {
                valuesArray = new double[ySplit.length];

                for (int i = 0; i < ySplit.length; i++) {
                    valuesArray[i] = Double.parseDouble(ySplit[i]);
                }

                arguementsArray = null;
            }

            else {
                if (argSplit[1].equals("-") && !xSplit[0].equals("-")) {
                    arguementsArray = new double[xSplit.length];

                    for (int i = 0; i < xSplit.length; i++) {
                        arguementsArray[i] = Double.parseDouble(xSplit[i]);
                    }

                    valuesArray = null;
                }
                else
                {
                    valuesArray = new double[ySplit.length];

                    for(int i = 0;i<ySplit.length;i++){
                        valuesArray[i]=Double.parseDouble(ySplit[i]);
                    }

                    arguementsArray = new double[xSplit.length];

                    for(int i = 0;i<xSplit.length;i++){
                        arguementsArray[i]=Double.parseDouble(xSplit[i]);
                    }

                }
            }
        }

    }



}
