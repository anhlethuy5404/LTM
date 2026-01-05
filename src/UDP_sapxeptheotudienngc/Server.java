/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_sapxeptheotudienngc;

import java.net.*;

/**
 *
 * @author asus
 */
public class Server {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(2208)) {
            System.out.println("Server dang doi tai cong 2208...");
            byte[] receiveData = new byte[1024];

            while (true) {
                // a. Nhan thong tin tu Client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String request = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Nhan tu client: " + request);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // b. Gui requestId;data
                String requestId = "REQ999";
                String data = "The quick brown fox";
                String sendStr = requestId + ";" + data;
                
                byte[] sendData = sendStr.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                // c. Nhan ket qua kiem tra
                byte[] resultData = new byte[1024];
                DatagramPacket resultPacket = new DatagramPacket(resultData, resultData.length);
                serverSocket.receive(resultPacket);
                String clientResult = new String(resultPacket.getData(), 0, resultPacket.getLength());
                
                System.out.println("Ket qua tu Client: " + clientResult);
                
                // Kiem tra logic: quick,fox,brown,The
                if (clientResult.equals("REQ999;quick,fox,brown,The")) {
                    System.out.println("TRANG THAI: DUNG");
                } else {
                    System.out.println("TRANG THAI: SAI");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
