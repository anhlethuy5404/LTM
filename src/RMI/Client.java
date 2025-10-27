/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;
import java.nio.charset.StandardCharsets;
import java.rmi.*;

/**
 *
 * @author asus
 */
public class Client {
    public static void main(String[] args) {
        String host = "203.162.10.109";
//        int port = 2209;
        String studentCode = "B22DCCN047";
        String qCode = "4WwQcThD";
        String url = "rmi://203.162.10.109/RMIByteService";
        try{
            ByteService service = (ByteService) Naming.lookup(url);
            byte[] receive = service.requestData(studentCode, qCode);
            String hexString = toHex(receive);
            System.out.println(hexString);
            byte[] send = hexString.getBytes();
            System.out.println(send);
            service.submitData(studentCode, qCode, send);
        } catch (RemoteException e){
            
        } catch (Exception e){
            
        }
    }
    public static String toHex(byte[] b){
        StringBuilder sb = new StringBuilder();
        for(byte x : b){
            sb.append(String.format("%02x", x));
        }
        return sb.toString();
    }
    public static byte[] toByte(String s){
        int n = s.length();
        byte[] b = new byte[n/2];
        for(int i=0; i<n; i+=2){
            b[i/2]=(byte)Integer.parseInt(s.substring(i, i+2), 16);
        }
        return b;
    }
}
