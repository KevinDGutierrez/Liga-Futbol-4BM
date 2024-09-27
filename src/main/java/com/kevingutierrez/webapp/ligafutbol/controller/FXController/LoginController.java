package com.kevingutierrez.webapp.ligafutbol.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kevingutierrez.webapp.ligafutbol.model.Equipo;
import com.kevingutierrez.webapp.ligafutbol.model.Jugador;
import com.kevingutierrez.webapp.ligafutbol.model.User;
import com.kevingutierrez.webapp.ligafutbol.service.UserService;
import com.kevingutierrez.webapp.ligafutbol.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class LoginController implements Initializable{
    @Setter

    @Autowired
    UserService userService;

    private Main stage;
    private int op = 0;

    @FXML
    private TextField tfUser;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Button btnIniciarSesion, btnRegistrar;

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnIniciarSesion) {
            Equipo equipo = buscarEquipo();
            if (equipo != null) {
                    if (op == 0) {
                        // Acceso correcto
                        btnRegistrar.setDisable(false);
                        btnIniciarSesion.setText("Ir Al Menu");
                        op = 1;
                    } else if (op == 1) {
                        stage.mostrarMenuView();
                    }
            }
        } else if (event.getSource() == btnRegistrar) {
            stage.mostrarUserView();
        }
    }

    public Equipo buscarEquipo() {
            User user = userService.buscarUserPorId(Long.parseLong(tfUser.getText()));
            if (user != null) {
                ObservableList<User> userBuscado = FXCollections.observableArrayList(user);  
            }
    }


    public void setStage(Main stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
