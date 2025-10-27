/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author asus
 */
public class Client {
    public static void main(String[] args) throws ClassNotFoundException {
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN047";
        String qCode = "zyd32j9n";
        String mess = ";" + studentCode + ";" + qCode;
        try(DatagramSocket socket = new DatagramSocket()){
            InetAddress address = InetAddress.getByName(host);
            byte[] send = mess.getBytes();
            DatagramPacket sPacket = new DatagramPacket(send, send.length, address, port);
            socket.send(sPacket);
            
            byte[] receive = new byte[1024];
            DatagramPacket rPacket = new DatagramPacket(receive, receive.length);
            socket.receive(rPacket);
            byte[] response = Arrays.copyOf(rPacket.getData(), rPacket.getLength());
            String requestId = new String(response, 0, 8);
            byte[] book = Arrays.copyOfRange(response, 8, response.length);
            Book b;
            try(ByteArrayInputStream bais = new ByteArrayInputStream(book);
                    ObjectInputStream ois = new ObjectInputStream(bais)){
                b = (Book) ois.readObject();
            }
            System.out.println(b);
            b.setTitle(normalizeName(b.getTitle()));
            b.setAuthor(normalizeAuthor(b.getAuthor()));
            b.setIsbn(normalizeIsbn(b.getIsbn()));
            b.setPublishDate(normalizeDate(b.getPublishDate()));
            System.out.println(b);
            byte[] res;
            try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos)){
                oos.writeObject(b);
                oos.flush();
                res = baos.toByteArray();
            }
            byte[] finalres = new byte[8 + res.length];
            System.arraycopy(requestId.getBytes(), 0, finalres, 0, 8);
            System.arraycopy(res, 0, finalres, 8, res.length);
            DatagramPacket resPacket = new DatagramPacket(finalres, finalres.length, address, port);
            socket.send(resPacket);
        }catch(IOException e){
            
        }
    }
    public static String normalizeName(String name){
        return Arrays.stream(name.toLowerCase().split("\\s+"))
                .map(word -> word.isEmpty() ? word : Character.toUpperCase(word.charAt(0))+ word.substring(1))
                .collect(Collectors.joining(" "));
    }
    public static String normalizeAuthor(String author){
        String[] words = author.split("\\s+");
        String last = words[0].toUpperCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < words.length; i++) {
            sb.append(words[i].trim()).append(" ");
        }
        String first = normalizeName(sb.toString().trim());
        return last + ", " + first;
    }
    public static String normalizeIsbn(String isbn){
        String num = isbn.replaceAll("[^0-9]", "");
        if(num.length()>=13){
            return num.substring(0,3) + "-" +
                    num.substring(3,4) + "-" +
                    num.substring(4,6) + "-" +
                    num.substring(6,12) + "-" +
                    num.substring(12);
        }
        return isbn;
    }
    public static String normalizeDate(String date){
        String[] d = date.split("-");
        return d[1] + "/" + d[0];
    }
}
