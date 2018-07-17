import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;

public class CustomClient {
    private Socket clientSocket;
    DataOutputStream os;
    DataInputStream in ;
    private HashMap<String,String> userList;
    private int counter;
    private boolean isAdmin;
    private boolean hasLogged;
    CustomClient(){
        hasLogged = false;
        isAdmin = false;
        counter = 0;
        userList = new HashMap<String,String>();
        in = null;
        os = null;
        clientSocket = null;
    }

    public void connect( String host, int port ) throws IOException {
        try{

            clientSocket = new Socket(host,port);
            os = new DataOutputStream(clientSocket.getOutputStream()); //write
            in = new DataInputStream((clientSocket.getInputStream())); //read
            os.writeInt(6);
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e ){
            e.printStackTrace();
        }

    }

    public void disconnect(){

        if(!clientSocket.isClosed()){
            try {
                isAdmin = false;
                os.writeInt(3);
                os.flush();
                os.close();
                in.close();
                clientSocket.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean login( String username, String password ) throws  IOException{

        try {
            os.writeInt(7);
            os.writeUTF(username);
            os.writeUTF(password);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("login failed");
            return false;
        }
        try {
            String response = in.readUTF();
            //System.out.println("isAdmin readBoolean");
            isAdmin = in.readBoolean();
            if(isAdmin) System.out.println("Login: Admin");
            else System.out.println("You have logged: " + response);
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean logout() throws IOException{
        try {
            isAdmin = false;
            os.writeInt(8);
            //os.writeUTF(username);
            //os.writeUTF(password);
            String response = in.readUTF();
            System.out.println("Status: " + response);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("logout failed");
            return false;
        }


        return true;
    }


    public boolean ping() throws IOException{
        try {
            os.writeInt(1);

            if (in.readInt() == 1) {
                System.out.println("Ping received");
                return true;
            }
            else throw new IOException();
        }
        catch(IOException e){
            System.out.println("Not logged");
            return false;
        }

    }
    public boolean echo(String text) throws IOException{
        try {
            os.writeInt(2);
            os.writeUTF(text);

            if(in.readInt()==2){
                System.out.println("Something has arrived");
                String tmp = in.readUTF();
                if(tmp.equals(text)){
                    System.out.println(tmp);
                    System.out.println("Echo works perfectly");
                    return true;
                }
                else throw new IOException();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went bad");
            return false;
        }
        return false;
    }

    public void terminate(){
        try {
            os.writeInt(4);
            os.flush();
            os.close();
            in.close();
            clientSocket.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kick(String kickGuy){
        try {
            os.writeInt(9);
            os.writeUTF(kickGuy);
            String tmp = in.readUTF();
            System.out.println(tmp);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isAdmin() {
        return isAdmin;
    }

}
