/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

/**
 *
 * @author PC
 */

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClaseRemota extends UnicastRemoteObject implements 
        InterfazRemota{
    String historial;
    String puerto;
    String nuevoMensaje;
    
    public ClaseRemota() throws RemoteException{
        historial = "";
        puerto = "1234";
    }
    
    
    public void broadcast() throws RemoteException{
        System.out.println(nuevoMensaje);
    }
    
    public void actualizar(String mensajeTemp) throws RemoteException{
        nuevoMensaje = mensajeTemp;
        historial += "\n"+mensajeTemp;
    }
    
    
    
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(
                    Integer.parseInt("1234"));

            InterfazRemota mir = new ClaseRemota();

            java.rmi.Naming.rebind("//"
                    + java.net.InetAddress.getLocalHost().getHostAddress()
                    + ":1234/ChatRMI", mir);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    

    
}
