import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class CustomServer {

    private int counter;
    private Thread [] threadList;
    private ServerSocket server;
    Thread serverThread;
    Socket incomingCalls;
    private HashMap<String,String> clientList;
    private HashMap<HashMap<String, String>,Thread> list ;
    private String admin = "Admin";
    private String passw = "AGH";
    public boolean isAdmin = false;
    private HashMap<String,Integer> clientIdentList;


    public CustomServer(){
        clientIdentList = new HashMap<String,Integer>();
        counter = 0;
        threadList = new Thread[5];
        clientList = new HashMap<String,String>();
    }

    public void run( int port ) throws IOException {
        try{
            //System.out.println("Server is running");
            server = new ServerSocket(port);
            //System.out.println(server.getLocalSocketAddress());
            serverThread = new Thread(()->{
                while(counter<5) {

                        try {


                            incomingCalls = server.accept();
                            threadList[counter] = new Thread(() -> {
                                if(counter>5){
                                    System.out.println("5 threads already. Stop server.");
                                    Thread.currentThread().interrupt();
                                }
                                Socket localSocket = incomingCalls;
                                System.out.println("Thread from user number: " + (counter) + " name: " + localSocket.getInetAddress());

                                try {
                                    synchronized (localSocket) {
                                        doClientLogic(localSocket);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            threadList[counter].start();
                            //threadList[counter].join();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } //catch (InterruptedException e) {
                        //  e.printStackTrace();
                        //}
                        counter++;

                }

            });
            serverThread.start();
            //serverThread.join();
        }catch(IOException e){
            e.printStackTrace();
        } 
    }


    public void stop(){
        try {
            clientList.clear();
            clientIdentList.clear();
            for (int i = 0; i < 5; i++) {
                if(!threadList[i].isAlive())continue;
                threadList[i].join();

            }
            if(!incomingCalls.isClosed()){ //incomingCalls.getInetAddress().isReachable(10000)
                incomingCalls.close();

                serverThread.join(); //welp jak zamienic

                if(!server.getInetAddress().isReachable(10000)){
                    server.close();
                }
            }
        } catch (SocketException e){
            System.out.println("Connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        finally {
            counter = 0;
        }
        
    }


    private static final int PING_FRAME = 1;
    private static final int HELLO_WORLD_FRAME = 2;
    private static final int CLOSE = 3;
    private static final int CLOSE_ALL = 4;
    private static final int WAIT = 5;
    private static final int CONNECT = 6;
    private static final int LOGIN = 7;
    private static final int LOGOUT = 8;
    private static final int KICK = 9;

    private void doClientLogic( Socket socket ) throws IOException{
        //System.out.println("1 Ping frame \n 2 Hello world \n 3 Close single \n 4 Close server \n 5 Wait 5 sec");
        DataInputStream input = new DataInputStream( socket.getInputStream() );
        DataOutputStream output = new DataOutputStream( socket.getOutputStream() );
        String localUsername = "user";
        String localPassword = "password";
        int localId = counter;
        boolean isInside = false;
        try {

            while (!Thread.currentThread().interrupted()) {
                System.out.println(" ");
                switch (input.readInt()) {
                    case CONNECT:
                        System.out.println("CONNECTED");
                        clientList.put(localUsername, localPassword);
                        clientIdentList.put(localUsername,localId);
                        isInside = true;
                        break;

                    case LOGOUT:


                        for (Map.Entry<String, String> search : clientList.entrySet()) {
                            if (search.getKey().equals(localUsername) && search.getValue().equals(localPassword)) {
                                clientList.remove(localUsername, localPassword);
                                output.writeUTF("User "+localUsername+" has been logged out");
                                isAdmin = false;
                                clientIdentList.remove(localUsername,localId);
                            }
                            break;
                        }

                        break;
                    case LOGIN:
                        boolean isAlready = false;
                        String tmp1, tmp2;
                        tmp1 = input.readUTF();
                        tmp2 = input.readUTF();
                      
                        localUsername = tmp1;
                        localPassword = tmp2;
                        for (Map.Entry<String, String> search : clientList.entrySet()) {
                            if (search.getKey().equals(tmp1)) {
                                System.out.println("User already here");
                                isAlready = true;
                                output.writeUTF("User already here");
                                output.writeBoolean(false);
                                break;
                            }
                        }

                    if(!isAlready){
                        clientList.put(tmp1, tmp2);
                        clientIdentList.put(tmp1,localId);
                        //System.out.println("Server: Before checking if user is Admin");
                        if (localUsername.equals(admin) && localPassword.equals(passw)) {
                            isAdmin = true;
                            System.out.println("Admin has joined to server.");
                            output.writeUTF("Welcome Admin");

                            output.writeBoolean(true);
                        } else {
                            System.out.println("User "+tmp1+" has joined to server.");
                            output.writeUTF("Welcome " + tmp1);
                            output.writeBoolean(false);
                        }

                }
                        break;
                    case CLOSE: //? idk what i'm doing
                        System.out.println("Close");
                        try {

                            for(Map.Entry<String, String> search: clientList.entrySet()){
                                if(search.getKey().equals(localUsername) && search.getValue().equals(localPassword) ) {
                                    clientList.remove(localUsername, localPassword);
                                    isAdmin = false;
                                    output.writeUTF("User "+localUsername+" has been logged out");

                                    //boolean clientConnectedThroughHashmap = false;
                                    for(Map.Entry<String,Integer> searchIndex:clientIdentList.entrySet()){
                                        if(searchIndex.getKey().equals(localUsername)){
                                            int tmpIndex = searchIndex.getValue();
                                            clientIdentList.remove(localUsername,searchIndex.getValue());
                                            threadList[tmpIndex].interrupt();
                                            //clientConnectedThroughHashmap = true;
                                        }
                                    }
                                   // if(!clientConnectedThroughHashmap){
                                    //    clientIdentList.put("noname",counter);
                                    //}
                                }
                                break;
                            }

                            socket.close();
                            output.flush();
                            input.close();
                            output.close();
                            //threadList[localId].interrupt();
                            counter--;
                            Thread.currentThread().interrupt();

                        } 
                        finally {
                            socket.close();
                        }
                        break;
                    case CLOSE_ALL:
                        System.out.println("Connection closed");
                        input.close();
                        output.flush();
                        output.close();
                        this.stop();
                        System.exit(0);
                        break;

                    case WAIT:
                        System.out.println("Wait");
                        try {
                            Thread.currentThread().wait(50000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;

                    case PING_FRAME:
                        boolean isFound = false;
                        //String tmpPIng = input.readUTF();
                        for(Map.Entry<String, String> search: clientList.entrySet()){
                            if(search.getKey().equals(localUsername) && search.getValue().equals(localPassword)  ) {
                                System.out.println("User is logged");
                                System.out.println("Ping Frame");
                                output.writeInt(PING_FRAME); // odsyłamy typ ramki (pusta ramka)
                                output.flush();
                                isFound = true;
                                break;
                            }

                        }
                        if(!isFound){
                            output.writeInt(0);
                        }

                        break;
                    case HELLO_WORLD_FRAME:
                        for(Map.Entry<String, String> search: clientList.entrySet()){
                            if(search.getKey().equals(localUsername) && search.getValue().equals(localPassword)  ) {
                                String echoData = input.readUTF();
                                System.out.println("User is logged");
                                System.out.println("Server Echo");
                                output.writeInt(HELLO_WORLD_FRAME); // typ ramki
                                output.writeUTF(echoData); // dane ramki

                                output.flush();
                                break;
                                }
                            }
                        break;
                    case KICK:
                        boolean isHere = false;
                        int tmpIndex;
                        String userKick = input.readUTF();

                        for(Map.Entry<String, String> search: clientList.entrySet()){
                            if(search.getKey().equals(userKick) ) {
                                isHere = true;
                                clientList.remove(userKick);

                                System.out.println("Kicked");
                                break;
                            }
                        }
                        if(isHere) {
                            for (Map.Entry<String, Integer> searchIndex : clientIdentList.entrySet()) {
                                if (searchIndex.getKey().equals(userKick)) {

                                    tmpIndex = searchIndex.getValue();
                                    clientIdentList.remove(userKick);
                                    counter--;
                                    threadList[tmpIndex].interrupt();

                                }
                            }
                        }
                        if(!isHere){
                            System.out.println("Not found");
                        }
                        break;
                }
            }

        }catch (SocketException e){
            System.out.println("Connection closed");
        }

        finally {
            output.flush();
            input.close();
            output.close();
            socket.close();
        }
    }
}
