/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS_ObjectService_Chi;

import java.util.*;

public class Client {
    public static void main(String[] args) {
        String sCode = "B22DCCN107";
        String qCode = "EN8rPLon";
        try {
            ObjectService_Service sv = new ObjectService_Service();
            ObjectService port = sv.getObjectServicePort();
            List<Student> st = port.requestListStudent(sCode, qCode);
            List<Student> res = new ArrayList<>();
            for (Student x : st){
                if(x.getScore() >= 8.0 || x.getScore() < 5.0){
                    res.add(x);
                }
            }
            port.submitListStudent(sCode, qCode, res);
        } catch (Exception e){
            
        }
    }
}
