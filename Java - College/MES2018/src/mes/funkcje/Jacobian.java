package mes.funkcje;

import java.util.List;

public class Jacobian {

    List<Element> importedElements;
    private final double KSI = 1.0 / Math.sqrt(3);

    private double[] N1, N2, N3, N4;
    private double[][] detJ;
    private double[][][] reversedDetJ ;
    private double[][] deltaNdeltaKsiArray;
    private double[][] deltaNdeltaEtaArray;
    private double[][][] jakobian;

    private Local2D[] local2DArray = {new Local2D(-KSI, -KSI), new Local2D(KSI, -KSI), new Local2D(KSI, KSI), new Local2D(-KSI, KSI)} ;
    private Node[][] nodesFromGrid;
    private Node[][] nodeXpArray;

    public Jacobian(List<Element> importedElements) {
        this.importedElements = importedElements;
        nodesFromGrid = new Node[importedElements.size()][4];
        this.createNodeArray(importedElements);
        nodeXpArray = new Node[importedElements.size()][4];

        N1 = new double[4];
        N2 = new double[4];
        N3 = new double[4];
        N4 = new double[4];

        detJ= new double[importedElements.size()][4];
        reversedDetJ = new double[importedElements.size()][4][4];
        deltaNdeltaKsiArray = new double[4][4];
        deltaNdeltaEtaArray = new double[4][4];
        jakobian = new double[importedElements.size()][4][4];

        this.calculateShapeFunctions();
        this.calculatePointsWithShapeFunctions();
        this.calculateDerivativeArray();
        this.calculateJacobianElements();
        this.calculateDetJ();
        this.calculateReversedJacobianElements();
    }

    /**
     * [element][kolmna w ktorej sa punkty]
     */
    private void createNodeArray(List<Element> inputElement){
        int index = 0;
        for(Element element: inputElement){
            this.nodesFromGrid[index][0] = element.getNode1();
            this.nodesFromGrid[index][1] = element.getNode2();
            this.nodesFromGrid[index][2] = element.getNode3();
            this.nodesFromGrid[index][3] = element.getNode4();
            index++;
        }
    }

    private void calculateShapeFunctions() {
        for (int i = 0; i < local2DArray.length; i++) {
            N1[i] = 0.25 * (1 - local2DArray[i].getKsi()) * (1 - local2DArray[i].getEta());
            N2[i] = 0.25 * (1 + local2DArray[i].getKsi()) * (1 - local2DArray[i].getEta());
            N3[i] = 0.25 * (1 + local2DArray[i].getKsi()) * (1 + local2DArray[i].getEta());
            N4[i] = 0.25 * (1 - local2DArray[i].getKsi()) * (1 + local2DArray[i].getEta());
        }
    }
/**
 * nodeXpArray [element][ilosc wezlow]
 */

    private void calculatePointsWithShapeFunctions() {
//4 punkty z elementu, i to index elementu
        for (int i = 0; i < importedElements.size(); i++) {
            for(int j =0;j<4;j++){
                nodeXpArray[i][j]= new Node(N1[j] * nodesFromGrid[i][0].getX() +
                        N2[j] * nodesFromGrid[i][1].getX() +
                        N3[j] * nodesFromGrid[i][2].getX() +
                        N4[j] * nodesFromGrid[i][3].getX(),
                        N1[j] * nodesFromGrid[i][0].getY() +
                        N2[j] * nodesFromGrid[i][1].getY() +
                        N3[j] * nodesFromGrid[i][2].getY() +
                        N4[j] * nodesFromGrid[i][3].getY());

            }
        }
    }

    private void calculateDerivativeArray() {

        for (int j = 0; j < deltaNdeltaEtaArray.length; j++) {
            deltaNdeltaKsiArray[0][j] = -0.25 * (1 - local2DArray[j].getEta());//N1
            deltaNdeltaKsiArray[1][j] = 0.25 * (1 - local2DArray[j].getEta());
            deltaNdeltaKsiArray[2][j] = 0.25 * (1 + local2DArray[j].getEta());
            deltaNdeltaKsiArray[3][j] = -0.25 * (1 + local2DArray[j].getEta());//N4

            deltaNdeltaEtaArray[0][j] = -0.25 * (1 - local2DArray[j].getKsi());
            deltaNdeltaEtaArray[1][j] = -0.25 * (1 + local2DArray[j].getKsi());
            deltaNdeltaEtaArray[2][j] = 0.25 * (1 + local2DArray[j].getKsi());
            deltaNdeltaEtaArray[3][j] = 0.25 * (1 - local2DArray[j].getKsi());
        }
    }

