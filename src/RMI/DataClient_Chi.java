/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;
/*
[Mã câu hỏi (qCode): 8npTLdYM].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu.
Giao diện từ xa:
public interface DataService extends Remote {
public Object requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
}
Trong đó:
•	Interface DataService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với tên là: RMIDataService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một chuỗi các số nguyên.
b. Sử dụng thuật toán sinh tổ hợp kế tiếp để tìm tổ hợp kế tiếp của chuỗi số này theo thứ tự từ điển. Nếu chuỗi đã là tổ hợp lớn nhất, trả về tổ hợp nhỏ nhất (sắp xếp lại từ đầu theo thứ tự từ điển).
Ví dụ: Với chuỗi 1, 2, 3 tổ hợp kế tiếp là 1, 3, 2. Nếu chuỗi là 3, 2, 1 (tổ hợp lớn nhất), kết quả sẽ là 1, 2, 3 (tổ hợp nhỏ nhất).
c. Triệu gọi phương thức submitData để gửi chuỗi (String) chứa tổ hợp kế tiếp đã tìm được trở lại server.
d. Kết thúc chương trình client.
*/
import java.rmi.*;
import java.util.*;
import java.util.stream.Collectors;
public class DataClient_Chi {
    public static void main(String[] args) {
        String sCode = "B22DCCN107";
        String qCode = "8npTLdYM";
        String url = "rmi://203.162.10.109/RMIDataService";
        try{
            DataService service = (DataService) Naming.lookup(url);
            Object revData = service.requestData(sCode, qCode);
            String rev = (String) revData;
            List<Integer> nums = new ArrayList<>();
            for (String x : rev.split(",")){
                nums.add(Integer.parseInt(x.trim()));
            }
            List<Integer> res = next(nums);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<res.size(); i++){
                sb.append(res.get(i));
                if(i<res.size()-1) sb.append(",");
            }
            String resStr = sb.toString();
            System.out.println(rev + "\n" + resStr);
            service.submitData(sCode, qCode, resStr);
        } catch (Exception e){
            
        }
    }
    public static List<Integer> next(List<Integer> nums){
        if(nums == null || nums.size()<=1) return nums;
        int n = nums.size();
        int k = -1;
        for(int i=n-2; i>=0; i--){
            if(nums.get(i) < nums.get(i+1)) {
                k=i;
                break;
            }
        }
        if(k==-1) {
            Collections.sort(nums);
            return nums;
        }
        int l = -1;
        for (int i=n-1; i>k; i--){
            if(nums.get(k) < nums.get(i)){
                l = i;
                break;
            }
        }
        Collections.swap(nums, k, l);
        int start = k+1;
        int end = n-1;
        while(start < end) {
            Collections.swap(nums, start, end);
            start++;
            end--;
        }
        return nums;
    }
}
