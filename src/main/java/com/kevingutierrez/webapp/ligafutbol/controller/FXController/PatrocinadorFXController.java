package com.kevingutierrez.webapp.ligafutbol.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kevingutierrez.webapp.ligafutbol.model.Equipo;
import com.kevingutierrez.webapp.ligafutbol.service.EquipoService;
import com.kevingutierrez.webapp.ligafutbol.model.Patrocinador;
import com.kevingutierrez.webapp.ligafutbol.service.PatrocinadorService;
import com.kevingutierrez.webapp.ligafutbol.system.Main;
import com.kevingutierrez.webapp.ligafutbol.util.LigaFutbolAlert;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class PatrocinadorFXController implements Initializable {
    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfNombre, tfLogo, tfPatrocinadorId;
    @FXML
    TableView tblPatrocinador;
    @FXML
    TableColumn colId, colNombre, colLogo, colEquipo;
    @FXML
    Button btnGuardar, btnEliminar, btnLimpiar, btnBuscar, btnRegresar;
    @FXML
    ListView<Equipo> lvEquipos;

    private List<Equipo> equiposSeleccionados = new ArrayList<>();

    @Autowired
    PatrocinadorService patrocinadorService;
    @Autowired
    EquipoService equipoService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        lvEquipos.setItems(FXCollections.observableList(equipoService.listarEquipos()));
        lvEquipos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(tfId.getText().isBlank()){
                if(!tfNombre.getText().isBlank() && !tfLogo.getText().isBlank()){
                    agregarPatrocinador();
                    LigaFutbolAlert.getInstance().mostrarAlertaInformacion(400);
                }else{
                    LigaFutbolAlert.getInstance().mostrarAlertaInformacion(600);
                    if(tfNombre.getText().isBlank()){
                        tfNombre.requestFocus();
                    }else if(tfLogo.getText().isBlank()){
                        tfLogo.requestFocus();
                    }
                }
            }else{
                if(!tfNombre.getText().isBlank() && !tfLogo.getText().isBlank()){
                    if(LigaFutbolAlert.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                        editarPatrocinador();
                        LigaFutbolAlert.getInstance().mostrarAlertaInformacion(500);
                    }
                }else{
                    LigaFutbolAlert.getInstance().mostrarAlertaInformacion(600);
                    if(tfNombre.getText().isBlank()){
                        tfNombre.requestFocus();
                    }else if(tfLogo.getText().isBlank()){
                        tfLogo.requestFocus();
                    }
                }
            }
        }else if(event.getSource() == btnEliminar){
            if(LigaFutbolAlert.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarPatrocinador();
                LigaFutbolAlert.getInstance().mostrarAlertaInformacion(700);
            }
        }else if(event.getSource() == btnLimpiar){
            if(LigaFutbolAlert.getInstance().mostrarAlertaConfirmacion(605).get() == ButtonType.OK){
                limpiarTextField();
            }
        }else if(event.getSource() == btnBuscar){
            buscarPatrocinador();
        }else if(event.getSource() == btnRegresar){
            stage.mostrarMenuView();
        }
    }

    public void cargarDatos(){
        tblPatrocinador.getItems().clear();
        tblPatrocinador.setItems(listarPatrocinadores());
        colId.setCellValueFactory(new PropertyValueFactory<Patrocinador, Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Patrocinador, String>("nombre"));
        colLogo.setCellValueFactory(new PropertyValueFactory<Patrocinador, String>("logo"));
    }

    public void cargarTextField(){
        Patrocinador patrocinador = (Patrocinador)tblPatrocinador.getSelectionModel().getSelectedItem();
        if(Patrocinador != null){
            tfId.setText(Long.toString(patrocinador.getId()));
            tfNombre.setText(patrocinador.getNombre());
            tfLogo.setText(patrocinador.getLogo());
            lvEquipos.getSelectionModel().clearSelection();
            for (Equipo equipo : equipo.getEquipos()) {
                lvEquipos.getSelectionModel().select(equipo);
            }

        }
    }

    public void limpiarTextField(){
        tfId.clear();
        tfNombre.clear();
        tfLogo.clear();
        lvEquipos.getSelectionModel().clearSelection();
        cargarDatos();
    }

    public ObservableList<Patrocinador> listarPatrocinadores(){
        return FXCollections.observableArrayList(patrocinadorService.listarPatrocinadores());
    }

    public void agregarPatrocinador(){
        Patrocinador patrocinador = new Patrocinador();
        patrocinador.setNombre(tfNombre.getText());
        patrocinador.setLogo(tfLogo.getText());
        equiposSeleccionados = lvEquipos.getSelectionModel().getSelectedItems();
        patrocinador.setEquipos(new ArrayList<>(equiposSeleccionados));
        patrocinadorService.guardarPatrocinador(patrocinador);
        cargarDatos();
    }

    public void editarPatrocinador(){
        Patrocinador patrocinador = patrocinadorService.buscarPatrocinadorPorId(Long.parseLong(tfId.getText()));
        patrocinador.setNombre(tfNombre.getText());
        patrocinador.setLogo(tfLogo.getText());
        patrocinador.setEquipos(equiposSeleccionados);
        patrocinadorService.guardarPatrocinador(patrocinador);
        cargarDatos();
    }

    public void eliminarPatrocinador(){
        Patrocinador patrocinador = patrocinadorService.buscarPatrocinadorPorId(Long.parseLong(tfId.getText()));
        patrocinadorService.eliminarPatrocinador(patrocinador);
        cargarDatos();
    }

    public void buscarPatrocinador(){
        tblPatrocinador.getItems().clear();
        if(tfPatrocinadorId.getText().isBlank()){
            cargarDatos();
        }else{
            Patrocinador patrocinador = patrocinadorService.buscarPatrocinadorPorId(Long.parseLong(tfPatrocinadorId.getText()));

            if(patrocinador != null){
                ObservableList<Patrocinador> patrocinadorBuscado = FXCollections.observableArrayList(patrocinador);
                tblPatrocinador.getItems().clear();
                tblPatrocinador.setItems(patrocinadorBuscado);
            }else{
                tblPatrocinador.getItems().clear();
            }
        }
    }

    public void createSelectEquipos() {
        equiposSeleccionados.clear();
        equiposSeleccionados.addAll(lvEquipos.getSelectionModel().getSelectedItems());
        System.out.println(equiposSeleccionados);
    }
}
