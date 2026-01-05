/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_qlittnhanvien;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author asus
 */
public class Server {
    public static void main(String[] args) {
        try (DatagramSocket server = new DatagramSocket(2209)) {
            System.out.println("Server 2209 dang chay (He thong cham thi tu dong)...");

            while (true) {
                byte[] receiveBuf = new byte[2048];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
                server.receive(receivePacket);

                // Dữ liệu giả định
                String reqID = "REQ" + (100 + new Random().nextInt(90000));
                String rawName = "nguyen van teo";
                double rawSalary = 1000.0;
                String rawDate = "2024-01-01"; // Tổng chữ số năm: 2+0+2+4 = 8 -> Lương mới: 1080.0

                Employee rawEmp = new Employee("NV01", rawName, rawSalary, rawDate);

                // 1. Gửi dữ liệu cho Client
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                baos.write(reqID.getBytes());
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(rawEmp);
                oos.flush();
                byte[] sendBuf = baos.toByteArray();
                server.send(new DatagramPacket(sendBuf, sendBuf.length, receivePacket.getAddress(), receivePacket.getPort()));

                // 2. Nhận kết quả từ Client
                byte[] resBuf = new byte[2048];
                DatagramPacket resPacket = new DatagramPacket(resBuf, resBuf.length);
                server.receive(resPacket);

                // 3. Giải mã và So sánh
                String resID = new String(resPacket.getData(), 0, 8).trim();
                ByteArrayInputStream bais = new ByteArrayInputStream(resPacket.getData(), 8, resPacket.getLength() - 8);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Employee clientEmp = (Employee) ois.readObject();

                // Logic tính đáp án chuẩn của Server
                String expectedName = "Nguyen Van Teo";
                double expectedSalary = 1000.0 * (1 + (double)(2+0+2+4)/100);
                String expectedDate = "01/01/2024";

                boolean isCorrect = clientEmp.getName().equals(expectedName) 
                                 && Math.abs(clientEmp.getSalary() - expectedSalary) < 0.001
                                 && clientEmp.getHireDate().equals(expectedDate);

                System.out.println("\n===== KET QUA KIEM TRA =====");
                System.out.println("Client ID: " + resID);
                System.out.println("Ten: " + clientEmp.getName() + (clientEmp.getName().equals(expectedName) ? " [OK]" : " [SAI]"));
                System.out.println("Luong: " + clientEmp.getSalary() + (Math.abs(clientEmp.getSalary() - expectedSalary) < 0.001 ? " [OK]" : " [SAI]"));
                System.out.println("Ngay: " + clientEmp.getHireDate() + (clientEmp.getHireDate().equals(expectedDate) ? " [OK]" : " [SAI]"));
                System.out.println("=> TRANG THAI: " + (isCorrect ? "CHINH XAC (PASS)" : "KHONG KHOP (FAIL)"));
                System.out.println("============================\n");
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
