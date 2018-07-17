package Files_service;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class SystemCache
{
    private HashMap<Parameter, Double> cache = new HashMap<>(); //1
    public void put( double[] input, double output )
    {
        this.cache.put( new Parameter( input ), output );
    }
    public Double get( double[] input )
    {
        return this.cache.get( new Parameter( input ) );
    }

    public double[] getArray(){
        Map.Entry<Parameter,Double> entry= cache.entrySet().iterator().next();
        return entry.getKey().getValues();
    }

    public double[] getArrayAndResult(){
        Map.Entry<Parameter,Double> entry= cache.entrySet().iterator().next();

        return entry.getKey().getValues();
    }

    public void printHashMap(){

        for(Map.Entry<Parameter,Double> entry: this.cache.entrySet()){
            String line = "";
            double [] result = entry.getKey().getValues();
            for(int i =0;i<result.length;i++) {
                if(i+1 == result.length){
                    double roundOff = Math.round(result[i] * 100.0) / 100.0;
                    line += roundOff+" ";
                }
                else {
                    double roundOff = Math.round(result[i] * 100.0) / 100.0;
                    line += roundOff + ",";

                }
            }
            System.out.println("Parameters: " +line+" result: " + entry.getValue());
        }
    }

    public void clear(){
        System.out.println("cache has been cleared");
        this.cache.clear();
    }

    public HashMap<double[],Double> getHashMap(){
        HashMap<double[],Double> result = new HashMap<>();
        for(Map.Entry<Parameter,Double> entry: cache.entrySet()){
            result.put(entry.getKey().getValues(),entry.getValue());
        }
        return result;
    }

    public void convert(HashMap<double[], Double> input){
        this.cache.clear();
        for(Map.Entry<double [], Double> entry: input.entrySet()){
            this.cache.put(new Parameter( entry.getKey() ),entry.getValue());
        }

    }

    public int getSize(){
        return cache.size();
    }


    public Parameter getParameter(){ //pobieram Parametr zeby bylo jak wyslac do pliku
        Map.Entry<Parameter,Double> entry= cache.entrySet().iterator().next();
        return new Parameter(entry.getKey().getValues());
    }

    private class Parameter
    {
        private double[] values;
        public Parameter( double[] values )
        {
            this.values = values;
        }
        protected double[] getValues(){
            return values;
        }



        @Override
        public int hashCode()
        {
            return 31 + Arrays.hashCode( this.values );
        }
        @Override
        public boolean equals( Object obj )
        {
            if ( this == obj )
                return true;
            if ( obj == null )
                return false;
            if ( this.getClass() != obj.getClass() )
                return false;
            Parameter other = (Parameter) obj;
            if ( !Arrays.equals( this.values, other.values ) )
                return false;
            return true;
        }
    }
}
