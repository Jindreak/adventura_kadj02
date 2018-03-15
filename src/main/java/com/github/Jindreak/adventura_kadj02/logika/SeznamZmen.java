package com.github.Jindreak.adventura_kadj02.logika;


/**
 *  Tato třída popisuje určité změny v realitě hry na základě použití příkazů "použij" a "interaguj"
 *  Pro provedení změn je nutné mít k dispozici herní plán i batoh.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  @author     Jindra Kadoun
 *  @version    ZS 2016/2017
 */
class SeznamZmen {
    
    private HerniPlan hPlan;
    private Batoh batoh;
   
    /**
     * Konstruktor
     * 
     * @param herniPlan odkaz na herni plan
     */
    public SeznamZmen(HerniPlan herniPlan) {
        this.hPlan = herniPlan;
        this.batoh = herniPlan.getBatoh();
    }
    
    
    /**
     * Meni vlastnosti objektu ve hre podle predem specifikovaneho scenare.
     *
     *@param prostor prostor, ve kterem se zmena bude odehravat
     *@param vec vec, ktera ke zmene bude pouzita
     *
     *@return textovy retezec popisujici zmenu
     */
    
    public String zmena(Prostor prostor, Vec vec){
        String spojenina = prostor.getNazev()+" "+vec.getNazev();
        
        String vystup = "";
        
        Prostor zmenovanyProstor;
        
        switch (spojenina){
            case "chodba_s_obranou emp_granát":     zmenovanyProstor = hPlan.getProstor("spojovací_chodba");
                                                    zmenovanyProstor.setLocked(false);
                                                    batoh.vyhod("emp_granát");
                                                    vystup = "Podařilo se ti vyřadit obranné prvky.\n";
                                                    vystup += "Otevřela se spojovací chodba";
                                                    break;
                                                    
            case "můstek ovládácí_panel":           zmenovanyProstor = hPlan.getProstor("přetlaková_komora");                                         
                                                    zmenovanyProstor.setLocked(false);
                                                    vystup = "Podařilo se ti hacknout elektronický zámek.\n";
                                                    vystup += "Otevřela se přetlaková komora.";
                                                    break;
                                                    
            case "přetlaková_komora konzole":       zmenovanyProstor = hPlan.getProstor("transportér");
                                                    zmenovanyProstor.setLocked(false);
                                                    vystup = "S pomocí sběračů se ti podařilo otevřít transporní místnost.\n";
                                                    vystup += "Otevřela se místnost transportér.";
                                                    break;
              
            case "transportér teleport":            vystup = "Nápad dobrý, ale vypadá to, že je teleport bez energie.\n";                                      
                                                    break;
                                                    
                                                    
            case "transportér baterie_s_temnou_energií":    
                                                    zmenovanyProstor = hPlan.getProstor("sklad");
                                                    zmenovanyProstor.setLocked(false);
                                                    hPlan.setAktualniProstor(zmenovanyProstor);
                                                    batoh.vyhod("baterie_s_temnou_energií");
                                                    vystup = "Baterie nohodila teleport a ten tě teleportoval přímo do skladu.\n";
                                                    vystup += "Byl si přemístěn do místnosti sklad.\n";
                                                    vystup += zmenovanyProstor.dlouhyPopis();
                                                    vystup += "\nBatoh: "+batoh.getObsah();
                                                    
                                                    zmenovanyProstor = hPlan.getProstor("spojovací_chodba");
                                                    zmenovanyProstor.setLocked(true);
                                                    break;
                                                    
            case "sklad zabarikádované_dveře":      zmenovanyProstor = hPlan.getProstor("chodba");                                        
                                                    hPlan.setAktualniProstor(zmenovanyProstor);
                                                    vystup = "Odsunul jsi barikádu od dveří a podle domluvy tě";
                                                    vystup += " sběrači vzali na svojí loď a odvezli tě na bezpečnou planetu.\n";
                                                    vystup += "Gratuluji, dokončil jsi hru.";
                                                    break;
                                                    
            default:                                vystup = "Nic se nestalo.";
        }
        
        return vystup;
    }
    
}

