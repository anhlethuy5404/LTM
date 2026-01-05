/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_sapxeptheovitribd;

import java.net.*;
import java.util.*;

/*BÀI 1. SẮP XẾP THEO VỊ TRÍ BAN ĐẦU
[Mã câu hỏi (qCode): aKZwZxWk].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN009;F3E8B2D4".
b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;string", với:
--- requestId là chuỗi ngẫu nhiên duy nhất.
---string là một chuỗi chứa các chuỗi con bị thay đổi vị trí. Ví dụ: "veM3xgA1g:4,IPFfgEanY:5,aWXlSzDwe:2,PHupvPc:3,PR3gH8ahN:6,UEEKHLIt:7,M6dpWTE:1"
c. Xử lý chuỗi xáo trộn và gửi về chuỗi sau khi sắp xếp: "requestId;string". Ví dụ chuỗi đã được xử lý: "M6dpWTE,aWXlSzDwe,PHupvPc,veM3xgA1g,IPFfgEanY,PR3gH8ahN,UEEKHLIt"
d. Đóng socket và kết thúc chương trình.

 */
public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 2207;
        try(DatagramSocket socket = new DatagramSocket()){
            InetAddress address = InetAddress.getByName(host);
            String mess = ";B22DCCN047;aKZwZxWk";
            byte[] send1 = mess.getBytes();
            DatagramPacket s1 = new DatagramPacket(send1, send1.length, address, port);
            socket.send(s1);
            
            byte[] rev = new byte[2048];
            DatagramPacket r = new DatagramPacket(rev, rev.length);
            socket.receive(r);
            String str = new String(r.getData()).trim();
            String[] part = str.split(";");
            String rId = part[0];
            String[] tmp = part[1].split(",");
            Map<Integer, String> m = new TreeMap<>();
            for(String x : tmp){
                String[] pair = x.split(":");
                m.put(Integer.parseInt(pair[1]), pair[0]);
            }
            String res = rId + ";";
            for(String x : m.values()){
                res += x + ",";
            }
            res = res.substring(0, res.length()-1);
            byte[] send2 = res.getBytes();
            DatagramPacket s2 = new DatagramPacket(send2, send2.length, address, port);
            socket.send(s2);
        }catch (Exception e){
            
        }
    }
}
