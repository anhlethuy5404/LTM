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
public class Order implements Serializable{
    private static final long serialVersionUID = 20241132L;
    private String id, customerCode, orderDate, shippingType, orderCode;

    public Order(String id, String customerCode, String orderDate, String shippingType) {
        this.id = id;
        this.customerCode = customerCode;
        this.orderDate = orderDate;
        this.shippingType = shippingType;
    }

    public Order() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
    
}
