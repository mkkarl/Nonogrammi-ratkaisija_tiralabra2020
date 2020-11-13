/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nonogrammiratkaisija.logic;

/**
 *
 * @author mari
 */
public class Patka {

    private int pituus;
    private int liikkumavaraAlku;
    private int liikkumavaraLoppu;
    private int varmatAlku;
    private int varmatLoppu;
    private Patka edeltavaPatka;
    private Patka seuraavaPatka;
    private boolean valmis;
    private boolean odottaaKasittelya;
    private boolean rivi;   //true = rivi, false = sarake
    private int rsNro; // rivi/sarakenumero
//    private Ruutu[][] ruudukko;

    public Patka(int pituus, int liikkumavaraAlku, int liikkumavaraLoppu, Patka edeltavaPatka, boolean rivi, int rsNro, Ruutu[][] ruudukko) {
        this.pituus = pituus;
        this.liikkumavaraAlku = liikkumavaraAlku;
        this.liikkumavaraLoppu = liikkumavaraLoppu;
        this.edeltavaPatka = edeltavaPatka;
        this.seuraavaPatka = null;
        this.valmis = false;
        this.odottaaKasittelya = true;
        this.rivi = rivi;
        this.rsNro = rsNro;

    }
    
    public int getPituus() {
        return pituus;
    }
    
    public int getLiikkumavaraAlku() {
        return liikkumavaraAlku;
    }
    
    public int getLiikkumavaraLoppu() {
        return liikkumavaraLoppu;
    }

    public int getLiikkumavaraPituus() {
        return liikkumavaraLoppu - liikkumavaraAlku + 1;
    }
    
    public void setVarmatAlku(int varmatAlku) {
        this.varmatAlku = varmatAlku;
    }
    
    public int getVarmatAlku() {
        return varmatAlku;
    }
    
    public void setVarmatLoppu(int varmatLoppu) {
        this.varmatLoppu = varmatLoppu;
    }
    
    public int getVarmatLoppu() {
        return varmatLoppu;
    }
    
    public boolean getRivi() {
        return rivi;
    }
    
    public int getRsNro() {
        return rsNro;
    }

}
