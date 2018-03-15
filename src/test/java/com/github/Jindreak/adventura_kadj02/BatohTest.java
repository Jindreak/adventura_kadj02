package com.github.Jindreak.adventura_kadj02;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.Jindreak.adventura_kadj02.logika.Batoh;
import com.github.Jindreak.adventura_kadj02.logika.Vec;

/**
 * Třída testující batoh
 *
 * @author  Jindra Kadoun
 * @version ZS 2016/2017
 */
public class BatohTest
{


    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * Test vlozeni veci
     */
    @Test
    public void testVlozVec() {
        Batoh batoh = new Batoh();
        Vec vec1 = new Vec("vec1", true);
        assertEquals(true, batoh.vloz(vec1));
    }
    
    /**
     * Test odlozeni veci
     */
    @Test
    public void testOdlozVec() {
        Batoh batoh = new Batoh();
        Vec vec1 = new Vec("vec1", true);
        assertEquals(true, batoh.vloz(vec1));
        assertEquals(vec1, batoh.vyhod("vec1"));
    }

    /**
     * Test kapacity baothu
     */
    @Test
    public void testKapacitaBatohu() {
        Batoh batoh = new Batoh();
        Vec vec1 = new Vec("vec1", true);
        Vec vec2 = new Vec("vec2", true);
        Vec vec3 = new Vec("vec3", true);
        Vec vec4 = new Vec("vec4", true);
        Vec vec5 = new Vec("vec5", true);
        assertEquals(true, batoh.vloz(vec1));
        assertEquals(true, batoh.vloz(vec2));
        assertEquals(true, batoh.vloz(vec3));
        assertEquals(true, batoh.vloz(vec4));
        assertEquals(false, batoh.vloz(vec5));
    }

    /**
     * Test nepřenositelných věcí
     */
    @Test
    public void testVlozeniNeprenositelneVeci()
    {
        Batoh batoh = new Batoh();
        Vec vec1 = new Vec("vec1", false);
        assertEquals(false, batoh.vloz(vec1));
    }
}
