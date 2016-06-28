# Käyttöohjeet

### Lähdekoodin hankinta

Lähdekoodin lataaminen Gitin avulla:

	$ git clone git://github.com/AlePaa/Huflzwzip AlePaa-Huflzwzip

Lähdekoodi käännetään antamalla projektin juurikansiossa komento:

	$ mvn install

Tämä luo tiedoston _target/Huf-1.0.jar_,
joka voidaan ajaa komennolla 'java-jar <parametrit>'.

Testauksen koodikattavuusdokumentaatio luodaan komennolla:

	$ mvn cobertura:cobertura

Jonka jälkeen se on selaimella luettavissa kansiossa _target/site/cobertura_

### Käyttö

Ohjelman pakkaus -ja purkutoimintoja käytetään seuraavasti

	Pakkausta varten käytetään komentoa:
	$ java -jar Huf-1.0.jar + <syötetiedosto> <ulostulotiedosto>

	Purkua varten käytetään komentoa:
	$ java -jar Huf-1.0.jar - <syötetiedosto> <ulostulotiedosto>
