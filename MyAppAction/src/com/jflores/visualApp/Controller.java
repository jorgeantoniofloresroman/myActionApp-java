package com.jflores.visualApp;

import com.jflores.visualApp.data.Data;
import com.jflores.visualApp.data.Item;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Controller {

    //Variables de la clase controller los cuales son elementos que se usan para la lógica o clases para relacionar
    // su parte logica con la interfaz. Ej: boton. Para relacioanrlo se usa la notación @FXML = id de la view
    private ArrayList<Item> items;
    @FXML
    private Button button;
    @FXML
    private Label label;

    public void initialize() {

        //Llamamos a la instancia para cargar los datos cuando se inicializa la pantalla
        Data.getInstance();

        //Control de errores en el caso de que no encuentre el archivo de configuración properties.conf
        //Es simplemente un aviso en un Lbal, cambiar a otro lugar cuando se ejecute
        if (Data.isFlag()){
            label.setText("Error a la hora de encontrar el archivo de configuración");
        }
        setIcons();



        //Guardamos los datos e items
        items = Data.getInstance().getItems();
//Pintamos los objetos que ha encontrado para depurar.
        for (int i=0;i<items.size();i++){
            System.out.println(items.get(i).getDetails());
        }


    }

    public void setIcons(){
        //Crear una imagen de la carpeta donde está la imagen
        Image iconLedImageApagado = new Image("com/jflores/visualApp/img/ledApagado.png",240,240,false,false);
        // se establece la imagen del boton con el metodo setGraphic, se debe usar como parametro una subclase de Node que es ImageView
        //public class ImageView
        //extends Node
        //The ImageView is a Node used for painting images loaded with Image class.
        button.setGraphic(new ImageView(iconLedImageApagado));
    }

//No usamos threads para no mandar más datos a la vez.



    //Metodo para manejar el encendido del Led cuando se hace click sobre el boton.
    @FXML
    public void handleClickButton(){

        label.setText("");

        //iamgenes para hacer el cambio y mostrar que hay un cambio y se esta enviando el mensaje.
        Image iconLedImageApagado = new Image("com/jflores/visualApp/img/ledApagado.png",240,240,false,false);
        Image iconLedImageEnceniddo = new Image("com/jflores/visualApp/img/ledEncendido.png",240,240,false,false);


//https://docs.oracle.com/javase/8/javafx/api/javafx/concurrent/Task.html
        Task<Void> myTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {}
                return null;

            }

            @Override
            protected void succeeded() {
                button.setGraphic(new ImageView(iconLedImageApagado));
                button.setDisable(false);
            }

            @Override
            protected void running() {
                button.setGraphic(new ImageView(iconLedImageEnceniddo));
                button.setDisable(true);
            }

            };

        Client newClient = new Client();

        Task<Void> myTask2 = new Task<Void>() {
            boolean x=false;
            @Override
            protected Void call() throws Exception {
                try { //subcestible de ser comentado
                    newClient.openConnection(items.get(0).getDetails(),Integer.parseInt(items.get(1).getDetails()));
                    newClient.openStream();
                    newClient.send(items.get(2).getDetails());
                    newClient.closeConnection();
                }catch (Exception e){
                    x=true;
                    System.out.println("errorrr"); //capturar el throws, mirar que es eso
                }
                return null;

            }

            @Override
            protected void succeeded() {
               if(!x){
                   label.setText("Orden " + items.get(2).getShortDescription() + " enviada.");
               }else{
                   label.setText("Error al conectar con el uC");
               }

            }
        };


        new Thread(myTask).start();
        new Thread(myTask2).start();



    }




}
