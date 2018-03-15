package com.github.Jindreak.adventura_kadj02.logika;

import java.util.HashMap;
import java.util.Map;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version    ZS 2016/2017
 */
public class HerniPlan {

    private static final String CILOVY_PROSTOR = "chodba";

    private Prostor aktualniProstor;
    private  Map<String,Prostor> mapaProstoru; 
    private SeznamZmen seznamZmen;
    private Batoh batoh;

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
       mapaProstoru = new HashMap<>();
       zalozProstoryHry();
       this.batoh = new Batoh();
       this.seznamZmen = new SeznamZmen (this);
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor stazove_komory = new Prostor("stázové_komory","se stázovými komorami.");
        mapaProstoru.put("stázové_komory", stazove_komory);
        
        Prostor transporter = new Prostor("transportér", "s transportním zařízením.",true);
        mapaProstoru.put("transportér", transporter);
        
        Prostor strojovna = new Prostor("strojovna","strojovna - zde by mohli být zajímavé věci.");
        mapaProstoru.put("strojovna", strojovna);
        
        Prostor chodba_s_obranou = new Prostor("chodba_s_obranou","která obsahuje obranné prvky.");
        mapaProstoru.put("chodba_s_obranou", chodba_s_obranou);
        
        Prostor spojovaci_chodba = new Prostor("spojovací_chodba","v centru lodi.",true);
        mapaProstoru.put("spojovací_chodba", spojovaci_chodba);
        
        Prostor pretlakova_komora = new Prostor("přetlaková_komora","sloužící k přestupu na jiné lodě.",true);
        mapaProstoru.put("přetlaková_komora", pretlakova_komora);
        
        Prostor sklad = new Prostor ("sklad","se zajímavými věci a šílencem uvnitř.",true);
        mapaProstoru.put("sklad", sklad);
        
        Prostor mustek = new Prostor ("můstek",", které je řídící centrum celé vesmírné lodi.");
        mapaProstoru.put("můstek", mustek);
        
        Prostor finalni = new Prostor ("chodba","");
        mapaProstoru.put("chodba", finalni);

        // přiřazují se průchody mezi prostory (sousedící prostory)
        stazove_komory.setVychod(strojovna);
        stazove_komory.setVychod(transporter);
        stazove_komory.setVychod(chodba_s_obranou);
        
        strojovna.setVychod(stazove_komory);
        
        transporter.setVychod(stazove_komory);
        
        chodba_s_obranou.setVychod(stazove_komory);
        chodba_s_obranou.setVychod(spojovaci_chodba);
        
        spojovaci_chodba.setVychod(chodba_s_obranou);
        spojovaci_chodba.setVychod(sklad);
        spojovaci_chodba.setVychod(pretlakova_komora);
        spojovaci_chodba.setVychod(mustek);
        
        pretlakova_komora.setVychod(spojovaci_chodba);
        
        sklad.setVychod(spojovaci_chodba);
        
        mustek.setVychod(spojovaci_chodba);

        // vytvoříme několik věcí
        Vec emp_granat = new Vec("emp_granát", true);
        Vec baterie = new Vec("baterie_s_temnou_energií", true);
        Vec warpovy_pohon = new Vec("warpový_pohon", false);
        Vec ovladaci_panel = new Vec("ovládácí_panel",false);
        Vec konzole = new Vec("konzole",false);
        Vec teleport = new Vec("teleport",false);
        Vec barikada = new Vec("zabarikádované_dveře",false);
        
        // umístíme věci do prostorů
        strojovna.vlozVec(emp_granat);
        strojovna.vlozVec(baterie);
        strojovna.vlozVec(warpovy_pohon);
        mustek.vlozVec(ovladaci_panel);
        pretlakova_komora.vlozVec(konzole);
        transporter.vlozVec(teleport);
        sklad.vlozVec(barikada);
        
        // vytvorim postavy
        Postava sberaci = new Postava ("sběrači","Je smůla, že je tady někdo naživu...takhle si nebudeme"
                                        +" moci odvézt všechny ty věci.\n Víš co, uděláme dohodu."
                                        +" Otevři pro nás ten sklad naproti a my tě svezeme na nejblížší"
                                        +" bezpečnou planetu,\npak už budeš moct kontaktovat svoje nadřízené."
                                        +"\nZkus tuhle konzoli.");
                                        
        Postava blazen = new Postava ("blázen","NULA NULA NULA NULA. TY SEŠ PRVNÍ.\nTY SI BYL POSLEDNÍ"
                                        +"TEĎ BUDEŠ POSLEDNÍ");                                
                                        
        //dám postavy do prostoru
        pretlakova_komora.vlozPostavu(sberaci);
        sklad.vlozPostavu(blazen);
        
        aktualniProstor = stazove_komory;  
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda vrací odkaz na prostor specifikován textem
     *
     *@return prostor, která se rovná textu
     */
    
    public Prostor getProstor(String nazevProstoru) {
        return mapaProstoru.get(nazevProstoru);
    }
    
     /**
     *  Metoda vrací odkaz na batoh
     *
     *@return     odkaz na batoh
     */
    
    public Batoh getBatoh() {
        return batoh;
    }
    
    /**
     *  Metoda vrací odkaz na zmeny
     *
     *@return     odkaz na seznam zmen
     */
    
    public SeznamZmen getZmeny() {
        return seznamZmen;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

    /**
     *  Metoda zkouma, zdali byly splneny podminky pro vyhru
     *
     * @return true, pokud hrac vyhral
     */
    public boolean hracVyhral() { 
        if (aktualniProstor.getNazev().equals(CILOVY_PROSTOR)) {
            return true;
        }
        
        return false;
    }

}
