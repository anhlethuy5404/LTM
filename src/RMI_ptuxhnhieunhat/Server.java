/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI_ptuxhnhieunhat;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
public class Server extends UnicastRemoteObject implements ByteService{
    public Server() throws RemoteException {
        super();
    }

    @Override
    public byte[] requestData(String studentCode, String qCode) throws RemoteException {
        System.out.println("Client " + studentCode + " dang request byte data...");
        return new byte[]{1, 2, 3, 2, 1, 2}; // Dữ liệu mẫu
    }

    @Override
    public void submitData(String studentCode, String qCode, byte[] data) throws RemoteException {
        System.out.println("\n===== KET QUA KIEM TRA =====");
        System.out.println("Du lieu Client gui: " + Arrays.toString(data));
        
        // Ky vong: phan tu 2 xuat hien 3 lan -> [2, 3]
        byte[] expected = {2, 3};
        if (Arrays.equals(data, expected)) {
            System.out.println("=> TRANG THAI: CHINH XAC (PASS)");
        } else {
            System.out.println("=> TRANG THAI: SAI (FAIL)");
        }
        System.out.println("============================\n");
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/RMIByteService", new Server());
            System.out.println("Server ByteService dang chay...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
