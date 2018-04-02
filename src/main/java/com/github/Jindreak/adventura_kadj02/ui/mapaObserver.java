package com.github.Jindreak.adventura_kadj02.ui;

import com.github.Jindreak.adventura_kadj02.logika.Batoh;
import com.github.Jindreak.adventura_kadj02.logika.Prostor;

/**
 * Trida realizujici posluchace pro mapu. Posluchac
 * zada controllera o prekresleni.
 * @author Jindra
 *
 */

public class mapaObserver implements IObserver {
	
	private HomeController c;
	
	/**
	 * Konstruktor, kde se predava controller
	 * @param controller
	 */
	mapaObserver (HomeController controller) {
		c = controller;
	}
	
	

	@Override
	public void update(Prostor p, Batoh b) {
		c.zmenPozici(p.getNazev());
		
	}

}
