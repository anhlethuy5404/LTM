/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS_ObjectService_149;

import java.util.*;

/*[Mã câu hỏi (qCode): gck7KaKd].  Một dịch vụ web (hỗ trợ SOAP version 1.1) được định nghĩa và mô tả trong tệp ObjectService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/ObjectService?wsdl để xử lý các bài toán với đối tượng.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện các công việc sau:
a. Triệu gọi phương thức requestListEmployeeY với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một danh sách đối tượng EmployeeY từ server. Mỗi đối tượng EmployeeY có các thuộc tính:
•	name: kiểu String, đại diện cho tên của nhân viên.
•	startDate: kiểu Date, đại diện cho ngày bắt đầu làm việc của nhân viên.
b. Sắp xếp danh sách EmployeeY theo thứ tự thâm niên từ cao đến thấp (người có ngày bắt đầu làm việc sớm nhất sẽ đứng đầu danh sách).
    Nếu hai nhân viên có cùng ngày bắt đầu làm việc, giữ nguyên thứ tự ban đầu của họ trong danh sách.
c. Triệu gọi phương thức submitListEmployeeY(String studentCode, String qCode, List<EmployeeY> data) để gửi danh sách nhân viên đã sắp xếp trở lại server.
d. Kết thúc chương trình client.
 */
public class Client {
    public static void main(String[] args) {
        String sCode = "B22DCCN149";
        String qCode = "gck7KaKd";
        ObjectService_Service sv = new ObjectService_Service();
        ObjectService port = sv.getObjectServicePort();
        List<EmployeeY> e = port.requestListEmployeeY(sCode, qCode);
        System.out.println(e);
        Collections.sort(e, new Comparator<>(){
            @Override
            public int compare(EmployeeY a, EmployeeY b){
                return a.getStartDate().compare(b.getStartDate());
            }
        });
        System.out.println(e);
        port.submitListEmployeeY(sCode, qCode, e);
    }
}
