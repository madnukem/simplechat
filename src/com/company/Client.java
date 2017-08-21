package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class Client {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;


    public Client() {

        System.out.println("Pls type ip");

        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();


        try {
            socket = new Socket(ip, Const.port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("nick?");
            out.println(scanner.nextLine());

            Resender resender = new Resender();
            resender.start();

            String str = "";
            while (!str.equals("exit")) {
                str = scanner.nextLine();
                out.println(str);
            }

            resender.setStop();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Потоки не закрылись!");
        }
    }


    private class Resender extends Thread{
        private boolean stoped;
        public  void setStop(){
            stoped = true;
        }
        @Override
        public void run(){
            try{
                while(!stoped){
                    String str = in.readLine();
                    System.out.println(str);
                }
            } catch (IOException e){
                System.err.println("Ошибка при получении собщения.");
                e.printStackTrace();
            }
        }
    }
}