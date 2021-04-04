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
public class Ppatka extends Patka {

    private int sarakenro;

    public Ppatka(int pituus, int liikkumavaraAlku, int liikkumavaraLoppu, Patka edeltavaPatka, int sarakenro) {
        super(pituus, liikkumavaraAlku, liikkumavaraLoppu, edeltavaPatka);
        this.sarakenro = sarakenro;
    }

    @Override
    public int[] koordinaatit(int indeksi) {
        int[] xy = new int[2];

        xy[0] = indeksi;
        xy[1] = sarakenro;

        return xy;
    }
    
    @Override
    public String patkanTyyppi() {
        return "Ppatka";
    }
    
    @Override
    public String toString() {
        return "S" +  super.toString();
    }

}
