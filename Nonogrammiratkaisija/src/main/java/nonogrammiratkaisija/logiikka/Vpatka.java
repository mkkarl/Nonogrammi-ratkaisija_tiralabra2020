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
public class Vpatka extends Patka {
    private int rivinro;

    public Vpatka(int pituus, int liikkumavaraAlku, int liikkumavaraLoppu, Patka edeltavaPatka, int rivinro) {
        super(pituus, liikkumavaraAlku, liikkumavaraLoppu, edeltavaPatka);
        this.rivinro = rivinro;
    }

    @Override
    public int[] koordinaatit(int indeksi) {
        int[] xy = new int[2];

        xy[0] = rivinro;
        xy[1] = indeksi;

        return xy;
    }
    
    @Override
    public String patkanTyyppi() {
        return "Vpatka";
    }
    
    @Override
    public String toString() {
        return "R" +  super.toString();
    }
    
}
