/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.rmi.*;
import java.util.*;
import java.util.stream.Collectors;

public class DataClient {
    public static void main(String[] args) {
        String studentCode = "B22DCCN047";
        String qCode = "LD0vE7nG";
        String url = "rmi://203.162.10.109/RMIDataService";
        try{
            DataService service = (DataService) Naming.lookup(url);
            Object rev = service.requestData(studentCode, qCode);
            int amount = (Integer) rev;
            String res = greed(amount);
            service.submitData(studentCode, qCode, res);
        }catch(RemoteException e){
            
        }catch(Exception e){
            
        }
    }
    public static String greed(int amount){
        int[] coin = {10,5,2,1};
        if(amount < 0) return "-1";
        if(amount == 0) return "0";
        int remain = amount;
        int minCoin = 0;
        List<Integer> selectedCoin = new ArrayList<>();
        for(int x : coin){
            while(remain >= x){
                remain -= x;
                minCoin++;
                selectedCoin.add(x);
            }
        }
        if(remain > 0){
            return "-1";
        }
        String coinList = selectedCoin.stream()
                                    .map(String::valueOf)
                                    .collect(Collectors.joining(","));
        return minCoin + "; " + coinList;
    }
}
