package mes.funkcje;

import java.util.List;

public class MatrixH {
    /**
     * [elementy][rozmiar]
     * [elementy][rozmiar][rozmiar]
     * [elementy][nrtablicy][rozmiar][rozmiar]
     */
    private final double CONDUCTIVITY;//30 alfa?? 300 //k
    private final double CONVECTION; //25  alfa w excelu
    private final double ENVIRONMENT_TEMPERATURE; //1200

    double[][][] reversedJacobian;     //
    double[][] detJ;                   // All imported from jacobian object (first exel tab)
    double[][] deltaNdeltaKsiArray;  //
    double[][] deltaNdeltaEtaArray;  //
    double[][][] jacobian;             //
    double[][][] deltaNdeltaX;
    double[][][] deltaNdeltaY;

    private double[][][][] deltaNdeltaXtimesTransposeDetJ;
    private double[][][][] deltaNdeltaYtimesTransposeDetJ;
    private double[][][][] deltaNdeltaXtimesTranspose;
    private double[][][][] deltaNdeltaYtimesTranspose;
    private double[][][][] sumXandYmultipliedByConductivity;

    private double[][][] matrixH;
    private double[][] globalMatrixH;

    private double [][][] load_vector;

    List<Element> importedElements;
    private double[][][] border_H_Array;
    private double [][] nextStepTemperature;
     //edgeCalculationPoints = new Local2D[8];

    public MatrixH(List<Element> importedElements, double[][][] reversedJacobian, double[][] detJ, double[][] deltaNdeltaKsiArray,
                   double[][] deltaNdeltaEtaArray, double[][][] jacobian, double inputConvection, double inputConductivity, double inputEnvTemperature) {
        this.importedElements = importedElements;
        this.reversedJacobian = reversedJacobian;
        this.detJ = detJ;
        this.deltaNdeltaKsiArray = deltaNdeltaKsiArray;
        this.deltaNdeltaEtaArray = deltaNdeltaEtaArray;
        this.jacobian = jacobian;
        this.CONVECTION = inputConvection;
        this.CONDUCTIVITY = inputConductivity;
        this.ENVIRONMENT_TEMPERATURE = inputEnvTemperature;

        deltaNdeltaXtimesTransposeDetJ = new double[jacobian.length][4][4][4];
        deltaNdeltaYtimesTransposeDetJ = new double[jacobian.length][4][4][4];
        deltaNdeltaXtimesTranspose = new double[jacobian.length][4][4][4];
        deltaNdeltaYtimesTranspose = new double[jacobian.length][4][4][4];
        sumXandYmultipliedByConductivity = new double[jacobian.length][4][4][4];
        load_vector = new double[jacobian.length][4][4];

        deltaNdeltaX = new double[jacobian.length][4][4];
        deltaNdeltaY = new double[jacobian.length][4][4];
        matrixH = new double[jacobian.length][4][4];
        border_H_Array = new double[jacobian.length][4][4];
        globalMatrixH = new double[4][4];

        this.calculateDeltaNdeltaXandDeltaY();
        this.calculateCalculationPoints();
        this.multipleCalculationPointsByDetJ();

        this.multipleSumXandYcalculationPointsCoordinates();
        this.calculateMatrixH();
        this.calculateBorders();
        //this.calculateGlobalMatrixH();
        //this.printMatrixH();

    }





