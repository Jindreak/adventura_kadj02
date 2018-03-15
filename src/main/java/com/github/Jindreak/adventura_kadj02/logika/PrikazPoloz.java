package com.github.Jindreak.adventura_kadj02.logika;


/**
 * Příkaz polož odstraní věc z batohu a umístí ho do prostoru
 * 
 * @author Jindra Kadoun 
 * @version ZS 2016/2017
 */
public class PrikazPoloz implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "polož";
    
    private HerniPlan herniPlan;
    private Batoh batoh;

    //== Konstruktory a tovární metody =============================================

    /**
     *  Konstruktor ....
     *  
     *   @param hPlan odkaz na herni plan
     */
    public PrikazPoloz(HerniPlan hPlan)
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
            return "nevím, co mám položit";
        }
        
        
        String nazevVeci = parametry[0];
        
        
       if (!batoh.obsahuje(nazevVeci)){
           return "Takovou věc v batohu nemáš!";
        }
        
        
       Vec vec = batoh.vyhod(nazevVeci);
       herniPlan.getAktualniProstor().vlozVec(vec);
       
       
        return "věc '" + nazevVeci + "' jsi vyhodil z batohu";  
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
