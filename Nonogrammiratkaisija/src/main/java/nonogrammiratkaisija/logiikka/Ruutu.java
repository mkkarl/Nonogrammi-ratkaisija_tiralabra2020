/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nonogrammiratkaisija.logiikka;

import java.util.*;

/**
 *
 * @author mari
 */
public class Ruutu {

    private int rivi;
    private int sarake;
    private int tila;   // 0 = käsittelemätön, 1= tyhjä, 2=musta
//    private List<Patka> patkat;
    private List<Patka> vpatkat;
    private List<Patka> ppatkat;
    private Patka varmaVpatka;
    private Patka varmaPpatka;

    public Ruutu(int rivi, int sarake) {
        this.rivi = rivi;
        this.sarake = sarake;
        this.tila = 0;
//        this.patkat = new ArrayList();
        this.vpatkat = new ArrayList();
        this.ppatkat = new ArrayList();
        this.varmaVpatka = null;
        this.varmaPpatka = null;
    }

    public void setTyhja() {
        if (tila == 2) {
            System.out.println("VIRHE!!!");
            // keskeytys
            return;
        }

        tila = 1;
    }

    public void setMusta() {
        if (tila == 1) {
            System.out.println("VIRHE!!!");
            // keskeytys
            return;
        }

        tila = 2;
    }

    public int getTila() {
        return tila;
    }

    public boolean onTyhja() {
        if (this.tila == 1) {
            return true;
        }

        return false;
    }
    
    public boolean onMusta() {
        if (this.tila == 2) {
            return true;
        }

        return false;
    }

//    public void lisaaPatka(Patka patka) {
//        patkat.add(patka);
//    }
    
    public void lisaaVpatka(Patka patka) {
        vpatkat.add(patka);
    }
    
    public void lisaaPpatka(Patka patka) {
        ppatkat.add(patka);
    }

    public void poistaPatka(Patka patka) {
        
        if (patka.patkanTyyppi() == "Vpatka") {
            vpatkat.remove(this);
            
            if (vpatkat.isEmpty()) {
                this.setTyhja();
            }
        } else if (patka.patkanTyyppi() == "Ppatka") {
            ppatkat.remove(this);
            
            if (ppatkat.isEmpty()) {
                this.setTyhja();
            }
        }
    }
    
    public void asetaVarmaPatka(Patka patka) {
        if (patka.patkanTyyppi().equals("Vpatka")) {
            this.varmaVpatka = patka;
        } else if (patka.patkanTyyppi().equals("Ppatka")) {
            this.varmaPpatka = patka;
        }
    }
    
//    public void asetaVarmaVpatka(Patka patka) {
//        this.varmaVpatka = patka;
//    }
//    
//    public void asetaVarmaPpatka(Patka patka) {
//        this.varmaPpatka = patka;
//    }
    
    public List<Patka> patkaLista(Patka patka) {
        if (patka.patkanTyyppi().equals("Vpatka")) {
            return vpatkat;
        } else if (patka.patkanTyyppi().equals("Ppatka")) {
            return ppatkat;
        }
        return null;
    }
    
    public boolean sisRisteavaPatka(Patka patka) {
        if (patka.patkanTyyppi().equals("Vpatka") && !ppatkat.isEmpty()) {
            return true;
        } else if (patka.patkanTyyppi().equals("Ppatka") && !vpatkat.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (tila == 1) {
            return " ";
        } else if (tila == 2) {
            return "O";
        }

        return "-";
    }

}
