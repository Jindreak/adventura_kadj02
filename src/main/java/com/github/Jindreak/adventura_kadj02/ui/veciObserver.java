package com.github.Jindreak.adventura_kadj02.ui;

import com.github.Jindreak.adventura_kadj02.logika.Batoh;
import com.github.Jindreak.adventura_kadj02.logika.Prostor;
import com.github.Jindreak.adventura_kadj02.logika.Vec;


import java.util.Map;

/**
 * Trida realizujici posluchace pro veci. Posluchac
 * zada controllera o prekresleni.
 * @author Jindra
 *
 */
public class veciObserver implements IObserver {
	
private HomeController c;
	/**
	 * Konstruktor, kde se predava controller
	 * @param controller
	 */
	veciObserver (HomeController controller){
		c = controller;
	}
	
	

	
	
	@Override
	public void update(Prostor p, Batoh b) {
		Map <String, Vec> veci = p.getVeci();
		
		c.smazVeci();
		
		for (String nazev : veci.keySet()) {
            c.pridejVec(nazev);
        }
		
	}

}
