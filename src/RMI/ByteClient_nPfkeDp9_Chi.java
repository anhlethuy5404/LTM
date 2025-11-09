/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.rmi.*;
public class ByteClient_nPfkeDp9_Chi {
    public static void main(String[] args) {
        String sCode = "B22DCCN107";
        String qCode = "nPfkeDp9";
        String url = "rmi://203.162.10.109/RMIByteService";
        try{
            ByteService service = (ByteService) Naming.lookup(url);
            byte[] rev = service.requestData(sCode, qCode);
            int n = rev.length;
            System.out.println(rev);
            byte[] res = encode(rev, n);
            System.out.println(res);
            service.submitData(sCode, qCode, res);
        } catch (Exception e){
            
        }
    }
    public static byte[] encode (byte[] b, int n){
        byte[] res = new byte[b.length];
        for(int i=0; i<b.length; i++){
            res[i] = (byte)(b[i] + n);
        }
        return res;
    }
}
