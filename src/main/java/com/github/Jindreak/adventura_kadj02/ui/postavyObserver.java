package com.github.Jindreak.adventura_kadj02.ui;

import java.util.Map;

import com.github.Jindreak.adventura_kadj02.logika.Batoh;
import com.github.Jindreak.adventura_kadj02.logika.Postava;
import com.github.Jindreak.adventura_kadj02.logika.Prostor;


public class postavyObserver implements IObserver {

	
	private HomeController c;
	
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
