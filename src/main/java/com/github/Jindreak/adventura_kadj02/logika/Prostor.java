package com.github.Jindreak.adventura_kadj02.logika;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor. Prostor má vlastnost locked, která
 * popisuje, jestli je místnost přístupná přes příkaz "jdi". Dále obsahuje seznam
 * postav uvnitř postav.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version ZS 2016/2017
 */
public class Prostor {

    private String nazev;
    private String popis;
    private boolean locked;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci;
    private Map<String, Postava> postavy;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new HashMap<>();
        postavy = new HashMap<>();
        locked = false;
    }
    
     /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem" a indikací, jestli je prostor zamčen
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     * @param zamceno Indikuje, zdali je místnost zamčená.
     */
    public Prostor(String nazev, String popis, boolean zamceno){
        this(nazev, popis);
        locked = zamceno;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

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
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
                + popisVychodu() + "\n"
                + popisVeci() + "\n"
                + popisPostav();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "vychody:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }
    
    /**
     * Vrací textový řetězec, který popisuje věci uvnitř místnosti
     *
     * @return Popis věcí uvnitř místnosti
     */
    private String popisVeci() {
        String vracenyText = "veci:";
        for (String nazev : veci.keySet()) {
            vracenyText += " " + nazev;
        }
        
        return vracenyText;
    }
    
    /**
     * Metoda popisPostav vrací text, ktery popisuje postavy v prostoru
     *
     * @return Text postav prostoru
     */
    private String popisPostav() {
        String vypis = "Postavy:";
        for (String jmeno : postavy.keySet()) {
            vypis += " " + jmeno ;
        }
        return vypis;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    /**
     * Vrací veci v prostoru, ktere nedaji modifikovat kvuli
     * zapozdreni.
     *
     * @return Veci, ktere se nachazi v prostoru. Nelze modifikovat
     */
    public Map<String, Vec> getVeci() {
        return Collections.unmodifiableMap(veci);
    }
    
    /**
     * Vrací veci v prostoru ktere nedaji modifikovat kvuli
     * zapouzdreni.
     *
     * @return Veci, ktere se nachazi v prostoru. Nelze modifikovat
     */
    public Map<String, Postava> getPostavy() {
        return Collections.unmodifiableMap(postavy);
    }
    
    
     /**
     * Vklada vec do prostoru
     *
     * @param vec  vec, kterou chete vlozit
     */
    public void vlozVec(Vec vec) {
        veci.put(vec.getNazev(), vec);
    }
    
    /**
     * Odebira vec z prostoru na zaklade textoveho retezce, vraci odkaz na smazanou vec
     *
     * @return vec, ktera byla odebrana
     */
    public Vec odeberVec(String nazev) {
        return veci.remove(nazev);
    }
    
    /**
     * Vraci pravdivostni hodnotu, zda je mistnost zavrena
     *
     * @return boolean, zda je mistnost zavrena
     */
    public boolean isLocked(){
        return locked;
    }
    
    /**
     * Nastavuje zavrenost prostoru
     *
     * @param zamceno boolean, zda je mistnost zavrena
     */
    public void setLocked(boolean zamceno){
        locked = zamceno;
    }
    
    /**
     * Vklada postavu do prostoru
     *
     * @param postava postava, kterou chcete pridat
     */
    public void vlozPostavu(Postava postava) {
        postavy.put(postava.getNazev(), postava);
    }
    
    /**
     * Meotda getPostava vrací jméno postavy pokud se nachází v prostoru
     *
     * @return instance nalezené postavy
     */
    public Postava getPostava(String jmeno) {
        return postavy.get(jmeno);
    }
}
