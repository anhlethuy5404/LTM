/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_loaikitutrungnhau;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*[Mã câu hỏi (qCode): 74gn4MYZ].  [Loại bỏ ký tự đặc biệt và ký tự trùng giữ nguyên thứ tự xuất hiện]
Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208 . Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:
a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode”. Ví dụ: ";B15DCCN001;B34D51E0"
b.	Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;str1;str2".
•	requestId là chuỗi ngẫu nhiên duy nhất
•	str1,str2 lần lượt là chuỗi thứ nhất và chuỗi thứ hai
c.	Loại bỏ các ký tự trong chuỗi thứ nhất mà xuất hiện trong chuỗi thứ hai, giữ nguyên thứ tự xuất hiện. Gửi thông điệp là một chuỗi lên server theo định dạng "requestId;strOutput", trong đó chuỗi strOutput là chuỗi đã được xử lý ở trên.
d.	Đóng socket và kết thúc chương trình.
 */
public class Client {
    public static void main(String[] args){
        try(DatagramSocket socket = new DatagramSocket()){
            int port = 2208;
            String host = "203.162.10.109";
            InetAddress address = InetAddress.getByName(host);
            String mess = ";B22DCCN149;74gn4MYZ";
            byte[] send1 = mess.getBytes();
            DatagramPacket s1 = new DatagramPacket(send1, send1.length, address, port);
            socket.send(s1);
            
            byte[] rev = new byte[4096];
            DatagramPacket r1 = new DatagramPacket(rev, rev.length);
            socket.receive(r1);
            String str = new String(r1.getData()).trim();
            String[] part = str.split(";");
            String rId = part[0];
            String x1 = part[1];
            String x2 = part[2];
            String res = rId + ";";
            for(int i=0; i<x1.length(); i++){
                char c = x1.charAt(i);
                if(x2.indexOf(c)==-1){
                    res += c;
                }
            }
            byte[] send2 = res.getBytes();
            DatagramPacket s2 = new DatagramPacket(send2, send2.length, address, port);
            socket.send(s2);
        } catch (Exception e){
            
        }
    }
}
