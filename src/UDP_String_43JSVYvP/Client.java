/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_String_43JSVYvP;

import java.math.*;
import java.net.*;

/**
 *
 * @author asus
 */
public class Client {
    public static void main(String[] args) {
        String studentCode = "B22DCCN047";
        String qCode = "43JSVYvP";
        int port = 2208;
        String host = "203.162.10.109";
        String mess = ";" + studentCode + ";" + qCode;
        try(DatagramSocket socket = new DatagramSocket()){
            InetAddress address = InetAddress.getByName(host);
            byte[] send = mess.getBytes();
            DatagramPacket sPacket = new DatagramPacket(send, send.length, address, port);
            socket.send(sPacket);
            
            byte[] rev = new byte[1024];
            DatagramPacket rpacket = new DatagramPacket(rev, rev.length);
            socket.receive(rpacket);
            String response = new String(rpacket.getData(), 0, rpacket.getLength());
            String[] parts = response.split(";");
            String requestId = parts[0];
            String[] bins = parts[1].split(",");
            String sum = cal(bins[0], bins[1]);
            byte[] send2 = (requestId + ";" + sum).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(send2, send2.length, address, port);
            socket.send(sendPacket);
        } catch (Exception e){
            
        }
    }
    public static String cal(String s1, String s2){
        BigInteger b1 = new BigInteger(s1, 2);
        BigInteger b2 = new BigInteger(s2, 2);
        BigInteger sum = b1.add(b2);
        return sum.toString();
    }
}
