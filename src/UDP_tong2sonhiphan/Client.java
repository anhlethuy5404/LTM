/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_tong2sonhiphan;

import java.math.BigInteger;
import java.net.*;

/*BÀI 1. TỔNG HAI SỐ NHỊ PHÂN
[Mã câu hỏi (qCode): lIQVug9S].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN000;XbYdNZ3”.
b. Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;b1,b2”, trong đó:
    requestId là chuỗi ngẫu nhiên duy nhất.
    b1 là số nhị phân thứ nhất
    b2 là số nhị phân thứ hai.
Ví dụ: requestId;0100011111001101,1101000111110101
c. Thực hiện tính tổng hai số nhị phân nhận được, chuyển về dạng thập phân và gửi lên server theo định dạng “requestId;sum”
Kết quả: requestId;72130 
d. Đóng socket và kết thúc chương trình.

 */
public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 2208;
        try(DatagramSocket socket = new DatagramSocket()){
            InetAddress address = InetAddress.getByName(host);
            String mess = ";B22DCCN047;lIQVug9S";
            byte[] send1 = mess.getBytes();
            DatagramPacket s1 = new DatagramPacket(send1, send1.length, address, port);
            socket.send(s1);
            
            byte[] rev = new byte[2048];
            DatagramPacket r = new DatagramPacket(rev, rev.length);
            socket.receive(r);
            String str = new String(r.getData()).trim();
            String[] part = str.split(";");
            String rId = part[0];
            String[] bin = part[1].split(",");
            BigInteger b1 = new BigInteger(bin[0], 2);
            BigInteger b2 = new BigInteger(bin[1], 2);
            BigInteger sum = b1.add(b2);
            String res = rId + ";" + sum.toString();
            // muon chuyen co so 2 thi viet sum.toString(2)
            byte[] send2 = res.getBytes();
            DatagramPacket s2 = new DatagramPacket(send2, send2.length, address, port);
            socket.send(s2);
        } catch (Exception e){
            
        }
    }
}
