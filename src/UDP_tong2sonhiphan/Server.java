/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_tong2sonhiphan;

import java.net.*;

/**
 *
 * @author asus
 */
public class Server {
    public static void main(String[] args) {
        try (DatagramSocket server = new DatagramSocket(2208)) {
            System.out.println("Server 2208 dang chay (Binary Sum Test)...");
            byte[] buf = new byte[2048];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
                server.receive(receivePacket);
                
                InetAddress address = receivePacket.getAddress();
                int port = receivePacket.getPort();

                // Gửi dữ liệu mẫu giống ví dụ
                String requestId = "REQ123";
                String b1 = "0100011111001101";
                String b2 = "1101000111110101";
                String sendStr = requestId + ";" + b1 + "," + b2;
                
                byte[] sendBuf = sendStr.getBytes();
                server.send(new DatagramPacket(sendBuf, sendBuf.length, address, port));

                // Nhận kết quả từ Client
                byte[] resBuf = new byte[2048];
                DatagramPacket resPacket = new DatagramPacket(resBuf, resBuf.length);
                server.receive(resPacket);
                String clientResult = new String(resPacket.getData(), 0, resPacket.getLength());
                
                System.out.println("Client gui: " + clientResult);
                // Kiểm tra: 0100011111001101 (18381) + 1101000111110101 (53749) = 72130
                if (clientResult.equals("REQ123;72130")) {
                    System.out.println("=> TRANG THAI: CHINH XAC (PASS)");
                } else {
                    System.out.println("=> TRANG THAI: SAI");
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
