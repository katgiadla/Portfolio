package Files_service;

public class ScatterSystem {

    public Double makeOperation(double [] input){
        //System.out.println("makeOperation");
        Double result = 0.0;
        for(int i =0;i<input.length;i++){
            result +=input[i];
        }
        //System.out.println(result/(double)input.length);
        return Math.round((result/(double)input.length)*100.0)/100.0;
    }

}
