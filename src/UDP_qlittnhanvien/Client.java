/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_qlittnhanvien;

import java.io.*;
import java.net.*;
import java.util.*;

/*[Mã câu hỏi (qCode): ySsumsIE].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản sau:
Đối tượng trao đổi là thể hiện của lớp UDP.Employee được mô tả:
-	Tên đầy đủ lớp: UDP.Employee
-	Các thuộc tính: id (String), name (String), salary (double), hireDate (String)
-	Hàm khởi tạo:
        public Employee(String id, String name, double salary, String hireDate)
-	Trường dữ liệu: private static final long serialVersionUID = 20261107L
Thực hiện:
a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B23DCCN006;ITleSdqV"
b. Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Employee từ server. Trong đó, các thuộc tính id, name, salary và hireDate đã được thiết lập sẵn.
c. Thực hiện:
- Chuẩn hóa name: viết hoa chữ cái đầu của mỗi từ, ví dụ "john doe" thành "John Doe".
- Tăng salary: tăng x% lương, với x bằng tổng các chữ số của năm sinh.
- Chuyển đổi hireDate từ định dạng yyyy-mm-dd sang dd/mm/yyyy. Ví dụ: "2023-07-15" thành "15/07/2023".
- Gửi lại đối tượng đã được chuẩn hóa về server với cấu trúc: 08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Employee đã được sửa đổi.
d. Đóng socket và kết thúc chương trình.

 */
public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 2209;
        try(DatagramSocket socket = new DatagramSocket()){
            InetAddress address = InetAddress.getByName(host);
            String mess = ";B22DCCN047;ySsumsIE";
            byte[] send1 = mess.getBytes();
            DatagramPacket s1 = new DatagramPacket(send1, send1.length, address, port);
            socket.send(s1);
            
            byte[] rev = new byte[2048];
            DatagramPacket r1 = new DatagramPacket(rev, rev.length);
            socket.receive(r1);
            String request = new String(r1.getData(), 0, 8);
            System.out.println(request);
            ByteArrayInputStream bais = new ByteArrayInputStream(r1.getData(), 8, r1.getLength()-8);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Employee e = (Employee) ois.readObject();
            System.out.println(e);
            e.setName(chuanHoaTen(e.getName()));
            e.setSalary(tinhLuong(e.getSalary(), e.getHireDate()));
            e.setHireDate(chuanHoaNgay(e.getHireDate()));
            System.out.println(e);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(request.getBytes());
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(e);
            oos.flush();
            byte[] send2 = baos.toByteArray();
            DatagramPacket s2 = new DatagramPacket(send2, send2.length, address, port);
            socket.send(s2);
        } catch (Exception e){
            
        }
    }
    private static String chuanHoa(String x){
        return Character.toUpperCase(x.charAt(0)) + x.substring(1).toLowerCase();
    }
    private static String chuanHoaTen(String x){
        String[] part = x.split("\\s+");
        String res = "";
        for (String i : part){
            res += chuanHoa(i) + " ";
        }
        return res.trim();
    }
    private static double tinhLuong(double luong, String hdate){
        String[] part = hdate.split("-");
        String y = part[0];
        int x = 0;
        for(int i=0; i<y.length(); i++){
            x+=y.charAt(i)-'0';
        }
        return luong + luong * x / 100.0;
    }
    private static String chuanHoaNgay(String x){
        String[] part = x.split("-");
        return part[2] + "/" + part[1] + "/" + part[0];
    }
}
