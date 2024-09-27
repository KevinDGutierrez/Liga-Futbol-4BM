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
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.LoginController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.MenuPrincipalController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.PartidoController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.PatrocinadorController;
import com.kevingutierrez.webapp.ligafutbol.controller.FXController.UserController;

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

    @Override
    public void init() {
        this.applicationContext = new SpringApplicationBuilder(LigafutbolApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("Liga de Futbol Spring Boot");

        mostrarMenuView(); 
        stage.show();
    }

    public Initializable cambiarEscena(String fxmlName, int width, int height) throws IOException {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Métodos para cambiar a otras vistas de manera más limpia
    public void mostrarGolView() {
        try {
            GolController controller = (GolController) cambiarEscena("GolView.fxml", 1200, 750);
            controller.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarJugadorView() {
        try {
            JugadorController controller = (JugadorController) cambiarEscena("JugadorView.fxml", 1200, 750);
            controller.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarPartidoView() {
        try {
            PartidoController controller = (PartidoController) cambiarEscena("PartidoView.fxml", 1200, 750);
            controller.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarPatrocinadorView() {
        try {
            PatrocinadorController controller = (PatrocinadorController) cambiarEscena("PatrocinadorView.fxml", 1200, 750);
            controller.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEquipamientoView() {
        try {
            EquipamientoController controller = (EquipamientoController) cambiarEscena("EquipamientoView.fxml", 1200, 750);
            controller.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarMenuView() {
        try {
            MenuPrincipalController controller = (MenuPrincipalController) cambiarEscena("MenuView.fxml", 1200, 750);
            controller.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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