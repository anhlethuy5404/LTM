/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI_bo3sopytago;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Server extends UnicastRemoteObject implements DataService{
    public Server() throws RemoteException { super(); }

    @Override
    public Object requestData(String studentCode, String qCode) throws RemoteException {
        return 20; // Gia lap N = 20
    }

    @Override
    public void submitData(String studentCode, String qCode, Object data) throws RemoteException {
        System.out.println("===== KET QUA KIEM TRA =====");
        List<List<Integer>> clientResult = (List<List<Integer>>) data;
        
        // Đáp án chuẩn cho N = 20
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(3, 4, 5));
        expected.add(Arrays.asList(5, 12, 13));
        expected.add(Arrays.asList(8, 15, 17));

        // Kiểm tra
        if (clientResult != null && clientResult.equals(expected)) {
            System.out.println("Ket qua: CHINH XAC (PASS)");
            System.out.println("Du lieu nhan duoc: " + clientResult);
        } else {
            System.out.println("Ket qua: SAI (FAIL)");
            System.out.println("Mong doi: " + expected);
            System.out.println("Thuc te: " + clientResult);
        }
        System.out.println("============================\n");
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("RMIDataService", new Server());
            System.out.println("Server RMI san sang...");
        } catch (Exception e) { e.printStackTrace(); }
    }
}
