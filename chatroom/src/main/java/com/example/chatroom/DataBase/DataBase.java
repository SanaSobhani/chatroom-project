package com.example.chatroom.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    String URL = "jdbc:mysql://localhost/messager"; // address of the database we use
    String UserName ="root";
    String PassWord ="123";
    Connection connection;
    public DataBase() throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection(URL , UserName , PassWord);
        Class.forName("com.mysql.cj.jdbc.Driver");

    }
}
