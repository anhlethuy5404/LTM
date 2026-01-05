/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_ptulonnhatcuadaycon;

import java.net.*;

/**
 *
 * @author asus
 */
public class Server {
    public static void main(String[] args) {
        try (DatagramSocket server = new DatagramSocket(2207)) {
            System.out.println("Server 2207 dang chay...");
            byte[] buf = new byte[2048];

            while (true) {
                // a. Nhan ket noi tu Client
                DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
                server.receive(receivePacket);
                String info = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Client connect: " + info);

                // b. Gui du lieu mau giong vi du
                String requestId = "REQ123";
                String data = requestId + ";5;3;1,5,2,3,4";
                byte[] sendBuf = data.getBytes();
                server.send(new DatagramPacket(sendBuf, sendBuf.length, 
                        receivePacket.getAddress(), receivePacket.getPort()));

                // c. Nhan ket qua va kiem tra
                byte[] resBuf = new byte[2048];
                DatagramPacket resPacket = new DatagramPacket(resBuf, resBuf.length);
                server.receive(resPacket);
                String result = new String(resPacket.getData(), 0, resPacket.getLength());
                
                System.out.println("Ket qua tu Client: " + result);
                if (result.equals("REQ123;5,5,4")) {
                    System.out.println("=> TRANG THAI: CHINH XAC (PASS)");
                } else {
                    System.out.println("=> TRANG THAI: SAI");
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
