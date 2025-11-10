/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;
import java.io.*;
import java.util.*;
import java.rmi.*;
public class ObjectClient_TAnh {
    public static void main(String[] args) {
        String sCode = "B22DCCN035";
        String qCode = "xFzaRhPX";
        String url = "rmi://203.162.10.109/RMIObjectService";
        try{
            ObjectService sv = (ObjectService) Naming.lookup(url);
            Serializable rev = sv.requestObject(sCode, qCode);
            Order order = (Order) rev;
            order.setOrderCode(process(order));
            sv.submitObject(sCode, qCode, order);
        } catch (Exception e){
            
        }
    }
    public static String process(Order order){
        String shippingType = order.getShippingType().toUpperCase();
        String customerCode = order.getCustomerCode();
        String orderDate = order.getOrderDate();
        String part1 = shippingType.substring(0, Math.min(2, shippingType.length()));
        String part2 = customerCode.substring(Math.max(0, customerCode.length()-3));
        String[] date = orderDate.split("-");
        String month = date[1];
        String day = date[2];
        String part3 = day + month;
        return part1 + part2 + part3;
    }
}
