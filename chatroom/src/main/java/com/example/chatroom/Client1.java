package com.example.chatroom;

import java.util.Scanner;

public class Client1 {
    static String userName , passWord , bio;
    static int birthYear;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        userName=sc.nextLine();
        passWord=sc.nextLine();
        birthYear=sc.nextInt();sc.nextLine();
        bio=sc.nextLine();
    }
}
