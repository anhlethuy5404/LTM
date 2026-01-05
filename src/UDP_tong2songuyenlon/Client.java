/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_tong2songuyenlon;

import java.math.BigInteger;
import java.net.*;

/*
[Mã câu hỏi (qCode): 2sIjAYaU].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode".
Ví dụ: ";B15DCCN010;D3F9A7B8"
b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;a;b", với:
•	requestId là chuỗi ngẫu nhiên duy nhất.
•	a và b là chuỗi thể hiện hai số nguyên lớn (hơn hoặc bằng 100 chữ số).
Ví dụ: "X1Y2Z3;9876543210;123456789"
c. Tính tổng và hiệu của hai số a và b, gửi thông điệp lên server theo định dạng "requestId;sum;difference".Ví dụ: 
Nếu nhận được "X1Y2Z3;9876543210,123456789", tổng là 9999999999 và hiệu là 9753086421. Kết quả gửi lại sẽ là "X1Y2Z3;9999999999,9753086421".
d. Đóng socket và kết thúc chương trình
 */
public class Client {
    public static void main(String[] args) {
        String sCode = "B22DCCN047";
        String qCode = "2sIjAYaU";
        int port = 2207;
        String host = "localhost";
        //String host = "203.162.10.109";
        String mess = ";" + sCode + ";" + qCode;
        try (DatagramSocket socket = new DatagramSocket()){
            InetAddress address = InetAddress.getByName(host);
            //a
            byte[] send1 = mess.getBytes();
            DatagramPacket s1 = new DatagramPacket(send1, send1.length, address, port);
            socket.send(s1);
            //b
            byte[] rev1 = new byte[1024];
            DatagramPacket r1 = new DatagramPacket(rev1, rev1.length);
            socket.receive(r1);
            String str = new String(r1.getData()).trim();
            String[] part = str.split(";");
            String request = part[0];
            BigInteger a = new BigInteger(part[1]);
            BigInteger b = new BigInteger(part[2]);
            //c
            BigInteger sum = a.add(b);
            BigInteger diff = a.subtract(b);
            String res = request + ";" + sum.toString() + ";" + diff.toString();
            byte[] send2 = res.getBytes();
            DatagramPacket s2 = new DatagramPacket(send2, send2.length, address, port);
            socket.send(s2);
        } catch (Exception e){
            
        }
    }
}
