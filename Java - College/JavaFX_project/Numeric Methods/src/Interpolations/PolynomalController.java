package Interpolations;
import Jama.Matrix;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PolynomalController {

    @FXML
    private TextField xTxt;

    @FXML
    private TextField yTxt;

    @FXML
    private Button reultBtn;

    @FXML
    private TextArea resultTxt;

    @FXML
    void showResult(ActionEvent event) {
        interpolacja();

    }

    private void interpolacja(){

        String tmp = xTxt.getText();
        String xParts[] = tmp.split(",");
        tmp = yTxt.getText();
        String yParts[] = tmp.split(",");
        double x[] = new double[xParts.length];
        double y[][] = new double[yParts.length][1];
        for(int i = 0; i < xParts.length; i++){
            x[i] = Double.parseDouble(xParts[i]);
            y[i][0] = Double.parseDouble(yParts[i]);
        }

        Matrix a = polynomialInterpolation(x,y);
        String result = "";
        for(int i = 0; i < x.length; i++){
            result += " a"+i+" = " +a.get(i, 0);
        }
        resultTxt.setText(result);
    }

    private Matrix polynomialInterpolation(double[] x, double[][] y){

        int n = x.length;
        Matrix A = new Matrix(n,n);
        for(int i =0; i< n; i++)
            for(int j = 0; j< n; j++)
                A.set(i, j, Math.pow(x[i], j));

        Matrix a = A.solve(new Matrix(y));
        return a;
    }



}
