/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.Jindreak.adventura_kadj02.logika;



/*******************************************************************************
 * Implementuje prikaz pro mluveni s postavou v prostoru. S kazdou postavou je
 * mozne promluvit pouze jednou.
 * 
 * @author    Jindra Kadoun
 * @version   ZS 2016/2017
 */
public class PrikazPromluv implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "promluv";
    
    private HerniPlan hPlan;

    //== Konstruktory a tovární metody =============================================

    /**
     *  Konstruktor ....
     *  
     *  @param hPlan odkaz na herni plan
     */
    public PrikazPromluv(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
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
            return "nevím, s kým mám promlvit";
        }
        
        String nazevPostavy = parametry[0];
        
        Prostor prostor = hPlan.getAktualniProstor();
        Postava postava = prostor.getPostava(nazevPostavy);
        if (postava == null) {
            return "Taková postava tu není.";
        }
        
        if (postava.getMluvil()){
            return "Tahle postava už ti nic nepoví.";
        }
        
        postava.setMluvil(true);
        return postava.getDialog();
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
