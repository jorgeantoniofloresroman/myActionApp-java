package com.jflores.visualApp.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Data {
    //Referencia del unico objeto que vamos a crear
    private static Data instance;
    //Nombre Fichero de configuración donde estan los datos, esos datos luego se alamacenaran en item
    private static String filename = "properties.conf";
    //Lista de Item(shortDescription,details) para guardarlos en una lista.
    private static ArrayList<Item> listOfItem = new ArrayList();

    private static boolean flag = false;

    public static Data getInstance() {
        if (instance==null){
            // se crea la instancia si no existe y ademas se va a buscar sólo una vez  los datos al fchero
            instance = new Data();
            try {
                BufferedReader br = new BufferedReader(new FileReader(filename));
                String input= br.readLine();
                while (input != null) {
                    //si podemos, deberiamos cambiar por otro caracter
                    String[] itemPieces = input.split("\t");
                    //capturamos cada dato en un array
                    String shortDescription = itemPieces[0];
                    String details = itemPieces[1];
                    //Creamos el Item y lo agregamos a una lista de Items
                    Item newItem = new Item(shortDescription, details);
                    listOfItem.add(newItem);
                    input= br.readLine();
                }

            } catch (IOException e){
                //En el caso que hubiera un error de lectura avisa con una bandera
                System.out.println(e.getMessage());
                flag = true;
            }

        }
        //si este método se llama por segunda vez devuelve la primera instancia y no crea otro objeto
        return instance;
    }
// Singleton , constructor privado para que no se pueda acceder a el  (clase con una únca instancia)
    //aunque no se pueda acceder cuando se crea la instancia aparece inizializado.
    private Data() {
        System.out.println("Inicializado");
    }

    //método para devolver los Items (datos del archivo de conf y llevamos a un arrayList)
    public ArrayList<Item> getItems() {
        return listOfItem;
    }

    //Comprueba si la bandera es falsa o verdarea para controlar el error de lectura del fichero
    public static boolean isFlag() {
        return flag;
    }
}
