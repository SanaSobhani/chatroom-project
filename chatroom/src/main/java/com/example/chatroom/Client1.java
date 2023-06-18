package com.example.chatroom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Client1 {
    static String userName , passWord , bio;
    static int birthYear;
    public static void main(String[] args) {
        GetInformation getInformation = new GetInformation();
        getInformation.getInformation();
    }
}
class GetInformation {
    void getInformation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("userName : ");
        Client1.userName = sc.nextLine();
        System.out.println("passWord : ");
        Client1.passWord = sc.nextLine();
        System.out.println("birthYear : ");
        Client1.birthYear = sc.nextInt();
        sc.nextLine();
        System.out.println("Bio : ");
        Client1.bio = sc.nextLine(); // receive information
        saveInformationByDataBase();
    }
    void saveInformationByDataBase(){
        String URL = "jdbc:mysql://localhost/clientinformation"; // address of the database we use
        String UserName ="root";
        String PassWord ="123";
        try{ // need try catch to see if ant=y Exception will happen
            //Class.forName("com.mysql.jdbc.Driver"); // code says we dont need this
            Connection connection = DriverManager.getConnection(URL ,UserName , PassWord); // we need a connection to dataBase
            System.out.println("connected");
            String sqlCmd =String.format("INSERT INTO `information` (`UserName`, `PassWord`, `BirthYear` , `Bio` ) VALUES ('%s', '%s', '%d' , '%s' )" ,Client1.userName , Client1.passWord ,Client1.birthYear ,Client1.bio);
            Statement statement = connection.prepareStatement(sqlCmd); // need statement to prepare command
            statement.execute(sqlCmd); // do the command
            connection.close();
            System.out.println("finished");
        }
        catch (Exception ex){
            return;
        }
    }
}
