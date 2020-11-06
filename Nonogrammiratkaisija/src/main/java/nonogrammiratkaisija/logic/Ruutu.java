/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nonogrammiratkaisija.logic;

import java.util.*;
import nonogrammiratkaisija.ui.Patka;

/**
 *
 * @author mari
 */
public class Ruutu {
    
    private int rivi;
    private int sarake;
    private String tila;
    private List<Patka> patkat;
    
    public Ruutu(int rivi, int sarake) {
        this.rivi = rivi;
        this.sarake = sarake;
        this.tila = "ET";
        this.patkat = new ArrayList();
    }
    
    public void setTyhja() {
        if (tila.equals("M")) {
            System.out.println("VIRHE!!!");
            // keskeytys
            return;
        }
        
        tila = "T";
    }
    
    public void setMusta() {
        if (tila.equals("T")) {
            System.out.println("VIRHE!!!");
            // keskeytys
            return;
        }
        
        tila = "M";
    }
    
    public String getTila() {
        return tila;
    }
    
    public void lisaaPatka(Patka patka) {
        patkat.add(patka);
    }
    
    public void poistaPatka(Patka patka) {
        patkat.remove(patka);
    }
    
}
