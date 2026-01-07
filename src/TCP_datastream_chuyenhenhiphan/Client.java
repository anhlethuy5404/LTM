/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_datastream_chuyenhenhiphan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/*[Mã câu hỏi (qCode): ViLrEVBK].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2207 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu sinh viên xây dựng chương trình client để tương tác với server, sử dụng các luồng data (DataInputStream và DataOutputStream) để trao đổi thông tin theo thứ tự sau:
a. Gửi mã sinh viên và mã câu hỏi: Chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;D68C93F7".
b. Nhận một số nguyên hệ thập phân từ server. Ví dụ: 45
c. Chuyển đổi số nguyên nhận được sang hệ nhị phân và gửi chuỗi kết quả này lại cho server. Ví dụ: 45 sẽ thành chuỗi "101101"
d. Đóng kết nối và kết thúc chương trình.
 */
public class Client {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2207;
        try(Socket socket = new Socket(host, port)){
            String mess = "B22DCCN173;ViLrEVBK";
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(mess);
            out.flush();
            int num = in.readInt();
            String res = Integer.toBinaryString(num);
            out.writeUTF(res);
            out.flush();
        } catch(Exception e){
            
        }
    }
}
