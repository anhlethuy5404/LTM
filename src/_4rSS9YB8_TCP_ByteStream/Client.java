package _4rSS9YB8_TCP_ByteStream;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "203.162.10.109";
        int port = 2206;
        String studentCode = "B22DCCN047";
        String qCode = "4rSS9YB8";
        String mess = studentCode + ";" + qCode;
        try(Socket socket = new Socket(host, port)){
            socket.setSoTimeout(5000);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            
            out.write((mess+'\n').getBytes());
            out.flush();
            
            byte[] buffer = new byte[1024];
            int byteRead = in.read(buffer);
            if (byteRead==-1) return;
            String num = new String(buffer, 0, byteRead).trim();
            String[] numStr = num.split(",");
            int[] numbers;
            int index = 0;
            numbers = Arrays.stream(numStr).map(String::trim).mapToInt(Integer::parseInt).toArray();
            String res = findLongestSubarray(numbers);
            System.out.println(num);
            System.out.println(res);
            out.write((res+"\n").getBytes());
            out.flush();
        }
    }
    public static String findLongestSubarray(int[] numbers){
        int n = numbers.length;
        int[] len = new int[n];
        int[] prev = new int[n];
        Arrays.fill(len, 1);
        Arrays.fill(prev, -1);
        int maxLen = 0;
        int end = -1;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                //neu so sau lon hon va tao ra list dai hon
                if(numbers[i]>numbers[j] && len[j]+1 > len[i]){
                    len[i]=len[j]+1;
                    prev[i]=j;
                }
            }
            if(len[i]>maxLen){
                maxLen = len[i];
                end = i;
            }
        }
        
        List<Integer> a = new ArrayList<>();
        int curr = end;
        while(curr != -1){
            a.add(numbers[curr]);
            curr = prev[curr];
        }
        Collections.reverse(a);
        String res = a.stream().map(String::valueOf).collect(Collectors.joining(","));
        return res + ";" + maxLen;
    }
}
