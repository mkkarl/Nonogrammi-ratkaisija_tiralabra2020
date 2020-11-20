/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nonogrammiratkaisija.ui;

import java.util.Scanner;
import nonogrammiratkaisija.logic.Logiikka;

/**
 *
 * @author mari
 */
public class UserInterface {

    private Scanner lukija;
    private Logiikka logiikka;

    public UserInterface() {
        this.lukija = new Scanner(System.in);
    }

    public void kaynnista() {
        System.out.println("Tervetuloa Nonogrammiratkaisijaan!");
        System.out.println("");

        while (true) {
            System.out.println("Komennot:\n");

            System.out.println("L - Luo nonogrammi");
            System.out.println("A - Anna luvut");
            System.out.println("T - Tulosta nonogrammi");
            System.out.println("R - Ratkaise nonogrammi");
            System.out.println("S - Lopeta");

            System.out.print("\nAnna komento: ");
            String komento = lukija.nextLine();
            System.out.println("");

            if (komento.equals("L")) {
                luodaanRuudukko();
            } else if (komento.equals("T")) {
                logiikka.tulostaRuudukko();
            } else if (komento.equals("A")) {
                annaLuvut();
            } else if (komento.equals("R")) {
                logiikka.ratkaise();
            } else if (komento.equals("S")) {
                break;
            } else {
                System.out.println("KOMENTO TUNTEMATON!");
            }

        }
    }

    private void luodaanRuudukko() {
        System.out.print("Anna korkeus: ");
        int korkeus = Integer.parseInt(lukija.nextLine());
        System.out.print("Anna leveys: ");
        int leveys = Integer.parseInt(lukija.nextLine());

        this.logiikka = new Logiikka(korkeus, leveys);
    }

    private void annaLuvut() {
        System.out.println("Anna luvut rivi ja sarake kerrallaam. Erottele luvut toisistaan välilyönnillä.");
        System.out.println("Jos haluat keskeyttää, syötä K");
        System.out.println("");

        int korkeus = logiikka.getKorkeus();
        int leveys = logiikka.getLeveys();

        String[] rivit = new String[korkeus];
        String[] sarakkeet = new String[leveys];

        for (int i = 0; i < korkeus; i++) {
            System.out.print("Rivi " + (i + 1) + ": ");
            String syote = lukija.nextLine();
            if (syote.equals("K")) {
                return;
            } else {
                rivit[i] = syote;
            }

        }

        logiikka.luoRivitSarakkeet(rivit, true);

        for (int i = 0; i < leveys; i++) {
            System.out.print("Sarake " + (i + 1) + ": ");
            String syote = lukija.nextLine();
            if (syote.equals("K")) {
                return;
            } else {
                sarakkeet[i] = syote;
            }

        }

        logiikka.luoRivitSarakkeet(sarakkeet, false);
    }

}
