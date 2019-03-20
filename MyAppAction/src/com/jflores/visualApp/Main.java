package com.jflores.visualApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String VIEW = "view.fxml";
    private static final String TITLE = "Visual Action App";
    private static final String ICON = "com/jflores/visualApp/img/inicio.png";
    private static final Integer WIDTH = 920;
    private static final Integer HEIGHT = 600;

    //sobreescribimos el método start para cargar la interzaz
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Cargar el fxml de la vista con javafx
        Parent root = FXMLLoader.load(getClass().getResource(VIEW));
        //establecer el nombre de la app
        primaryStage.setTitle(TITLE);
        //Establecer icono de la interfaz y agregarlo
        primaryStage.getIcons().add(new Image(ICON));
        //Establecer el tamaño de la ventana
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        //Mostrar  el escenario (ventana)
        primaryStage.show();

    }


//ejecutamos el metodo launch , buscar en doc FX.
    public static void main(String[] args) {
        launch(args);
    }

}
