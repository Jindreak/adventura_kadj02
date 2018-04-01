package com.github.Jindreak.adventura_kadj02.uiText;

import com.github.Jindreak.adventura_kadj02.logika.Hra;
import com.github.Jindreak.adventura_kadj02.logika.IHra;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Třída slouží ke spuštění adventury.
 * Při spuštění bez parametru konstruuje okno aplikace,
 * s parametrem -text se spouští v textovém režimu
 * 
 * @author Filip Vencovsky
 *
 */
public class Application {

	/**
	 * Spouštěcí metoda pro aplikaci
	 * @param args
	 */
	public static void main(String[] args) {
		 IHra hra = new Hra();
         TextoveRozhrani ui = new TextoveRozhrani(hra);
         ui.hraj();
	}
	/**
	 * Metoda, ve které se konstruuje okno, kontroler a hra,
	 * která se předává kontroleru
	 */
	

}
