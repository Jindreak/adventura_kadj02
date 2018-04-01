package com.github.Jindreak.adventura_kadj02.ui;

import com.github.Jindreak.adventura_kadj02.logika.Batoh;
import com.github.Jindreak.adventura_kadj02.logika.Prostor;

public interface IObserver {
	
	/**
	 * Update informaci pro observery
	 * @param p je prostor, ktery je aktualni
	 * @param b reference na batoh
	 */
	public void update(Prostor p, Batoh b);
	
	
}
