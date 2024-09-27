package com.kevingutierrez.webapp.ligafutbol.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kevingutierrez.webapp.ligafutbol.model.Equipo;
import com.kevingutierrez.webapp.ligafutbol.service.EquipoService;
import com.kevingutierrez.webapp.ligafutbol.model.Jugador;
import com.kevingutierrez.webapp.ligafutbol.service.JugadorService;
import com.kevingutierrez.webapp.ligafutbol.system.Main;
import com.kevingutierrez.webapp.ligafutbol.util.LigaFutbolAlert;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class JugadorFXController implements Initializable {
    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfNombre, tfApellido, tfDorsal, tfJugadorId;
    @FXML
    TableView tblJugador;
    @FXML
    TableColumn colId, colNombreJugador, colApellidoJugador, colDorsal, colEquipo;
    @FXML
    Button btnGuardar, btnEliminar, btnLimpiar, btnBuscar, btnRegresar;
    @FXML
    ComboBox cmbEquipo;

    @Autowired
    JugadorService jugadorService;
    @Autowired
    EquipoService equipoService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(tfId.getText().isBlank()){
                if(!tfNombre.getText().isBlank() && !tfApellido.getText().isBlank() && !tfDorsal.getText().isBlank() && cmbEquipo.getSelectionModel().getSelectedItem() != null){
                    agregarJugador();
                    LigaFutbolAlert.getInstance().mostrarAlertaInformacion(400);
                }else{
                    LigaFutbolAlert.getInstance().mostrarAlertaInformacion(600);
                    if(tfNombre.getText().isBlank()){
                        tfNombre.requestFocus();
                    }else if(tfApellido.getText().isBlank()){
                        tfApellido.requestFocus();
                    }else if(tfDorsal.getText().isBlank()){
                        tfDorsal.requestFocus();
                    }else if(cmbEquipo.getSelectionModel().getSelectedItem() == null){
                        cmbEquipo.requestFocus();
                    }
                }
            }else{
                if(!tfNombre.getText().isBlank() && !tfApellido.getText().isBlank() && !tfDorsal.getText().isBlank() && cmbEquipo.getSelectionModel().getSelectedItem() != null){
                    if(LigaFutbolAlert.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                        editarJugador();
                        LigaFutbolAlert.getInstance().mostrarAlertaInformacion(500);
                    }
                }else{
                    LigaFutbolAlert.getInstance().mostrarAlertaInformacion(600);
                    if(tfNombre.getText().isBlank()){
                        tfNombre.requestFocus();
                    }else if(tfApellido.getText().isBlank()){
                        tfApellido.requestFocus();
                    }else if(tfDorsal.getText().isBlank()){
                        tfDorsal.requestFocus();
                    }else if(cmbEquipo.getSelectionModel().getSelectedItem() == null){
                        cmbEquipo.requestFocus();
                    }
                }
            }
        }else if(event.getSource() == btnEliminar){
            if(LigaFutbolAlert.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarJugador();
                LigaFutbolAlert.getInstance().mostrarAlertaInformacion(700);
            }
        }else if(event.getSource() == btnLimpiar){
            if(LigaFutbolAlert.getInstance().mostrarAlertaConfirmacion(605).get() == ButtonType.OK){
                limpiarTextField();
            }
        }else if(event.getSource() == btnBuscar){
            buscarJugador();
        }else if(event.getSource() == btnRegresar){
            stage.mostrarMenuView();
        }
    }

    public void cargarDatos(){
        tblJugador.getItems().clear();
        tblJugador.setItems(listarJugadores());
        colId.setCellValueFactory(new PropertyValueFactory<Jugador, Long>("id"));
        colNombreJugador.setCellValueFactory(new PropertyValueFactory<Jugador, String>("nombre"));
        colApellidoJugador.setCellValueFactory(new PropertyValueFactory<Jugador, String>("apellido"));
        colDorsal.setCellValueFactory(new PropertyValueFactory<Jugador, String>("dorsal"));
        colEquipo.setCellValueFactory(new PropertyValueFactory<Jugador, Equipo>("equipo"));
    }

    public void cargarTextField(){
        Jugador jugador = (Jugador)tblJugador.getSelectionModel().getSelectedItem();
        if(jugador != null){
            tfId.setText(Long.toString(jugador.getId()));
            tfNombre.setText(jugador.getNombre());
            tfApellido.setText(jugador.getApellido());
            tfDorsal.setText(jugador.getDorsal());
            cmbEquipo.getSelectionModel().select(0);
        }
    }

    public void cargarEquipos(){
        ObservableList<Equipo> equipos = FXCollections.observableArrayList(equipoService.listarEquipos());
        cmbEquipo.setItems(equipos);
    }

    public void limpiarTextField(){
        tfId.clear();
        tfNombre.clear();
        tfApellido.clear();
        tfDorsal.clear();
        cmbEquipo.getSelectionModel().clearSelection();
        cargarDatos();
    }

    public ObservableList<Jugador> listarJugadores(){
        return FXCollections.observableArrayList(jugadorService.listarJugadores());
    }

    public void agregarJugador(){
        Jugador jugador = new Jugador();
        jugador.setNombre(tfNombre.getText());
        jugador.setApellido(tfApellido.getText());
        jugador.setDorsal(tfDorsal.getText());
        Equipo equipo = (Equipo)cmbEquipo.getSelectionModel().getSelectedItem();
        jugadorService.guardarJugador(jugador);
        cargarDatos();
    }

    public void editarJugador(){
        Jugador jugador = jugadorService.buscarJugadorPorId(Long.parseLong(tfId.getText()));
        jugador.setNombre(tfNombre.getText());
        jugador.setApellido(tfApellido.getText());
        jugador.setDorsal(tfDorsal.getText());
        Equipo equipo = (Equipo)cmbEquipo.getSelectionModel().getSelectedItem();
        jugadorService.guardarJugador(jugador);
        cargarDatos();
    }

    public void eliminarJugador(){
        Jugador jugador = jugadorService.buscarJugadorPorId(Long.parseLong(tfId.getText()));
        jugadorService.eliminarJugador(jugador);
        cargarDatos();
    }

    public void buscarJugador(){
        tblJugador.getItems().clear();
        if(tfJugadorId.getText().isBlank()){
            cargarDatos();
        }else{
            Jugador jugador = jugadorService.buscarJugadorPorId(Long.parseLong(tfJugadorId.getText()));

            if(jugador != null){
                ObservableList<Jugador> jugadorBuscado = FXCollections.observableArrayList(jugador);
                tblJugador.getItems().clear();
                tblJugador.setItems(jugadorBuscado);
            }else{
                tblJugador.getItems().clear();
            }
        }
    }
}
