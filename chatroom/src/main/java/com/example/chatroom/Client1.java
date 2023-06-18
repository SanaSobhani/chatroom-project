package com.example.chatroom;

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
    }
}
