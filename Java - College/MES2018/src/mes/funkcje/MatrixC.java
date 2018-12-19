package mes.funkcje;

import java.util.List;

public class MatrixC {
    private final double SPECIFIC_HEAT;//700
    private final double DENSITY;//7800

    double [][] detJ;
    double [][][]jacobian;
    double [][][]reversedJacobian;
    double [] N1,N2,N3,N4;


    List<Element> importedElements;
    private double[][][][] shapeFunctionsPointsArray;
    private double[][][] matrixC;
    private double [][] globalMatrixC;

    private GlobalData globalData;

    public MatrixC( Grid grid, MatrixH matrixH, Jacobian jakobian_2d, GlobalData globalData) {
        this.importedElements = grid.getElementsCollection();
        this.detJ = matrixH.getDetJ();
        this.jacobian = matrixH.getJacobian();
        this.reversedJacobian = matrixH.getReversedJacobian();
        N1 = jakobian_2d.getN1();
        N2 = jakobian_2d.getN2();
        N3 = jakobian_2d.getN3();
        N4 = jakobian_2d.getN4();
        shapeFunctionsPointsArray = new double[jacobian.length][4][4][4];
        matrixC = new double[jacobian.length][4][4];
        globalMatrixC = new double[globalData.getnH()*globalData.getnB()][globalData.getnB()*globalData.getnH()];

        this.globalData = globalData;
        this.SPECIFIC_HEAT = globalData.getIndividualHeat();
        this.DENSITY = globalData.getDensity();

        this.calculateCalculationPointsFromShapeFunctions();
        this.calculateMatrixC();
        this.calculateGlobalMatrixC();
    }

    private void calculateCalculationPointsFromShapeFunctions(){
        for(int elementIndex = 0;elementIndex<jacobian.length;elementIndex++){
            for(int row =0;row<shapeFunctionsPointsArray[0][0].length;row++){
                for(int column =0;column<shapeFunctionsPointsArray[0][0][0].length;column++){
                    shapeFunctionsPointsArray[elementIndex][0][row][column]= DENSITY * SPECIFIC_HEAT *detJ[elementIndex][0]*N1[row]*N1[column];
                    shapeFunctionsPointsArray[elementIndex][1][row][column]= DENSITY * SPECIFIC_HEAT *detJ[elementIndex][1]*N2[row]*N2[column];
                    shapeFunctionsPointsArray[elementIndex][2][row][column]= DENSITY * SPECIFIC_HEAT *detJ[elementIndex][2]*N3[row]*N3[column];
                    shapeFunctionsPointsArray[elementIndex][3][row][column]= DENSITY * SPECIFIC_HEAT *detJ[elementIndex][3]*N4[row]*N4[column];
                }
            }
        }
    }

    private void calculateMatrixC(){
        for(int elementIndex = 0;elementIndex<jacobian.length;elementIndex++){
            for(int row =0;row<matrixC[0].length;row++){
                for(int column =0;column<matrixC[0][0].length;column++){
                    matrixC[elementIndex][row][column]=
                                    shapeFunctionsPointsArray[elementIndex][0][row][column]+
                                    shapeFunctionsPointsArray[elementIndex][1][row][column]+
                                    shapeFunctionsPointsArray[elementIndex][2][row][column]+
                                    shapeFunctionsPointsArray[elementIndex][3][row][column];
                }
            }
        }
    }

    private void calculateGlobalMatrixC() {
        for(int i=0;i<globalMatrixC.length;i++){
            for(int j=0;j<globalMatrixC[0].length;j++){
                globalMatrixC[i][j]=0;
            }
        }

        for (int elemIndex = 0; elemIndex < jacobian.length; elemIndex++) {
            for (int i = 0; i < globalMatrixC.length; i++) {
                for (int j = 0; j < globalMatrixC[0].length; j++) {

                    for(int localRowIndex =0;localRowIndex<importedElements.get(elemIndex).getNodes().length;localRowIndex++){
                        for(int localColumnIndex =0;localColumnIndex<importedElements.get(elemIndex).getNodes().length;localColumnIndex++){

                            if(importedElements.get(elemIndex).getNodes()[localRowIndex].getId() == i && importedElements.get(elemIndex).getNodes()[localColumnIndex].getId() == j) {
                                globalMatrixC[i][j] += matrixC[elemIndex][localRowIndex][localColumnIndex];
                                continue;
                            }
                        }
                    }
                }
            }
            /*for(int testRowIndex =0;testRowIndex<globalMatrixC.length;testRowIndex++) {
                for (int testColumnIndex = 0; testColumnIndex < globalMatrixC[0].length; testColumnIndex++) {
                    System.out.printf("%.6f\t",globalMatrixC[testRowIndex][testColumnIndex]);
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();*/
        }
    }

    public double[][][] getMatrixC() {
        return matrixC;
    }

    public double[][] getGlobalMatrixC() {
        return globalMatrixC;
    }
}
