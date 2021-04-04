/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nonogrammiratkaisija.logiikka;

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
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko[i].length; j++) {
                System.out.print(ruudukko[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");

        tulostaPatkat("");

//        String[][] kokoRuudukko = new String[ruudukko.length + 1][ruudukko[0].length + 1];
//
//        for (int i = 1; i < kokoRuudukko.length; i++) {
//            kokoRuudukko[i][0] = rivit[i - 1] + " ";
//        }
//
//        for (int i = 1; i < kokoRuudukko[0].length; i++) {
//            String pystyteksti = "";
//            for (int j = 0; j < sarakkeet[i - 1].length(); j++) {
//                pystyteksti += sarakkeet[i - 1].substring(j, j + 1) + "\n";
//            }
//            kokoRuudukko[0][i] = pystyteksti;
//        }
//
//        for (int i = 0; i < ruudukko.length; i++) {
//            for (int j = 0; j < ruudukko[i].length; j++) {
//                kokoRuudukko[i + 1][j + 1] = ruudukko[i][j].toString();
//            }
//        }
//
//        for (int i = 0; i < kokoRuudukko.length; i++) {
//            for (int j = 0; j < kokoRuudukko[i].length; j++) {
//                System.out.print(kokoRuudukko[i][j]);
//            }
//            System.out.println("");
//        }
//        System.out.println("");
    }

    public void tulostaPatkat(String selite) {
        System.out.println(selite);
        System.out.println(patkat.toString());
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

            if (rivitSarakkeet[i].isEmpty()) { // tyhjien rivien ja sarakkeiden käsittely
                if (rivi) {
                    for (int j = 0; j < getLeveys(); j++) {
                        ruudukko[i][j].setTyhja();
                    }
                } else {
                    for (int j = 0; j < getKorkeus(); j++) {
                        ruudukko[j][i].setTyhja();
                    }
                }
            } else {

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
//                    uusiPatka = new Patka(luvut[j], kertyma, kertyma + luvut[j] + leveysKorkeus - summa - 1, edellinen, rivi, i);

                    if (rivi) {
                        uusiPatka = new Vpatka(luvut[j], kertyma, kertyma + luvut[j] + leveysKorkeus - summa - 1, edellinen, i);
                    } else {
                        uusiPatka = new Ppatka(luvut[j], kertyma, kertyma + luvut[j] + leveysKorkeus - summa - 1, edellinen, i);
                    }

                    merkitseVarmatAlussa(uusiPatka);
                    patkat.addFirst(uusiPatka);
                    if (edellinen != null) {
                        edellinen.setSeuraavaPatka(uusiPatka);
                    }
                    edellinen = uusiPatka;
                    kertyma += luvut[j] + 1;
                }
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
                int[] xy = patka.koordinaatit(i);
                ruudukko[xy[0]][xy[1]].setMusta();
                ruudukko[xy[0]][xy[1]].asetaVarmaPatka(patka);
            }
        }

        for (int i = patka.getLiikkumavaraAlku(); i <= patka.getLiikkumavaraLoppu(); i++) {

            int[] xy = patka.koordinaatit(i);

            if (patka.patkanTyyppi().equals("Vpatka")) {
                ruudukko[xy[0]][xy[1]].lisaaVpatka(patka);
            } else if (patka.patkanTyyppi().equals("Ppatka")) {
                ruudukko[xy[0]][xy[1]].lisaaPpatka(patka);
            }
        }
    }

    public void ratkaise() {
        Patka edellinenKasitelty = null;

        while (!patkat.isEmpty()) {
            Patka kasiteltava = patkat.pollFirst();

            if (kasiteltava == edellinenKasitelty) {
                System.out.println("Algoritmin kyky loppu, kehitä algoritmia!");
                break;
            } else if (edellinenKasitelty == null) {
                edellinenKasitelty = kasiteltava;
            }

            if (kasiteltava.onValmis()) {
                continue;
            } else if (kasiteltava.odottaaKasittelya()) {

                System.out.println("käsiteltävä pätkä alussa: " + kasiteltava);
                tyhjatRuudut(kasiteltava);
                System.out.println("käsitelty tyhjät ryydut: " + kasiteltava);
//                tulostaPatkat("käsitelty tyhjät ruudut");
                mustatVarmojenVieressa(kasiteltava);
                System.out.println("käsitelty mustat varmojen vieressä: " + kasiteltava);
//                tulostaPatkat("käsitelty mustat varmojen vieressä");
                mustatEiMuidenAlueella(kasiteltava);
                System.out.println("käsitelty mustat ei muiden alueella: " + kasiteltava);
//                tulostaPatkat("käsitelty mustat ei muiden alueella");

                kasiteltava.setEiOdotaKasittelya();
                edellinenKasitelty = kasiteltava;
            }

            if (!kasiteltava.onValmis()) {
                patkat.add(kasiteltava);
            }
        }
    }

    /*
    Onko liikkumavaraan ilmestynyt tyhjiä ruutuja? Lyhennä liikkumavaraa.
    Päivitä varmat ruudut.
     */
    public void tyhjatRuudut(Patka patka) {
        int pituuslaskuri = 0;
        int alku = patka.getLiikkumavaraAlku();
        int loppu = patka.getLiikkumavaraLoppu();

        for (int i = alku; i <= loppu; i++) {

            int[] xy = patka.koordinaatit(i);

            if (!ruudukko[xy[0]][xy[1]].onTyhja()) {
                pituuslaskuri++;

            } else {
                if (pituuslaskuri > 0 && pituuslaskuri < patka.getPituus()) {
                    for (int j = pituuslaskuri; j > 0; j--) {

                        int[] ab = patka.koordinaatit(i - j);
                        ruudukko[ab[0]][ab[1]].poistaPatka(patka);
                    }

                }

                pituuslaskuri = 0;

                ruudukko[xy[0]][xy[1]].poistaPatka(patka);
                patka.setLiikkumavaraAlku(i + 1);
            }

        }

        if (pituuslaskuri > 0 && pituuslaskuri < patka.getPituus()) {
            for (int j = pituuslaskuri; j > 0; j--) {
                int[] ab = patka.koordinaatit(loppu - j);

                ruudukko[ab[0]][ab[1]].poistaPatka(patka);
            }
        }

    }

    /*
    Onko varmojen vieressä uusia mustia? Merkitse varmoiksi ja lyhennä
    liikkumavaraa.
     */
    public void mustatVarmojenVieressa(Patka patka) {
        int alku = patka.getVarmatAlku();

        if (alku != -1) {
            for (int i = alku - 1; i >= patka.getLiikkumavaraAlku(); i--) {

                int[] xy = patka.koordinaatit(i);

                if (ruudukko[xy[0]][xy[1]].onMusta()) {
                    patka.setVarmatAlku(patka.getVarmatAlku() - 1);
                    patka.setLiikkumavaraLoppu(patka.getLiikkumavaraLoppu() - 1);
                } else {
                    break;
                }

            }
        }

        int loppu = patka.getVarmatLoppu();

        if (loppu != -1) {
            for (int i = loppu + 1; i <= patka.getLiikkumavaraLoppu(); i++) {

                int[] xy = patka.koordinaatit(i);

                if (ruudukko[xy[0]][xy[1]].onMusta()) {
                    patka.setVarmatLoppu(patka.getVarmatLoppu() + 1);
                    patka.setLiikkumavaraAlku(patka.getLiikkumavaraAlku() + 1);
                } else {
                    break;
                }

            }
        }

    }

    /*
    Onko liikkumavarassa muita mustia, jotka eivät kuulu muiden samansuuntaisetn
    pätkien liikkumavaraan? Merkitse varmoiksi, täytä aukot ja lyhennä
    liikkumavaraa.
     */
    public void mustatEiMuidenAlueella(Patka patka) {

        if (patka.getVarmatAlku() != -1) {
            for (int i = patka.getLiikkumavaraAlku(); i < patka.getVarmatAlku(); i++) {
                int[] xy = patka.koordinaatit(i);

                Ruutu ruutu = ruudukko[xy[0]][xy[1]];

                if (ruutu.onMusta() && ruutu.patkaLista(patka).size() == 1 && ruutu.patkaLista(patka).contains(patka)) {

                    for (int j = i; j < patka.getVarmatAlku(); j++) {
                        int[] ab = patka.koordinaatit(j);
                        Ruutu ruutu2 = ruudukko[ab[0]][ab[1]];

                        ruutu2.setMusta();
                        ruutu2.asetaVarmaPatka(patka);
                    }

//                patka.setLiikkumavaraLoppu(patka.getLiikkumavaraLoppu() - patka.getVarmatAlku() - i);
                    patka.setLiikkumavaraLoppu(i + patka.getPituus() - 1);
                    patka.setVarmatAlku(i);

                    break;
                }
            }
        } else {
            for (int i = patka.getLiikkumavaraAlku(); i < patka.getLiikkumavaraLoppu(); i++) {
                int[] xy = patka.koordinaatit(i);

                Ruutu ruutu = ruudukko[xy[0]][xy[1]];

                if (ruutu.onMusta() && ruutu.patkaLista(patka).size() == 1 && ruutu.patkaLista(patka).contains(patka)) {

                    patka.setLiikkumavaraLoppu(i + patka.getPituus() - 1);
                    patka.setVarmatAlku(i);
                    
                    mustatVarmojenVieressa(patka);

                    break;
                }
            }
        }

        if (patka.getVarmatLoppu() != -1) {
            for (int i = patka.getLiikkumavaraLoppu(); i > patka.getVarmatLoppu(); i--) {
                int[] xy = patka.koordinaatit(i);

                Ruutu ruutu = ruudukko[xy[0]][xy[1]];

                if (ruutu.onMusta() && ruutu.patkaLista(patka).size() == 1 && ruutu.patkaLista(patka).contains(patka)) {

                    for (int j = i; j > patka.getVarmatLoppu(); j--) {
                        int[] ab = patka.koordinaatit(j);
                        Ruutu ruutu2 = ruudukko[ab[0]][ab[1]];

                        ruutu2.setMusta();
                        ruutu2.asetaVarmaPatka(patka);
                    }

//                patka.setLiikkumavaraAlku(patka.getLiikkumavaraAlku() - patka.getVarmatLoppu() + i);
                    patka.setLiikkumavaraAlku(i - patka.getPituus() + 1);
                    patka.setVarmatLoppu(i);

                    break;
                }
            }
        }
    }

    /*
    Onko liikkumavara-alueella liikaa vierekkäisiä mustia? Vertaa muihin
    samansuuntaisiin pätkiin ja lyhennä vaikutusaluetta.
     */
 /*
    Onko varmoja mustia tarpeeksi? Lisää päätyihin tyhjät, samoin vierekkäiseen
    valmiiseen pätkään tai reunaan asti. Merkitse pätkä valmiiksi.
     */
}
