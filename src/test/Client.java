/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author asus
 */
public class Client {
    public static void main(String[] args) throws Exception{
        String host = "";
        int port = 2201;
        Socket socket = new Socket(host, port);
        GZIPInputStream in = new GZIPInputStream(socket.getInputStream());
        GZIPOutputStream out = new GZIPOutputStream(socket.getOutputStream());
        out.write(host.getBytes());
        out.finish();
        out.flush();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] rev = new byte[1024];
        int len;
        while((len=in.read(rev)) != -1){
            baos.write(rev, 0, len);
        }
        String res = baos.toString();
    }
}
