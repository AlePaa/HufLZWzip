Toteutetaan tiedon pakkaus -ja purkualgoritmit käyttäen Huffman-koodausta
ja Lempel-Ziv-Welchiä (LZW) ja näiden käyttämät tietorakenteet.

Kehityskielenä käytetään Javaa. Pääasiallisena projektin 
hallintatyökaluna toimii Maven. Testaus toteutetaan JUnitilla ja dokumentoidaan
Coberturalla.

Huffman-koodaus käyttää erityistä Huffman-puuta, prioriteettijonoa, pinoa ja dynaamista
taulukkoa.

LZW käyttä sanakirjaa, joka tullaan toteuttamaan hajautustauluna.

Molemmat algoritmit saavat syötteekseen mielivaltaisen tiedoston ja kirjoittavat uuteen tiedostoon
enkoodatussa muodossa olevan pakatun version kyseisestä tiedostosta.

Tavoiteltu aika- ja tilavaativuus molemmille algoritmeille on niille määritellyt O(n).