    private void calculateBorders() {
        int elementIndex = 0;
        Local2D[] edgeCalculationPoints = importedElements.get(0).getLocal_2D_Calculation_Points();
        for (Element element : importedElements) {
            if (element.isEdge1Border()) {
                double edge_1_detJ = element.getEdge1()/2;
                double n1_p1 = element.calculateShapeFunction1(
                        element.getLocal_2D_Calculation_Points()[0].getKsi(),
                        element.getLocal_2D_Calculation_Points()[0].getEta());

                double n2_p1 = element.calculateShapeFunction2(
                        element.getLocal_2D_Calculation_Points()[0].getKsi(),
                        element.getLocal_2D_Calculation_Points()[0].getEta());

                double n1_p2 = element.calculateShapeFunction1(
                        element.getLocal_2D_Calculation_Points()[1].getKsi(),
                        element.getLocal_2D_Calculation_Points()[1].getEta());

                double n2_p2 = element.calculateShapeFunction2(
                        element.getLocal_2D_Calculation_Points()[1].getKsi(),
                        element.getLocal_2D_Calculation_Points()[1].getEta());

                double[][] calculation_Point_1_Array = {{n1_p1*n1_p1*CONVECTION,n1_p1*n2_p1*CONVECTION,0,0},{n1_p1*n2_p1*CONVECTION,n2_p1*n2_p1*CONVECTION,0,0},{0,0,0,0},{0,0,0,0}};
                double[][] calculation_Point_2_Array = {{n1_p2*n1_p2*CONVECTION,n1_p2*n2_p2*CONVECTION,0,0},{n1_p2*n2_p2*CONVECTION,n2_p2*n2_p2*CONVECTION,0,0},{0,0,0,0},{0,0,0,0}};
                double[][] edge_1_sum = new double[4][4];
                for(int i=0;i<calculation_Point_1_Array.length;i++){
                    for(int j =0;j<calculation_Point_1_Array[0].length;j++){
                        edge_1_sum[i][j]=(calculation_Point_1_Array[i][j]+calculation_Point_2_Array[i][j])*edge_1_detJ;
                        border_H_Array[elementIndex][i][j]+=edge_1_sum[i][j];
                    }
                }
                /**
                 * [element][krawedz][NrFunkcjiKsztaltu]
                 */
                        load_vector[elementIndex][0][0] = CONVECTION*ENVIRONMENT_TEMPERATURE*  element.calculateShapeFunction1(element.getLocal_2D_Calculation_Points()[0].getKsi(),element.getLocal_2D_Calculation_Points()[0].getEta());
                        load_vector[elementIndex][0][1] = CONVECTION*ENVIRONMENT_TEMPERATURE*  element.calculateShapeFunction2(element.getLocal_2D_Calculation_Points()[1].getKsi(),element.getLocal_2D_Calculation_Points()[1].getEta());
                        load_vector[elementIndex][0][2] = 0;
                        load_vector[elementIndex][0][3] = 0;
            }

            if (element.isEdge2Border()){
                double edge_2_detJ = element.getEdge2()/2;
                double n2_p3 = element.calculateShapeFunction2(
                        element.getLocal_2D_Calculation_Points()[2].getKsi(),
                        element.getLocal_2D_Calculation_Points()[2].getEta());

                double n3_p3 = element.calculateShapeFunction3(
                        element.getLocal_2D_Calculation_Points()[2].getKsi(),
                        element.getLocal_2D_Calculation_Points()[2].getEta());

                double n2_p4 = element.calculateShapeFunction2(
                        element.getLocal_2D_Calculation_Points()[3].getKsi(),
                        element.getLocal_2D_Calculation_Points()[3].getEta());

                double n3_p4 = element.calculateShapeFunction3(
                        element.getLocal_2D_Calculation_Points()[3].getKsi(),
                        element.getLocal_2D_Calculation_Points()[3].getEta());

                double[][] calculation_Point_3_Array = {{0,0,0,0},{0,n2_p3*n2_p3*CONVECTION,n2_p3*n3_p3*CONVECTION,0},{0,n2_p3*n3_p3*CONVECTION,n3_p3*n3_p3*CONVECTION,0},{0,0,0,0}};
                double[][] calculation_Point_4_Array = {{0,0,0,0},{0,n2_p4*n2_p4*CONVECTION,n2_p4*n3_p4*CONVECTION,0},{0,n2_p4*n3_p4*CONVECTION,n3_p4*n3_p4*CONVECTION,0},{0,0,0,0}};
                double[][] edge_2_sum = new double[4][4];
                for(int i=0;i<calculation_Point_3_Array.length;i++){
                    for(int j =0;j<calculation_Point_3_Array[0].length;j++){
                        edge_2_sum[i][j]=(calculation_Point_3_Array[i][j]+calculation_Point_4_Array[i][j])*edge_2_detJ;
                        border_H_Array[elementIndex][i][j]+=edge_2_sum[i][j];
                    }
                }
                    load_vector[elementIndex][1][0] = 0;
                    load_vector[elementIndex][1][1] = CONVECTION*ENVIRONMENT_TEMPERATURE*  element.calculateShapeFunction2(element.getLocal_2D_Calculation_Points()[2].getKsi(),element.getLocal_2D_Calculation_Points()[2].getEta());
                    load_vector[elementIndex][1][2] = CONVECTION*ENVIRONMENT_TEMPERATURE*  element.calculateShapeFunction3(element.getLocal_2D_Calculation_Points()[3].getKsi(),element.getLocal_2D_Calculation_Points()[3].getEta());
                    load_vector[elementIndex][1][3] = 0;



            }

            if (element.isEdge3Border()){
                double edge_3_detJ = element.getEdge3()/2;
                double n3_p5 = element.calculateShapeFunction3(
                        element.getLocal_2D_Calculation_Points()[4].getKsi(),
                        element.getLocal_2D_Calculation_Points()[4].getEta());

                double n4_p5 = element.calculateShapeFunction4(
                        element.getLocal_2D_Calculation_Points()[4].getKsi(),
                        element.getLocal_2D_Calculation_Points()[4].getEta());

                double n3_p6 = element.calculateShapeFunction3(
                        element.getLocal_2D_Calculation_Points()[5].getKsi(),
                        element.getLocal_2D_Calculation_Points()[5].getEta());

                double n4_p6 = element.calculateShapeFunction4(
                        element.getLocal_2D_Calculation_Points()[5].getKsi(),
                        element.getLocal_2D_Calculation_Points()[5].getEta());

                double[][] calculation_Point_5_Array = {{0,0,0,0},{0,0,0,0},{0,0,n3_p5*n3_p5*CONVECTION,n3_p5*n4_p5*CONVECTION},{0,0,n3_p5*n4_p5*CONVECTION,n4_p5*n4_p5*CONVECTION}};
                double[][] calculation_Point_6_Array = {{0,0,0,0},{0,0,0,0},{0,0,n3_p6*n3_p6*CONVECTION,n3_p6*n4_p6*CONVECTION},{0,0,n3_p6*n4_p6*CONVECTION,n4_p6*n4_p6*CONVECTION}};
                double[][] edge_3_sum = new double[4][4];
                for(int i=0;i<calculation_Point_5_Array.length;i++){
                    for(int j =0;j<calculation_Point_5_Array[0].length;j++){
                        edge_3_sum[i][j]=(calculation_Point_5_Array[i][j]+calculation_Point_6_Array[i][j])*edge_3_detJ;
                        border_H_Array[elementIndex][i][j]+=edge_3_sum[i][j];

                    }
                }

                load_vector[elementIndex][2][0] = 0;
                load_vector[elementIndex][2][1] = 0;
                load_vector[elementIndex][2][2] = CONVECTION*ENVIRONMENT_TEMPERATURE*  element.calculateShapeFunction3(element.getLocal_2D_Calculation_Points()[4].getKsi(),element.getLocal_2D_Calculation_Points()[4].getEta());
                load_vector[elementIndex][2][3] = CONVECTION*ENVIRONMENT_TEMPERATURE*  element.calculateShapeFunction4(element.getLocal_2D_Calculation_Points()[5].getKsi(),element.getLocal_2D_Calculation_Points()[5].getEta());

            }

            if (element.isEdge4Border()){
                double edge_4_detJ = element.getEdge4()/2;
                double n4_p7 = element.calculateShapeFunction4(
                        element.getLocal_2D_Calculation_Points()[6].getKsi(),
                        element.getLocal_2D_Calculation_Points()[6].getEta());

                double n1_p7 = element.calculateShapeFunction1(
                        element.getLocal_2D_Calculation_Points()[6].getKsi(),
                        element.getLocal_2D_Calculation_Points()[6].getEta());

                double n4_p8 = element.calculateShapeFunction4(
                        element.getLocal_2D_Calculation_Points()[7].getKsi(),
                        element.getLocal_2D_Calculation_Points()[7].getEta());

                double n1_p8 = element.calculateShapeFunction1(
                        element.getLocal_2D_Calculation_Points()[7].getKsi(),
                        element.getLocal_2D_Calculation_Points()[7].getEta());

                double[][] calculation_Point_7_Array = {{n4_p7*n4_p7*CONVECTION,0,0,n4_p7*n1_p7*CONVECTION},{0,0,0,0},{0,0,0,0},{n4_p7*n1_p7*CONVECTION,0,0,n1_p7*n1_p7*CONVECTION}};
                double[][] calculation_Point_8_Array = {{n4_p8*n4_p8*CONVECTION,0,0,n4_p8*n1_p8*CONVECTION},{0,0,0,0},{0,0,0,0},{n4_p8*n1_p8*CONVECTION,0,0,n1_p8*n1_p8*CONVECTION}};
                double[][] edge_4_sum = new double[4][4];


                for(int i=0;i<calculation_Point_7_Array.length;i++){
                    for(int j =0;j<calculation_Point_7_Array[0].length;j++){
                        edge_4_sum[i][j]=(calculation_Point_7_Array[i][j]+calculation_Point_8_Array[i][j])*edge_4_detJ;
                        border_H_Array[elementIndex][i][j]+=edge_4_sum[i][j];
                    }
                }
                load_vector[elementIndex][3][0] = CONVECTION*ENVIRONMENT_TEMPERATURE*  element.calculateShapeFunction1(element.getLocal_2D_Calculation_Points()[7].getKsi(),element.getLocal_2D_Calculation_Points()[7].getEta());
                load_vector[elementIndex][3][1] =0;
                load_vector[elementIndex][3][2] =0;
                load_vector[elementIndex][3][3] = CONVECTION*ENVIRONMENT_TEMPERATURE*  element.calculateShapeFunction4(element.getLocal_2D_Calculation_Points()[6].getKsi(),element.getLocal_2D_Calculation_Points()[6].getEta());
            }

            for(int i=0;i<4;i++){
                for(int j =0;j<4;j++){
                    matrixH[elementIndex][i][j]+= border_H_Array[elementIndex][i][j];
                }
            }

            for(int i=0;i<4;i++){
                /*for(int j =0;j<8;j++){
                    System.out.println(element.getLocal_2D_Calculation_Points()[7].getKsi()+" "+element.getLocal_2D_Calculation_Points()[7].getEta());
                }*/
                System.out.println("element: "+elementIndex+" "+load_vector[elementIndex][i][0] + " "+ load_vector[elementIndex][i][1]+" "+load_vector[elementIndex][i][2]+" "+load_vector[elementIndex][i][3]);

            }
            System.out.println();
            elementIndex++;
        }
    }

