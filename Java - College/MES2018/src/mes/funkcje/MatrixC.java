package mes.funkcje;

public class MatrixC {
    private final double SPECIFIC_HEAT;//700
    private final double DENSITY;//7800

    double [][] detJ;
    double [][][]jacobian;
    double [][][]reversedJacobian;
    double [] N1;
    double [] N2;
    double [] N3;
    double [] N4;

    private double[][][][] shapeFunctionsPointsArray;
    private double[][][] matrixC;

    public MatrixC(double[][] detJ, double[][][] jacobian, double[][][] reversedJacobian, double[] n1, double[] n2, double[] n3, double[] n4, double inputSpecificHeat, double inputDensity) {
        this.detJ = detJ;
        this.jacobian = jacobian;
        this.reversedJacobian = reversedJacobian;
        N1 = n1;
        N2 = n2;
        N3 = n3;
        N4 = n4;
        shapeFunctionsPointsArray = new double[jacobian.length][4][4][4];
        matrixC = new double[jacobian.length][4][4];
        this.SPECIFIC_HEAT = inputSpecificHeat;
        this.DENSITY = inputDensity;

        this.calculateCalculationPointsFromShapeFunctions();
        this.calculateMatrixC();
        this.printMatrixC();
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

    private void printMatrixC() {
        for (int elementIndex = 0; elementIndex < matrixC.length; elementIndex++) {
            System.out.println("element: "+(elementIndex+1));
            for (int k = 0; k < matrixC[0].length; k++) {
                for (int j = 0; j < matrixC[0][0].length; j++) {
                    System.out.print(matrixC[elementIndex][k][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
