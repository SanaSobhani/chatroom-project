package allClients;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException, InterruptedException {
        //--------------
        Scanner sc = new Scanner(System.in);
        long time1 = System.nanoTime();
        Socket clientSocket = new Socket("127.0.0.1", 6666);
        long time2 = System.nanoTime();
        System.out.println("Client connected to the server...\nping:"+(time2-time1));
        //---------------
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        //--------------
        InputStreamReader reader =new InputStreamReader(clientSocket.getInputStream());
        BufferedReader in = new BufferedReader(reader);
        //--------------
        ReceiveMessage receiveMessage = new ReceiveMessage(reader,clientSocket);
        SendMessages sendMessages = new SendMessages(clientSocket,out);
        Thread receiver = new Thread(receiveMessage);
        Thread sender = new Thread(sendMessages);
        sender.start();
        receiver.start();
        receiver.join();
        sender.join();
        out.close();
        clientSocket.close();
    }
}