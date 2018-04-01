package com.github.Jindreak.adventura_kadj02.ui;

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
public class Application extends javafx.application.Application {

	/**
	 * Spouštěcí metoda pro aplikaci
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else {
                System.out.println("Neplatný parametr");
            }
        }
	}
	/**
	 * Metoda, ve které se konstruuje okno, kontroler a hra,
	 * která se předává kontroleru
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
//		Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass()
		          .getResource("UI.fxml"));
		Parent root = loader.load();

		HomeController c = loader.getController();
		
        primaryStage.setTitle("Vesmírná loď");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
        
        
        //===========Jindrovo kod==========================================
        
        IHra hra = new Hra();
        
        c.inicializuj(hra);
		
        
        
        
	}

}
