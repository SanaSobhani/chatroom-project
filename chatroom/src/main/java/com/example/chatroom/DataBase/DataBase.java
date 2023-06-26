package com.example.chatroom.DataBase;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    String URL = "jdbc:mysql://localhost/messager"; // address of the database we use
    String UserName ="root";
    String PassWord ="123";
    Connection connection;
    public DataBase() throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection(URL , UserName , PassWord);
        Class.forName("com.mysql.cj.jdbc.Driver");

    }
    public ArrayList<String> select() throws SQLException {
        ArrayList<String> messagesArrayList = new ArrayList<>();
        String sqlCmd ="SELECT  UserName , Message FROM messages";
        Statement statement = connection.prepareStatement(sqlCmd);
        ResultSet resultSet = statement.executeQuery(sqlCmd);
        while(resultSet.next()){
            messagesArrayList.add(resultSet.getString("UserName") + " : " +resultSet.getString("Message"));
        }
        return messagesArrayList;
    }
    void saveToDataBase(String message, String clientUserName) throws SQLException, ClassNotFoundException {
        String sqlCmd =String.format("INSERT INTO `messages` (`NumberOfMessages`, `UserName`, `Message`) VALUES (NULL,'%s', '%s')" , clientUserName ,message);
        Statement statement = connection.prepareStatement(sqlCmd); // need statement to prepare command
        statement.execute(sqlCmd); // do the command
    }
}
