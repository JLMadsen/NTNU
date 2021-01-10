<a href="../README.md">Tilbake</a>

# Spørsmål fra tidligere eksamener

Take me to [Nettverk](#Nettverk)  
Take me to [Programvare](#Programvare)  
Take me to [Krypto](#Krypto)  

# Nettverk <a name="Nettverk"></a>

<hr>

**Script**  
**Forklar hva dette scriptet gjør:**
```sh
#!/bin/sh
git pull
find . -name "*.$1" -exec rm {} \;
git add .
git commit -m "`whoami` ryddet opp i $1"
git push
```

_Shell scriptet henter nyeste versjon fra Git, finner alle filer av filtype lik første parameter, dermed sletter den disse. Etter det legger den til alle endringer med `git add .` så commiter den endringene med navnet på brukeren med meldingen "\<bruker\> ryddet opp i \<filtype\>". til slutt blir dette lastet opp på Git._
*
<hr>

**Script**  
**Forklar detaljert hva dette scriptet gjør:**
```bash
#!/bin/bash
find / -name $1.docx -exec cp {} {}.backup
echo `whoami` kopierte $1 > > script.log
```

_Bash scriptet finner alle docx filer med navn likt første parameter i root mappen og ned, deretter kopierer disse med ".backup" på slutten av filnavnet. På linje 3 vil den først kjøpe kommandoen `whoami` slik at den får navnet på brukeren. Deretter skriver den "\<bruker\> kopierte \<filnavn\>" til en fil som heter script.log som har samme plassering som scriptet._

<hr>

**Versjonskontroll**  
**Fortell om fordeler versjonskontrollsystemer har, sammenlignet med å bare ha en felles filtjener.**

_Versjonskontroll har er mye lettere enn en filtjener da man kun trenger å laste opp nye deler av filer. Man kan også se hvem som endrer hvilke linjer i en fil. Det er mulig å lage en kopi (branch) av hele prosjektet slik at man kan endre ting uten at det påvirker andre. Man kan også lett gå tilbake til en tidligere versjon, uten å måtte sette opp backup system slik man må på filtjener_

<hr>

**IDS / IPS**  
**Fortell hvordan et IPS virker.**

IPS, Intrusion Prevention System, skal forhindre at hackere kan utnytte et system. I motsetning til IDS som skal detektere og si ifra om noen bryter seg inn skal IPS stoppe de. IPS finnes i flere former, men oftest som brannmur hvor den blokkerer mistenksom data. IPS kan blokkere data basert på mønsteret, oppførselen eller policy.

Det finnes mange kjente mønster for hvordan virus og lignende oppfører seg når de prøver å nå et nettverk, et IPS system oppdateres hele tiden men ny virus informasjon. Man kan også sette et sett med regler som den skal følge, som for eksempel å stoppe pakker basert på IP eller protokoll.

<hr>

<hr>

<hr>

# Program <a name="Programvare"></a>

# Krypto <a name="Krypto"></a>