/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_sapxeptheovitribd;

import java.net.*;

/**
 *
 * @author asus
 */
public class Server {
    public static void main(String[] args) {
        try (DatagramSocket server = new DatagramSocket(2207)) {
            System.out.println("Server 2207 dang chay (Sort Position Test)...");
            byte[] buf = new byte[2048];

            while (true) {
                // a. Nhan yeu cau tu Client
                DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
                server.receive(receivePacket);
                
                InetAddress address = receivePacket.getAddress();
                int port = receivePacket.getPort();

                // b. Gui chuoi xao tron mau
                String requestId = "REQ123";
                String data = "veM3xgA1g:4,IPFfgEanY:5,aWXlSzDwe:2,PHupvPc:3,PR3gH8ahN:6,UEEKHLIt:7,M6dpWTE:1";
                String sendStr = requestId + ";" + data;
                
                byte[] sendBuf = sendStr.getBytes();
                server.send(new DatagramPacket(sendBuf, sendBuf.length, address, port));

                // c. Nhan ket qua va kiem tra
                byte[] resBuf = new byte[2048];
                DatagramPacket resPacket = new DatagramPacket(resBuf, resBuf.length);
                server.receive(resPacket);
                String clientResult = new String(resPacket.getData(), 0, resPacket.getLength());
                
                System.out.println("Client gui: " + clientResult);
                String expected = "REQ123;M6dpWTE,aWXlSzDwe,PHupvPc,veM3xgA1g,IPFfgEanY,PR3gH8ahN,UEEKHLIt";
                
                if (clientResult.equals(expected)) {
                    System.out.println("=> TRANG THAI: CHINH XAC (PASS)");
                } else {
                    System.out.println("=> TRANG THAI: SAI");
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
