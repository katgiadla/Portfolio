package mes.funkcje;

import Jama.Matrix;

public class TemperatureIteration {
    private double[][] globalMatrixH;
    private double[][] globalMatrixC;
    private double [][] H_Prime;

    private double [] temperature_1;
    private double [] temperature_0;
    private double [] load_vector;
    private double [] P_Prime;

    private GlobalData globalData;
    private double simulationTime;
    private double stepTime;
    private double initialTemperature;

    public TemperatureIteration(MatrixH matrixH, MatrixC matrixC ,GlobalData globalData) {
        this.globalMatrixH = matrixH.getGlobalMatrixH();
        this.globalMatrixC = matrixC.getGlobalMatrixC();
        this.globalData = globalData;
        this.load_vector = matrixH.getGlobalLoadVector();

        this.simulationTime = globalData.getSimulationTime();
        this.stepTime = globalData.getSimulationStepTime();
        this.initialTemperature = globalData.getInitialTemperature();
        temperature_0 = new double [load_vector.length];
        temperature_1 = new double[ load_vector.length];

        temperature_1 = this.zeroTemperature(temperature_1);
        temperature_0 = this.calculateTemperature(initialTemperature);
        P_Prime = new double[load_vector.length];

        H_Prime = this.calculate_H_Prime(globalMatrixH,globalMatrixC,stepTime);

        this.doFem();
    }

    private void doFem(){
        double[] tmpTemp = this.calculateTemperature(initialTemperature);

        P_Prime = this.calculate_P_Prime(stepTime,globalMatrixC,tmpTemp,load_vector);

        for(int i=0;i<(simulationTime/stepTime);i++){
            double[] nextTemperature = this.calculateFemEquation(H_Prime,P_Prime);
            P_Prime = this.calculate_P_Prime(stepTime,globalMatrixC,nextTemperature,load_vector);
            this.findMinMaxTemp(nextTemperature,(i+1)*stepTime);
        }
    }

    private double[] calculateFemEquation(double[][]matrix_H_Prime, double[] globalLoadVector){
        double [] temperatureVectorResult = new double[load_vector.length];

        Matrix matrix = new Matrix(matrix_H_Prime);
        Matrix inverseMatrix = matrix.inverse();
        double[][] inverseMatrixH = inverseMatrix.getArray();

        for(int i = 0;i<inverseMatrixH.length;i++){
            for(int j =0;j<inverseMatrixH[0].length;j++){
                temperatureVectorResult[i]+=(inverseMatrixH[i][j]*globalLoadVector[j]);
            }
            //System.out.println(temperatureVectorResult[i]);
        }
        return temperatureVectorResult;
    }

    private double[][] calculate_H_Prime(double[][]matrixH, double[][] matrixC, double inputStepTime){
        double [][]H_Iteration = new double[globalMatrixH.length][globalMatrixH.length];
        for(int i=0;i<H_Iteration.length;i++){
            for(int j = 0;j<H_Iteration[0].length;j++){
                H_Iteration[i][j]+=matrixH[i][j]+ matrixC[i][j]/inputStepTime;
            }
        }
        //this.print(H_Iteration);
        return H_Iteration;
    }

    private double[] calculate_P_Prime(double inputStepTime, double[][] matrixC, double[] temperature_0, double[] inputLoadVector){
        double [] P_Iteration = new double[load_vector.length];
        for(int i =0;i<load_vector.length;i++) {
            for (int j = 0; j < load_vector.length; j++) {
                P_Iteration[i] += (matrixC[i][j] / inputStepTime) * temperature_0[j];
            }
            P_Iteration[i]+=inputLoadVector[i] ;
            //System.out.println(P_Iteration[i]);
            //P_Iteration[i]= -P_Iteration[i];
            //System.out.print(P_Iteration[i]+" ");
        }
        //System.out.println();
        return P_Iteration;
    }

    private void findMinMaxTemp(double [] temp, double step){
        double min = temp[0], max = temp[0];
        for(int i =0;i<temp.length;i++){
            if(temp[i]>max) max = temp[i];
            if(temp[i]<min) min = temp[i];
        }

        System.out.println(/*"Step  "+step+" "+min+" "+*/max);
    }

    private double [] calculateTemperature(double value){
        double [] tmp =  new double[load_vector.length];
        for(int i=0;i<temperature_0.length;i++){
            tmp[i]=value;
        }
        return tmp;
    }
    private double [] zeroTemperature(double [] input){
        for(int i=0;i<input.length;i++){
            input[i]=0;
        }
        return input;
    }
}