    private void calculateJacobianElements() {
        for (int k = 0; k < nodesFromGrid.length; k++) {
            for (int i = 0; i < jakobian[0][0].length; i++) {
                jakobian[k][0][i] = deltaNdeltaKsiArray[0][i] * nodesFromGrid[k][0].getX() + deltaNdeltaKsiArray[1][i] * nodesFromGrid[k][1].getX() +
                        deltaNdeltaKsiArray[2][i] * nodesFromGrid[k][2].getX() + deltaNdeltaKsiArray[3][i] * nodesFromGrid[k][3].getX();
                jakobian[k][1][i] = deltaNdeltaKsiArray[0][i] * nodesFromGrid[k][0].getY() + deltaNdeltaKsiArray[1][i] * nodesFromGrid[k][1].getY() +
                        deltaNdeltaKsiArray[2][i] * nodesFromGrid[k][2].getY() + deltaNdeltaKsiArray[3][i] * nodesFromGrid[k][3].getY();
                jakobian[k][2][i] = deltaNdeltaEtaArray[0][i] * nodesFromGrid[k][0].getX() + deltaNdeltaEtaArray[1][i] * nodesFromGrid[k][1].getX() +
                        deltaNdeltaEtaArray[2][i] * nodesFromGrid[k][2].getX() + deltaNdeltaEtaArray[3][i] * nodesFromGrid[k][3].getX();
                jakobian[k][3][i] = deltaNdeltaEtaArray[0][i] * nodesFromGrid[k][0].getY() + deltaNdeltaEtaArray[1][i] * nodesFromGrid[k][1].getY() +
                        deltaNdeltaEtaArray[2][i] * nodesFromGrid[k][2].getY() + deltaNdeltaEtaArray[3][i] * nodesFromGrid[k][3].getY();
            }
        }

    }

    private void calculateDetJ() {
        for (int k = 0; k < nodesFromGrid.length; k++) {
            for (int i = 0; i < jakobian[0].length; i++) {
                detJ[k][i] = jakobian[k][0][i] * jakobian[k][3][i] - jakobian[k][1][i] * jakobian[k][2][i];
            }
        }
    }

    private void calculateReversedJacobianElements(){
        for(int k =0;k<nodesFromGrid.length;k++) {
            for (int i = 0; i < reversedDetJ[0].length; i++) {
                reversedDetJ[k][0][i] = jakobian[k][3][i] / detJ[k][i];
                reversedDetJ[k][1][i] = -jakobian[k][1][i] / detJ[k][i];
                reversedDetJ[k][2][i] = -jakobian[k][2][i] / detJ[k][i];
                reversedDetJ[k][3][i] = jakobian[k][0][i] / detJ[k][i];
            }
        }
    }

    public double[][][] getReversedDetJ() {
        return reversedDetJ;
    }
    public double[][] getDetJ() {
        return detJ;
    }
    public double[][] getDeltaNdeltaKsiArray() {
        return deltaNdeltaKsiArray;
    }
    public double[][] getDeltaNdeltaEtaArray() {
        return deltaNdeltaEtaArray;
    }
    public double[][][] getJakobian() {
        return jakobian;
    }

    public double[] getSingleDetJ(int index){
        return detJ[index];
    }

    public double[][] getSingleReversedDetJ(int index){
        return reversedDetJ[index];
    }

    public double[][] getSingleJacobian(int index){
        return jakobian[index];
    }

    public double[] getN1() {
        return N1;
    }

    public double[] getN2() {
        return N2;
    }

    public double[] getN3() {
        return N3;
    }

    public double[] getN4() {
        return N4;
    }
}

/*
zadania teorii plynow
te same rownania

rwnania teorii plastycznosci


przemiesczenie rozniczkujemy wzgledem czasu - predkosc
pochodna predkosci wzgledem x


 */
