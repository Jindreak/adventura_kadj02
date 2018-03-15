package com.github.Jindreak.adventura_kadj02;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.github.Jindreak.adventura_kadj02.logika.Postava;
import com.github.Jindreak.adventura_kadj02.logika.Prostor;
import com.github.Jindreak.adventura_kadj02.logika.Vec;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková
 * @version   ZS 2016/2017
 */
public class ProstorTest
{
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě");
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku");
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }
    
    
    /**
     * Testuje vkladani veci do prostoru
     */
    @Test
    public void testVeci()
    {
        Prostor prostor1 = new Prostor(null, null);
        Vec vec1 = new Vec("a", true);
        Vec vec2 = new Vec("b", false);
        prostor1.vlozVec(vec1);
        prostor1.vlozVec(vec2);
        assertSame(vec1, prostor1.odeberVec("a"));
        assertNull(prostor1.odeberVec("c"));
    }
    
    /**
     * Testuje vkladani postav do prostoru
     */
    @Test
    public void testPostavy()
    {
        Prostor prostor1 = new Prostor(null, null);
        Postava postava1 = new Postava("postava1", "Dobry den");
        Postava postava2 = new Postava("postava2", "Dobry vecer");
        prostor1.vlozPostavu(postava1);
        prostor1.vlozPostavu(postava2);
        assertSame(postava1, prostor1.getPostava("postava1"));
        assertSame(postava2, prostor1.getPostava("postava2"));
    }

}
