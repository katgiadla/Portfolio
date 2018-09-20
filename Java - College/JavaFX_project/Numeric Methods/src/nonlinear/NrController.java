package nonlinear;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class NrController {

    @FXML
    private TextArea NewtonRaphsonResult;

    @FXML
    private TextArea functionArea;

    @FXML
    private TextArea pochodnaArea;

    @FXML
    private Button functionSend;

    @FXML
    private Button pochodnaSend;

    @FXML
    private TextArea abdokladnoscInput;

    @FXML
    private Button abdokladnoscSend;


    double a,b,dokladnosc;
    double [] functionArray;
    double [] pochodnaArray;

    double wartoscFunkcji(double x) {
        double wynik = 0.0;
        for (int i = 0; i < functionArray.length; i++) {
            wynik += functionArray[i] * pow(x, i);
        }
        return wynik;
    }

    double wartoscPochodna(double x) {
        double wynik = 0.0;
        for (int i = 0; i < pochodnaArray.length; i++) {
            wynik += pochodnaArray[i] * pow(x, i);
        }
        return wynik;
    }

    double oblicz() {
        double x1 = a;
        double x2 = b;
        do {
            x1 = x2;
            x2 = x1 - wartoscFunkcji(x1)/wartoscPochodna(x1);
            System.out.println(x2);
            if (abs(x2 - x1) <= dokladnosc)
                break;
            if (abs(wartoscFunkcji(x1)) <= dokladnosc)
                break;
        } while (true);
        return x2;
    }


    @FXML
    void abDokladnoscSending(ActionEvent event) {
        String[] argSplit = abdokladnoscInput.getText().split(",");
        a = Double.parseDouble(argSplit[0]);
        b = Double.parseDouble(argSplit[1]);
        dokladnosc = Double.parseDouble(argSplit[2]);

        if(wartoscFunkcji(a)*wartoscFunkcji(b)>0){
            NewtonRaphsonResult.setText(" nie ma przeciecia odcietej ");
            return;
        }
        else{
            NewtonRaphsonResult.setText("Nr Zero value: "+this.oblicz());
        }
    }

    @FXML
    void functionSending(ActionEvent event) {
        String[] argSplit = functionArea.getText().split("/");
        functionArray = new double[argSplit.length];
        for(int i = 0;i<argSplit.length;i++){
            functionArray[i]=Double.parseDouble(argSplit[i]);
        }
    }

    @FXML
    void pochodnaSending(ActionEvent event) {
        String[] argSplit = pochodnaArea.getText().split("/");
        pochodnaArray = new double[argSplit.length];
        for(int i = 0;i<argSplit.length;i++){
            pochodnaArray[i]=Double.parseDouble(argSplit[i]);
        }
    }

}
