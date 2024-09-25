package com.kevingutierrez.webapp.ligafutbol.controller.FXController;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.kevingutierrez.webapp.ligafutbol.model.Equipo;
import com.kevingutierrez.webapp.ligafutbol.system.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class LoginController implements Initializable{
    private Main stage;
    private int op = 0;

    private static Connection conexion = null;
    private static PreparedStatement statement = null;

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
                    }else {
                    mostrarAlerta("Error", "Credenciales incorrectas", AlertType.ERROR);
                }
            } else {
                mostrarAlerta("Error", "El equipo no fue encontrado", AlertType.ERROR);
            }
        } else if (event.getSource() == btnRegistrar) {
            stage.mostrarRegistroView();
        }
    }

    public Equipo buscarEquipo() {
        Equipo equipo = null;

        try {
            String sql = "SELECT * FROM equipos WHERE nombre = ? AND password = ?";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfUser.getText());
            statement.setString(2, tfPassword.getText());

            
        } catch (SQLException e) {
            System.out.println("Error al buscar equipo: ");
        }
        return equipo;
    }


    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    public void setStage(Main stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicializar si es necesario
    }

}
