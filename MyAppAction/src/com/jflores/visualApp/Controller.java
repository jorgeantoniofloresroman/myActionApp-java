package com.jflores.visualApp;

import com.jflores.visualApp.data.Data;
import com.jflores.visualApp.data.Item;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Controller {

    private static final String TV_OFF = "com/jflores/visualApp/img/off.png";
    private static final String TV_ON = "com/jflores/visualApp/img/on.png";
    private static final String UP_CHANNEL_ON = "com/jflores/visualApp/img/up-channel_on.png";
    private static final String UP_CHANNEL_OFF = "com/jflores/visualApp/img/up-channel_off.png";
    private static final String DOWN_CHANNEL_ON = "com/jflores/visualApp/img/down-channel_on.png";
    private static final String DOWN_CHANNEL_OFF = "com/jflores/visualApp/img/down-channel_off.png";
    private static final String UP_VOLUME_ON = "com/jflores/visualApp/img/up_volume_on.png";
    private static final String UP_VOLUME_OFF = "com/jflores/visualApp/img/up_volume_off.png";
    private static final String DOWN_VOLUME_ON = "com/jflores/visualApp/img/down-volume-on.png";
    private static final String DOWN_VOLUME_OFF = "com/jflores/visualApp/img/down-volume-off.png";
    private static final String GENERIC_PORT_ON = "com/jflores/visualApp/img/generic-on.png";
    private static final String GENERIC_PORT_OFF = "com/jflores/visualApp/img/generic-off.png";

    private static final Integer LECTURE_IP = 0;
    private static final Integer LECTURE_PORT = 1;
    private static final Integer LECTURE_TV_ON_OFF = 2;
    private static final Integer LECTURE_TV_UP_VOLUME = 3;
    private static final Integer LECTURE_DOWN_VOLUME = 4;
    private static final Integer LECTURE_UP_CHANNEL = 5;
    private static final Integer LECTURE_DOWN_CHANNEL = 6;
    private static final Integer LECTURE_GEERIC_PORT = 7;

    private static final Integer WIDTH = 240;
    private static final Integer HEIGHT = 240;

    private static final Integer THREAD_SLEEP = 2000;

    //Variables de la clase controller los cuales son elementos que se usan para la lógica o clases para relacionar
    // su parte logica con la interfaz. Ej: boton. Para relacioanrlo se usa la notación @FXML = id de la view
    private ArrayList<Item> items;
    @FXML
    private Button buttonOnTv;
    @FXML
    private Button buttonUpChannel;
    @FXML
    private Button buttonDownChannel;
    @FXML
    private Button buttonUpVolume;
    @FXML
    private Button buttonDownVolume;
    @FXML
    private Button buttonGenericPort;
    @FXML
    private Label label;

    Client newClient = new Client();

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
        Image iconTVOff = new Image(TV_OFF,WIDTH,HEIGHT,false,false);
        // se establece la imagen del boton con el metodo setGraphic, se debe usar como parametro una subclase de Node que es ImageView
        //public class ImageView
        //extends Node
        //The ImageView is a Node used for painting images loaded with Image class.
        buttonOnTv.setGraphic(new ImageView(iconTVOff));

        Image iconUpChannel = new Image(UP_CHANNEL_OFF,WIDTH,HEIGHT,false,false);
        buttonUpChannel.setGraphic(new ImageView(iconUpChannel));

        Image iconDownChannel = new Image(DOWN_CHANNEL_OFF,WIDTH,HEIGHT,false,false);
        buttonDownChannel.setGraphic(new ImageView(iconDownChannel));

        Image iconDownVolume = new Image(DOWN_VOLUME_OFF,WIDTH,HEIGHT,false,false);
        buttonDownVolume.setGraphic(new ImageView(iconDownVolume));

        Image iconUpVolume = new Image(UP_VOLUME_OFF,WIDTH,HEIGHT,false,false);
        buttonUpVolume.setGraphic(new ImageView(iconUpVolume));

        Image iconGenericPort = new Image(GENERIC_PORT_OFF,WIDTH,HEIGHT,false,false);
        buttonGenericPort.setGraphic(new ImageView(iconGenericPort));

    }

