package com.kevingutierrez.webapp.ligafutbol.util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class LigaFutbolAlert {
    private static LigaFutbolAlert instance;
    
    private LigaFutbolAlert(){
    
    }
    
    public static LigaFutbolAlert getInstance(){
        if(instance == null){
            instance = new LigaFutbolAlert();
        }
        return instance;
    }
    
    public void mostrarAlertaInformacion(int code){
        if(code == 400){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmación Registro");
            alert.setHeaderText("Confirmación de Regitro");
            alert.setContentText("¡Registro realizado con éxito!");
            alert.showAndWait();
        }else if(code == 500){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edición Registro");
            alert.setHeaderText("Edición de Regitro");
            alert.setContentText("¡Edición realizado con éxito!");
            alert.showAndWait();
        }else if(code == 700){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Eliminación Registro");
            alert.setHeaderText("Eliminación de Regitro");
            alert.setContentText("¡Eliminación realizado con éxito!");
            alert.showAndWait();
        }else if(code == 600){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Pendientes");
            alert.setHeaderText("Campos Pendientes");
            alert.setContentText("¡Algunos campos necesarios aun estan vacios!");
            alert.showAndWait();
        }else if(code == 800){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Usuario Incorrecto");
            alert.setHeaderText("El usuario ingresado es incorrecto");
            alert.setContentText("Verificar el usuario");
            alert.showAndWait();
        }else if(code == 900){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Contraseña Incorrecta");
            alert.setHeaderText("Contraseña incorrecta");
            alert.setContentText("Verificar la contraseña");
            alert.showAndWait();
        }
    }
    
    public Optional<ButtonType> mostrarAlertaConfirmacion(int code){
        Optional<ButtonType> action = null;
        
        if(code == 404){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminacion Registro");
            alert.setHeaderText("Eliminacion de Registro");
            alert.setContentText("¿Esta seguro de eliminar el registro?");
            action = alert.showAndWait();
        }else if(code == 505){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edición Registro");
            alert.setHeaderText("Edición de Registro");
            alert.setContentText("¿Esta seguro de editar el registro?");
            action = alert.showAndWait();
        }else if(code == 605){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vaciar Form");
            alert.setHeaderText("Vacio del Form");
            alert.setContentText("¿Esta seguro de vaciar el form?");
            action = alert.showAndWait();
        }
        
        return action;
    }
    
    public void alertaSaludo(String usuario){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bienvenido :D");
        alert.setHeaderText("Bienvenido " + usuario + " :D");
        alert.showAndWait();
    }
}
