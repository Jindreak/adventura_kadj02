package com.github.Jindreak.adventura_kadj02.ui;

import java.util.Map;

import com.github.Jindreak.adventura_kadj02.logika.Batoh;
import com.github.Jindreak.adventura_kadj02.logika.Postava;
import com.github.Jindreak.adventura_kadj02.logika.Prostor;
import com.github.Jindreak.adventura_kadj02.logika.Vec;

public class batohObserver implements IObserver {
	
	
	private HomeController c;
	
	batohObserver (HomeController controller) {
		c = controller;
	}
	

	@Override
	public void update(Prostor p, Batoh b) {
		
		Map<String, Vec> batoh = b.getBatoh();
		
		c.smazBatoh();
		
		for (String nazev : batoh.keySet()) {
            c.pridejBatoh(nazev);
            
        }
		
	}

}
