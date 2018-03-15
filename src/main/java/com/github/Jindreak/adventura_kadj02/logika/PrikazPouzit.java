/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.Jindreak.adventura_kadj02.logika;



/*******************************************************************************
 * Tento příkaz vymaže věc z prostoru a umístí ho do batohu
 *
 * @author    Jindra Kadoun
 * @version   ZS 2016/2017
 */
public class PrikazPouzit implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "použít";
    
    private HerniPlan herniPlan;
    private Batoh batoh;
    private SeznamZmen zmeny;

    //== Konstruktory a tovární metody =============================================

    /**
     *  Konstruktor ....
     *  
     *  @param hPlan odkaz na herni plan
     */
    public PrikazPouzit(HerniPlan hPlan)
    {
        this.herniPlan = hPlan;
        this.batoh = herniPlan.getBatoh();
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
            return "Nevím, co mám použít.";
        }
        
        String nazevVeci = parametry[0];
        
       if (!batoh.obsahuje(nazevVeci)){
           return "Takovou věc v batohu nemáš!";
        }
        
       Vec vec = batoh.getVec(nazevVeci);
       Prostor prostor = herniPlan.getAktualniProstor();
       
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
