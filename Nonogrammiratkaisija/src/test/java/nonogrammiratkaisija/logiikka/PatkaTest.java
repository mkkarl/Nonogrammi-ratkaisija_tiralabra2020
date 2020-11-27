/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nonogrammiratkaisija.logiikka;

import nonogrammiratkaisija.logiikka.Patka;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mari
 */
public class PatkaTest {
    private Patka patka;
    
    @Before
    public void setUp() {
        this.patka = new Patka(3, 0, 5, null, true, 0);
    }
    
    @Test
    public void palauttaaAlkuarvot() {
        assertEquals(3, patka.getPituus());
        assertEquals(0, patka.getLiikkumavaraAlku());
        assertEquals(5, patka.getLiikkumavaraLoppu());
        assertEquals(null, patka.getEdeltavaPatka());
        assertTrue(patka.onRivi());
    }
}
