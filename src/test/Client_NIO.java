/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.*;

/**
 *
 * @author asus
 */
public class Client_NIO {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("203.162.10.109", 2209));
        String mess = "B22";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        buffer.put(mess.getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        
        buffer.clear();
        int rev = socketChannel.read(buffer);
        if(rev > 0){
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            String str = new String(data);
            
            buffer.clear();
            buffer.put(str.getBytes());
            buffer.flip();
            while(buffer.hasRemaining()) socketChannel.write(buffer);
        }
    }
}
