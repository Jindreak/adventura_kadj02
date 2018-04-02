package com.github.Jindreak.adventura_kadj02.ui;
/**
 * Interface subjectu, pro prehlednost
 * @author Jindra
 *
 */
public interface ISubject  {

	/**
	 * Registruje noveho observera
	 * @param o trida implementujici interface IObserver
	 */
	public void register(IObserver o);
	
	/**
	 * Maze observera
	 * @param o trida implementujici interface IObserver
	 */
	public void unregister(IObserver o);
	
	/**
	 * Dava observerum vedet zmeny
	 */
	public void notifyObserver();

}
