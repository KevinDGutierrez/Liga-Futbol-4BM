package com.kevingutierrez.webapp.ligafutbol.controller.FXController;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.kevingutierrez.webapp.ligafutbol.model.Equipo;
import com.kevingutierrez.webapp.ligafutbol.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RegistroController implements Initializable {
    private Main stage;

    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    private static Connection connection = null;

    @FXML
    private TextField tfUser, tfPassword;

    @FXML
    private ComboBox<Equipo> cmbEquipoId;

    @FXML
    private Button btnRegistrar, btnAgregarEquipo;

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegistrar) {
            agregarEquipos();
            stage.mostrarLoginView();
        } else if (event.getSource() == btnAgregarEquipo) {
            stage.mostrarEquipoView();
        }
    }

    public ObservableList<Equipo> listarEquipo() {
        ArrayList<Equipo> equipos = new ArrayList<>();
        String query = "SELECT * FROM equipos"; 

        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return FXCollections.observableList(equipos);
    }

    public void agregarEquipos() {
        String query = "INSERT INTO usuarios (username, password, equipoId) VALUES (?, ?, ?)";

        try {
            statement = connection.prepareStatement(query);
            String encryptedPassword = encryptPassword(tfPassword.getText());
            statement.setString(1, tfUser.getText());
            statement.setString(2, encryptedPassword);

            Equipo selectedEquipo = cmbEquipoId.getSelectionModel().getSelectedItem();
            if (selectedEquipo != null) {
                statement.setLong(3, selectedEquipo.getId());
            }

            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String encryptPassword(String password) {
        return password;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbEquipoId.setItems(listarEquipo());
    }
}
