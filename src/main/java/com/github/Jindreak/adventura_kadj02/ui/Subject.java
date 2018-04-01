package com.github.Jindreak.adventura_kadj02.ui;

import java.util.ArrayList;

import com.github.Jindreak.adventura_kadj02.logika.Batoh;
import com.github.Jindreak.adventura_kadj02.logika.IHra;
import com.github.Jindreak.adventura_kadj02.logika.Prostor;

public class Subject implements ISubject  {
	
	private ArrayList<IObserver> observers;
	private Prostor prostor;
	private Batoh batoh;
	IHra odkazNaHru;
	
	
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
