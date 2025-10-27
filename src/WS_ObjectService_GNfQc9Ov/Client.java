/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS_ObjectService_GNfQc9Ov;

import java.util.*;

/**
 *
 * @author asus
 */
public class Client {
    public static void main(String[] args) {
        String studentCode = "B22DCCN047";
        String qCode = "GNfQc9Ov";
        String url = "http://203.162.10.109:8080/JNPWS/ObjectService?wsdl";
        ObjectService_Service sv = new ObjectService_Service();
        ObjectService port = sv.getObjectServicePort();
        List<Student> student = port.requestListStudent(studentCode, qCode);
        List<Student> res = filter(student);
        port.submitListStudent(studentCode, qCode, res);
    }
    public static List<Student> filter(List<Student> st){
        List<Student> res = new ArrayList<>();
        for(Student x : st){
            if(x.getScore()>=8.0f || x.getScore()<5.0f){
                res.add(x);
            }
        }
        return res;
    }
}
