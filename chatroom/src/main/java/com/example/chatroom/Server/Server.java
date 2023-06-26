package com.example.chatroom.Server;

import com.example.chatroom.DataBase.DataBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Server {
    public static ArrayList<CommunicationHandler> clients = new ArrayList<>();
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(6666);
       DataBase dataBase = new DataBase();
        while (true) {
            Socket clientSocket = serverSocket.accept(); // wait until somebody connect
            InputStreamReader reader = new InputStreamReader(clientSocket.getInputStream());// use in bufferReader
            BufferedReader in = new BufferedReader(reader);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            CommunicationHandler cHandler = new CommunicationHandler(clientSocket,in,out,dataBase);
            clients.add(cHandler);
            cHandler.start();
            //System.out.println(cHandler.isInterrupted());
        }
        //---------------
    }

    static class CommunicationHandler extends Thread {

        private Socket socket;
        private static int threadCount = 0;
        private int threadID; // make the thread count
        private String userName;
        BufferedReader in;
        PrintWriter out;
        DataBase dataBase;

        public CommunicationHandler(Socket socket, BufferedReader in, PrintWriter out , DataBase dataBase) throws SQLException, ClassNotFoundException {
            this.socket = socket;
            threadCount++;
            this.in = in;
            this.out = out;
            threadID = threadCount;
            this.dataBase = dataBase;
        }
        @Override
        public void run() {
            try {
                String message;
                out.println("Enter a user name");
                userName = in.readLine();
                out.println("@"+userName+" welcome to chatroom"); // send message to one that recently join not others
                //--------------
                if(dataBase.select().size()!=0) // send messages to other client if there is any online client in chatRoom
                {
                    for(String msg : dataBase.select())
                        out.println(msg);// send messages to one who is online
                }
                else
                    out.println("chatroom is empty"); // send message to one is connected not others
                //---------------
                while ((message = in.readLine()) != null) { // while reading message from clients is not null
                    if(!message.equals("calculating ping") && !message.equals("PING")){
                        dataBase.saveToDataBase(message,userName); // get messages and save them to DataBase
                        if(message.equals("EXIT"))
                            socket.close();
                        for(CommunicationHandler ch : Server.clients) {
                            if(ch.threadID!=this.threadID)
                                ch.sendToClients(message,this.userName); // send message we received to other clients except the client who send the message
                        }
                    }
                    if(message.equals("calculating ping"))
                    {
                        out.println("from server"); // the message which we will send client to calculate ping
                    }
                    //-------------------------
                    System.out.printf("Server %d: %s\n" ,threadID , message); // message in chatRoom(Server)
                }
                //---------------
                in.close();
                socket.close();
            }
            catch (IOException | SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        private void sendToClients(String message,String userName){
            out.println(userName+" : "+message);
        }
    }
}

