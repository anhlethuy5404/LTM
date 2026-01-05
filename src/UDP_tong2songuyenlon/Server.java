/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_tong2songuyenlon;

import java.net.*;
import java.math.*;

/**
 *
 * @author asus
 */
public class Server {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(2207)) {
            System.out.println("UDP Server đang đợi tại cổng 2207...");
            byte[] receiveData = new byte[1024];

            while (true) {
                // a. Nhận mã SV từ Client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String request = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Nhận từ Client: " + request);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // b. Gửi requestId;a;b (a và b là số > 100 chữ số)
                String requestId = "REQ12345";
                String a = "99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
                String b = "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
                String sendStr = requestId + ";" + a + ";" + b;
                
                byte[] sendData = sendStr.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                // c. Nhận kết quả từ Client để kiểm tra (Testcase)
                byte[] resultData = new byte[2048];
                DatagramPacket resultPacket = new DatagramPacket(resultData, resultData.length);
                serverSocket.receive(resultPacket);
                String clientResult = new String(resultPacket.getData(), 0, resultPacket.getLength());
                
                // Kiểm tra logic
                BigInteger biA = new BigInteger(a);
                BigInteger biB = new BigInteger(b);
                String expected = requestId + ";" + biA.add(biB).toString() + ";" + biA.subtract(biB).toString();
                
                if (clientResult.equals(expected)) {
                    System.out.println("KẾT QUẢ ĐÚNG! -> " + clientResult);
                } else {
                    System.out.println("SAI RỒI! \nMong đợi: " + expected + "\nNhận được: " + clientResult);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
