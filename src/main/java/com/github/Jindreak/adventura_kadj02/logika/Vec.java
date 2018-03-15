/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.Jindreak.adventura_kadj02.logika;



/*******************************************************************************
 * Popisuje vec v hernim svete. Vec je umistena do prostoru, ma svuj jedinecny nazev
 * a muze byt prenositelna do jinyho prostoru nebo staticka.
 *
 * @author    Jindra Kadoun
 * @version   ZS 2016/2017
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelna;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     *  
     *  @param nazev nazev veci
     *  @param prenositelna true/false zdali je vec prenositelna
     */
    public Vec(String nazev, boolean prenositelna)
    {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    
    /**
     * Vraci nazev veci
     * @return textovy retezec s nazvem veci
     */
    public String getNazev() {
        return nazev;
    }
     
    /**
     * Vraci indikaci prenositelnosti
     * @return vraci boolean, zdali je vec prenositelna
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }

    // Možná bude potřeba přidat settery pro atributy 'popis' a 'prenositelna'.
    // Atribut 'nazev' by se měnit neměl.

    //== Soukromé metody (instancí i třídy) ========================================

}
