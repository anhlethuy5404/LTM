/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS_DataService_Chi;

import java.util.*;

/**
 *
 * @author asus
 */
public class Client {
    public static void main(String[] args) {
        String sCode = "B22DCCN107";
        String qCode = "xbFG7S15";
        String url = "http://203.162.10.109:8080/JNPWS/DataService?wsdl";
        try{
           DataService_Service sv = new DataService_Service();
           DataService port = sv.getDataServicePort();
           List<Integer> rev = port.getData(sCode, qCode);
           List<String> res = toBin(rev);
           port.submitDataStringArray(sCode, qCode, res);
        } catch (Exception e){
            
        }
    }
    public static List<String> toBin(List<Integer> rev){
        List<String> res = new ArrayList<>();
        for(Integer x : rev){
            res.add(Integer.toBinaryString(x));
        }
        return res;
    }
}
