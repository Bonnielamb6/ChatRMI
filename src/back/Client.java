/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Client extends UnicastRemoteObject implements InterfazRemotaCliente{

    String historial;
    String nuevoMensaje;
    String direccionIP;
    String puerto;
    String historialIndividual;

    public Client() throws RemoteException{
        historial = "";
        historialIndividual ="";
        nuevoMensaje = "";
        direccionIP = "127.0.0.1";
        puerto = "1234";
    }

    public void mandarMensaje(String mensajeTemp, String direccionDestino, String puertoDestino) {
        
    }

    public void recibirMensajes(String mensajeTemp) {
        historialIndividual += "\n"+mensajeTemp;
        System.out.println(historialIndividual);
    }
    
    public String mensajeIndividual(){
        return historialIndividual;
    }

    public void levantarServicio(){
        try {
            Registry registry = LocateRegistry.createRegistry(
                    Integer.parseInt("1235"));

            InterfazRemotaCliente mir = new Client();

            java.rmi.Naming.rebind("//"
                    + java.net.InetAddress.getLocalHost().getHostAddress()
                    + ":1235/ChatRMI", mir);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void buscarCambiosIndividuales(String direccionDestino){
        Thread hilo = new Thread(() -> {
            try {
                InterfazRemotaCliente interfaz
                        = (InterfazRemotaCliente) Naming.lookup("//"
                                + direccionDestino + ":" + "1234/ChatRMI");
                while (true) {
                    if (!interfaz.mensajeIndividual().equals(historialIndividual) ) {
                        historialIndividual = interfaz.mensajeIndividual();
                        System.out.println(historialIndividual);
                    }
                }
            } catch (Exception e) {
                System.out.println("Hubo un error " + e);
            }
        });
        hilo.start();
    }
    
    public void buscarCambios(String direccionDestino) {

        Thread hilo = new Thread(() -> {
            try {
                InterfazRemota interfaz
                        = (InterfazRemota) Naming.lookup("//"
                                + direccionDestino + ":" + "1234/ChatRMI");
                while (true) {
                    if (!interfaz.broadcast().equals(historial) ) {
                        historial = interfaz.broadcast();
                        System.out.println(historial);
                    }
                }
            } catch (Exception e) {
                System.out.println("Hubo un error " + e);
            }
        });
        hilo.start();
    }

//    public static void main(String[] args) throws RemoteException {
//        Client cliente = new Client();
//        String direccionDestino = "127.0.0.1";
//        String mensaje = "no hay mensaje";
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Escribe la direccion de destino");
//        direccionDestino = sc.nextLine();
//        cliente.buscarCambios(direccionDestino);
//        try {
//            InterfazRemota interfaz
//                    = (InterfazRemota) Naming.lookup("//"
//                            + direccionDestino + ":" + "1234/ChatRMI");
//            while (cliente.direccionIP == direccionDestino) {
//
//                mensaje = sc.nextLine();
//                interfaz.actualizar(mensaje);
//                System.out.println(interfaz.broadcast());
//            }
//        } catch (Exception e) {
//            System.out.println("Hubo un problema " + e);
//        }
//    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public String getNuevoMensaje() {
        return nuevoMensaje;
    }

    public void setNuevoMensaje(String nuevoMensaje) {
        this.nuevoMensaje = nuevoMensaje;
    }

    public String getDireccionIP() {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getHistorialIndividual() {
        return historialIndividual;
    }

    public void setHistorialIndividual(String historialIndividual) {
        this.historialIndividual = historialIndividual;
    }
    
    
}
