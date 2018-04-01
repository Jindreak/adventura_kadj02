package com.github.Jindreak.adventura_kadj02.ui;



import java.util.Collection;

import com.github.Jindreak.adventura_kadj02.logika.Hra;
import com.github.Jindreak.adventura_kadj02.logika.IHra;
import com.github.Jindreak.adventura_kadj02.logika.Prostor;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Filip Vencovsky
 *
 */
public class HomeController extends GridPane {
	
	@FXML private TextField vstupniText;
	@FXML private ListView vychody;
	@FXML private ListView veci;
	@FXML private TextArea vystup;
	@FXML private ListView postavy;
	@FXML private ImageView pozice;

	private IHra hra;
	private Subject subject;
	private IObserver vychodyObs;
	private IObserver veciObs;
	private IObserver postavyObs;
	
	/**
	 * metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho
	 */
	@FXML public void odesliPrikaz() {
		String vystupPrikazu = hra.zpracujPrikaz(vstupniText.getText());
		vystup.appendText("\n----------\n"+vstupniText.getText()+"\n----------\n");
		vystup.appendText(vystupPrikazu);
		vstupniText.setText("");
		
		subject.notifyObserver();
		
		if(hra.konecHry()) {
			vystup.appendText("\n----------\nKonec hry\n----------\n");
			vstupniText.setDisable(true);
		}  
	}
	
	/**
	 * Metoda bude soužit pro předání objektu se spuštěnou hrou
	 * kontroleru a zobrazí stav hry v grafice.
	 * @param objekt spuštěné hry
	 */
	public void inicializuj(IHra hra) {
		vystup.setText(hra.vratUvitani());
		vystup.setEditable(false);
		this.hra = hra;
		
		subject = new Subject(hra);
		
		vychodyObs = new vychodyObserver(this);
		veciObs = new veciObserver(this);
		postavyObs = new postavyObserver(this);
		
		subject.register(vychodyObs);
		subject.register(veciObs);
		subject.register(postavyObs);
		
		
		subject.notifyObserver();
		
		
		pozice.setFocusTraversable(true);
		pozice.setLayoutX (2000);
		pozice.setLayoutY(3000);
		pozice.setX (2000);
		pozice.setY(3000);
		
	}
	
	
//=================KRESLENI=====
	
	public void smazVychody () {
		vychody.getItems().clear();
	}
	
	public void pridejVychod(String nazevVychodu) {
		vychody.getItems().add(nazevVychodu);
	}
	
	public void smazVeci () {
		veci.getItems().clear();
	}
	
	public void pridejVec(String nazevVeci) {
		veci.getItems().add(nazevVeci);
	}
	
	public void smazPostavy () {
		postavy.getItems().clear();
	}
	
	public void pridejPostavu (String nazevPostavy) {
		postavy.getItems().add(nazevPostavy);
	}

}
