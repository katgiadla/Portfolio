package mes.funkcje;

import Jama.Matrix;

public class TemperatureIteration {
    private double[][] globalMatrixHnoBorders;


    private double[][] globalMatrixH;
    private double[][] globalMatrixC;

    private double[][] iterationMatrixH;
    private double [][] matrix_C_DividedByDeltaTau;
    private double [][] H_Prime;

    private double [] temperature_1;
    private double [] temperature_0;
    private double [] load_vector;
    private double [] P_Prime;

    private GlobalData globalData;
    private double simulationTime;
    private double stepTime;
    private double initialTemperature;
    //private Macierz macierz;

    public TemperatureIteration(double[][] globalMatrixH, double[][] globalMatrixHnoBorders, double[][] globalMatrixC, double[] load_vector ,GlobalData globalData) {
        this.globalMatrixH = globalMatrixH;
        this.globalMatrixHnoBorders = globalMatrixHnoBorders;
        this.globalMatrixC = globalMatrixC;
        this.globalData = globalData;
        this.load_vector = load_vector;

        this.simulationTime = globalData.getSimulationTime();
        this.stepTime = globalData.getSimulationStepTime();
        this.initialTemperature = globalData.getInitialTemperature();
        temperature_0 = new double [load_vector.length];
        temperature_1 = new double[ load_vector.length];
        iterationMatrixH = new double[globalMatrixH.length][globalMatrixH.length];

        temperature_1 = this.zeroTemperature(temperature_1);
        temperature_0 = this.calculateTemperature(initialTemperature);
        P_Prime = new double[load_vector.length];

        //matrix_C_DividedByDeltaTau = new double[globalMatrixC.length][globalMatrixC.length];

        //this.print();
        H_Prime = this.add_H_toCdividedBydeltaTau(globalMatrixH,globalMatrixC,stepTime);

        this.doFem();
        //this.add_P_toCdividedByDeltaTauMultipliedByInitialTempVector(stepTime,globalMatrixC,temperature_0,load_vector);
    }

    private void doFem(){
        double[] nextTemperature = new double[load_vector.length];

        nextTemperature = this.zeroTemperature(nextTemperature);
        double[] tmpTemp = this.calculateTemperature(initialTemperature);

        P_Prime = this.add_P_toCdividedByDeltaTauMultipliedByInitialTempVector(stepTime,globalMatrixC,tmpTemp,load_vector);

        for(int i=0;i<(simulationTime/stepTime);i++){
            nextTemperature = this.calculateFemEquation(H_Prime,P_Prime);
            tmpTemp = nextTemperature;
            P_Prime = this.add_P_toCdividedByDeltaTauMultipliedByInitialTempVector(stepTime,globalMatrixC,tmpTemp,load_vector);
            this.findMinMaxTemp(tmpTemp,(i+1)*stepTime);
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


    private double[][] add_H_toCdividedBydeltaTau(double[][]matrixH,double[][] matrixC,double inputStepTime){
        double [][]H_Iteration = new double[globalMatrixH.length][globalMatrixH.length];
        for(int i=0;i<H_Iteration.length;i++){
            for(int j = 0;j<H_Iteration[0].length;j++){
                H_Iteration[i][j]+=matrixH[i][j]+ matrixC[i][j]/inputStepTime;
            }

        }
        //this.print(H_Iteration);
        return H_Iteration;
    }

    private double[] add_P_toCdividedByDeltaTauMultipliedByInitialTempVector(double inputStepTime, double[][] matrixC,double[] temperature_0,double[] inputLoadVector){
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
        System.out.println();
        return P_Iteration;
    }


    private void findMinMaxTemp(double [] temp, double step){
        double min = temp[0], max = temp[0];
        for(int i =0;i<temp.length;i++){
            if(temp[i]>max) max = temp[i];
            if(temp[i]<min) min = temp[i];
        }

        System.out.println("Step  "+step+" "+min+" "+max);
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


    private void print(double [] input){
        for(int i =0;i<input.length;i++){
            System.out.printf("%.6 \t",input[i]);
        }
    }

    private void print(double[][] input){
        for(int i =0;i<input.length;i++){
            for(int j=0;j<input[0].length;j++){
                System.out.printf("%.6f \t",input[i][j]);
            }
            System.out.println();
        }
    }


}
