/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uhXzJ87l;

import java.io.*;
import java.net.*;

/**
 *
 * @author asus
 */
public class Client {
    public static void main(String[] args) throws IOException {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN047";
        String qCode = "uhXzJ87l";
        String mess = studentCode + ";" + qCode;
        try(Socket socket = new Socket(host, port)){
            socket.setSoTimeout(5000);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(mess);
            out.flush();
            
            int response = in.readInt();
            out.writeUTF(convert(response));
            out.flush();
        }
    }
    public static String convert(int x){
        String bin = Integer.toBinaryString(x);
        String hex = Integer.toHexString(x);
        return bin + ";" + hex;
    }
}
