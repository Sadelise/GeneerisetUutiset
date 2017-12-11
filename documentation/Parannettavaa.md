# Ohjelman kaipaamia parannuksia ja ongelmia

### Pahat ongelmat
- Login uudelleenohjaa css tiedostoon tai bootstrapin l‰hdetiedostoon riippuen j‰rjestyksest‰ html-sivulla. H‰mment‰v‰ virhe, jonka syy olisi eritt‰in hauska tiet‰‰.
- Ilmeisesti aikaisempaan liittyv‰ virhe, jossa css-tiedosto ei ole k‰ytˆss‰ ennen kuin k‰ytt‰j‰ kirjautuu sis‰‰n. 
- Liian pitk‰t tekstit aiheuttavat virheen uutista julkaistaessa/editoidessa. (Tekstin pituus on s‰‰detty Article-luokassa, mutta jotain muutakin t‰ytyisi tehd‰.)
- Virheit‰ ei k‰sitell‰.

### Puutteet toiminnallisuudessa
- Suosituimmat-listaus listaa nyt kaikkien klikkausten perusteella, eik‰ vain tietyn ajanjakson perusteella.
- Sivutusta ei ole ollenkaan, eli suurilla tietom‰‰rill‰ tulee ongelmia.
- Deletoinnissa voisi olla varmistus ennen kuin artikkeli oikeasti poistetaan.
- Kirjoittajia saa vain lis‰tty‰, ei poistettua. 
- K‰ytt‰jien hallinta puuttuu.

### L‰hdekoodin puutteet
- Kategorian perusteella hakeminen on toteutettu tehottomasti. ("Pikaratkaisu", koska tuotantoversiossa oli jokin ongelma aikaisemman, paremman toteutuksen kanssa.)
- Deletoinnin integraatiotestaaminen ei onnistu "StackOverFlow"-errorin takia. Itse deletointi-toiminto kuitenkin toimii n‰enn‰isen hyvin.

### Tyyliongelmat
- Uutisen julkaisup‰iv‰m‰‰r‰ voisi olla formatoitu k‰ytt‰j‰yst‰v‰llisemp‰‰n muotoon. 
- Ulkoasu voisi n‰ytt‰‰ houkuttelevammalta. 
- K‰yttˆliittym‰ voisi olla intuitiivisempi, etenkin hallintapaneelin ja editoinnin osalta.
