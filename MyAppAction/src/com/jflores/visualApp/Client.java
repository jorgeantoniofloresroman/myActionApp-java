//esta clase no se usa solo para ver mejor la estructuracion

package com.jflores.visualApp;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private static Socket socket;
    private static DataOutputStream bufferOut = null;



    public void openConnection(String ip, int puerto)  throws Exception{
        try {
            socket = new Socket(ip, puerto);
            System.out.println("Conectado a :" + socket.getInetAddress().getHostName());
        } catch (Exception e) {
            System.out.println("Excepción al levantar conexión: " + e.getMessage());
        }
    }


    public void openStream() throws Exception{
        try {
            bufferOut = new DataOutputStream(socket.getOutputStream());
            bufferOut.flush();
        } catch (IOException e) {
            System.out.println("Error en la apertura de flujos");
        }
    }

    public void send(String s) throws Exception{
        try {
            bufferOut.writeUTF(s);
            bufferOut.flush();
        } catch (IOException e) {
            System.out.println("IOException a enviar");
        }
    }

    public void closeConnection() throws Exception{
        try {
            bufferOut.close();
            socket.close();
            //Lo ponemos a null para que vuelva a crearlo de nuevo cuando se vuelva a llamar y controlar bien las excepciones
            socket=null;
            System.out.println("Conexión terminada");
        } catch (IOException e) {
            System.out.println("IOException on cerrarConexion()");
        }
    }

}
