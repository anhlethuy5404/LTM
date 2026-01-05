/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_chuanhoattsach;

import java.net.*;
import java.io.*;

/**
 *
 * @author asus
 */
public class Server {
    public static void main(String[] args) {
        try (DatagramSocket server = new DatagramSocket(2209)) {
            System.out.println("Server 2209 dang chay (Testcase san sang)...");

            while (true) {
                // 1. Nhan yeu cau ket noi tu Client
                byte[] receiveBuf = new byte[2048];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
                server.receive(receivePacket);
                System.out.println("Co Client ket noi...");

                // 2. Chuan bi du lieu mau (Input giong vi du)
                String reqID = "kEfAIDHb";
                Book rawBook = new Book("kEfAIDHb", 
                    "wAsSegd NCAxhCY hPaSVlUJC YRUyI", 
                    "cFPISv tlpiAvv TzfHf hSgRO vhorbuZXe RtKAxjdPK", 
                    "2485368573116", 
                    "2007-05-16");

                // Gui 8 byte ID + Object Book cho Client
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                baos.write(reqID.getBytes());
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(rawBook);
                oos.flush();
                byte[] sendBuf = baos.toByteArray();
                server.send(new DatagramPacket(sendBuf, sendBuf.length, receivePacket.getAddress(), receivePacket.getPort()));

                // 3. Nhan ket qua tra ve tu Client de kiem tra
                byte[] resBuf = new byte[2048];
                DatagramPacket resPacket = new DatagramPacket(resBuf, resBuf.length);
                server.receive(resPacket);

                // Giai ma ket qua tu Client
                String resID = new String(resPacket.getData(), 0, 8);
                ByteArrayInputStream bais = new ByteArrayInputStream(resPacket.getData(), 8, resPacket.getLength() - 8);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Book clientBook = (Book) ois.readObject();

                // 4. So sanh voi ket qua dung (Output mong doi)
                boolean isCorrect = checkLogic(clientBook);
                
                System.out.println("--- KET QUA TEST ---");
                System.out.println("ID: " + resID);
                System.out.println("Title: " + clientBook.getTitle());
                System.out.println("Author: " + clientBook.getAuthor());
                System.out.println("ISBN: " + clientBook.getIsbn());
                System.out.println("Date: " + clientBook.getPublishDate());
                System.out.println("TRANG THAI: " + (isCorrect ? "CHINH XAC (PASS)" : "SAI LOGIC (FAIL)"));
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static boolean checkLogic(Book b) {
        boolean t = b.getTitle().equals("Wassegd Ncaxhcy Hpasvlujc Yruyi");
        boolean a = b.getAuthor().equals("CFPISV, Tlpiavv Tzfhf Hsgro Vhorbuzxe Rtkaxjdpk");
        boolean i = b.getIsbn().equals("248-5-36-857311-6");
        boolean d = b.getPublishDate().equals("05/2007");
        return t && a && i && d;
    }
}
