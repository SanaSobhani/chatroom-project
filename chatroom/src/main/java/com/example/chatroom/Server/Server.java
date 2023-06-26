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

    }
}

