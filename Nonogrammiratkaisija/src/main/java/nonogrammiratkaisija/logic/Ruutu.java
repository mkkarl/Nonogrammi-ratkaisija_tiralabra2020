/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nonogrammiratkaisija.logic;

import java.util.*;

/**
 *
 * @author mari
 */
public class Ruutu {

    private int rivi;
    private int sarake;
    private int tila;   // 0 = käsittelemätön, 1= tyhjä, 2=musta
    private List<Patka> patkat;

    public Ruutu(int rivi, int sarake) {
        this.rivi = rivi;
        this.sarake = sarake;
        this.tila = 0;
        this.patkat = new ArrayList();
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

    public void lisaaPatka(Patka patka) {
        patkat.add(patka);
    }

    public void poistaPatka(Patka patka) {
        patkat.remove(patka);

        if (patkat.isEmpty()) {
            this.setTyhja();
        }
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
