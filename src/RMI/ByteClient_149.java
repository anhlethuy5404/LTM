/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

/*[Mã câu hỏi (qCode): Xj85KTbq].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu nhị phân.
Giao diện từ xa:
public interface ByteService extends Remote {
public byte[] requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, byte[] data) throws RemoteException;
}
Trong đó:
•	Interface ByteService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa ByteService được đăng ký với RegistryServer với tên là: RMIByteService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhị phân nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một mảng dữ liệu nhị phân (byte[]) từ server.
b. Chuyển đổi mảng dữ liệu nhị phân nhận được thành một chuỗi biểu diễn hex. Mỗi byte trong mảng sẽ được chuyển đổi thành hai ký tự hex tương ứng.
Ví dụ: Nếu dữ liệu nhị phân nhận được là [72, 101, 108, 108, 111], chương trình sẽ chuyển đổi mảng này thành chuỗi hex "48656c6c6f", tương ứng với chuỗi "Hello" trong ASCII.
c. Triệu gọi phương thức submitData để gửi chuỗi biểu diễn hex đã chuyển đổi thành mảng byte trở lại server.
d. Kết thúc chương trình client.
 */
import java.rmi.*;
public class ByteClient_149 {
    public static void main(String[] args) throws Exception{
        String sCode = "B22DCCN149";
        String qCode = "Xj85KTbq";
        String url = "rmi://203.162.10.109/RMIByteService";
        ByteService sv = (ByteService) Naming.lookup(url);
        byte[] rev = sv.requestData(sCode, qCode);
        String res = byteToHex(rev);
        sv.submitData(sCode, qCode, res.getBytes());
    }
    private static String byteToHex(byte[] rev){
        String res = "";
        for (byte x : rev){
            res += String.format("%02x", x);
        }
        return res;
    } 
}