    private void calculateDeltaNdeltaXandDeltaY() {
        for (int elementIndex = 0; elementIndex < matrixH.length; elementIndex++) {
            for (int i = 0; i < deltaNdeltaX[0].length; i++) {
                for (int j = 0; j < deltaNdeltaX[0][0].length; j++) {
                    deltaNdeltaX[elementIndex][j][i] = reversedJacobian[elementIndex][0][j] * deltaNdeltaKsiArray[i][j] + reversedJacobian[elementIndex][1][j] * deltaNdeltaEtaArray[i][j];
                    deltaNdeltaY[elementIndex][j][i] = reversedJacobian[elementIndex][2][j] * deltaNdeltaKsiArray[i][j] + reversedJacobian[elementIndex][3][j] * deltaNdeltaEtaArray[i][j];
                }
            }
        }
    }

    private void calculateCalculationPoints() {
        for (int elementIndex = 0; elementIndex < matrixH.length; elementIndex++) {
            for (int k = 0; k < deltaNdeltaXtimesTranspose[0].length; k++) {
                for (int i = 0; i < deltaNdeltaXtimesTranspose[0][0].length; i++) {
                    for (int j = 0; j < deltaNdeltaXtimesTranspose[0][0][0].length; j++) {
                        deltaNdeltaXtimesTranspose[elementIndex][k][i][j] = deltaNdeltaX[elementIndex][k][i] * deltaNdeltaX[elementIndex][k][j];
                        deltaNdeltaYtimesTranspose[elementIndex][k][i][j] = deltaNdeltaY[elementIndex][k][i] * deltaNdeltaY[elementIndex][k][j];
                    }
                }
            }
        }
    }

