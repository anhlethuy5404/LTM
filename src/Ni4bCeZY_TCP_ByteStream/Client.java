/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ni4bCeZY_TCP_ByteStream;

import java.io.*;
import java.net.*;

/**
 *
 * @author asus
 */
public class Client {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2206;
        try(Socket socket = new Socket(host, port)){
            socket.setSoTimeout(5000);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            
            String studentCode = "B22DCCN047";
            String qCode = "Ni4bCeZY";
            String mess = studentCode + ";" + qCode;
            output.write((mess+"\n").getBytes());
            output.flush();
            
            byte[] buffer = new byte[1024];
            int byteRead = input.read(buffer);
            if(byteRead==-1){
                System.out.println("Khong nhan duoc dl tu server");
                return;
            }
            String num = new String(buffer, 0, byteRead).trim();
            String[] numStr = num.split("\\|");
            long sum = 0;
            for(String x : numStr){
                sum += Long.parseLong(x.trim());
            }
            
            System.out.println(num);
            System.out.println(sum);
            
            String sumStr = String.valueOf(sum);
            byte[] sumBytes = (sumStr + '\n').getBytes();
            output.write(sumBytes);
            output.flush();            
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
