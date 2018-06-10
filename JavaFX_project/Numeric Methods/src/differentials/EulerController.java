package differentials;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import static java.lang.Math.pow;

public class EulerController {

    @FXML
    private TextArea EulerResultArea;

    @FXML
    private TextArea x0by0hArea;

    @FXML
    private Button x0y0bhButton;

    @FXML
    private Button fxySend;

    @FXML
    private TextArea xyArea;


/*double []funkcja = {0,1,0,0};
    int sizeFunkcja;
    double []pochodna;
    int sizePochodna;
    double x0,b,;
    double b = 3;
    double y0=1;
    double h = 0.01 ;
    double N = (b-x0)/h;
  */
    private double valuesArray[];
    private double arguementsArray[];

    private double x0,b,y0,h,n;

    @FXML
    void sendingX0BY0H(ActionEvent event) {
        String[] argSplit = x0by0hArea.getText().split(",");
        x0 = Double.parseDouble(argSplit[0]);
        b = Double.parseDouble(argSplit[1]);
        y0 = Double.parseDouble(argSplit[2]);
        h = Double.parseDouble(argSplit[3]);
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
                arguementsArray = null;
                valuesArray = new double[ySplit.length];

                for (int i = 0; i < ySplit.length; i++) {
                    valuesArray[i] = Double.parseDouble(ySplit[i]);
                }
            }

            else {
                if (argSplit[1].equals("-") && !xSplit[0].equals("-")) {
                    valuesArray = null;
                    arguementsArray = new double[xSplit.length];
                    for (int i = 0; i < xSplit.length; i++) {
                        arguementsArray[i] = Double.parseDouble(xSplit[i]);
                    }
                }
                else
                {
                    valuesArray = new double[ySplit.length];
                    arguementsArray = new double[xSplit.length];


                    for(int i = 0;i<xSplit.length;i++){
                        arguementsArray[i]=Double.parseDouble(xSplit[i]);
                    }
                    for(int i = 0;i<ySplit.length;i++){
                        valuesArray[i]=Double.parseDouble(ySplit[i]);
                    }
                }
            }
        }
    }

    double wartoscFunkcji(double x,double y) {

        double wynik = 0.0;
        if (arguementsArray != null) {
            for (int i = 0; i < arguementsArray.length; i++) {
                wynik += arguementsArray[i] * pow(x, i);
            }
        }
        if(valuesArray != null) {
            for (int i = 0; i < valuesArray.length; i++) {
                wynik += valuesArray[i] * pow(y, i);
            }
        }
        return wynik;
    }



    @FXML
    private Button calculateButton;

    @FXML
    void calculatingEulerIrow(ActionEvent event) {
        for( int i =0;i<n;i++){
            y0 = y0+h*wartoscFunkcji(x0,y0);
            x0 += h;

            EulerResultArea.appendText(x0+" "+y0+"\n");
        }


    }

}
