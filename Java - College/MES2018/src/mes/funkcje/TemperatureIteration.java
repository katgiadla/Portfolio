package mes.funkcje;

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

        //this.doFem();


         double[][] test = {{1,3,5,7},{6,9,4,7},{3,2,7,4},{1,5,5,7}};
        double [][] transponowana = wyznaczMacierzOdwrotna(test);
        double [][] odwrotna = pomnozPrzezSkalarTablice(1/wyznaczWyznacznikMacierzy(test), transponowana);
        for(int i=0;i<test.length;i++){
            for(int j=0;j<test[0].length;j++){
                System.out.print(odwrotna[i][j]+" ");
            }
            System.out.println();
        }
    }

    private void doFem(){
        double[] nextTemperature = new double[load_vector.length];

        nextTemperature = this.zeroTemperature(nextTemperature);
        double[] tmpTemp = this.calculateTemperature(initialTemperature);

        P_Prime = this.add_P_toCdividedByDeltaTauMultipliedByInitialTempVector(stepTime,globalMatrixC,tmpTemp,load_vector);

        for(int i=0;i<(simulationTime/stepTime);i++){
            nextTemperature = this.calculateFemEquation(H_Prime,tmpTemp,P_Prime);
            tmpTemp = nextTemperature;
            P_Prime = this.add_P_toCdividedByDeltaTauMultipliedByInitialTempVector(stepTime,globalMatrixC,tmpTemp,P_Prime);
            this.findMinMaxTemp(tmpTemp,(i+1)*stepTime);
        }
    }
 /* http://www.darmoweskrypty.linuxpl.info/macierz/java - stad zaczerpniete polskie wzory*/

    public double[][] wyznaczMacierzOdwrotna(double [][]inputArray) {
        if (inputArray[0].length != inputArray.length) {
            throw new RuntimeException("array is not N x N Dimension");
        }
        double[][] reversedArray = new double[inputArray.length][inputArray[0].length];

        double[][] macierzDolaczona = new double[inputArray.length][inputArray[0].length];
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[0].length; j++) {
                double[][] temp = new double[inputArray.length - 1][inputArray[0].length - 1];
                int a = 0, b = 0;
                for (int w = 0; w < inputArray.length; w++) {
                    for (int z = 0; z < inputArray[0].length; z++) {
                        if (w != i && z != j) {
                            if (b >= temp.length) {
                                b = 0;
                                a++;
                            }
                            temp[a][b] = inputArray[w][z];
                            b++;
                        }
                    }
                }

                double wyznacznikTemp = this.wyznaczWyznacznikMacierzy(temp);

                if ((i + j) % 2 != 0) {//Niparzyste czyli zmiana znaku wyznacznika
                    if (wyznacznikTemp > 0) {
                        wyznacznikTemp -= 2 * wyznacznikTemp;
                    } else {
                        wyznacznikTemp -= 2 * wyznacznikTemp;
                    }
                } else {
                }
                macierzDolaczona[i][j] = wyznacznikTemp;
            }
        }
        macierzDolaczona = this.transponujTablice(macierzDolaczona);
        //reversedArray = this.pomnozPrzezSkalarTablice(1 / this.wyznaczWyznacznikMacierzy(this.tablica), macierzDolaczona);
        return (macierzDolaczona);


    }



    private double[] calculateFemEquation(double[][]matrixH, double[] startTemperatureVector, double[] globalLoadVector){
        double [] temperatureVectorResult = new double[load_vector.length];

        double [][] macierzDolaczona = this.wyznaczMacierzOdwrotna(matrixH);
        double [][] reversed_H_prime = pomnozPrzezSkalarTablice(1/this.wyznaczWyznacznikMacierzy(matrixH),macierzDolaczona);


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
                P_Iteration[i] += -(matrixC[i][j] / inputStepTime) * temperature_0[j];
            }
            P_Iteration[i]+=inputLoadVector[i] ;
            P_Iteration[i]= -P_Iteration[i];
            //System.out.print(P_Iteration[i]+" ");
        }
        return P_Iteration;
    }

    /*private double [] multiplyMatrixCdividedByDeltaTauByInitialTempVector(){
        double [] result = new double[load_vector.length];
                for(int i=0;i<globalMatrixC.length;i++){
                    for(int j =0;j<globalMatrixC[0].length;j++){
                        result[i] += ;
                    }
                }
                return result;
    }*/

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


    /*public double matrixDeterminant (double[][] matrix) {
        double temporary[][];
        double result = 0;

        if (matrix.length == 1) {
            result = matrix[0][0];
            return (result);
        }

        if (matrix.length == 2) {
            result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
            return (result);
        }

        for (int i = 0; i < matrix[0].length; i++) {
            temporary = new double[matrix.length - 1][matrix[0].length - 1];

            for (int j = 1; j < matrix.length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    if (k < i) {
                        temporary[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        temporary[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }

            result += matrix[0][i] * Math.pow (-1, (double) i) * matrixDeterminant (temporary);
        }
        return (result);
    }*/


    private double[][] pomnozPrzezSkalarTablice(double skalar, double[][] tablica) {
        double[][] macierzPomnozona = new double[tablica.length][tablica[0].length];
        for (int i = 0; i < tablica.length; i++) {
            for (int j = 0; j < tablica[0].length; j++) {
                macierzPomnozona[i][j] = (tablica[i][j] * skalar);
            }
        }
        return macierzPomnozona;
    }

    private double[][] transponujTablice(double[][] tablica) {
        double[][] macierzTransponowana = new double[tablica[0].length][tablica.length];
        for (int i = 0; i < tablica.length; i++) {
            for (int j = 0; j < tablica[0].length; j++) {
                macierzTransponowana[j][i] = tablica[i][j];
            }
        }
        return macierzTransponowana;
    }

    private double wyznaczWyznacznikMacierzy(double[][] tablica) {
        double wyznacznik = 0;

        if (tablica.length == 1 && tablica[0].length == 1) {
            wyznacznik = tablica[0][0];
        } else if (tablica.length != tablica[0].length) {
            throw new RuntimeException("Nie można wyznaczyć wyznacznika dla macierzy która nie jest kwadratowa");
        } else if (tablica.length == 2 && tablica[0].length == 2) {
            wyznacznik = (tablica[0][0] * tablica[1][1] - tablica[0][1] * tablica[1][0]);
        } else {
            double[][] nTab = new double[tablica.length + (tablica.length - 1)][tablica[0].length];
            for (int i = 0, _i = 0; i < nTab.length; i++, _i++) {
                for (int j = 0; j < tablica[0].length; j++) {
                    if (_i < tablica.length && j < tablica[0].length) {
                        nTab[i][j] = tablica[_i][j];
                    } else {
                        _i = 0;
                        nTab[i][j] = tablica[_i][j];
                    }
                }
            }

            double iloczyn = 1;
            int _i;

            for (int i = 0; i < tablica.length; i++) {
                _i = i;
                for (int j = 0; j < tablica[0].length; j++) {
                    iloczyn *= nTab[_i][j];
                    _i++;
                }
                wyznacznik += iloczyn;
                iloczyn = 1;
            }

            iloczyn = 1;
            for (int i = 0; i < tablica.length; i++) {
                _i = i;
                for (int j = tablica[0].length - 1; j >= 0; j--) {
                    iloczyn *= nTab[_i][j];
                    _i++;
                }
                wyznacznik -= iloczyn;
                iloczyn = 1;
            }
        }
        return wyznacznik;
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
