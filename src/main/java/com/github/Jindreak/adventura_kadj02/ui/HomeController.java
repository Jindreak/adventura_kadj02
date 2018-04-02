package com.github.Jindreak.adventura_kadj02.ui;



import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Collection;

import com.github.Jindreak.adventura_kadj02.logika.Hra;
import com.github.Jindreak.adventura_kadj02.logika.IHra;
import com.github.Jindreak.adventura_kadj02.logika.Prostor;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
	@FXML private ImageView emp;
	@FXML private ImageView temna;
	
	
	private IHra hra;
	private Subject subject;
	private IObserver vychodyObs;
	private IObserver veciObs;
	private IObserver postavyObs;
	private IObserver batohObs;
	private IObserver mapaObs;
	
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
 
	 * @param hra spuštěné hry
	 */
	public void inicializuj(IHra hra) {
		vystup.setText(hra.vratUvitani());
		vystup.setEditable(false);
		vstupniText.setDisable(false);
		this.hra = hra;
		
		subject = new Subject(hra);
		
		vychodyObs = new vychodyObserver(this);
		veciObs = new veciObserver(this);
		postavyObs = new postavyObserver(this);
		batohObs = new batohObserver(this);
		mapaObs = new mapaObserver(this);
		
		subject.register(vychodyObs);
		subject.register(veciObs);
		subject.register(postavyObs);
		subject.register(batohObs);
		subject.register(mapaObs);
		
		
		subject.notifyObserver();
		
		
		
		
		pozice.setTranslateX(95);
		pozice.setTranslateY(10);
		
	}
	
	
//=================KRESLENI=====
	
	/**
	 * Metoda, ktera maze vychody
	 */
	public void smazVychody () {
		vychody.getItems().clear();
	}
	
	/**
	 * Pridava vychod do listView
	 * @param nazevVychodu stringovy nazev vychodu
	 */
	public void pridejVychod(String nazevVychodu) {
		vychody.getItems().add(nazevVychodu);
	}
	/**
	 * Metoda, ktera maze veci
	 */
	public void smazVeci () {
		veci.getItems().clear();
	}
	
	/**
	 * Pridava vec do listView
	 * @param nazevVeci stringovy nazev veci
	 */
	public void pridejVec(String nazevVeci) {
		veci.getItems().add(nazevVeci);
	}
	/**
	 * Metoda, ktera maze postavy
	 */
	public void smazPostavy () {
		postavy.getItems().clear();
	}
	
	/**
	 * Pridava postavu do listView
	 * @param nazevPostavy stringovy nazev postavy
	 */
	public void pridejPostavu (String nazevPostavy) {
		postavy.getItems().add(nazevPostavy);
	}
	/**
	 * Metoda, ktera maze batoh
	 */
	public void smazBatoh () {
		
		emp.setVisible(false);
		temna.setVisible(false);
		
	}
	
	/**
	 * Pridava vec do batohu (obrazek se zobrazi)
	 * @param nazevItemu stringovy nazev veci (emp, baterie)
	 */
	public void pridejBatoh (String nazevItemu) {
		
		switch (nazevItemu) {
		
		case "emp_granát": emp.setVisible(true);
		break;
		
		case "baterie_s_temnou_energií": temna.setVisible(true);
		break;
		}
		
	}
	
	/**
	 * Metoda, ktera prekresluje obrazek pozice
	 * v zavislosti na mistnosti
	 * @param nazevPozice stringovy nazev pozice
	 */
	public void zmenPozici (String nazevPozice) {
		
		switch (nazevPozice) {
			case "stázové_komory" : 	pozice.setTranslateX(95);
										pozice.setTranslateY(10);
										break;
									
			case "transportér" 	:		pozice.setTranslateX(20);
										pozice.setTranslateY(40);
										break;
									
			case "strojovna"	:		pozice.setTranslateX(170);
										pozice.setTranslateY(40);
										break;
									
			case "chodba_s_obranou":	pozice.setTranslateX(95);
										pozice.setTranslateY(-90);
										break;
									
			case "spojovací_chodba":	pozice.setTranslateX(95);
										pozice.setTranslateY(-135);
										break;
									
			case "přetlaková_komora":	pozice.setTranslateX(20);
										pozice.setTranslateY(-135);
										break;
										
			case "sklad" :				pozice.setTranslateX(170);
										pozice.setTranslateY(-135);
										break;
										
			case "můstek" :				pozice.setTranslateX(95);
										pozice.setTranslateY(-175);
										break;
										
			case "chodba" :				pozice.setTranslateX(95);
										pozice.setTranslateY(-135);
										break;
										
		}
	}
	
	/**
	 * Pro tlacitko Nova hra -
	 * vytvori novou instanci hry
	 */
	public void novaHra () {
		IHra hra = new Hra();
		inicializuj(hra);
	}
	
	/**
	 * Trida slouzici k vyvolani okna s napovedou
	 * branou z html souboru.
	 * 
	 */
	public void napovedaHtml () {
		
        oknoNapovedy oknoHelpu = new oknoNapovedy("napoveda.html");
        oknoHelpu.setLocation(250,250);
        oknoHelpu.setSize(600,300);
        oknoHelpu.setVisible(true);
		
		
		
	}
	
	/**
	 * Ukonci cely program -
	 * pro tlacitko Konec
	 */
	public void konec() {
		
        System.exit(0);
	}

}
