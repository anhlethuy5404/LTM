/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

/*[Mã câu hỏi (qCode): A8it4pBH].  Một chương trình (tạm gọi là RMI server) cung cấp giao diện cho phép triệu gọi từ xa với thông tin như sau:
-	Giao diện từ xa
    public interface ObjectService extends Remote {
        public Serializable requestObject(String studentCode, String qAlias) throws RemoteException;

        public void submitObject(String studentCode, String qAlias, Serializable object) throws RemoteException;
    }
-	Lớp Product gồm các thông tin: id String, code String, importPrice double, exportPrice double.
    Trường dữ liệu: private static final long serialVersionUID = 20151107L;
    02 hàm khởi dựng 
        public Product()
        public Product(id String, String code,double ImportPrice, double ExportPrice)
Trong đó:
-	interface ObjectService và lớp Product được viết trong package RMI
-	Đối tượng cài đặt giao diện từ xa ObjectService được đăng ký với RegistryServer: RMIObjectService

Yêu cầu yêu cầu viết chương trình tại máy trạm (RMI client) thực hiện chuẩn hóa sản phẩm theo thứ tự:
a.	Triệu gọi phương thức requestObject để lấy về đối tượng sản phẩm cần chuẩn hóa.
b.	Thực hiện chuẩn hóa đối tượng nhận được theo nguyên tắc:
        - Chuyển mã sản phẩm thành in hoa.
        - Cập nhật giá xuất (exportPrice) bằng giá nhập (importPrice) + 20%

c.  Triệu gọi phương thức submitObject để gửi dữ liệu đã chuẩn hóa
d.  Kết thúc chương trình client
 */
import java.rmi.*;
public class ObjectClient_510 {
    public static void main(String[] args) throws Exception{
        String sCode = "B22DCCN510";
        String qCode = "A8it4pBH";
        String url = "rmi://203.162.10.109/RMIObjectService";
        ObjectService sv = (ObjectService) Naming.lookup(url);
        Product p = (Product) sv.requestObject(sCode, qCode);
        System.out.println(p);
        p.setCode(chuyen(p.getCode()));
        p.setExportPrice(p.getImportPrice()*1.2);
        System.out.println(p);
        sv.submitObject(sCode, qCode, p);
    }
    private static String chuyen(String x){
        return x.toUpperCase();        
    }
}
