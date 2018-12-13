package mes;

import mes.funkcje.*;

public class mainApp {
    public static void main(String[] args){
        GlobalData globalData = new GlobalData("C:\\AGH\\Portfolio\\Java - College\\MES2018\\src\\mes\\data\\dane.txt");
        Grid grid = new Grid( globalData.getB(), globalData.getH(),globalData.getnB(),globalData.getnH());

        Jacobian jakobian_2d = new Jacobian(grid.getElementsCollection());
        MatrixH matrixH = new MatrixH(grid.getElementsCollection(),jakobian_2d.getReversedDetJ(),jakobian_2d.getDetJ(),
                jakobian_2d.getDeltaNdeltaKsiArray(),jakobian_2d.getDeltaNdeltaEtaArray(),jakobian_2d.getJakobian(),
                globalData);
        MatrixC matrixC = new MatrixC(grid.getElementsCollection(),matrixH.getDetJ(),matrixH.getJacobian(),matrixH.getReversedJacobian(),jakobian_2d.getN1(),jakobian_2d.getN2(),jakobian_2d.getN3(),
                jakobian_2d.getN4(), globalData);
        TemperatureIteration temperatureIteration = new TemperatureIteration(matrixH.getGlobalMatrixH(),matrixH.getGlobalMatrixHnoBorderCondition(),matrixC.getGlobalMatrixC(),matrixH.getGlobalLoadVector() ,globalData);
    }
}