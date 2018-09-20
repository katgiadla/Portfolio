package integration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GaussController {

    @FXML
    private TextField xTxt;

    @FXML
    private TextField yTxt;

    @FXML
    private Button resultBtn;

    @FXML
    private TextArea resultTxt;

    @FXML
    void showResult(ActionEvent event) {
        kwadratury();
    }

    private void kwadratury(){
        double wsp_x[] = new double[4], wsp_y[] = new double[4], waga[] = new double[2], punkt[] = new double[2];
        double fksztalt[][][] = new double[2][2][4];
        double poch_ksi[][] = new double[2][4], poch_ni[][] = new double[2][4], fundetj[][] = new double[2][2];
        double dxdksi, dydksi, dxdni, dydni;
        String tmp = xTxt.getText();
        String xParts[] = tmp.split(",");
        tmp = yTxt.getText();
        String yParts[] = tmp.split(",");
        for(int i = 0; i<4; i++) {
            wsp_x[i] = Double.parseDouble(xParts[i]);
            wsp_y[i] = Double.parseDouble(yParts[i]);
        }
        waga[0] = 1;
        waga[1] = 1;
        punkt[0] = -0.5773502692;
        punkt[1] = 0.5773502692;
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                fksztalt[j][i][0] = 0.25*(1.0 - punkt[j])*(1 - punkt[i]);
                fksztalt[j][i][1] = 0.25*(1.0 + punkt[j])*(1 - punkt[i]);
                fksztalt[j][i][2] = 0.25*(1.0 + punkt[j])*(1 + punkt[i]);
                fksztalt[j][i][3] = 0.25*(1.0 - punkt[j])*(1 + punkt[i]);
                poch_ksi[i][0] = -0.25*(1 - punkt[i]);
                poch_ksi[i][1] = 0.25*(1 - punkt[i]);
                poch_ksi[i][2] = 0.25*(1 + punkt[i]);
                poch_ksi[i][3] = -0.25*(1 + punkt[i]);
                poch_ni[j][0] = -0.25*(1 - punkt[j]);
                poch_ni[j][1] = -0.25*(1 + punkt[j]);
                poch_ni[j][2] = 0.25*(1 + punkt[j]);
                poch_ni[j][3] = 0.25*(1 - punkt[j]);
            }
        }
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                dxdksi = poch_ksi[i][0] * wsp_x[0] + poch_ksi[i][1] * wsp_x[1] + poch_ksi[i][2] * wsp_x[2] + poch_ksi[i][3] * wsp_x[3];
                dydksi = poch_ksi[i][0] * wsp_y[0] + poch_ksi[i][1] * wsp_y[1] + poch_ksi[i][2] * wsp_y[2] + poch_ksi[i][3] * wsp_y[3];
                dxdni = poch_ni[j][0] * wsp_x[0] + poch_ni[j][1] * wsp_x[1] + poch_ni[j][2] * wsp_x[2] + poch_ni[j][3] * wsp_x[3];
                dydni = poch_ni[j][0] * wsp_y[0] + poch_ni[j][1] * wsp_y[1] + poch_ni[j][2] * wsp_y[2] + poch_ni[j][3] * wsp_y[3];
                fundetj[j][i] = dxdksi*dydni - dxdni * dydksi;
            }
        }
        double powierzchnia = 0;
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                powierzchnia += Math.abs(fundetj[j][i]) * waga[j] * waga[i];
            }
        }
        resultTxt.setText("Powierzchnia: " + powierzchnia);
    }

}
