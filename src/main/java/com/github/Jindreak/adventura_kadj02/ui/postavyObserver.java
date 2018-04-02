package com.github.Jindreak.adventura_kadj02.ui;

import java.util.Map;

import com.github.Jindreak.adventura_kadj02.logika.Batoh;
import com.github.Jindreak.adventura_kadj02.logika.Postava;
import com.github.Jindreak.adventura_kadj02.logika.Prostor;

/**
 * Trida realizujici posluchace pro postavy. Posluchac
 * zada controllera o prekresleni.
 * @author Jindra
 *
 */
public class postavyObserver implements IObserver {

	
	private HomeController c;
	/**
	 * Kontruktor, kde se predava controller
	 * @param controller
	 */
	postavyObserver (HomeController controller){
		c = controller;
	}
	
	
	
	@Override
	public void update(Prostor p, Batoh b) {
		Map<String, Postava> postavy = p.getPostavy();
		
		
		
		c.smazPostavy();
		
		for (String nazev : postavy.keySet()) {
            c.pridejPostavu(nazev);
        }
		
	}

}
