package com.github.Jindreak.adventura_kadj02.ui;

import java.util.ArrayList;

import com.github.Jindreak.adventura_kadj02.logika.Batoh;
import com.github.Jindreak.adventura_kadj02.logika.IHra;
import com.github.Jindreak.adventura_kadj02.logika.Prostor;


/**
 * Hlavni publisher zmen v ramci navrhoveho
 * vzoru Observer. Registruje vsechny posluchace
 * a notifikuje je o zmenach
 * @author Jindra
 *
 */
public class Subject implements ISubject  {
	
	private ArrayList<IObserver> observers;
	private Prostor prostor;
	private Batoh batoh;
	IHra odkazNaHru;
	
	/**
	 * Konstruktor, ktery z predane hry
	 * vytahne aktualni prostor a batoh
	 * @param hra
	 */
	public Subject(IHra hra) {
		observers = new ArrayList<IObserver>();
		odkazNaHru = hra;
		prostor = hra.getHerniPlan().getAktualniProstor();
		batoh = hra.getHerniPlan().getBatoh();
	}
	

	@Override
	public void register(IObserver newObserver) {

		observers.add(newObserver);

		
	}

	@Override
	public void unregister(IObserver deleteObserver) {
		 int observerIndex = observers.indexOf(deleteObserver);
		 observers.remove(observerIndex);


		
	}

	@Override
	public void notifyObserver() {
		
		prostor = odkazNaHru.getHerniPlan().getAktualniProstor();
		batoh = odkazNaHru.getHerniPlan().getBatoh();
		
		for(IObserver observer : observers){
			
		    observer.update(prostor, batoh);
			
	   }

		
	}

}
