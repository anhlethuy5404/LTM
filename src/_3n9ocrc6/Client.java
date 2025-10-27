/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package _3n9ocrc6;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author asus
 */
public class Client {
    public static void main(String[] args) throws IOException {
        String host = "203.162.10.109";
        int port = 2208;
        String studentCode = "B22DCCN047";
        String qCode = "3n9ocrc6";
        String mess = studentCode + ";" + qCode;
        try(Socket socket = new Socket(host, port)){
            socket.setSoTimeout(5000);
            BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            write.write(mess+"\n");
            write.flush();
            
            String response = read.readLine();
            write.write(process(response)+"\n");
            System.out.println(response);
            System.out.println(process(response));
            write.flush();
        }
    }
    public static String process(String input){
        StringBuilder sb = new StringBuilder();
        Set<Character> s = new LinkedHashSet<>();
        for(int i=0; i<input.length(); i++){
            char c = input.charAt(i);
            if(Character.isLetter(c)){
                if(s.add(c)){
                    sb.append(c);
                }
            }
        }
        return sb.toString().trim();
    }
}
