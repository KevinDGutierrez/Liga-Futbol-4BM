package com.kevingutierrez.webapp.ligafutbol.system;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.kevingutierrez.webapp.ligafutbol.LigafutbolApplication;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.JugadorFXController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.EquipamientoFXController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.EquipoFXController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.GolFXController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.PartidoFXController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.PatrocinadorFXController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.LoginController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.RegistroController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.MenuPrincipalController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    
    private ConfigurableApplicationContext applicationContext;
    private Stage stage;
    private Scene scene;

    @Override
    public void init(){
        this.applicationContext = new SpringApplicationBuilder(LigafutbolApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("Liga Futbol Aplicación");
        mostrarMenuView();
        stage.show();
    }

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

    public void mostrarJugadorView(){
        try {
            JugadorFXController jugadorView = (JugadorFXController)cambiarEscena("JugadorView.fxml", 1200, 750);
            jugadorView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarEquipamientoView(){
        try {
            EquipamientoFXController equipamientoView = (EquipamientoFXController)cambiarEscena("EquipamientoView.fxml", 1200, 750);
            equipamientoView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void mostrarEquipoView(){
        try {
            EquipoFXController equipoView = (EquipoFXController)cambiarEscena("EquipoView.fxml", 1200, 750);
            equipoView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarGolView(){
        try {
            GolFXController golView = (GolFXController)cambiarEscena("GolView.fxml", 1200, 750);
            golView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarLoginView(){
        try {
            LoginController loginView = (LoginController)cambiarEscena("LoginView.fxml", 500, 750);
            loginView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarMenuView(){
        try {
            MenuPrincipalController menuView = (MenuPrincipalController)cambiarEscena("MenuView.fxml", 1200, 750);
            menuView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarPartidoView(){
        try {
            PartidoFXController partidoView = (PartidoFXController)cambiarEscena("PartidoView.fxml", 1200, 750);
            partidoView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarPatrocinadorView(){
        try {
            PatrocinadorFXController patrocinadorView = (PatrocinadorFXController)cambiarEscena("PatrocinadorView.fxml", 1200, 750);
            patrocinadorView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarRegistroView(){
        try {
            RegistroController registroView = (RegistroController)cambiarEscena("RegistroView.fxml", 500, 750);
            registroView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
