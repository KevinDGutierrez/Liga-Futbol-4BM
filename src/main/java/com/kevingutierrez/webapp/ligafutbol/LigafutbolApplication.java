package com.kevingutierrez.webapp.ligafutbol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.kevingutierrez.webapp.ligafutbol.system.Main;
import javafx.application.Application;


@SpringBootApplication
public class LigafutbolApplication {

	public static void main(String[] args) {
		Application.launch(Main.class, args);
		SpringApplication.run(LigafutbolApplication.class, args);
	}

}

