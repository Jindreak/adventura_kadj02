package com.github.Jindreak.adventura_kadj02.ui;

import com.github.Jindreak.adventura_kadj02.logika.Batoh;
import com.github.Jindreak.adventura_kadj02.logika.Prostor;

public class mapaObserver implements IObserver {
	
	private HomeController c;
	
	mapaObserver (HomeController controller) {
		c = controller;
	}
	
	

	@Override
	public void update(Prostor p, Batoh b) {
		c.zmenPozici(p.getNazev());
		
	}

}
