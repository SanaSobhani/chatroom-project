package com.example.chatroom;

//package serverapp;

import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;


public class Main {
    public static ArrayList <CommunicationHandler> clients = new ArrayList<>();
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(6666);
        //---------------
        while(true)
        {
            Socket clientSocket = serverSocket.accept(); // wait until somebody connect
            InputStreamReader reader = new InputStreamReader(clientSocket.getInputStream()); // receive what client says
            BufferedReader in = new BufferedReader(reader);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
        }
        //---------------
    }
}
class CommunicationHandler extends Thread {

    private Socket socket;
    private static int threadCount = 0;
    private int threadID;
    private String userName;
    BufferedReader in;
    PrintWriter out;
}