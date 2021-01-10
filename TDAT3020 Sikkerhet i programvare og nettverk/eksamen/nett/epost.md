<a href="../README.md">Tilbake</a>

# Epost

# DNS tjener på tjener

- Revers oppslag på sender
    - har sender gyldig A-record
    - hvis ikke dropp sending
- Sjekk «HELO/EHLO hostname»
    - Fins A/CNAME for «hostname»?
    - Stemmer IP-adressen med forbindelsen
    - Hvis nei, avbryt forbindelsen
- For brevene, sjekk «MAIL FROM bruker@domene.no»
    - Fins MX-record for domene.no?
    - Hvis nei, avvis meldingen
- Kan risikere å blokkere dårlig epostsystem

# SPF-test på tjener (Sender Policy Framework)

Standard for å oppgi hvilke maskiner som har lov å sende epost for et domene.  

# SMTP med autentisering

Autentisering med brukernavn/passord.  
Ofte kryptert med TLS.  

# Epost baser angrep

- 1988: Første epost-orm, buffer overflow
- 1999: Første epostvirus
    - epostvirus bare mulig pga. veldig dårlig sikkerhet
- 2000: ILOVEYOU angrep millioner av maskiner på få timer.
    - mest ødeleggende noensinne
    - skrevet i vb, som outlook eksekverte uten videre

# Vampyr

Bruker opp spammeren sine ressurser.  
Laster ned innholdet millioner av ganger.  
Øker kostnaden for spamming.

