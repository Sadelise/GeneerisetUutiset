# Käyttötapaukset

### Käyttäjä kirjautuu sisään
_Käyttäjä:_ käyttäjä jolla on tunnukset palveluun

_Tavoite:_ lukea/julkaista/editoida uutisia

_Esiehto:_ Käyttäjällä on tunnukset palveluun

_Jälkiehto:_ Käyttäjä on kirjattu sisään ja näkee roolinsa mukaisen näkymän

_Käyttötapauksen kulku:_
1. Käyttäjä siirtyy sivulle
2. Käyttäjä klikkaa login-painiketta navigaatiopalkissa
3. Käyttäjä täyttää kirjautumistietonsa lomakkeeseen ja painaa login

### Käyttäjä kirjautuu ulos
_Käyttäjä:_ käyttäjä jolla on tunnukset palveluun

_Tavoite:_ Kirjautua ulos

_Esiehto:_ Käyttäjä on kirjautunut sisään

_Jälkiehto:_ Käyttäjä on kirjattu ulos palvelusta

_Käyttötapauksen kulku:_
2. Käyttäjä klikkaa logout-painiketta navigaatiopalkissa
3. Käyttäjä on kirjattu ulos

### Käyttäjä selaa uutisia
_Käyttäjä:_ kuka tahansa

_Tavoite:_ lukea uutisia

_Laukaisija:_ Halu tietää maailman menosta

_Käyttötapauksen kulku:_
1. Käyttäjä siirtyy sivulle
2. Käyttäjä lukee uusimpia otsikoita etusivulta
2b. Käyttäjä klikkaa kategorian auki navigaatiopalkista ja lukee otsikoita auenneelta sivulta.
3. Käyttäjä klikkaa uutisen auki
4. Käyttäjä lukee uutisen sisällön


### Käyttäjä lisää uutisen
_Käyttäjä:_ Admin-oikeuksilla varustettu käyttäjä

_Tavoite:_ julkaista uutinen

_Laukaisija:_ Halu lisätä tietoisuutta maailman menosta

_Esiehto:_ Käyttäjä on kirjautunut sisään

_Jälkiehto:_ Uutinen näkyy kaikille käyttäjille

_Käyttötapauksen kulku:_
1. Käyttäjä siirtyy sivulle
2. Käyttäjä klikkaa hallintapaneelin auki navigaatiopalkista
3. Käyttäjä syöttää uuden uutisen tiedot avautuneeseen lomakkeeseen
4. Käyttäjä painaa "julkaise"-nappulaa

### Käyttäjä muokkaa uutista
_Käyttäjä:_ Admin-oikeuksilla varustettu käyttäjä

_Tavoite:_ muokata uutista

_Laukaisija:_ tarve korjata uutisten sisältöä

_Esiehto:_ Käyttäjä on kirjautunut sisään

_Jälkiehto:_ Muokatun uutisen sisältö on muuttunut

_Käyttötapauksen kulku:_

1. Käyttäjä siirtyy sivulle
2. Käyttäjä klikkaa hallintapaneelin auki navigaatiopalkista
3. Käyttäjä selaa uutisia ja klikkaa sitä uutista, jota haluaa muokata
4. Käyttäjä korjaa syötteet lomakkeessa, johon on automaattisesti tuotu halutun uutisen tiedot 
5. Käyttäjä painaa "muokkaa"-nappulaa
