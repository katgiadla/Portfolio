package Files_service;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Parameter;
import java.util.Random;
public class Main {

    public static  void main (String[] args){
        Random rand = new Random();
        ExtendedSystemCache cache1 = new ExtendedSystemCache();
        ScatterSystem system = new ScatterSystem();
        double[] input = new double [3];
        for(int i =0;i<3;i++){
            input[i] = 1.0 + rand.nextDouble();
        }


        double[] input1 = new double [5];
        for(int i =0;i<5;i++){
            input1[i] = 1.0 + rand.nextDouble();
        }
        cache1.put(input,system.makeOperation(input));
        //System.out.println(cache1.getSize());
        cache1.put(input1,system.makeOperation(input1));
        //System.out.println(cache1.getSize());
        try{
            cache1.exportCSV("trololo.csv");
            cache1.printHashMap();

            cache1.clear();
            System.out.println("after clear");
            cache1.printHashMap();
            System.out.println("after importing");
            cache1.importCSV("trololo.csv");
            cache1.printHashMap();

            System.out.println("Serialize");
            cache1.serialize("serialize.bin");
            System.out.println("Deserialize");
            cache1.deserialize("serialize.bin");
            cache1.printHashMap();
            cache1.save("save.bin");
            cache1.clear();
            cache1.load("save.bin");
            cache1.printHashMap();
        }catch(IOException e1){

        }
        catch(ArrayIndexOutOfBoundsException e2) {
            System.out.println("Zdarza sie");
        }


    }
}
