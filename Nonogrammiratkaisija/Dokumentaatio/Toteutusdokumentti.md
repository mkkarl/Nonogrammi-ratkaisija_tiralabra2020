# Toteutusdokumentti

## Luokat

`Ruutu`
- tila (musta / tyhjä / ei tietoa)
- mahdolliset pätkät

*Luokan* `Ruutu`* avulla luodaan taulukko* `Ruutu[][] ruudukko`

`Patka`
- pätkän pituus
- liikkumavara
- varmat ruudut
- edeltävä pätkä
- seuraava pätkä
- valmis (T/F)
- odottaa käsittelyä (T/F)

*Luokat* `Ppatka` *ja* `Vpatka` *perivät luokan* `Patka`.

## Algoritmin toiminta

1. Merkitse riveittäin ja sarakkeittain pätkien liikkumavarat sekä varmat ruudut.
2. Käy pätkiä läpi määritellyssä järjestyksessä.
  - Jos ruutuun tehdään muutos, merkitse kyseisen pätkän kanssa samansuuntaiset vierekkäiset  pätkät sekä risteävän suuntaisista pätkistä ne, joiden liikkumavaraan ruutu kuuluu, odottamaan käsittelyä.

### Pätkän käsittely

1. Onko liikkumavaraan ilmestynyt tyhjiä ruutuja? Lyhennä liikkumavaraa. Päivitä varmat ruudut.
2. Onko varmojen vieressä uusia mustia? Merkitse varmoiksi ja lyhennä liikkumavaraa.
3. Onko liikkumavarassa muita mustia, jotka eivät kuulu muiden samansuuntaisetn pätkien liikkumavaraan? Merkitse varmoiksi, täytä aukot ja lyhennä liikkumavaraa.
4. Onko liikkumavara-alueella liikaa vierekkäisiä mustia? Vertaa muihin samansuuntaisiin pätkiin ja lyhennä vaikutusaluetta.
5. Onko varmoja mustia tarpeeksi? Lisää päätyihin tyhjät, samoin vierekkäiseen valmiiseen pätkään tai reunaan asti. Merkitse pätkä valmiiksi.

### Pätkien käsittelyjärjestys

Kaksi jonoa, yleinen ja priorisoitu, toteutus ainakin aluksi käyttäen Javan `ArrayDequeue`-luokkaa.
Kaikki pätkät laitetaan aluksi yleiseen jonoon arvolla odottaa käsittelyä: true.
Priorisoitu jono on aluksi tyhjä, mutta sinne lisätään pätkiä muiden pätkien käsittelyn perusteella (ks. Pätkän käsittely).

Ensisijaisesti käsitellään priorisoitua jonoa, mutta jos se on tyhjä, käsitellään yleistä jonoa.

Priorisoidusta jonosta otetaan käsittelyyn jonon ensimmäinen.
Jos odottaa käsittelyä: false, niin pätkä vain poistetaan jonosta ja siirrytään seuraavaan (pätkä on ollut jonossa jo aiemmin eikä uutta käsittelytarvetta ole tullut).
Jos sen sijaan odottaa käsittelyä: true, niin pätkä käsitellään edellä kuvatusti ja poistetaan sitten jonosta arvolla odottaa käsittelyä: false.

Jos priorisoitu jono on tyhjä, otetaan käsittelyyn yleisen jonon ensimmäinen pätkä.
- Jos pätkä on valmis, poistetaan se kokonaan jonosta.
- Jos odottaa käsittelyä: false, siirretään pätkä jonon viimeiseksi.
- Jos odottaa käsittelyä: true, suoritetaan pätkän käsittely.
Jos pätkä valmistuu, siirretään se pois jonosta, muutoin siirretään jonon viimeiseksi arvlla odottaa käsittelyä: false.

Kun molemmat jonot ovat tyhjiä, nonogrammi on ratkaistu.

*(Onko mahdollista, että yleinen jono jää looppaamaan kaikkien pätkien ollessa tilassa odottaa käsittelyä: false?
Tarkoittaako silloin, että nonogrammi ei ole ratkaistavissa?)*

