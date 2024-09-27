package com.kevingutierrez.webapp.ligafutbol.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kevingutierrez.webapp.ligafutbol.model.Jugador;
import com.kevingutierrez.webapp.ligafutbol.service.JugadorService;
import com.kevingutierrez.webapp.ligafutbol.model.Gol;
import com.kevingutierrez.webapp.ligafutbol.service.GolService;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class GolFXController implements Initializable {
    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfMinuto, tfGolId;
    @FXML
    TextArea taDescripcion;
    @FXML
    TableView tblGol;
    @FXML
    TableColumn colId, colMinuto, colDescripcion, colJugador;
    @FXML
    Button btnGuardar, btnEliminar, btnLimpiar, btnBuscar, btnRegresar;
    @FXML
    ComboBox cmbJugador;

    @Autowired
    GolService golService;
    @Autowired
    JugadorService jugadorService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(tfId.getText().isBlank()){
                if(!tfMinuto.getText().isBlank() && !taDescripcion.getText().isBlank() && cmbJugador.getSelectionModel().getSelectedItem() != null){
                    agregarGol();
                    LigaFutbolAlert.getInstance().mostrarAlertaInformacion(400);
                }else{
                    LigaFutbolAlert.getInstance().mostrarAlertaInformacion(600);
                    if(tfMinuto.getText().isBlank()){
                        tfMinuto.requestFocus();
                    }else if(taDescripcion.getText().isBlank()){
                        taDescripcion.requestFocus();
                    }else if(cmbJugador.getSelectionModel().getSelectedItem() == null){
                        cmbJugador.requestFocus();
                    }
                }
            }else{
                if(!tfMinuto.getText().isBlank() && !taDescripcion.getText().isBlank() && cmbJugador.getSelectionModel().getSelectedItem() != null){
                    if(LigaFutbolAlert.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                        editarGol();
                        LigaFutbolAlert.getInstance().mostrarAlertaInformacion(500);
                    }
                }else{
                    LigaFutbolAlert.getInstance().mostrarAlertaInformacion(600);
                    if(tfMinuto.getText().isBlank()){
                        tfMinuto.requestFocus();
                    }else if(taDescripcion.getText().isBlank()){
                        taDescripcion.requestFocus();
                    }else if(cmbJugador.getSelectionModel().getSelectedItem() == null){
                        cmbJugador.requestFocus();
                    }
                }
            }
        }else if(event.getSource() == btnEliminar){
            if(LigaFutbolAlert.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarGol();
                LigaFutbolAlert.getInstance().mostrarAlertaInformacion(700);
            }
        }else if(event.getSource() == btnLimpiar){
            if(LigaFutbolAlert.getInstance().mostrarAlertaConfirmacion(605).get() == ButtonType.OK){
                limpiarTextField();
            }
        }else if(event.getSource() == btnBuscar){
            buscarGol();
        }else if(event.getSource() == btnRegresar){
            stage.mostrarMenuView();
        }
    }

    public void cargarDatos(){
        tblGol.getItems().clear();
        tblGol.setItems(listarGoles());
        colId.setCellValueFactory(new PropertyValueFactory<Gol, Long>("id"));
        colMinuto.setCellValueFactory(new PropertyValueFactory<Gol, String>("minutoAnotacion"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<Gol, String>("descripcion"));
        colJugador.setCellValueFactory(new PropertyValueFactory<Gol, Jugador>("jugador"));
    }

    public void cargarTextField(){
        Gol gol = (Gol)tblGol.getSelectionModel().getSelectedItem();
        if(gol != null){
            tfId.setText(Long.toString(gol.getId()));
            tfMinuto.setText(gol.getMinutoAnotacion());
            taDescripcion.setText(gol.getDescripcion());
            cmbJugador.getSelectionModel().select(0);
        }
    }

    public void cargarJugadores(){
        ObservableList<Jugador> jugadores = FXCollections.observableArrayList(jugadorService.listarJugadores());
        cmbJugador.setItems(jugadores);
    }

    public void limpiarTextField(){
        tfId.clear();
        tfMinuto.clear();
        taDescripcion.clear();
        cmbJugador.getSelectionModel().clearSelection();
        cargarDatos();
    }

    public ObservableList<Gol> listarGoles(){
        return FXCollections.observableArrayList(golService.listarGoles());
    }

    public void agregarGol(){
        Gol gol = new Gol();
        gol.setMinutoAnotacion(tfMinuto.getText());
        gol.setDescripcion(taDescripcion.getText());
        Jugador jugador = (Jugador)cmbJugador.getSelectionModel().getSelectedItem();
        golService.guardarGol(gol);
        cargarDatos();
    }

    public void editarGol(){
        Gol gol = golService.buscarGolPorId(Long.parseLong(tfId.getText()));
        gol.setMinutoAnotacion(tfMinuto.getText());
        gol.setDescripcion(taDescripcion.getText());
        Jugador jugador = (Jugador)cmbJugador.getSelectionModel().getSelectedItem();
        golService.guardarGol(gol);
        cargarDatos();
    }

    public void eliminarGol(){
        Gol gol = golService.buscarGolPorId(Long.parseLong(tfId.getText()));
        golService.eliminarGol(gol);
        cargarDatos();
    }

    public void buscarGol(){
        tblGol.getItems().clear();
        if(tfGolId.getText().isBlank()){
            cargarDatos();
        }else{
            Gol gol = golService.buscarGolPorId(Long.parseLong(tfGolId.getText()));

            if(gol != null){
                ObservableList<Gol> golBuscado = FXCollections.observableArrayList(gol);
                tblGol.getItems().clear();
                tblGol.setItems(golBuscado);
            }else{
                tblGol.getItems().clear();
            }
        }
    }
}