//No usamos threads para no mandar más datos a la vez.



    //Metodo para manejar el encendido de la TV cuando se hace click sobre el boton On/Off.
    @FXML
    public void handleClickButtonOn(){
        //Como no es posible mandar un metodo con parametros a traves de la vista o al menos no lo encontre en la doc
        //si que vi la forma de encapsular un metodo y pasar hay parametros de forma muy sencilla
        //Con el fin de no repetir codigo y tenerlo todo mas organizado
        encapsuleMethod(
                TV_OFF,
                TV_ON,
                buttonOnTv,
                LECTURE_TV_ON_OFF
        );


    }


    @FXML
    public void handleClickButtonUpChannel(){
        encapsuleMethod(
                UP_CHANNEL_OFF,
                UP_CHANNEL_ON,
                buttonUpChannel,
                LECTURE_UP_CHANNEL
        );
    }

    @FXML
    public void handleClickButtonDownChannel(){
        encapsuleMethod(
                DOWN_CHANNEL_OFF,
                DOWN_CHANNEL_ON,
                buttonDownChannel,
                LECTURE_DOWN_CHANNEL
        );
    }

    @FXML
    public void handleClickButtonUpVolume(){
        encapsuleMethod(
                UP_VOLUME_OFF,
                UP_VOLUME_ON,
                buttonUpVolume,
                LECTURE_TV_UP_VOLUME
        );
    }

    @FXML
    public void handleClickButtonDownVolume(){
        encapsuleMethod(
                DOWN_VOLUME_OFF,
                DOWN_VOLUME_ON,
                buttonDownVolume,
                LECTURE_DOWN_VOLUME
        );
    }

    @FXML
    public void handleClickButtonGenericPort(){
        encapsuleMethod(
                GENERIC_PORT_OFF,
                GENERIC_PORT_ON,
                buttonGenericPort,
                LECTURE_GEERIC_PORT
        );
    }


public void encapsuleMethod(String imagenApagada,String imagenEncendida, Button botonPulsado, Integer datoAEnviar){

//    label.setText("");

    //iamgenes para hacer el cambio y mostrar que hay un cambio y se esta enviando el mensaje.
    Image iconApagado = new Image(imagenApagada,WIDTH,HEIGHT,false,false);
    Image iconEncendido = new Image(imagenEncendida,WIDTH,HEIGHT,false,false);


//https://docs.oracle.com/javase/8/javafx/api/javafx/concurrent/Task.html
    Task<Void> myTask = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            try {
                Thread.sleep(THREAD_SLEEP);
            } catch (Exception e) {}
            return null;

        }

        @Override
        protected void succeeded() {
            botonPulsado.setGraphic(new ImageView(iconApagado));
            botonPulsado.setDisable(false);
        }

        @Override
        protected void running() {
            botonPulsado.setGraphic(new ImageView(iconEncendido));
            botonPulsado.setDisable(true);
        }

    };



    Task<Void> myTask2 = new Task<Void>() {
        boolean x=false;
        @Override
        protected Void call() throws Exception {
            try { //subcestible de ser comentado
                newClient.openConnection(items.get(LECTURE_IP).getDetails(),Integer.parseInt(items.get(LECTURE_PORT).getDetails()));
                newClient.openStream();
                newClient.send(items.get(datoAEnviar).getDetails());
                newClient.closeConnection();
            }catch (Exception e){
                x=true;
                System.out.println("Falla conexión"); //capturar el throws, mirar que es eso
            }
            return null;

        }

        @Override
        protected void succeeded() {
            if(!x){
                label.setText("Orden:  " + items.get(datoAEnviar).getShortDescription() + "  enviada.");
                label.setTextFill(Color.color(0.2,1,0.1));
            }else{
                label.setText("Error al conectar con el uC");
                label.setTextFill(Color.color(1,0,0));
            }

        }
    };


    new Thread(myTask).start();
    new Thread(myTask2).start();


}

}
