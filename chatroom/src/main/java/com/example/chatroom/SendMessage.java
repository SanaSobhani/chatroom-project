package streams;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class SendMessages extends Thread
{
    private String message = "null";
    private Socket clientSocket;
    PrintWriter out ;
    public SendMessages(Socket clientSocket,PrintWriter out){
        this.out = out;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        try {
            while (!message.equals("EXIT")) {
                message = sc.nextLine();
                if (message.equals("PING")) {
                    try {
                        System.out.println("ping:"+calculatingPing()+" ns");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else
                    out.println(message);
            }
        }finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private long calculatingPing() throws IOException {
        InputStreamReader reader =new InputStreamReader(clientSocket.getInputStream());
        BufferedReader in = new BufferedReader(reader);
        long time1 = System.nanoTime();
        out.println("calculating ping");
        System.out.println(in.readLine());
        long time2 = System.nanoTime();
        return time2-time1;
    }
}