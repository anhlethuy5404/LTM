/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_221_daycondainhat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*[Mã câu hỏi (qCode): 3mjATwQY].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
    a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;76B68B3B".
    b. Nhận dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách bởi ký tự ",". Ví dụ: 5,10,20,25,50,40,30,35.
    c. Tìm chuỗi con tăng dần dài nhất và gửi độ dài của chuỗi đó lên server theo format "chuỗi tăng dài nhất; độ dài". Ví dụ: 5,10,20,25,50;5
    d. Đóng kết nối và kết thúc chương trình.
 */
public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        String mess = "B22DCCN221;3mjATwQY";
        out.write(mess.getBytes());
        out.flush();
        
        byte[] r = new byte[1024];
        int len = in.read(r);
        String rev = new String(r, 0, len).trim();
        System.out.println(rev);
        String[] part = rev.split(",");
        int[] a = new int[part.length];
        for(int i=0; i<part.length; i++) a[i] = Integer.parseInt(part[i]);
        int maxLen = 1;
        int currLen = 1;
        int maxStart = 0;
        int currStart = 0;
        for(int i=1; i<a.length; i++){
            if(a[i] > a[i-1]) currLen++;
            else{
                if(currLen > maxLen){
                    maxLen = currLen;
                    maxStart = currStart;
                }
                currStart = i;
                currLen = 1;
            }
        }
        String res = "";
        for(int i=maxStart; i<maxStart + maxLen; i++){
            if(i!=maxStart) res += ",";
            res += a[i];            
        }
        res += ";" + maxLen;
        out.write(res.getBytes());
        out.flush();
    }
}
