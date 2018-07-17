package Files_service;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;
import static org.junit.runners.Parameterized.*;
import java.io.*;
import java.util.*;

import org.junit.*;
import org.junit.rules.ExpectedException;


public class ExtendedSystemCache extends SystemCache {



    void exportCSV(String path) throws IOException {
        exportCSV(new File(path));
        /*File file = new File(path);
        if(!file.exists())file.createNewFile();

        if (file.exists() && !file.isDirectory()) {


            final OutputStream out = new FileOutputStream(file);
            final PrintStream printStream = new PrintStream(out);
            HashMap<double[],Double> tmp = this.getHashMap();

            for(Map.Entry<double[],Double> entry: getHashMap().entrySet()){
                String line = "";
                double [] localTmp = entry.getKey();
                double result = this.get(localTmp);
                for(int i =0;i<localTmp.length;i++){
                    if(i+1 == localTmp.length){
                        double roundOff = Math.round(localTmp[i] * 100.0) / 100.0;
                        line += roundOff+";";
                    }
                    else {

                        line += (Math.round(localTmp[i] * 100.0) / 100.0) + ",";
                    }
                }
                printStream.print(line.toString());
                printStream.print(result+"\n");
            }
            out.flush();
            out.close();
        }else
            throw new IOException("Can't write to file");*/
    }

    void exportCSV(File inpFile) throws IOException{
        exportCSV(new FileOutputStream(inpFile));
    }

    void exportCSV(OutputStream stream) throws IOException {
        Writer writer = new OutputStreamWriter(stream, "UTF-8");
        HashMap<double[],Double> tmp = this.getHashMap();

        for(Map.Entry<double[],Double> entry: getHashMap().entrySet()){
            String line = "";
            double [] localTmp = entry.getKey();
            double result = this.get(localTmp);
            for(int i =0;i<localTmp.length;i++){
                if(i+1 == localTmp.length){
                    double roundOff = Math.round(localTmp[i] * 100.0) / 100.0;
                    line += roundOff+";";
                }
                else {
                    double roundOff = Math.round(localTmp[i] * 100.0) / 100.0;
                    line += roundOff + ",";
                }
            }
            writer.write(line);
            writer.write(Double.toString(result));
            writer.write("\n");
        }
        writer.close();
        stream.close();
    }

    //B
    void importCSV(String path) throws IOException {
        importCSV(new File(path));
/*
        File file = new File(path);
        if (!file.exists())
            throw new IOException("File doesn't exist!");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        //this.clear();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] array = line.split(";");
            String[] array2 = array[0].split(",");
            double[] arrayDouble = new double[array2.length];
            for (int i = 0; i < array2.length; i++)
                arrayDouble[i] = Double.parseDouble(array2[i]);

            this.put(arrayDouble,Double.parseDouble(array[1]));
        }
        bufferedReader.close();
        //this.printHashMap();*/
    }
    void importCSV(File file) throws IOException{
        importCSV(new FileInputStream(file));
    }
    void importCSV(InputStream stream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        //this.clear();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            // lol
            String[] arraycolon = line.split(";");
            String[] array2 = arraycolon[0].split(",");
            double[] arrayDouble = new double[array2.length];
            for (int i = 0; i < array2.length; i++){
                arrayDouble[i] = Double.parseDouble(array2[i]);
            }
            this.put(arrayDouble,Double.parseDouble(arraycolon[1]));
        }
        bufferedReader.close();
        this.printHashMap();
    }
    //C
    void serialize(String path) throws IOException{
        serialize(new File(path));
        /*FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this.getHashMap());
        objectOutputStream.close();
        fileOutputStream.close();*/
    }
    void serialize(File file) throws IOException{
        serialize(new FileOutputStream(file));
    }
    void serialize(OutputStream stream) throws IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
        objectOutputStream.writeObject(this.getHashMap());
        objectOutputStream.close();
        stream.close();
    }
    //D
    void deserialize(String path) throws IOException{
        deserialize(new File(path));
        /*File file = new File(path);
        if (!file.exists())
            throw new IOException("File doesn't exist!");

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            HashMap<double[],Double> inputObject = (HashMap<double[],Double>) objectInputStream.readObject();
            convert(inputObject);

            objectInputStream.close();
            fileInputStream.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }
    void deserialize(File file) throws IOException{
        deserialize(new FileInputStream(file));
    }
    void deserialize(InputStream stream) throws IOException{
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(stream);
            HashMap<double[],Double> inputObject = (HashMap<double[],Double>) objectInputStream.readObject();
            convert(inputObject);
            objectInputStream.close();

            stream.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void save( String path ) throws IOException
    {
        save(new File(path));
        /*
        DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(path));
        for(Map.Entry<double[],Double> entry: getHashMap().entrySet())
        {
            dataOut.writeInt(entry.getKey().length);
            for(double tmp:entry.getKey()){
                dataOut.writeDouble(tmp);
            }
            dataOut.writeDouble(entry.getValue());
            dataOut.writeUTF("\n");
        }

        //serialize(path);

        */
    }
    void save( File file ) throws IOException
    {
        save( new FileOutputStream(file));
    }

    void save( OutputStream stream ) throws IOException
    {
        DataOutputStream dataOut = new DataOutputStream(stream);
        for(Map.Entry<double[],Double> entry: getHashMap().entrySet())
        {

            int size = entry.getKey().length;
            dataOut.writeInt(size);
            for(double tmp:entry.getKey()){
                dataOut.writeDouble(tmp);
            }
            dataOut.writeDouble(entry.getValue() );
            dataOut.writeUTF("\n");
        }
    }

    void load( String path ) throws IOException{
        //this.clear();
        load(new File(path));
        /*DataInputStream dataIn = new DataInputStream(new FileInputStream(path));

        try{
            int number = dataIn.readInt();
            double[]arrayDouble = new double[number];
            for(int i =0;i<number;i++) {
                arrayDouble[i] = dataIn.readDouble();
            }
            this.put(arrayDouble, dataIn.readDouble());
            dataIn.readUTF();
        }catch (IOException e){

        }
        dataIn.close();
        */

    }

    void load( File file ) throws IOException{
        load(new FileInputStream(file));
    }

    void load( InputStream stream ) throws IOException{
        this.clear();
        DataInputStream dataIn = new DataInputStream(stream);

        try{
            int number = dataIn.readInt();
            double[]arrayDouble = new double[number];
            for(int i =0;i<number;i++)arrayDouble[i]=dataIn.readDouble();

            this.put(arrayDouble, dataIn.readDouble());
            dataIn.readUTF();
        }catch (IOException e){

        }
        dataIn.close();

    }
}