    private void multipleCalculationPointsByDetJ() {
        for (int elementIndex = 0; elementIndex < matrixH.length; elementIndex++) {
            for (int k = 0; k < deltaNdeltaXtimesTranspose[0].length; k++) {
                for (int i = 0; i < deltaNdeltaXtimesTranspose[0][0].length; i++) {
                    for (int j = 0; j < deltaNdeltaXtimesTranspose[0][0][0].length; j++) {
                        deltaNdeltaXtimesTransposeDetJ[elementIndex][k][i][j] = deltaNdeltaXtimesTranspose[elementIndex][k][i][j] * detJ[elementIndex][k];
                        deltaNdeltaYtimesTransposeDetJ[elementIndex][k][i][j] = deltaNdeltaYtimesTranspose[elementIndex][k][i][j] * detJ[elementIndex][k];
                    }
                }
            }
        }
    }

    private void multipleSumXandYcalculationPointsCoordinates() {
        for (int elementIndex = 0; elementIndex < matrixH.length; elementIndex++) {
            for (int k = 0; k < deltaNdeltaXtimesTranspose[0].length; k++) {
                for (int i = 0; i < deltaNdeltaXtimesTranspose[0][0].length; i++) {
                    for (int j = 0; j < deltaNdeltaXtimesTranspose[0][0][0].length; j++) {
                        sumXandYmultipliedByConductivity[elementIndex][k][i][j] =
                                (deltaNdeltaXtimesTransposeDetJ[elementIndex][k][i][j] + deltaNdeltaYtimesTransposeDetJ[elementIndex][k][i][j]) * CONDUCTIVITY;
                    }
                }
            }
        }
    }

