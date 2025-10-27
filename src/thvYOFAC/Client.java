/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thvYOFAC;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author asus
 */
public class Client {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN047";
        String qCode = "thvYOFAC";
        String mess = ";" + studentCode + ";" + qCode;
        try(DatagramSocket socket = new DatagramSocket()){
            InetAddress inetAddress = InetAddress.getByName(host);
            byte[] sendData = mess.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
            socket.send(sendPacket);
            
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData()).trim();
            System.out.println(response);
            String[] parts = response.split(";");
            String requestId = parts[0];
            int n = Integer.parseInt(parts[1]);
            int k = Integer.parseInt(parts[2]);
            String[] str = parts[3].split(",");
            int[] nums = Arrays.stream(str)
                                .map(String::trim)
                                .filter(s -> !s.isEmpty())
                                .mapToInt(Integer::parseInt)
                                .toArray();
            List<Integer> a = process(nums, k);
            String res = a.stream().map(String::valueOf).collect(Collectors.joining(","));
            System.out.println(res);
            byte[] responseData = (requestId + ";" + res).getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, inetAddress, port);
            socket.send(responsePacket);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static List<Integer> process(int[] a, int k){
        List<Integer> res = new ArrayList<>();
        int n = a.length;
        for(int i=0; i<=n-k; i++){
            int curr = Integer.MIN_VALUE;
            for(int j=i; j<i+k; j++){
                if(a[j]>curr) curr = a[j];
            }
            res.add(curr);
        }
        return res;
    }
}
