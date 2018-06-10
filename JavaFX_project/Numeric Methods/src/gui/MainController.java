package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {


    @FXML
    private TextArea areaInputNumber;

    @FXML
    private Button sendNumberButton;

    private int choice = 0;

    @FXML
    void sendChoice(ActionEvent event) {

        try {
            int choice = Integer.parseInt(areaInputNumber.getText());

        switch(choice){

            case 1:

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Interpolations/LagrangeWindow.fxml"));
                Parent root1 = null;
                try{
                    root1 = (Parent) fxmlLoader.load();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(root1,600,400));
                stage.setTitle("Lagrange Method");
                stage.setResizable(false);
                stage.show();

                break;

            case 2:

                FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/Interpolations/NewtonWindow.fxml"));
                Parent root2 = null;
                try{
                    root2 = (Parent) fxmlLoader2.load();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(root2,600,400));
                stage2.setTitle("Newton Method");
                stage2.setResizable(false);
                stage2.show();

                break;

            case 3:

                FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("/Interpolations/PolynomalWindow.fxml"));
                Parent root3 = null;
                try{
                    root3 = (Parent) fxmlLoader3.load();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                Stage stage3 = new Stage();
                stage3.setScene(new Scene(root3,600,400));
                stage3.setTitle("Polynomal Method");
                stage3.setResizable(false);
                stage3.show();


                break;

            case 4:
                FXMLLoader fxmlLoader4 = new FXMLLoader(getClass().getResource("/integration/CrammerWindow.fxml"));
                Parent root4 = null;
                try{
                    root4 = (Parent) fxmlLoader4.load();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                Stage stage4 = new Stage();
                stage4.setScene(new Scene(root4,700,400));
                stage4.setTitle("Rectangle Method");
                stage4.setResizable(false);
                stage4.show();
                break;

            case 5:
                FXMLLoader fxmlLoader5 = new FXMLLoader(getClass().getResource("/integration/RectanglesWindow.fxml"));
                Parent root5 = null;
                try{
                    root5 = (Parent) fxmlLoader5.load();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                Stage stage5 = new Stage();
                stage5.setScene(new Scene(root5,700,400));
                stage5.setTitle("Rectangle Method");
                stage5.setResizable(false);
                stage5.show();
                break;
            case 6:
                FXMLLoader fxmlLoader6 = new FXMLLoader(getClass().getResource("/integration/TrapezoidWindow.fxml"));
                Parent root6 = null;
                try{
                    root6 = (Parent) fxmlLoader6.load();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                Stage stage6 = new Stage();
                stage6.setScene(new Scene(root6,700,400));
                stage6.setTitle("Trapezoid Method");
                stage6.setResizable(false);
                stage6.show();
                break;

            case 7:
                FXMLLoader fxmlLoader7 = new FXMLLoader(getClass().getResource("/integration/SimpsonWindow.fxml"));
                Parent root7 = null;
                try{
                    root7 = (Parent) fxmlLoader7.load();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                Stage stage7 = new Stage();
                stage7.setScene(new Scene(root7,700,400));
                stage7.setTitle("Simpson Method");
                stage7.setResizable(false);
                stage7.show();
                break;

            case 8:
                FXMLLoader fxmlLoader8 = new FXMLLoader(getClass().getResource("/integration/McWindow.fxml"));
                Parent root8 = null;
                try{
                    root8 = (Parent) fxmlLoader8.load();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                Stage stage8 = new Stage();
                stage8.setScene(new Scene(root8,700,400));
                stage8.setTitle("M C Method");
                stage8.setResizable(false);
                stage8.show();
                break;

            case 9:
                FXMLLoader fxmlLoader9 = new FXMLLoader(getClass().getResource("/integration/GaussWindow.fxml"));
                Parent root9 = null;
                try{
                    root9 = (Parent) fxmlLoader9.load();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                Stage stage9 = new Stage();
                stage9.setScene(new Scene(root9,700,400));
                stage9.setTitle("Gauss Method");
                stage9.setResizable(false);
                stage9.show();
                break;

            case 10:
                FXMLLoader fxmlLoader10 = new FXMLLoader(getClass().getResource("/nonlinear/BisectionWindow.fxml"));
                Parent root10 = null;
                try{
                    root10 = (Parent) fxmlLoader10.load();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                Stage stage10 = new Stage();
                stage10.setScene(new Scene(root10,700,400));
                stage10.setTitle("Bisection Method");
                stage10.setResizable(false);
                stage10.show();

                break;

            case 11:
                FXMLLoader fxmlLoader11 = new FXMLLoader(getClass().getResource("/nonlinear/NrWindow.fxml"));
                Parent root11 = null;
                try{
                    root11 = (Parent) fxmlLoader11.load();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                Stage stage11 = new Stage();
                stage11.setScene(new Scene(root11,770,400));
                stage11.setTitle("Newton-Raphson Method");
                stage11.setResizable(false);
                stage11.show();
                break;

            case 12:
                FXMLLoader fxmlLoader12 = new FXMLLoader(getClass().getResource("/differentials/EulerWindow.fxml"));
                Parent root12 = null;
                try{
                    root12 = (Parent) fxmlLoader12.load();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                Stage stage12 = new Stage();
                stage12.setScene(new Scene(root12,770,400));
                stage12.setTitle("Euler Method");
                stage12.setResizable(false);
                stage12.show();
                break;

            case 13:
                FXMLLoader fxmlLoader13 = new FXMLLoader(getClass().getResource("/differentials/HeunWindow.fxml"));
                Parent root13 = null;
                try{
                    root13 = (Parent) fxmlLoader13.load();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                Stage stage13 = new Stage();
                stage13.setScene(new Scene(root13,770,400));
                stage13.setTitle("Heun Method");
                stage13.setResizable(false);
                stage13.show();
                break;

            case 14:
                FXMLLoader fxmlLoader14 = new FXMLLoader(getClass().getResource("/differentials/Rk4Window.fxml"));
                Parent root14 = null;
                try{
                    root14 = (Parent) fxmlLoader14.load();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                Stage stage14 = new Stage();
                stage14.setScene(new Scene(root14,770,400));
                stage14.setTitle("Rk4 Method");
                stage14.setResizable(false);
                stage14.show();
                break;

            default:
                areaInputNumber.setText("1-14!");
        }

        }
        catch (NumberFormatException number){
            areaInputNumber.setText("error!");
        }

    }
}
