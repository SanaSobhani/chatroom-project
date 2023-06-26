package com.example.chatroom.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(6666);
        while (true) {
            Socket clientSocket = serverSocket.accept(); // wait until somebody connect
            InputStreamReader reader = new InputStreamReader(clientSocket.getInputStream());// use in bufferReader
            BufferedReader in = new BufferedReader(reader);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        }
        //---------------
    }

    class CommunicationHandler extends Thread {

        private Socket socket;
        private static int threadCount = 0;
        private int threadID; // make the thread count
        private String userName;
        BufferedReader in;
        PrintWriter out;

        public CommunicationHandler(Socket socket, BufferedReader in, PrintWriter out) throws SQLException, ClassNotFoundException {
            this.socket = socket;
            threadCount++;
            this.in = in;
            this.out = out;
            threadID = threadCount;
        }
        @Override
        public void run() {
            try {
                String message;
                out.println("Enter a user name");
                userName = in.readLine();
                out.println("@"+userName+" welcome to chatroom"); // send message to one that recently join not others
                //--------------

}catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

