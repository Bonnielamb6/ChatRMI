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
public interface InterfazRemotaCliente extends Remote{
    public String mensajeIndividual() throws RemoteException;
    
    public void recibirMensajes(String mensajeTemp) throws RemoteException;
}
