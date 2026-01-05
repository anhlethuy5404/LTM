/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_sapxeptheotudienngc;

import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*BÀI 2. SẮP XẾP THEO TỪ ĐIỂN NGƯỢC
[Mã câu hỏi (qCode): 9UfU4Vky].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: ";B15DCCN009;EF56GH78"
b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;data", với:
•	requestId là chuỗi ngẫu nhiên duy nhất.
•	data là một chuỗi ký tự chứa nhiều từ, được phân cách bởi dấu cách.
Ví dụ: "EF56GH78;The quick brown fox"
c. Sắp xếp các từ trong chuỗi theo thứ tự từ điển ngược (z đến a) và gửi thông điệp lên server theo định dạng "requestId;word1,word2,...,wordN".
Ví dụ: Với data = "The quick brown fox", kết quả là: "EF56GH78;quick,fox,brown,The"
d. Đóng socket và kết thúc chương trình
 */
public class Client {
    public static void main(String[] args) {
        int port = 2208;
        String host = "localhost";
        try (DatagramSocket socket = new DatagramSocket()){
            InetAddress address = InetAddress.getByName(host);
            String mess = ";B22DCCN047;9UfU4Vky";
            byte[] send1 = mess.getBytes();
            DatagramPacket s1 = new DatagramPacket(send1, send1.length, address, port);
            socket.send(s1);
            
            byte[] rev1 = new byte[1024];
            DatagramPacket r1 = new DatagramPacket(rev1, rev1.length);
            socket.receive(r1);
            String str = new String(r1.getData()).trim();
            str = str.replace(";", " ");
            String[] part = str.split(" ");
            String request = part[0];
            ArrayList<String> a = new ArrayList<>();
            for(int i=1; i<part.length; i++){
                a.add(part[i]);
            }
//            Collections.sort(a, Collections.reverseOrder());
//            Collections.sort(a);
//            Collections.reverse(a);
            Collections.sort(a, new Comparator<String>(){
                @Override
                public int compare(String x1, String x2){
                    return x2.toLowerCase().compareTo(x1.toLowerCase());
                }
            });
            String res = request + ";";
            res += String.join(",", a);
            byte[] send2 = res.getBytes();
            DatagramPacket s2 = new DatagramPacket(send2, send2.length, address, port);
            socket.send(s2);
        } catch (Exception e) {
            
        }
    }
}
