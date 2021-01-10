<a href="../README.md">Tilbake</a>

# Brannmur

En brannmur er et filter mellom ulike nett.

## 2 Filosofier

- Max sikkerhet
    - Stenger for alt bortsett fra det vi bruker
    - for organisasjoner med stort sikkerhetsbehov
    - Drift bør være raske med å åpne for nye behov
- Max nytte
    - Åpent for alle, sperrer bare for problemer
    - Organisasjoner som er opptatt av nye ting
    - Bør være observante i forhold til nye problemer

# Pakkefilter

- Hver pakke sjekkes mot brannmurreglene
- Alle felt i ip, tcp, udp, icmp ... kan sjekkes
    - til og fra adresse
    - portnummer / protokoller
    - inn og utgående nettverkskort
    - tcp-flagg
- pakke kan kastes basert på innold

# Sperre

- uønsket protokoller
    - HTTP: 80, SMTP: 25 ...
- usikre tjenester
    - windows fildeling
- uønsket tjenester
    - facebook
    - spill
    - tvilsomt innhold

# Eksempler

- epost bare til/fra eposttjener
- sikker sone kan ikke kontakter utenfra, kun få svar
    - dns, udp, tcp
- stoppe umulige adresser
    - pakker utenfra med intern adresse
        - 127.x.x.x
        -  10.x.x.x
        - 192.168.x.x
- ugyldige pakker
    - SYN+FIN

# Pakkefilter med tilstand

- pakkefilter med oversikt over forbindelser
- tiden mellom pakker
    - kort tid, mulig dos angrep
    - lang tid, mulig ressurs angrep
- øker sekvens/ack-nummer som forventet
- pakker som ikke tilhører forbindelser kan stoppes

# NAT (Network Address Translation)

Flere maskiner på samme nett deler ip adresse.  
Brannmur fordeler pakker til riktig maskin.  
Umulig å kontakte utenfra maskiner.  
