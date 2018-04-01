package com.github.Jindreak.adventura_kadj02.ui;

import java.util.Collection;

import com.github.Jindreak.adventura_kadj02.logika.Batoh;
import com.github.Jindreak.adventura_kadj02.logika.Prostor;

public class vychodyObserver implements IObserver {
	
	private HomeController c;
	
	vychodyObserver (HomeController controller){
		c = controller;
	}
	

	@Override
	public void update(Prostor p, Batoh b) {
		
		Collection<Prostor> vychody = p.getVychody();
		
		
		
		c.smazVychody();
		for(Prostor prostor : vychody){
			
			
			c.pridejVychod(prostor.getNazev());
		        
		    
	   }
		
	}

}
