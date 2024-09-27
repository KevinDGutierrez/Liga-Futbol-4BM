package com.kevingutierrez.webapp.ligafutbol.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kevingutierrez.webapp.ligafutbol.model.Equipo;
import com.kevingutierrez.webapp.ligafutbol.model.Equipamiento;
import com.kevingutierrez.webapp.ligafutbol.model.Patrocinador;
import com.kevingutierrez.webapp.ligafutbol.service.EquipoService;
import com.kevingutierrez.webapp.ligafutbol.service.EquipamientoService;
import com.kevingutierrez.webapp.ligafutbol.service.PatrocinadorService;
import com.kevingutierrez.webapp.ligafutbol.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class EquipamientoFXController implements Initializable {

    @Setter
    private Main stage;

    @FXML
    private TextField tfEquipamientoId, tfColor, tfEscudo;

    @FXML
    private ComboBox<Equipo> cmbEquipo;
    @FXML
    private ComboBox<Patrocinador> cmbPatrocinador;

    @FXML
    private TableView<Equipamiento> tblEquipamiento;
    @FXML
    private TableColumn<Equipamiento, Long> colId;
    @FXML
    private TableColumn<Equipamiento, String> colColor;
    @FXML
    private TableColumn<Equipamiento, String> colEscudo;
    @FXML
    private TableColumn<Equipamiento, Patrocinador> colPatrocinador;
    @FXML
    private TableColumn<Equipamiento, Equipo> colEquipo;

    @FXML
    private Button btnGuardar, btnEliminar, btnLimpiar, btnBuscar, btnRegresar;

    @Autowired
    private EquipamientoService equipamientoService;
    @Autowired
    private EquipoService equipoService;
    @Autowired
    private PatrocinadorService patrocinadorService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        cargarEquipos();
        cargarPatrocinadores();
    }

    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (tfEquipamientoId.getText().isBlank()) {
                agregarEquipamiento();
            } else {
                editarEquipamiento();
            }
        } else if (event.getSource() == btnEliminar) {
            eliminarEquipamiento();
        } else if (event.getSource() == btnBuscar) {
            buscarEquipamiento();
        } else if (event.getSource() == btnLimpiar) {
            limpiarTextField();
        } else if (event.getSource() == btnRegresar) {
            stage.mostrarMenuView();
        }
    }

    public void cargarDatos() {
        tblEquipamiento.getItems().clear();
        tblEquipamiento.setItems(listarEquipamientos());

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colEscudo.setCellValueFactory(new PropertyValueFactory<>("escudo"));
        colPatrocinador.setCellValueFactory(new PropertyValueFactory<>("patrocinador"));
        colEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));
    }

    public void cargarTextField() {
        Equipamiento equipamiento = tblEquipamiento.getSelectionModel().getSelectedItem();
        if (equipamiento != null) {
            tfEquipamientoId.setText(equipamiento.getId().toString());
            tfColor.setText(equipamiento.getColor());
            tfEscudo.setText(equipamiento.getEscudo());
            cmbEquipo.setValue(equipamiento.getEquipo());
            cmbPatrocinador.setValue(equipamiento.getPatrocinador());
        }
    }

    public ObservableList<Equipamiento> listarEquipamientos() {
        return FXCollections.observableArrayList(equipamientoService.listarEquipamientos());
    }

    public void limpiarTextField() {
        tfEquipamientoId.clear();
        tfColor.clear();
        tfEscudo.clear();
        cmbEquipo.getSelectionModel().clearSelection();
        cmbPatrocinador.getSelectionModel().clearSelection();
    }

    public void agregarEquipamiento() {
        Equipamiento equipamiento = new Equipamiento();
        equipamiento.setColor(tfColor.getText());
        equipamiento.setEscudo(tfEscudo.getText());
        equipamiento.setEquipo(cmbEquipo.getSelectionModel().getSelectedItem());
        equipamiento.setPatrocinador(cmbPatrocinador.getSelectionModel().getSelectedItem());

        equipamientoService.guardarEquipamiento(equipamiento);
        cargarDatos();
        limpiarTextField();
    }

    public void editarEquipamiento() {
        Equipamiento equipamiento = equipamientoService.buscarEquipamientoPorId(Long.parseLong(tfEquipamientoId.getText()));
        equipamiento.setColor(tfColor.getText());
        equipamiento.setEscudo(tfEscudo.getText());
        equipamiento.setEquipo(cmbEquipo.getSelectionModel().getSelectedItem());
        equipamiento.setPatrocinador(cmbPatrocinador.getSelectionModel().getSelectedItem());

        equipamientoService.guardarEquipamiento(equipamiento);
        cargarDatos();
        limpiarTextField();
    }

    public void eliminarEquipamiento() {
        Equipamiento equipamiento = equipamientoService.buscarEquipamientoPorId(Long.parseLong(tfEquipamientoId.getText()));
        equipamientoService.eliminarEquipamiento(equipamiento);
        cargarDatos();
        limpiarTextField();
    }

    public void buscarEquipamiento() {
        tblEquipamiento.getItems().clear();
        if (tfEquipamientoId.getText().isBlank()) {
            cargarDatos();
        } else {
            Equipamiento equipamiento = equipamientoService.buscarEquipamientoPorId(Long.parseLong(tfEquipamientoId.getText()));
            if (equipamiento != null) {
                ObservableList<Equipamiento> equipamientoBuscado = FXCollections.observableArrayList(equipamiento);
                tblEquipamiento.setItems(equipamientoBuscado);
            } else {
                tblEquipamiento.getItems().clear();
            }
        }
    }

    public void cargarEquipos() {
        ObservableList<Equipo> equipos = FXCollections.observableArrayList(equipoService.listarEquipos());
        cmbEquipo.setItems(equipos);
    }

    public void cargarPatrocinadores() {
        ObservableList<Patrocinador> patrocinadores = FXCollections.observableArrayList(patrocinadorService.listarPatrocinadores());
        cmbPatrocinador.setItems(patrocinadores);
    }
}
