package streams;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveMessage extends Thread {
    private Socket clientSocket;
    private InputStreamReader inputStreamReader;
    BufferedReader in;

    public ReceiveMessage(InputStreamReader inputStreamReader, Socket clientSocket) {
        this.inputStreamReader = inputStreamReader;
        this.clientSocket = clientSocket;
        in = new BufferedReader(inputStreamReader);
    }

    @Override
    public void run() {
        while (true){
            try {
                if (in.ready()) {
                    System.out.println(in.readLine());
                }
            }
            catch (IOException e) {
                System.out.println(e.getMessage());;
            }
        }

    }
}