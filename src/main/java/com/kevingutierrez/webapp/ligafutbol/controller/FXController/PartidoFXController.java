package com.kevingutierrez.webapp.ligafutbol.controller;

import com.kevingutierrez.webapp.ligafutbol.model.Equipo;
import com.kevingutierrez.webapp.ligafutbol.model.Partido;
import com.kevingutierrez.webapp.ligafutbol.service.EquipoService;
import com.kevingutierrez.webapp.ligafutbol.service.PartidoService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PartidoFXController implements Initializable {

    @FXML
    TextField tfPartidoId, tfGolLocal, tfGolVisitante, tfFecha, tfBuscar;

    @FXML
    Button btnGuardar, btnLimpiar, btnRegresar, btnEliminar, btnBuscar;

    @FXML
    ListView<Equipo> lvEquipo;

    @FXML
    TableView<Partido> tblPartidos;

    @FXML
    TableColumn<Partido, Long> colId;
    @FXML
    TableColumn<Partido, Integer> colGolLocal, colGolVisitante;
    @FXML
    TableColumn<Partido, Date> colFecha;
    @FXML
    TableColumn<Partido, String> colEquipo;

    private List<Equipo> equiposSeleccionados = new ArrayList<>();

    @Setter
    private Main stage;

    @Autowired
    PartidoService partidoService;

    @Autowired
    EquipoService equipoService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lvEquipo.setItems(FXCollections.observableList(equipoService.listarEquipos()));
        lvEquipo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        cargarDatos();
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (tfPartidoId.getText().isBlank()) {
                agregarPartido();
            } else {
                editarPartido();
            }
        } else if (event.getSource() == btnLimpiar) {
            limpiarForm();
        } else if (event.getSource() == btnRegresar) {
            stage.inicioView();
        } else if (event.getSource() == btnEliminar) {
            eliminarPartido();
        } else if (event.getSource() == btnBuscar) {
            tblPartidos.getItems().clear();
            if (tfBuscar.getText().isBlank()) {
                cargarDatos();
            } else {
                tblPartidos.getItems().add(buscarPartido());
            }
        }
    }

    public void cargarDatos() {
        tblPartidos.setItems(listarPartidos());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colGolLocal.setCellValueFactory(new PropertyValueFactory<>("golLocales"));
        colGolVisitante.setCellValueFactory(new PropertyValueFactory<>("golVisitante"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colEquipo.setCellValueFactory(new PropertyValueFactory<>("equipos"));
    }

    public ObservableList<Partido> listarPartidos() {
        return FXCollections.observableList(partidoService.listarPartidos());
    }

    public void cargarForm() {
        Partido partido = tblPartidos.getSelectionModel().getSelectedItem();
        if (partido != null) {
            tfPartidoId.setText(partido.getId().toString());
            tfGolLocal.setText(partido.getGolLocales());
            tfGolVisitante.setText(partido.getGolVisitante());
            tfFecha.setText(partido.getFecha().toString());

            lvEquipo.getSelectionModel().clearSelection();
            for (Equipo equipo : partido.getEquipos()) {
                lvEquipo.getSelectionModel().select(equipo);
            }
        }
    }

    public void limpiarForm() {
        tfPartidoId.clear();
        tfGolLocal.clear();
        tfGolVisitante.clear();
        tfFecha.clear();
        lvEquipo.getSelectionModel().clearSelection();
    }

    public void agregarPartido() {
        try {
            Partido partido = new Partido();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fecha = formatter.parse(tfFecha.getText());
            partido.setFecha(new java.sql.Date(fecha.getTime()));
            partido.setGolLocales(tfGolLocal.getText());
            partido.setGolVisitante(tfGolVisitante.getText());

            equiposSeleccionados = lvEquipo.getSelectionModel().getSelectedItems();
            partido.setEquipos(new ArrayList<>(equiposSeleccionados));

            if (validarPartido(partido)) {
                partidoService.guardarPartido(partido);
                cargarDatos();
            } else {
                mostrarErrorValidacion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editarPartido() {
        try {
            Partido partido = partidoService.buscarPartidoPorId(Long.parseLong(tfPartidoId.getText()));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fecha = formatter.parse(tfFecha.getText());
            partido.setFecha(new java.sql.Date(fecha.getTime()));
            partido.setGolLocales(tfGolLocal.getText());
            partido.setGolVisitante(tfGolVisitante.getText());

            equiposSeleccionados = lvEquipo.getSelectionModel().getSelectedItems();
            partido.setEquipos(new ArrayList<>(equiposSeleccionados));

            if (validarPartido(partido)) {
                partidoService.guardarPartido(partido);
                cargarDatos();
            } else {
                mostrarErrorValidacion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarPartido() {
        Partido partido = partidoService.buscarPartidoPorId(Long.parseLong(tfPartidoId.getText()));
        partidoService.eliminarPartido(partido);
        cargarDatos();
    }

    public Partido buscarPartido() {
        return partidoService.buscarPartidoPorId(Long.parseLong(tfBuscar.getText()));
    }

    public boolean validarPartido(Partido partido) {
        return partido.getEquipos() != null && partido.getEquipos().size() == 2 &&
               !partido.getEquipos().get(0).equals(partido.getEquipos().get(1));
    }

    public void mostrarErrorValidacion() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Validación");
        alert.setHeaderText("Error al guardar el partido");
        alert.setContentText("Debe seleccionar 2 equipos diferentes para el partido.");
        alert.showAndWait();
    }
}
