package com.github.Jindreak.adventura_kadj02.logika;


/**
 * Třída postava popisuje postavy v místnosti, se kterýma lze promluvit.
 * Postavy mají svoje jméno, dialog a indikaci, zda již hráč s touto osobou mluvil.
 * 
 * @author Jindra Kadoun
 * @version ZS 2016/2017
 */
public class Postava {
    // instance variables - replace the example below with your own
    private String nazev;
    private String dialog;
    private boolean mluvil;

    /**
     * Konstruktor - defaultne nastavena indikace mluveni na false
     * 
     * @param jmeno jmeno postavy
     * @param kec dialog, ktery postava rekne hraci
     */
    public Postava(String jmeno, String kec) {
        nazev = jmeno;
        dialog = kec;
        mluvil = false;
    }
    
    /**
     * Metoda equals pro porovnání dvou postav. Překrývá se metoda equals ze
     * třídy Object. Dvě postavy jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param obj object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaná postava stejný název, jinak false
     */  
    @Override
    public boolean equals(Object obj) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == obj) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(obj instanceof Postava)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Postava druhy = (Postava) obj;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }
    
    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 4;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 22 * vysledek + hashNazvu;
        return vysledek;
    }
    
    /**
     * Metoda na vraceni jmena postavy
     * @return jmeno postavy v textove forme
     */
    public String getNazev(){
        return nazev;
    }
    
    /**
     * Vrati indikaci, zda hrac s postavou jiz mluvil
     * @return true, pokud hrac s postavou mluvil
     */
    public boolean getMluvil(){
        return mluvil;
    }
    
    /**
     * Vrati textovy retezec, ktery postava prednese pri prikazu "promluv"
     * @return text, ktery postava rekne
     */
    public String getDialog(){
        return dialog;
    }
    
    /**
     * Nastavuje idikaci, zdali probehl dialog
     * 
     * @param probehlDialog true/false zdali probehl dialog
     */
    public void setMluvil(boolean probehlDialog){
        mluvil = probehlDialog;
    }
    

}
