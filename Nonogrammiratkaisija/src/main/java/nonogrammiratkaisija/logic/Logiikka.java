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
    private String[] rivit;
    private String[] sarakkeet;

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
//        for (int i = 0; i < ruudukko.length; i++) {
//            for (int j = 0; j < ruudukko[i].length; j++) {
//                System.out.print(ruudukko[i][j]);
//            }
//            System.out.println("");
//        }
//        System.out.println("");

        String[][] kokoRuudukko = new String[ruudukko.length + 1][ruudukko[0].length + 1];

        for (int i = 1; i < kokoRuudukko.length; i++) {
            kokoRuudukko[i][0] = rivit[i - 1];
        }

        for (int i = 1; i < kokoRuudukko[0].length; i++) {
            String pystyteksti = "";
            for (int j = 0; j < sarakkeet[i - 1].length(); j++) {
                pystyteksti += sarakkeet[i - 1].substring(j, j) + "\n";
            }
            kokoRuudukko[0][i] = pystyteksti;
        }

        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko[i].length; j++) {
                kokoRuudukko[i + 1][j + 1] = ruudukko[i][j].toString();
            }
        }

        for (int i = 0; i < kokoRuudukko.length; i++) {
            for (int j = 0; j < kokoRuudukko[i].length; j++) {
                System.out.print(kokoRuudukko[i][j]);
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

    public void luoRivitSarakkeet(String[] rivitSarakkeet, boolean rivi) {
        for (int i = 0; i < rivitSarakkeet.length; i++) {

            int leveysKorkeus;

            if (rivi) {
                this.rivit = rivitSarakkeet;
                leveysKorkeus = getLeveys();
            } else {
                this.sarakkeet = rivitSarakkeet;
                leveysKorkeus = getKorkeus();
            }

            //erotellaan luvut toisistaan
            String[] palat = rivitSarakkeet[i].split(" ");
            int[] luvut = new int[palat.length];
            for (int j = 0; j < palat.length; j++) {
                luvut[j] = Integer.valueOf(palat[j]);
            }

            //määritellään pätkien yhteispituus minimiväleillä
            int summa = luvut.length - 1;

            for (int luku : luvut) {
                summa += luku;
            }

            //luodaan pätkät
            Patka uusiPatka;
            Patka edellinen = null;
            int kertyma = 0;

            for (int j = 0; j < luvut.length; j++) {
                uusiPatka = new Patka(luvut[j], kertyma, kertyma + luvut[j] + leveysKorkeus - summa - 1, edellinen, rivi, i, ruudukko);
                merkitseVarmatAlussa(uusiPatka);
                patkat.addFirst(uusiPatka);
                edellinen = uusiPatka;
                kertyma += luvut[j] + 1;
            }
        }
    }

    public void merkitseVarmatAlussa(Patka patka) {
        if (patka.getLiikkumavaraPituus() < patka.getPituus() * 2) {
            int varmatAlku = patka.getLiikkumavaraLoppu() - patka.getPituus() + 1;
            patka.setVarmatAlku(varmatAlku);
            int varmatLoppu = patka.getLiikkumavaraAlku() + patka.getPituus() - 1;
            patka.setVarmatLoppu(varmatLoppu);

            for (int i = patka.getVarmatAlku(); i <= patka.getVarmatLoppu(); i++) {
                if (patka.getRivi()) {
                    ruudukko[patka.getRsNro()][i].setMusta();
                } else {
                    ruudukko[i][patka.getRsNro()].setMusta();
                }
            }
        }

        for (int i = patka.getLiikkumavaraAlku(); i <= patka.getLiikkumavaraLoppu(); i++) {
            if (patka.getRivi()) {
                ruudukko[patka.getRsNro()][i].lisaaPatka(patka);
            } else {
                ruudukko[i][patka.getRsNro()].lisaaPatka(patka);
            }
        }
    }

}
