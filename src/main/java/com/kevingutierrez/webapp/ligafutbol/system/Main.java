package com.kevingutierrez.webapp.ligafutbol.system;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.kevingutierrez.webapp.ligafutbol.LigafutbolApplication;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.EquipamientoController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.EquipoController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.GolController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.JugadorController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.PartidoController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.PatrocinadorController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
    private ConfigurableApplicationContext applicationContext;
    private Stage stage;
    private Scene scene;

    //Se ejecuta cada vez que yo instancie la clase MAIN
    @Override
    public void init(){
        this.applicationContext = new SpringApplicationBuilder(LigafutbolApplication.class).run();
    }

    //Se ejecuta al iniciar la aplicacion javaFX
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("Liga de futbol Springboot");
        //carga de la ecena principal
        indexView();
        stage.show();
    }

    //Metodo para cambiar la escena del stage
    public Initializable cambiarEscena(String fxmlName, int width, int height) throws IOException {
        Initializable initializable = null;
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(applicationContext::getBean);
        InputStream archivo = Main.class.getResourceAsStream("/templates/" + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/templates/" + fxmlName));

        scene = new Scene((AnchorPane)loader.load(archivo), width, height);
        stage.setScene(scene);
        stage.sizeToScene();

        initializable = (Initializable)loader.getController();

        return initializable;
    }

    //Metodo para mostrar el Main o el Index
    public void indexView(){
        try {
            EquipoController indexView = (EquipoController)cambiarEscena("Equipo.fxml", 1200, 750);
            indexView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            GolController indexView = (GolController)cambiarEscena("Gol.fxml", 1200, 750);
            indexView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JugadorController indexView = (JugadorController)cambiarEscena("jugador.fxml", 1200, 750);
            indexView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            PartidoController indexView = (PartidoController)cambiarEscena("partido.fxml", 1200, 750);
            indexView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            PatrocinadorController indexView = (PatrocinadorController)cambiarEscena("patrocinador.fxml", 1200, 750);
            indexView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            EquipamientoController indexView = (EquipamientoController)cambiarEscena("equipamiento.fxml", 1200, 750);
            indexView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}