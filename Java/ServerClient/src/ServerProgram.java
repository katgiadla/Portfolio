import java.io.IOException;

public class ServerProgram {
    public static void main(String [] args) {
        CustomServer server = new CustomServer();

        try
        {
            server.run(5005);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}