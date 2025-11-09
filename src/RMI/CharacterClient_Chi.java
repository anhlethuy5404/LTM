/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.rmi.*;
import java.util.*;
public class CharacterClient_Chi {
    public static void main(String[] args) {
        String sCode = "B22DCCN107";
        String qCode = "Ttm8NsQg";
        String url = "rmi://203.162.10.109/RMICharacterService";
        try{
            CharacterService service = (CharacterService) Naming.lookup(url);
            String rev = service.requestCharacter(sCode, qCode);
            String res = countC(rev);
            service.submitCharacter(sCode, qCode, res);
            System.out.println(rev + "\n" + res);
        } catch (Exception e){
            
        }
    }
    public static String countC(String x){
        Map<Character, Integer> m = new LinkedHashMap<>();
        for(char c : x.toCharArray()){
            m.put(c, m.getOrDefault(c, 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        for(var entry : m.entrySet()){
            sb.append(entry.getKey()).append(entry.getValue());
        }
        return sb.toString();
    }
}
