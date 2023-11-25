/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

import java.rmi.Naming;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Client {

    String historial;
    String nuevoMensaje;
    String direccionIP;
    String puerto;

    public Client() {
        historial = "";
        nuevoMensaje = "";
        direccionIP = "127.0.0.1";
        puerto = "1234";
    }
    
    

    public void mandarMensaje(String mensajeTemp, String direccionDestino, String puertoDestino) {

    }

    public void recibirMensajes(String mensajeTemp) {

    }

    public void buscarCambios(String direccionDestino) {
        
        Thread hilo = new Thread(() -> {
            try {
            InterfazRemota interfaz
                    = (InterfazRemota) Naming.lookup("//"
                            + direccionDestino + ":" + "1234/ChatRMI");
            if(interfaz.broadcast() != historial){
                historial = interfaz.broadcast();
            }
        } catch (Exception e) {
            System.out.println("Hubo un error " + e);
        }
        });
        hilo.start();
    }

    public static void main(String[] args) {
        Client cliente = new Client();
        String direccionDestino = "127.0.0.1";
        String mensaje = "no hay mensaje";
        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe la direccion de destino");
        direccionDestino = sc.nextLine();
        cliente.buscarCambios(direccionDestino);
        try {
            while (!"99".equals(mensaje)) {

                mensaje = sc.nextLine();
                InterfazRemota interfaz
                        = (InterfazRemota) Naming.lookup("//"
                                + direccionDestino + ":" + "1234/ChatRMI");

                interfaz.actualizar(mensaje);
                System.out.println(interfaz.broadcast());
            }
        } catch (Exception e) {
            System.out.println("Hubo un problema " + e);
        }
    }
}
