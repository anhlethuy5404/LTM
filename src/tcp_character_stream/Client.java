package tcp_character_stream;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "203.162.10.109";
        int port = 2208;

        try (Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(5000);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // a
            String studentCode = "B22DCCN047";
            String qCode = "2S87BluU";
            writer.write(studentCode + ";" + qCode);
            writer.newLine();
            writer.flush();

            // b
            String domainsLine = reader.readLine();
            String[] domains = domainsLine.split(",\\s*");

            // c
            List<String> eduDomains = new ArrayList<>();
            for (String d : domains) {
                if (d.trim().endsWith(".edu")) {
                    eduDomains.add(d.trim());
                }
            }
            String eduList = String.join(", ", eduDomains);
            writer.write(eduList);
            writer.newLine();
            writer.flush();
            
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
