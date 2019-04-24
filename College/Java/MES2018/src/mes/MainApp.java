package mes;

import mes.funkcje.*;

public class MainApp {

    public static void main(String[] args){
        GlobalData globalData = new GlobalData();
        Grid grid = new Grid( globalData);

        Jacobian jakobian_2d = new Jacobian(grid);
        MatrixH matrixH = new MatrixH(grid, jakobian_2d,globalData);
        MatrixC matrixC = new MatrixC(grid, matrixH,jakobian_2d, globalData);
        TemperatureIteration temperatureIteration = new TemperatureIteration(matrixH,matrixC,globalData);
    }
}