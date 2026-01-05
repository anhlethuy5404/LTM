/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_ptulonnhatcuadaycon;

import java.net.*;
import java.util.ArrayList;

/*BÀI 1. PHẦN TỬ LỚN NHẤT CỦA DÃY CON
[Mã câu hỏi (qCode): iv00Hrq6].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B21DCCN795;ylrhZ6UM".
b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;n;k;z1,z2,...,zn", trong đó:
    requestId là chuỗi ngẫu nhiên duy nhất.
    n là số phần tử của mảng.
    k là kích thước cửa sổ trượt (k < n).
    z1 đến zn là n phần tử là số nguyên của mảng.
c. Thực hiện tìm giá trị lớn nhất trong mỗi cửa sổ trượt với kích thước k trên mảng số nguyên nhận được, và gửi thông điệp lên server theo định dạng "requestId;max1,max2,...,maxm", trong đó max1 đến maxm là các giá trị lớn nhất tương ứng trong mỗi cửa sổ.
Ví dụ: "requestId;5;3;1,5,2,3,4"
Kết quả: "requestId;5,5,4"
d. Đóng socket và kết thúc chương trình.

 */
public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 2207;
        try (DatagramSocket socket = new DatagramSocket()){
            //a
            InetAddress address = InetAddress.getByName(host);
            String mess = ";B22DCCN047;iv00Hrq6";
            byte[] send1 = mess.getBytes();
            DatagramPacket s1 = new DatagramPacket(send1, send1.length, address, port);
            socket.send(s1);
            
            //b
            byte[] rev = new byte[2048];
            DatagramPacket r1 = new DatagramPacket(rev, rev.length);
            socket.receive(r1);
            String str = new String(r1.getData()).trim();
            String[] part = str.split(";");
            String rId = part[0];
            int n = Integer.parseInt(part[1]);
            int k = Integer.parseInt(part[2]);
            String[] num = part[3].split(",");
            ArrayList<Integer> a = new ArrayList<>();
            for (String x : num) a.add(Integer.parseInt(x));
            String res = rId + ";";
            for(int i=0; i<n-k+1; i++){
                int m = a.get(i);
                for(int j=i+1; j<i+k; j++){
                    if(a.get(j)>m) m = a.get(j);
                }
                res += m + ",";
            }
            res = res.substring(0, res.length()-1);
            byte[] send2 = res.getBytes();
            DatagramPacket s2 = new DatagramPacket(send2, send2.length, address, port);
            socket.send(s2);
        } catch (Exception e){
            
        }
    }
}
