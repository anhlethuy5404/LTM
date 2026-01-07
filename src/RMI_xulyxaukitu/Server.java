/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI_xulyxaukitu;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
public class Server extends UnicastRemoteObject implements CharacterService {
    public Server() throws RemoteException {
        super();
    }

    @Override
    public String requestCharacter(String studentCode, String qCode) throws RemoteException {
        // Chuỗi JSON mẫu
        return "{\"name\": \"Alice\", \"age\": 25, \"city\": \"Wonderland\", \"country\": \"Fictionland\"}";
    }

    @Override
    public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException {
        System.out.println("\n===== KET QUA KIEM TRA =====");
        System.out.println("Ket qua Client gui: " + strSubmit);
        
        String expected = "name: Alice, city: Wonderland; age: 25, country: Fictionland";
        if (strSubmit.equals(expected)) {
            System.out.println("=> TRANG THAI: CHINH XAC (PASS)");
        } else {
            System.out.println("=> TRANG THAI: SAI (FAIL)");
            System.out.println("=> MONG DOI: " + expected);
        }
        System.out.println("============================\n");
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/RMICharacterService", new Server());
            System.out.println("Server Character dang chay...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
