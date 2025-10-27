/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.io.*;

/**
 *
 * @author asus
 */
public class ProductX implements Serializable{
    private static final long serialVersionUID = 20171107; 
    private String id, code, discountCode;
    private int discount;

    public ProductX(String id, String code, String discountCode, int discount) {
        this.id = id;
        this.code = code;
        this.discountCode = discountCode;
        this.discount = discount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public int getDiscount() {
        return discount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    
    
}
