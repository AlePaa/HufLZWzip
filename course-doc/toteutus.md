# Tiralabra

Huffman-koodaus

Aleksi Paavola

## Toteutusdokumentti

### Johdanto

Projektin aiheena oli toteuttaa tiedoston pakkaus ja purku käyttäen
Huffman-koodausta, sekä sen käyttämät tietorakenteet.

### Käytetyt teknologiat

* Java 8
* Apache Maven
* Cobertura
* JavaDoc
* Markdown
* Git versionallintaan

### Ohjelman rakenne

* 'com.alpa.huffman' sisältää ohjelman pääluokan ja diagnostiikan.
* 'com.alpa.huffman.coding' sisältää algoritmin varsinaisen toteutuksen.
* 'com.alpa.huffman.io' sisältää tiedostoon kirjoitus -ja lukutoiminnot sekä niiden apuluokat.
* 'com.alpa.util' sisältää algoritmin käyttämien tietorakenteiden toteutukset.

### Pakkaus

0. Käydään syötetiedosto läpi kerran ja lasketaan sen symbolien esiintymiskerrat O(n)

1. Luodaan Huffman-koodipuu:
 1. Luodaan lehti jokaista symbolia kohden ja lisätään se jonoon
 2. Kun jonon koko on suurempi kuin 1
  1. Poista kaksi kevyintä solmua puusta
  2. Luo uusi solmu jolla nämä kaksi solmua lapsina
  3. Lisää uusi solmu jonoon
 3. Jäljellejäävä solmu on juuri
Aikavaativuus tälle O(n log n), missä kohdan 1 vaativuus O(n) ja 2 O(log n)

2. Luodaan kanooninen koodaus Huffman-puusta:
 1. Jos solmu on oksa:
  1. Kohta 1 vasemmalle lapselle
  2. Kohta 2 oikealle lapselle
 2. Syöte on lehti
  1. Kirjoita solmun syvyys muistiin koodin pituutena

3. Luodaan koodipuu kanoonisesta koodauksesta O(n log n)

4. Käydään läpi syötetiedostoa ja kirjoitetaan koodipuun mukaiset koodaukset
jokaiselle symbolille (n log n)


### Purku

0. Luodaan kanooninen koodaus lukemalla se tiedostosta (n log n)
1. Muunnetaan se koodipuuksi (n log n)
2. Käydään läpi syötetiedostoa ja kirjoitetaan koodipuun mukaiset dekoodaukset
jokaiselle symbolille (n log n)


### Tietorakenteet

### ArrayList

Geneerinen, dynaamisesti kasvava taulukko.

### PriorityQueue

Geneerinen prioriteettijono.
Minimikekona toteutettu, operaatioiden nopeus keskimäärin O(log n).

### Paranneltavaa

* HuffmanCoderin pakkaus- ja purkuominaisuudet voisi mahdollisesti siirtää omiin luokkiinsa.

* Huffman-puun luominen FrequencyTable:n buildCodeTree-metodissa voidaan suorittaa ajassa O(n)
käyttämällä kahta prioriteettijonoa.

* Automaattisen testauksen kattavuuden parantaminen mockaamalla käsiteltäviä tiedostoja.
