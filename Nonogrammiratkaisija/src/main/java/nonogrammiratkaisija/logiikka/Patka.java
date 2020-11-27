/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nonogrammiratkaisija.logiikka;

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

    public Patka(int pituus, int liikkumavaraAlku, int liikkumavaraLoppu, Patka edeltavaPatka, boolean rivi, int rsNro) {
        this.pituus = pituus;
        this.liikkumavaraAlku = liikkumavaraAlku;
        this.liikkumavaraLoppu = liikkumavaraLoppu;
        this.varmatAlku = -1;
        this.varmatLoppu = -1;
        this.edeltavaPatka = edeltavaPatka;
        this.seuraavaPatka = null;
        this.valmis = false;
        this.odottaaKasittelya = true;
        this.rivi = rivi;
        this.rsNro = rsNro;

    }
    
    public Patka(int pituus, int liikkumavaraAlku, int liikkumavaraLoppu, Patka edeltavaPatka) {
        this.pituus = pituus;
        this.liikkumavaraAlku = liikkumavaraAlku;
        this.liikkumavaraLoppu = liikkumavaraLoppu;
        this.varmatAlku = -1;
        this.varmatLoppu = -1;
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

    public void setLiikkumavaraAlku(int liikkumavaraAlku) {
        this.liikkumavaraAlku = liikkumavaraAlku;
    }

    public int getLiikkumavaraLoppu() {
        return liikkumavaraLoppu;
    }

    public int getLiikkumavaraPituus() {
        return liikkumavaraLoppu - liikkumavaraAlku + 1;
    }

    public void setLiikkumavaraLoppu(int liikkumavaraLoppu) {
        this.liikkumavaraLoppu = liikkumavaraLoppu;
    }

    public Patka getEdeltavaPatka() {
        return edeltavaPatka;
    }

    public void setSeuraavaPatka(Patka patka) {
        this.seuraavaPatka = patka;
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

    public boolean onValmis() {
        return valmis;
    }

    public boolean odottaaKasittelya() {
        return odottaaKasittelya;
    }

    public void setEiOdotaKasittelya() {
        odottaaKasittelya = false;
    }

    public boolean onRivi() {
        return rivi;
    }

    public int getRsNro() {
        return rsNro;
    }
    
    public int[] koordinaatit(int indeksi) {
        int[] xy = new int[2];
        
        xy[0] = 0;
        xy[1] = 0;
        
        return xy;
    }
    
    public String patkanTyyppi() {
        return "Patka";
    }

}
