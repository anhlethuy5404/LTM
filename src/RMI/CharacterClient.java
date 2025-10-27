/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.util.*;
import java.rmi.*;

/**
 *
 * @author asus
 */
public class CharacterClient {
    public static void main(String[] args) throws Exception, RemoteException {
        String host = "203.162.10.109";
        String studentCode = "B22DCCN047";
        String qCode = "M19cwsHm";
        String serviceName = "RMICharacterService";
        String url = "rmi://203.162.10.109/" + serviceName;
        CharacterService service = (CharacterService) Naming.lookup(url);
        String rev = service.requestCharacter(studentCode, qCode);
        String res = countString(rev);
        service.submitCharacter(studentCode, qCode, res);
    }
    public static String countString(String input){
        Map<Character, Integer> m = new LinkedHashMap<>();
        for (char c : input.toCharArray()){
            m.put(c, m.getOrDefault(c, 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Character, Integer> entry : m.entrySet()){
            sb.append(entry.getKey()).append(entry.getValue());
        }
        return sb.toString();
    }
}
