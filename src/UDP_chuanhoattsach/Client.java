/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_chuanhoattsach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

/*BÀI 3. CHUẨN HOÁ THÔNG TIN SÁCH
[Mã câu hỏi (qCode): LFACr5Bi].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản sau:
Đối tượng trao đổi là thể hiện của lớp UDP.Book được mô tả:
-	Tên đầy đủ lớp: UDP.Book
-	Các thuộc tính: id (String), title (String), author (String), isbn (String), publishDate (String)
-	Hàm khởi tạo:
        public Book(String id, String title, String author, String isbn, String publishDate)
-	Trường dữ liệu: private static final long serialVersionUID = 20251107L
Thực hiện:
a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B23DCCN005;eQkvAeId"
b. Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Book từ server. Trong đó, các thuộc tính id, title, author, isbn, và publishDate đã được thiết lập sẵn.
c. Thực hiện:
        1. Chuẩn hóa title: Với mỗi từ, viết hoa chữ cái đầu tiên, viết thường các chữ cái còn lại.
        2. Chuẩn hóa author theo định dạng "Họ, Tên". 
- Trong đó họ được hiểu là từ đầu tiên của tác giả, tất cả các từ còn lại là tên.
- Họ: Viết hoa tất cả các chữ cái
- Tên: Với mỗi từ, viết hoa chữ cái đầu tiên, viết thường các chữ cái còn lại, giữa mỗi từ chỉ có đúng một dấu cách
        3. Chuẩn hóa mã ISBN theo định dạng "978-3-16-148410-0"
        4. Chuyển đổi publishDate từ yyyy-mm-dd sang mm/yyyy.
d. Gửi lại đối tượng đã được chuẩn hóa về server với cấu trúc: 08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Book đã được sửa đổi.
e. Đóng socket và kết thúc chương trình.

 */
public class Client {
    public static void main(String[] args) {
        int port = 2209;
//        String host = "203.162.10.109";
        String host = "localhost";
        try(DatagramSocket socket = new DatagramSocket()){
            InetAddress address = InetAddress.getByName(host);
            String mess = ";B22DCCN047;LFACr5Bi";
            byte[] send1 = mess.getBytes();
            DatagramPacket s1 = new DatagramPacket(send1, send1.length, address, port);
            socket.send(s1);
            
            byte[] rev1 = new byte[1024];
            DatagramPacket  r1 = new DatagramPacket(rev1, rev1.length);
            socket.receive(r1);
            String request = new String(r1.getData(), 0, 8);
            ByteArrayInputStream input = new ByteArrayInputStream(r1.getData(), 8, r1.getLength()-8);
            ObjectInputStream ois = new ObjectInputStream(input);
            Book b = (Book) ois.readObject();
            b.setTitle(chuanHoaTen(b.getTitle()));
            b.setAuthor(chuanHoaTG(b.getAuthor()));
            b.setIsbn(chuanHoaIsbn(b.getIsbn()));
            b.setPublishDate(chuanHoaNgay(b.getPublishDate()));
            System.out.println(b);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            output.write(request.getBytes());
            ObjectOutputStream oos = new ObjectOutputStream(output);
            oos.writeObject(b);
            oos.flush();
            byte[] send2 = output.toByteArray();
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
    private static String chuanHoaTG(String x){
        String[] part = x.split("\\s+");
        String res = "";
        String ho = part[0];
        res += ho.toUpperCase() + ", ";
        for (int i=1; i<part.length; i++){
            res += chuanHoa(part[i]) + " ";
        }
        return res.trim();
    }
    private static String chuanHoaIsbn(String x){
        return x.substring(0,3)+"-"+x.substring(3,4)+"-"+x.substring(4,6)+"-"+x.substring(6,12)+"-" + x.substring(12);
    }
    private static String chuanHoaNgay(String x){
        String[] part = x.split("-");
        String m = part[1];
        String y = part[0];
        return m+"/"+y;
    }
}
