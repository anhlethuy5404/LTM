/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bImqrsP6_CharacterService;

import java.util.*;

public class Client {
    public static void main(String[] args) {
        String studentCode = "B22DCCN047";
        String qCode = "bImqrsP6";
        String url = "http://203.162.10.109:8080/JNPWS/CharacterService?wsdl";
        CharacterService_Service sv = new CharacterService_Service();
        CharacterService service = sv.getCharacterServicePort();
        String response = service.requestString(studentCode, qCode);
        System.out.println(response);
        List<String> data = convert(response);
        service.submitCharacterStringArray(studentCode, qCode, data);
    }
    public static List<String> convert(String response){
        String[] words = response.toLowerCase().split("[\\s_]+");
        StringBuilder pascal = new StringBuilder();
        StringBuilder camel = new StringBuilder();
        StringBuilder snake = new StringBuilder();
        for(int i=0; i<words.length; i++){
            String word = words[i];
            String capital = Character.toUpperCase(word.charAt(0))+ word.substring(1);
            pascal.append(capital);
            if (i==0) camel.append(word);
            else camel.append(capital);
            snake.append(word);
            if(i<words.length-1) snake.append("_");
        }
        List<String> res = new ArrayList<>();
        res.add(pascal.toString());
        res.add(camel.toString());
        res.add(snake.toString());
        return res;
    }
}
