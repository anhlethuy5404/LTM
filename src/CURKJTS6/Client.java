package CURKJTS6;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "203.162.10.109";
        int port = 2206;
        String studentCode = "B22DCCN047";
        String qCode = "4rSS9YB8";
        String mess = studentCode + ";" + qCode;
        try(Socket socket = new Socket(host, port)){
            socket.setSoTimeout(5000);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            out.writeUTF(mess + "\n");
            out.flush();
            
            String text = in.readUTF();
            int key = in.readInt();
            
            String res = decryptCaesar(text, key);
            System.out.println(res);
            out.writeUTF(res+"\n");
            out.flush();
        }
    }
    private static String decryptCaesar(String encryptedText, int key) {
        StringBuilder decryptedText = new StringBuilder();
        int shift = key % 26; 

        for (char character : encryptedText.toCharArray()) {
            if (character >= 'a' && character <= 'z') {
                char decryptedChar = (char) ('a' + (character - 'a' - shift + 26) % 26);
                decryptedText.append(decryptedChar);
            } else if (character >= 'A' && character <= 'Z') {
                char decryptedChar = (char) ('A' + (character - 'A' - shift + 26) % 26);
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(character);
            }
        }
        return decryptedText.toString();
    }
}
