/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nonogrammiratkaisija.logic;

import java.util.ArrayDeque;

/**
 *
 * @author mari
 */
public class Logiikka {
    private Ruutu[][] ruudukko;
    private ArrayDeque<Patka> patkat;
    
    public Logiikka(int korkeus, int leveys) {
        this.ruudukko = new Ruutu[korkeus][leveys];
        
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                ruudukko[i][j] = new Ruutu(i, j);
            }
        }
        
        patkat = new ArrayDeque<>();
    }
    
    public void tulostaRuudukko() {
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko[i].length; j++) {
                System.out.print(ruudukko[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public int getKorkeus() {
        return ruudukko.length;
    }
    
    public int getLeveys() {
        return ruudukko[0].length;
    }
    
    public void luoRivit(String[] rivit) {
        for (int i = 0; i < rivit.length; i++) {
            
        }
    }
    
}
