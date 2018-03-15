/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.Jindreak.adventura_kadj02.logika;



/*******************************************************************************
 * Tento příkaz vymaže věc z prostoru a umístí ho do batohu
 *
 * @author    Jindra Kadoun
 * @version   ZS 2016/2017
 */
public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi";
    
    private HerniPlan herniPlan;
     private Batoh batoh;

    //== Konstruktory a tovární metody =============================================

    /**
     *  Konstruktor ....
     *  
     *  @param hPlan odkaz na herni plan
     */
    public PrikazVezmi(HerniPlan hPlan)
    {
        this.herniPlan = hPlan;
        this.batoh = herniPlan.getBatoh();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Samotne provedeni prikazu - zkoumame nejdrive vyjimky, ktere mohou v hernim svete
     * nastat. Po te vyvolame zmenu v hernim svete pres seznam zmen.
     * 
     * @return text, ktery popisuje prubeh prikazu
     */
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "nevím, co mám sebrat";
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = herniPlan.getAktualniProstor().odeberVec(nazevVeci);
        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        
        if (!vec.isPrenositelna()) {
            herniPlan.getAktualniProstor().vlozVec(vec);
            return "věc '" + nazevVeci + "' fakt neuneseš";
        }
        
        // Je třeba dokončit vložení věci do batohu (až bude tato třída existovat).
        
         if (batoh.jePlny()) {
               herniPlan.getAktualniProstor().vlozVec(vec);
               return "batoh je plný";
           }

           batoh.vloz(vec);
           return "věc '" + nazevVeci + "' jsi uložil do batohu";
         
    }

    /**
     * Vraci nazev prikazu
     * 
     * @return text nazvem prikazu
     */
	public String getNazev() {
	    return NAZEV;
	}

    //== Soukromé metody (instancí i třídy) ========================================

}
