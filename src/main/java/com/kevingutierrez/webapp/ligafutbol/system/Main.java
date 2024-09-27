package com.kevingutierrez.webapp.ligafutbol.system;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.kevingutierrez.webapp.ligafutbol.LigafutbolApplication;
<<<<<<< HEAD
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.JugadorFXController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.EquipamientoFXController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.EquipoFXController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.GolFXController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.PartidoFXController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.PatrocinadorFXController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.LoginController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.RegistroController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.MenuPrincipalController;
=======
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.EquipamientoController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.EquipoController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.GolController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.JugadorController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.LoginController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.MenuPrincipalController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.PartidoController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.PatrocinadorController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.UserController;
>>>>>>> luisCuxun-2023518

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

<<<<<<< HEAD
public class Main extends Application {
    
=======
public class Main extends Application{
>>>>>>> luisCuxun-2023518
    private ConfigurableApplicationContext applicationContext;
    private Stage stage;
    private Scene scene;

    @Override
<<<<<<< HEAD
    public void init(){
=======
    public void init() {
>>>>>>> luisCuxun-2023518
        this.applicationContext = new SpringApplicationBuilder(LigafutbolApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
<<<<<<< HEAD
        stage.setTitle("Liga Futbol Aplicación");
        mostrarMenuView();
=======
        stage.setTitle("Liga de Futbol Spring Boot");

        mostrarMenuView(); 
>>>>>>> luisCuxun-2023518
        stage.show();
    }

    public Initializable cambiarEscena(String fxmlName, int width, int height) throws IOException {
<<<<<<< HEAD
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
=======
        Initializable controller = null;
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(applicationContext::getBean);
        try (InputStream archivo = Main.class.getResourceAsStream("/templates/" + fxmlName)) {
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(Main.class.getResource("/templates/" + fxmlName));

            scene = new Scene((AnchorPane) loader.load(archivo), width, height);
            stage.setScene(scene);
            stage.sizeToScene();

            controller = loader.getController();
        }

        return controller;
    }

    // Método optimizado para cargar la vista principal (equipo) u otras vistas
    public void mostrarEquipoView() {
        try {
            // Cambiar a la vista especificada
            EquipoController controller = (EquipoController) cambiarEscena("EquipoView.fxml", 1200, 750);
            controller.setStage(this);
>>>>>>> luisCuxun-2023518
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
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
=======
    // Métodos para cambiar a otras vistas de manera más limpia
    public void mostrarGolView() {
        try {
            GolController controller = (GolController) cambiarEscena("GolView.fxml", 1200, 750);
            controller.setStage(this);
        } catch (IOException e) {
>>>>>>> luisCuxun-2023518
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    public void mostrarGolView(){
        try {
            GolFXController golView = (GolFXController)cambiarEscena("GolView.fxml", 1200, 750);
            golView.setStage(this);
        } catch (Exception e) {
=======
    public void mostrarJugadorView() {
        try {
            JugadorController controller = (JugadorController) cambiarEscena("JugadorView.fxml", 1200, 750);
            controller.setStage(this);
        } catch (IOException e) {
>>>>>>> luisCuxun-2023518
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    public void mostrarLoginView(){
        try {
            LoginController loginView = (LoginController)cambiarEscena("LoginView.fxml", 500, 750);
            loginView.setStage(this);
        } catch (Exception e) {
=======
    public void mostrarPartidoView() {
        try {
            PartidoController controller = (PartidoController) cambiarEscena("PartidoView.fxml", 1200, 750);
            controller.setStage(this);
        } catch (IOException e) {
>>>>>>> luisCuxun-2023518
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    public void mostrarMenuView(){
        try {
            MenuPrincipalController menuView = (MenuPrincipalController)cambiarEscena("MenuView.fxml", 1200, 750);
            menuView.setStage(this);
        } catch (Exception e) {
=======
    public void mostrarPatrocinadorView() {
        try {
            PatrocinadorController controller = (PatrocinadorController) cambiarEscena("PatrocinadorView.fxml", 1200, 750);
            controller.setStage(this);
        } catch (IOException e) {
>>>>>>> luisCuxun-2023518
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    public void mostrarPartidoView(){
        try {
            PartidoFXController partidoView = (PartidoFXController)cambiarEscena("PartidoView.fxml", 1200, 750);
            partidoView.setStage(this);
        } catch (Exception e) {
=======
    public void mostrarEquipamientoView() {
        try {
            EquipamientoController controller = (EquipamientoController) cambiarEscena("EquipamientoView.fxml", 1200, 750);
            controller.setStage(this);
        } catch (IOException e) {
>>>>>>> luisCuxun-2023518
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    public void mostrarPatrocinadorView(){
        try {
            PatrocinadorFXController patrocinadorView = (PatrocinadorFXController)cambiarEscena("PatrocinadorView.fxml", 1200, 750);
            patrocinadorView.setStage(this);
        } catch (Exception e) {
=======
    public void mostrarMenuView() {
        try {
            MenuPrincipalController controller = (MenuPrincipalController) cambiarEscena("MenuView.fxml", 1200, 750);
            controller.setStage(this);
        } catch (IOException e) {
>>>>>>> luisCuxun-2023518
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    public void mostrarRegistroView(){
        try {
            RegistroController registroView = (RegistroController)cambiarEscena("RegistroView.fxml", 500, 750);
            registroView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
=======
    public void mostrarUserView() {
        try {
            UserController controller = (UserController) cambiarEscena("RegistroView.fxml", 500, 750);
            controller.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarLoginView() {
        try {
            LoginController controller = (LoginController) cambiarEscena("LoginView.fxml", 500, 750);
            controller.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
>>>>>>> luisCuxun-2023518