    private void calculateMatrixH() {
        for (int elementIndex = 0; elementIndex < matrixH.length; elementIndex++) {
            for (int k = 0; k < matrixH[0].length; k++) {
                for (int i = 0; i < matrixH[0].length; i++) {
                    for (int j = 0; j < matrixH[0][0].length; j++) {
                        matrixH[elementIndex][i][j] += sumXandYmultipliedByConductivity[elementIndex][k][i][j];
                    }
                }
            }
        }
    }


    /*private void calculateGlobalMatrixH() {
        for (int elemIndex = 0; elemIndex < jacobian.length; elemIndex++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    globalMatrixH[i][j] += matrixH[elemIndex][i][j];
                }
            }
        } zle to nie globalna
    }*/

    private void printMatrixH() {
        for (int elementIndex = 0; elementIndex < matrixH.length; elementIndex++) {
            System.out.println("element nr: " + (elementIndex + 1));
            for (int k = 0; k < matrixH[0].length; k++) {
                for (int j = 0; j < matrixH[0][0].length; j++) {
                    System.out.print(matrixH[elementIndex][k][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public double[][][] getMatrixH() {
        return matrixH;
    }

    public double[][] getSingleMatrixH(int index) {
        return matrixH[index];
    }

    public double[][][] getReversedJacobian() {
        return reversedJacobian;
    }

    public double[][] getDetJ() {
        return detJ;
    }

    public double[][][] getJacobian() {
        return jacobian;
    }
}

/**
 * temp. skalarem jest
 * mamy uklad rownan za kazdy uklad odpowiada 1 wartosc 1 temperatuja
 * jak zaczynamy mechanike to do tych wezlow, w tych wezlach mamy ile zmiennych -
 *
 *
 * 1 strona opisu, jaka metoda itp. , opis programu
 *
 *
 * rownanie z macierzy H jest odpowiada za rozwiazanie konkretnej temperatury w 1 wezle
 */
