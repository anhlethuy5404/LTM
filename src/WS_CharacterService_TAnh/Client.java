/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS_CharacterService_TAnh;

import java.util.*;

/**
 *
 * @author asus
 */
public class Client {
    public static void main(String[] args) {
        String sCode = "B22DCCN035";
        String qCode = "EGKgevmN";
        try {
            CharacterService_Service sv = new CharacterService_Service();
            CharacterService port = sv.getCharacterServicePort();
            String rev = port.requestString(sCode, qCode);
            List<String> res = process(rev);
            port.submitCharacterStringArray(sCode, qCode, res);
        } catch (Exception e){
            
        }
    }
    public static List<String> process(String rev){
        List<String> res = new ArrayList<>();
        String[] words = rev.toLowerCase().split("[\\s_]+");
        StringBuilder pascal = new StringBuilder();
        StringBuilder camel = new StringBuilder();
        StringBuilder snake = new StringBuilder();
        for(int i=0; i<words.length; i++){
            String word = words[i];
            String str = Character.toUpperCase(word.charAt(0)) + word.substring(1);
            pascal.append(str);
            if(i==0){
                camel.append(word);
                snake.append(word);
            }
            else{
                camel.append(str);
                snake.append("_" + word);
            }            
        }
        res.add(pascal.toString());
        res.add(camel.toString());
        res.add(snake.toString());
        return res;
    }
}
