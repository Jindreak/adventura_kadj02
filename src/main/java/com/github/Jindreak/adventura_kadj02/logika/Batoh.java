package com.github.Jindreak.adventura_kadj02.logika;

import java.util.*;

/*******************************************************************************
 * Třída Batoh implementuje úložný prostor pro věci.
 *
 * @author    Jindra Kadoun
 * @version   ZS 2016/2017
 */
public class Batoh {
    private Map<String, Vec> seznam;
    private static final int KAPACITA = 4;

    /**
     * Konstruktor třídy Batoh
     */
    public Batoh() {
        seznam = new HashMap<>();
    }

    /**
     * Přidávání věcí do batohu - zkoumá se, zdali je přenositelná a zdali je dostatečná kapacita
     *
     * @param item vkládáná věc do batohu
     * @return vrací boolean v závislosti, zdali byla věc přidána
     */
    public boolean vloz(Vec item){
        if (seznam.size() < KAPACITA){
            
            if(item.isPrenositelna()) {            
                seznam.put(item.getNazev(),item);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Vyhodí věc z batohu - zkoumá, zdali v batohu je
     *
     * @param item odtraňuje věc z batohu
     * @return vrací boolean v závislosti, zdali byla věc odebrána
     */
    public Vec vyhod(String item) {
        Vec vec;
        if (seznam.containsKey(item)) {
            vec = seznam.remove(item);
            return vec;
        }
        return null;
    }
    
    /**
     * Vraci odkaz na vec v batohu specifikovany textem
     *
     * @param item hledana vec v batohu
     * @return odkaz na vec v batohu
     */
        public Vec getVec(String item) {
        Vec vec;
        if (seznam.containsKey(item)) {
            vec = seznam.get(item);
            return vec;
        }
        return null;
    }
    
    /**
     * Zjišťování věci v batohu
     *
     * @param item  název věci
     * @return vrací boolean v závislosti, zdali je věc v batohu
     */
    public boolean obsahuje(String item) {
        return (seznam.containsKey(item));
    }


    /**
     * Výpis seznamu věcí v batohu
     *
     * @return text s obsahem batohu
     */
    public String getObsah() {
        String text = "";
        for (String nazev : seznam.keySet()) {
            text +=  nazev + " ";
        }
        return text;
    }
    
    /**
     * Preda nemodifikovatelnou kolekci batohu
     * 
     * @return nemodifikovatelny batoh - kolekce
     */
    public Map<String, Vec> getBatoh(){
    	return Collections.unmodifiableMap(seznam);
    }
    
    
    /**
     * Zkoumá, zdali je batoh plný
     *
     * @return vrací boolean v závislosti, zdali je batoh plný
     */
    public boolean jePlny() {
        return (seznam.size() >= KAPACITA);
    }
}