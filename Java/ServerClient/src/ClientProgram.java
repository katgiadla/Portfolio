import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientProgram {
    public static void main(String [] args){

        CustomClient client = new CustomClient();
        Scanner scanner = new Scanner(System.in);
        //CustomServer server = new CustomServer();
        try {

            //client.connect("localhost",5005);
            //client.login("Mateusz","Tchorek");
            // lol
//server.run(5005);

            while(true){
                if(client.isAdmin())System.out.println("Welcome admin: \n 1. connect  2. disconnect  3. login \n 4. logout  5. ping  6. echo \n 7. terminate 8. Exit 9. Kick");
                else System.out.println("Main method menu: \n 1. connect  2. disconnect  3. login \n 4. logout  5. ping  6. echo \n 7. terminate 8. Exit");
                switch(scanner.nextInt()){
                    case 1:
                        client.connect("localhost",5005);
                        break;
                    case 2:
                        client.disconnect();
                        break;
                    case 3:
                        System.out.println("Put name");

                        String tmp1 = scanner.nextLine();
                        String tmp3 = scanner.nextLine();
                        System.out.println("Put password");
                        String tmp2 = scanner.nextLine();
                        client.login(tmp3,tmp2);
                        //System.out.println("After login");
                        break;
                    case 4:
                        client.logout();
                        break;
                    case 5:
                        client.ping();
                        break;
                    case 6:
                        String tmp5 = scanner.nextLine();
                        System.out.println("Podaj tekst");
                        String tmp6 = scanner.nextLine();
                        //String tmp7 = scanner.nextLine();
                        client.echo(tmp6);
                        break;

                    case 7:
                        client.terminate();

                        break;
                    case 8:
                        System.exit(0);
                        break;
                    case 9:
                        if(client.isAdmin() == false){
                            System.out.println("No permission");
                            break;
                        }
                        else{
                            System.out.println("Admin permission");
                            String tmp73 = scanner.nextLine();
                            System.out.println("Input username of user to kick ");
                            String tmp8 = scanner.nextLine();
                            client.kick(tmp8);
                        }
                        break;
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
