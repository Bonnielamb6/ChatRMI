/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package back;


import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author PC
 */
public interface InterfazRemota extends Remote {
    
    public void broadcast() throws RemoteException;
    
    public void actualizar(String mensajeTemp) throws RemoteException;
    
}
