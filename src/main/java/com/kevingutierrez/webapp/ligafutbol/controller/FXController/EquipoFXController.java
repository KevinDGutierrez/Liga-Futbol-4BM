package com.kevingutierrez.webapp.ligafutbol.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kevingutierrez.webapp.ligafutbol.model.Equipo;
import com.kevingutierrez.webapp.ligafutbol.service.EquipoService;
import com.kevingutierrez.webapp.ligafutbol.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class EquipoFXController implements Initializable {
    @Setter
    private Main stage;

    @FXML
    private TextField tfId, tfNombre, tfEstadio, tfCiudad, tfAforo, tfEquipoId;
    @FXML
    private TableView<Equipo> tblEquipo;
    @FXML
    private TableColumn<Equipo, Long> colId;
    @FXML
    private TableColumn<Equipo, String> colNombre;
    @FXML
    private TableColumn<Equipo, String> colEstadio;
    @FXML
    private TableColumn<Equipo, String> colCiudad;
    @FXML
    private TableColumn<Equipo, Long> colAforo;
    @FXML
    private Button btnGuardar, btnEliminar, btnLimpiar, btnBuscar, btnRegresar;

    @Autowired
    private EquipoService equipoService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarEquipos();
    }

    public void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnGuardar) {
            if(tfId.getText().isBlank()) { 
                agregarEquipo(); 
            } else {
                editarEquipo();
            }
        } else if(event.getSource() == btnEliminar) {
            eliminarEquipo();
        } else if(event.getSource() == btnBuscar) {
            buscarEquipo();
        } else if(event.getSource() == btnLimpiar) {
            limpiarTextField();
        } else if(event.getSource() == btnRegresar) {
            stage.mostrarMenuView();
        }
    }

    public void cargarEquipos() {
        tblEquipo.getItems().clear();
        tblEquipo.setItems(listarEquipos());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstadio.setCellValueFactory(new PropertyValueFactory<>("estadio"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colAforo.setCellValueFactory(new PropertyValueFactory<>("aforo"));
    }

    public void cargarTextField() {
        Equipo equipo = tblEquipo.getSelectionModel().getSelectedItem();
        if (equipo != null) {
            tfId.setText(Long.toString(equipo.getId()));
            tfNombre.setText(equipo.getNombre());
            tfEstadio.setText(equipo.getEstadio());
            tfCiudad.setText(equipo.getCiudad());
            tfAforo.setText(Long.toString(equipo.getAforo()));
        }
    }

    public ObservableList<Equipo> listarEquipos() {
        return FXCollections.observableArrayList(equipoService.listarEquipos());
    }

    public void limpiarTextField() {
        tfId.clear();
        tfNombre.clear();
        tfEstadio.clear();
        tfCiudad.clear();
        tfAforo.clear();
        tfEquipoId.clear();
    }

    public void agregarEquipo() {
        Equipo equipo = new Equipo();
        equipo.setNombre(tfNombre.getText());
        equipo.setEstadio(tfEstadio.getText());
        equipo.setCiudad(tfCiudad.getText());
        equipo.setAforo(Long.parseLong(tfAforo.getText()));
        equipoService.guardarEquipo(equipo);
        cargarEquipos();
        limpiarTextField();
    }

    public void editarEquipo() {
        Equipo equipo = equipoService.buscarEquipoPorId(Long.parseLong(tfId.getText()));
        equipo.setNombre(tfNombre.getText());
        equipo.setEstadio(tfEstadio.getText());
        equipo.setCiudad(tfCiudad.getText());
        equipo.setAforo(Long.parseLong(tfAforo.getText()));
        equipoService.guardarEquipo(equipo);
        cargarEquipos();
        limpiarTextField();
    }

    public void eliminarEquipo() {
        Equipo equipo = equipoService.buscarEquipoPorId(Long.parseLong(tfId.getText()));
        equipoService.eliminarEquipo(equipo);
        cargarEquipos();
        limpiarTextField();
    }

    public void buscarEquipo() {
        tblEquipo.getItems().clear();
        if (tfEquipoId.getText().isBlank()) {
            cargarEquipos();
        } else {
            Equipo equipo = equipoService.buscarEquipoPorId(Long.parseLong(tfEquipoId.getText()));
            if (equipo != null) {
                ObservableList<Equipo> equipoBuscado = FXCollections.observableArrayList(equipo);  
                tblEquipo.setItems(equipoBuscado);
            } else {
                tblEquipo.getItems().clear();
            }
        }
    }
}
