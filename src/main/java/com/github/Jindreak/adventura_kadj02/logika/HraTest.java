package com.github.Jindreak.adventura_kadj02.logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra.
 *
 * @author   Jarmila Pavlíčková
 * @version  ZS 2016/2017
 */
public class HraTest {
    private Hra hra1;

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
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
        //nepotrebuji uklizet
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("stázové_komory", hra1.getHerniPlan().getAktualniProstor().getNazev());
        
        hra1.zpracujPrikaz("jdi transportér"); //zamcena
        assertEquals("stázové_komory", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi strojovna");
        assertEquals(false, hra1.konecHry());
        assertEquals("strojovna", hra1.getHerniPlan().getAktualniProstor().getNazev());
        
        hra1.zpracujPrikaz("vezmi emp_granát");
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahuje("emp_granát"));
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("vezmi baterie_s_temnou_energií");
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahuje("baterie_s_temnou_energií"));
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi stázové_komory");
        assertEquals("stázové_komory", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi chodba_s_obranou");
        assertEquals("chodba_s_obranou", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi spojovací_chodba"); //zamcena
        assertEquals("chodba_s_obranou", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("použít emp_granát");
        assertEquals(false, hra1.getHerniPlan().getBatoh().obsahuje("emp_granát"));
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi spojovací_chodba"); 
        assertEquals("spojovací_chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi sklad"); //zamcena
        assertEquals("spojovací_chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi přetlaková_komora"); //zamcena
        assertEquals("spojovací_chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi můstek"); 
        assertEquals("můstek", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("interagovat ovládácí_panel");
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi spojovací_chodba"); 
        assertEquals("spojovací_chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi přetlaková_komora"); 
        assertEquals("přetlaková_komora", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("interagovat konzole");
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi spojovací_chodba"); 
        assertEquals("spojovací_chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi chodba_s_obranou");
        assertEquals("chodba_s_obranou", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi stázové_komory");
        assertEquals("stázové_komory", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi transportér"); 
        assertEquals("transportér", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("použít baterie_s_temnou_energií"); 
        assertEquals("sklad", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.getHerniPlan().getBatoh().obsahuje("baterie_s_temnou_energií"));
        assertEquals(false, hra1.konecHry());
        
        
        hra1.zpracujPrikaz("interagovat zabarikádované_dveře");
        assertEquals(true, hra1.konecHry());
        
    }
}
