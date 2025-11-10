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
public class CharacterClient_TAnh {
    public static void main(String[] args) {
        String sCode = "B22DCCN035";
        String qCode = "R4nj6CkC";
        String url = "rmi://203.162.10.109/RMICharacterService";
        try{
            CharacterService sv = (CharacterService) Naming.lookup(url);
            String rev = sv.requestCharacter(sCode, qCode);
            String res = process(rev);
            sv.submitCharacter(sCode, qCode, res);
        } catch (Exception e){
            
        }
    }
    public static String process(String rev){
        Map<Character, Integer> m = new LinkedHashMap<>();
        for(char c : rev.toCharArray()){
            m.put(c, m.getOrDefault(c, 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for(var x : m.entrySet()){
            if(!first) sb.append(", ");
            String key = "\"" + x.getKey() + "\"";
            sb.append(key + ": " + x.getValue());
            first = false;
        }
        sb.append("}");
        return sb.toString();
    }
}
