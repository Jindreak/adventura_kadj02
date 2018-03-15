/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.Jindreak.adventura_kadj02.logika;



/*******************************************************************************
 * Implementace pro prikaz "interaguj" slouzici pro ucineni zmen v hernim svete.
 * Interagovat se da pouze se statickymi predmety.
 *
 * @author    Jindra Kadoun
 * @version   ZS 2016/2017
 */
public class PrikazInteragovat implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "interagovat";
    
    private HerniPlan herniPlan;
    private SeznamZmen zmeny;

    //== Konstruktory a tovární metody =============================================

    /**
     *  Konstruktor ....
     *  
     *  @param hPlan odkaz na herni plan
     */
    public PrikazInteragovat(HerniPlan hPlan) {
        this.herniPlan = hPlan;
        this.zmeny = herniPlan.getZmeny();
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
            return "Nevím s čím mám interagovat";
        }
        
       String nazevVeci = parametry[0];
        
       Prostor prostor = herniPlan.getAktualniProstor();
       Vec vec = herniPlan.getAktualniProstor().odeberVec(nazevVeci);
       
       if (vec == null){
           return "Tato věc v této místnosti není.";
       }
        
       if (vec.isPrenositelna()) {
            herniPlan.getAktualniProstor().vlozVec(vec);
            return "Tuto věc můžete použít až z batohu.";
       }    
       
       herniPlan.getAktualniProstor().vlozVec(vec);
       
       return zmeny.zmena(prostor, vec);
       

         
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
