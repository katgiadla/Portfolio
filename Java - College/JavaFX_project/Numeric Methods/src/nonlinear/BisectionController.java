package nonlinear;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class BisectionController {

    @FXML
    private TextArea BisectionResultArea;

    @FXML
    private TextArea functionInputArea;

    @FXML
    private Button sendFunctionButton;

    @FXML
    private TextArea abdokladnoscArea;

    @FXML
    private Button sendPrzedzial;

    double [] functionArray;
    double a,b,dokladnosc;
    @FXML
    void sendFunctionString(ActionEvent event) {
        String[] argSplit = functionInputArea.getText().split("/");
        functionArray = new double[argSplit.length];
        for(int i = 0;i<argSplit.length;i++){
            functionArray[i]=Double.parseDouble(argSplit[i]);
        }
    }

    double wartoscFunkcji(double x) {
        double wynik = 0.0;
        for (int i = 0; i < functionArray.length; i++) {
            wynik += functionArray[i] * pow(x, i);
        }
        return wynik;
    }

    double oblicz() {
        double x1;

        do{
            x1 = (a + b) / 2;
            if (wartoscFunkcji(x1) == 0) {
                return x1;
            }
            if (wartoscFunkcji(x1)*wartoscFunkcji(a) < 0) {
                b = x1;
            }
            else if (wartoscFunkcji(x1)*wartoscFunkcji(b) < 0) {
                a = x1;
            }
        }while (abs(a - b) > dokladnosc);
        return (a + b) / 2;
    }


    @FXML
    void sendPrzedzialandAcurracy(ActionEvent event) {
        String[] argSplit = abdokladnoscArea.getText().split(",");
        a = Double.parseDouble(argSplit[0]);
        b = Double.parseDouble(argSplit[1]);
        dokladnosc = Double.parseDouble(argSplit[2]);

        if(wartoscFunkcji(a)*wartoscFunkcji(b)>0){
            BisectionResultArea.setText(" nie ma przeciecia odcietej ");
            return;
        }
        else{
            BisectionResultArea.setText(" Zero value: "+this.oblicz());
        }
    }

}
