package com.kevingutierrez.webapp.ligafutbol.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kevingutierrez.webapp.ligafutbol.controller.EquipoController;
import com.kevingutierrez.webapp.ligafutbol.model.Equipo;
import com.kevingutierrez.webapp.ligafutbol.model.User;
import com.kevingutierrez.webapp.ligafutbol.service.EquipoService;
import com.kevingutierrez.webapp.ligafutbol.service.UserService;
import com.kevingutierrez.webapp.ligafutbol.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lombok.Setter;

@Component
public class RegistroController implements Initializable{
    @Setter
    
    @Autowired
    EquipoController equipoController;

    @Autowired
    UserService userService;

    @Autowired
    EquipoService equipoService;

    private Main stage;

    Equipo equipo;
    
    @FXML
    private TextField tfUser, tfPassword; 

    @FXML
    private ComboBox<Equipo> cmbEquipoId; 

    @FXML
    private Button btnRegistrar, btnAgregarEquipo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbEquipoId.setItems(listarEquipo());
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegistrar) {
            agregarUsuarios();
            stage.mostrarLoginView();
        } else if (event.getSource() == btnAgregarEquipo) {
            stage.mostrarEquipoView();
        }
    }

    public ObservableList<Equipo> listarEquipo() {
        return FXCollections.observableArrayList(equipoService.listarEquipos());
    }
    
    public void agregarUsuarios() {
        User user = new User();

        user.setUser(tfUser.getText());
        user.setPassword(encryptPassword(tfPassword.getText()));
        equipo = cmbEquipoId.getSelectionModel().getSelectedItem();
        user.setEquipo(equipo);
        userService.guardarUser(user);
    }

    private String encryptPassword(String password) {
        return password;
    }
    
    public void setStage(Main stage) {
        this.stage = stage;
    }
}
