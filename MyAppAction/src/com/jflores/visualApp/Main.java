package com.jflores.visualApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    //sobreescribimos el método start para cargar la interzaz
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Cargar el fxml de la vista con javafx
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        //establecer el nombre de la app
        primaryStage.setTitle("Visual Action App");
        //Establecer icono de la interfaz y agregarlo
        primaryStage.getIcons().add(new Image("com/jflores/visualApp/img/inicio.png"));
        //Establecer el tamaño de la ventana
        primaryStage.setScene(new Scene(root, 900, 500));
        //Mostrar  el escenario (ventana)
        primaryStage.show();
    }

//ejecutamos el metodo launch , buscar en doc FX.
    public static void main(String[] args) {
        launch(args);
    }

}
