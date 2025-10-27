/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author asus
 */
public class Client {
    public static void main(String[] args) throws ClassNotFoundException {
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN047";
        String qCode = "qUorxuko";
        String mess = studentCode + ";" + qCode;
        try(Socket socket = new Socket(host, port)){
            socket.setSoTimeout(5000);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(mess);
            out.flush();
            
            Laptop lt = (Laptop) in.readObject();
            System.out.println(lt);
            lt.setName(fixName(lt.getName()));
            lt.setQuantity(fixQuantity(lt.getQuantity()));
            System.out.println(lt);
            out.writeObject(lt);
            out.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String fixName(String name){
        String[] a = name.split(" ");
        List<String> res = Arrays.asList(a);
        Collections.swap(res, 0, res.size()-1);
        return String.join(" ", res);
    }
    public static int fixQuantity(int quantity){
        String q = String.valueOf(quantity);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<q.length(); i++){
            sb.append(q.charAt(i));
        }
        return Integer.parseInt(sb.reverse().toString());
    }
}


