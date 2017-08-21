package com.company;



import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
        System.out.println("Server/Client?(S/C)");
        while (true){
            char answer = Character.toLowerCase(in.nextLine().charAt(0));
            if (answer == 's') {
                new Server();
                break;
            } else if (answer == 'c') {
                new Client();
                break;
            }
            else if(answer == 'e'){
                break;
            }
            else {
                System.out.println("error typing pls try again");
            }
        }
    }
}










