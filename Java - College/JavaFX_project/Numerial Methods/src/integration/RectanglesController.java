package integration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RectanglesController {

    @FXML
    private TextArea resultTxt;

    @FXML
    private TextField funkctionTxt;

    @FXML
    private TextField nTxt;

    @FXML
    private TextField xpTxt;

    @FXML
    private TextField xkTxt;

    @FXML
    void wynikButton(ActionEvent event) {
        resultTxt.setText(prostokaty());
    }

    private double function(double x){
        String fun = funkctionTxt.getText();
        String parts[] = fun.split(",");
        double result = 0;
        double tmp = 0;
        double cos = parts.length-1;
        int coss = parts.length-1;
        double zero = Double.parseDouble(parts[coss]);
        for(int i = 0; (i<parts.length-1); i++)
        {
            tmp = Double.parseDouble(parts[i]);
            result+=tmp*(Math.pow(x,cos));
            System.out.println(parts[i]);
            cos--;
        }
        return result + zero;
    }

    private String prostokaty(){
        double xk = Double.parseDouble(xkTxt.getText());
        double xp = Double.parseDouble(xpTxt.getText());
        double n = Double.parseDouble(nTxt.getText());
        double dx, calka = 0;

        dx = (xk-xp)/(double)n;

        for (int i = 1; i <= n; i++)
        {
            calka += function(xk);
            xk -= dx;
        }

        return "Wynik " + calka*dx;
    }
}
