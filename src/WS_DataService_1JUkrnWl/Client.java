/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS_DataService_1JUkrnWl;

import java.util.*;

/**
 *
 * @author asus
 */
public class Client {
    public static void main(String[] args) {
        String studentCode = "B22DCCN047";
        String qCode = "1JUkrnWl";
        String url = "http://203.162.10.109:8080/JNPWS/DataService?wsdl";
        DataService_Service sv = new DataService_Service();
        DataService port = sv.getDataServicePort();
        List<Integer> rev = port.getData(studentCode, qCode);
        List<String> res = convert(rev);
        port.submitDataStringArray(studentCode, qCode, res);
    }
    public static List<String> convert(List<Integer> nums){
        List<String> res = new ArrayList<>();
        for (int x : nums){
            String oct = Integer.toOctalString(x);
            String hex = Integer.toHexString(x).toUpperCase();
            String str = oct + "|" + hex;
            res.add(str);
        }
        return res;
    }
}
