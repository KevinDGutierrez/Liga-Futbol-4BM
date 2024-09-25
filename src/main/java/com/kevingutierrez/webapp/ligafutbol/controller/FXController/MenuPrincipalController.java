package com.kevingutierrez.webapp.ligafutbol.controller.FXController;

import com.kevingutierrez.webapp.ligafutbol.system.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MenuPrincipalController {
    private Main stage;
    
    @FXML
    MenuItem btnEquipamientoController, btnEquipoController, btnGolController, btnJugadorController, 
    btnPartidoController, btnPatrocinadorController;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnEquipamientoController){
            stage.mostrarEquipamientoView();
        }else if(event.getSource() == btnEquipoController){
            stage.mostrarEquipoView();
        }else if(event.getSource() == btnGolController){
            stage.mostrarGolView();
        }else if(event.getSource() == btnJugadorController){
            stage.mostrarJugadorView();
        }else if(event.getSource() == btnPartidoController){
            stage.mostrarPartidoView();
        }else if(event.getSource() == btnPatrocinadorController){
            stage.mostrarPatrocinadorView();
        }
    }
    
    public void setStage(Main stage) {
        this.stage = stage;
    }
}