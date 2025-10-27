/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.io.*;
import java.rmi.*;

public class ObjectClient {
    public static void main(String[] args) {
        String studentCode = "B22DCCN047";
        String qCode = "KyVEN7sU";
        String url = "rmi://203.162.10.109/RMIObjectService";
        try{
            ObjectService service = (ObjectService) Naming.lookup(url);
            Serializable rev = service.requestObject(studentCode, qCode);
            ProductX product = (ProductX) rev;
            String discountCode = product.getDiscountCode();
            int discount = calDis(discountCode);
            product.setDiscount(discount);
            service.submitObject(studentCode, qCode, product);
        } catch(Exception e){
            
        } 
    }
    public static int calDis(String disCode){
        int sum = 0;
        for(char c : disCode.toCharArray()){
            if(Character.isDigit(c)){
                sum += Character.getNumericValue(c);
            }
        }
        return sum;
    }
}
