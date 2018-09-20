package differentials;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import static java.lang.Math.*;

public class HeunController {

    @FXML
    private TextArea HeunResultArea;

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

    private double x0,b,y0,h,n;

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
    void calculatingHeun(ActionEvent event) {
        for(int i =0;i<n;i++){
            y0 = y0 + (h/2.0) * (wartoscFunkcji(x0,y0)+ wartoscFunkcji(x0+h,y0+h* wartoscFunkcji(x0,y0)));
            x0 +=h;
            HeunResultArea.appendText(x0+" "+y0+"\n");
        }
    }

    @FXML
    void sendingX0BY0H(ActionEvent event) {
        String[] argSplit = x0by0hArea.getText().split(",");
        h = Double.parseDouble(argSplit[3]);
        y0 = Double.parseDouble(argSplit[2]);
        b = Double.parseDouble(argSplit[1]);
        x0 = Double.parseDouble(argSplit[0]);
        n = (b-x0)/h;
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
                arguementsArray = null;

                for (int i = 0; i < ySplit.length; i++) {
                    valuesArray[i] = Double.parseDouble(ySplit[i]);
                }
            }

            else {
                if (argSplit[1].equals("-") && !xSplit[0].equals("-")) {
                    arguementsArray = new double[xSplit.length];
                    valuesArray = null;

                    for (int i = 0; i < xSplit.length; i++) {
                        arguementsArray[i] = Double.parseDouble(xSplit[i]);
                    }
                }
                else
                {
                    arguementsArray = new double[xSplit.length];
                    valuesArray = new double[ySplit.length];

                    for(int i = 0;i<ySplit.length;i++){
                        valuesArray[i]=Double.parseDouble(ySplit[i]);
                    }

                    for(int i = 0;i<xSplit.length;i++){
                        arguementsArray[i]=Double.parseDouble(xSplit[i]);
                    }

                }
            }
        }
    }
}
