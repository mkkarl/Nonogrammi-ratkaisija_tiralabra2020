/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nonogrammiratkaisija.logiikka;

import nonogrammiratkaisija.logiikka.Ruutu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mari
 */
public class RuutuTest {
    private Ruutu ruutu;
    
    public RuutuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.ruutu = new Ruutu(1, 1);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void palauttaaKasittelematon() {
        assertEquals("-", this.ruutu.toString());
    }
    
    @Test
    public void palauttaaTyhjan() {
        this.ruutu.setTyhja();
        assertEquals(" ", this.ruutu.toString());
    }
    
    @Test
    public void palauttaaMustan() {
        this.ruutu.setMusta();
        assertEquals("O", this.ruutu.toString());
    }
}
