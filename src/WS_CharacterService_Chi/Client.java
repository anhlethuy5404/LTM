/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS_CharacterService_Chi;

import java.util.*;

/*
[Mã câu hỏi (qCode): a7Dpkygn].  Một dịch vụ web (hỗ trợ SOAP version 1.1) được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:
a. Triệu gọi phương thức requestStringArray với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một danh sách chuỗi (List<String>) từ server.
b. Phân loại các từ trong mảng chuỗi thành các nhóm có cùng số lượng nguyên âm. Tạo một chuỗi cho mỗi nhóm, trong đó liệt kê các từ cách nhau bằng dấu phẩy, và sắp xếp các từ theo thứ tự từ điển trong mỗi nhóm.
c. Triệu gọi phương thức submitCharacterStringArray(String studentCode, String qCode, List<String> data) để gửi danh sách chuỗi kết quả trở lại server, trong đó mỗi phần tử là một nhóm từ với cùng số lượng nguyên âm.
Ví dụ: Nếu danh sách chuỗi nhận được từ phương thức requestCharacter là ["apple", "banana", "pear", "grape", "kiwi"], các nhóm có thể là:
•	Nhóm 2 nguyên âm: "apple, banana"
•	Nhóm 1 nguyên âm: "grape, kiwi, pear"
Danh sách kết quả sẽ là ["apple, banana", "grape, kiwi, pear"], và danh sách này sẽ được gửi lại server qua phương thức submitCharacter.
d. Kết thúc chương trình client.
 */
public class Client {
    public static void main(String[] args) {
        String sCode = "B22DCCN107";
        String qCode = "a7Dpkygn";
        String url = "http://203.162.10.109:8080/JNPWS/CharacterService?wsdl";
        CharacterService_Service sv = new CharacterService_Service();
        CharacterService port = sv.getCharacterServicePort();
        List<String> rev = port.requestStringArray(sCode, qCode);
        List<String> res = process(rev);
        System.out.println(rev + "\n" + res);
        port.submitCharacterStringArray(sCode, qCode, res);
    }
    public static int countV(String word){
        int count = 0;
        String w = word.toLowerCase();
        for(int i=0; i<w.length(); i++){
            char c = w.charAt(i);
            if(c=='a' || c=='u' || c=='o' || c=='i' || c=='e'){
                count++;
            }
        }
        return count;
    }
    public static List<String> process(List<String> rev){
        Map<Integer, List<String>> m = new HashMap<>();
        for(String x : rev){
            int v = countV(x);
            m.computeIfAbsent(v, k -> new ArrayList<>()).add(x);
        }
        List<String> res = new ArrayList<>();
        for(List<String> list : m.values()){
            Collections.sort(list);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<list.size(); i++){
                sb.append(list.get(i));
                if(i<list.size()-1) sb.append(", ");
            }
            res.add(sb.toString());
        }
        return res;
    }
}
