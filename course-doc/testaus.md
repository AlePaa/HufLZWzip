# Tiralabra

Huffman-koodaus

Aleksi Paavola

### Testaus

Toteutus on testattu käyttäen automatisoitua testausta sekä manuaalisesti
pakkaamalla ja purkamalla tiedostoja.

JUnit-testit löytyvät _test_ kansion alta lähdekoodin juuresta, ja ne
voidaan suorittaa projektin juurikansiosta komennolla:

	$ mvn test

Koodikattavuusdokumentaation saa luotua kansioon _target/site/cobertura/_ komennolla:

	$ mvn cobertura:cobertura

Testauksessa käytettiin pääosin tekstitiedostoja määrittämään pakkauksen tehokkuutta.
Esimerkkeinä käytetyistä tiedostoista:
* 355 000 englannin kielen sanaa [https://github.com/dwyl/english-words]
* Kalevala .txt-formaatissa [https://trac.cc.jyu.fi/projects/ohj1/export/584/esimerkit/2012s/luennot/luento21/kalevala.txt]

Toteutuksen on testattu pystyvän käsittelemään binääritiedostoja.

Käsin tehdyn testauksen perusteella suurten ja keskisuurten tekstitiedostojen
pakkaussuhde on keskimäärin luokkaa 0.5-0.6.

Koska testilaitteella 562394 tavun kokoisen tiedoston pakkaamiseen
kului keskimäärin 260 millisekuntia pakkaussuhteella 0.56 ja 3712689 tavun tiedoston
pakkaamiseen keskimäärin 860 millisekuntia pakkaussuhteella 0.53,
On kohtuullista olettaa algoritmin aikavaativuuden olevan suunniteltu O(n log n)
