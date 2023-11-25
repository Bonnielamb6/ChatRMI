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
    
    public void mandarMensaje(String mensajeTemp, String direccionDestino, String puertoDestino){
        
    }
    
    public void recibirMensajes(String mensajeTemp){
        
    }
    
    public static void main(String[] args) {
        String direccionDestino = "127.0.0.1";
        
        System.out.println("Escribe la direccion de destino");
        Scanner sc = new Scanner(System.in);
        direccionDestino = sc.nextLine();
        try {
            InterfazRemota interfaz
                    = (InterfazRemota) Naming.lookup("//"
                            +direccionDestino+":"+"1234/ChatRMI");

        } catch (Exception e) {
            System.out.println("Hubo un problema "+e);
        }
    }
}
