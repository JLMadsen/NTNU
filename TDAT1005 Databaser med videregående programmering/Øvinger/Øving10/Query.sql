-- 1 Finn alle borettslag etablert i årene 1975-1985.

SELECT * FROM borettslag WHERE etabl_aar < 1985 AND etabl_aar > 1975;

-- 2 Skriv ut en liste over andelseiere. sorter etter ansiennnitet

SELECT fornavn, etternavn, ansiennitet FROM andelseier ORDER BY ansiennitet;

-- 3 I hvilket år ble det eldste borettslaget etablert?

SELECT MIN(etabl_aar) FROM borettslag;

-- 4 Finn adressene til alle bygninger som inneholder leiligheter med minst tre rom.

SELECT bygn_adr FROM bygning b
RIGHT JOIN leilighet l ON b.bygn_id = l.bygn_id
WHERE ant_rom > 3;

-- 5 Finn antall bygninger i borettslaget "Tertitten".

SELECT count(bolag_navn) FROM bygning WHERE bolag_navn = 'Tertitten';

-- 6 Lag en liste som viser antall bygninger i hvert enkelt borettslag. 
-- Listen skal være sortert på borettslagsnavn. Husk at det kan finnes borettslag uten bygninger - de skal også med.

SELECT borettslag.bolag_navn, COUNT(bygn_id) FROM bygning 
RIGHT JOIN borettslag ON bygning.bolag_navn = borettslag.bolag_navn
GROUP BY bolag_navn;

-- 7 Finn antall leiligheter i borettslaget "Tertitten".

SELECT count(leil_nr) FROM leilighet
RIGHT JOIN bygning ON leilighet.bygn_id = bygning.bygn_id
WHERE bolag_navn = 'Tertitten';

-- 8 Hvor høyt kan du bo i borettslaget "Tertitten"?

SELECT MAX(etasje) FROM leilighet
RIGHT JOIN bygning ON leilighet.bygn_id = bygning.bygn_id
WHERE bolag_navn = 'Tertitten';

-- 9 Finn navn og nummer til andelseiere som ikke har leilighet.

SELECT fornavn, etternavn, telefon FROM andelseier
LEFT JOIN leilighet ON andelseier.and_eier_nr = leilighet.and_eier_nr
WHERE leilighet.and_eier_nr IS NULL;

-- 10 Finn antall andelseiere pr borettslag, sortert etter antallet. 
-- Husk at det kan finnes borettslag uten andelseiere - de skal også med.

SELECT borettslag.bolag_navn, count(andelseier.and_eier_nr) FROM andelseier
RIGHT JOIN borettslag ON borettslag.bolag_navn = andelseier.bolag_navn
GROUP BY borettslag.bolag_navn;

-- 11 Skriv ut en liste over alle andelseiere. For de som har leilighet, skal leilighetsnummeret skrives ut.

SELECT andelseier.*, leilighet.leil_nr FROM andelseier
LEFT JOIN leilighet ON andelseier.and_eier_nr = leilighet.and_eier_nr;

-- 12 Hvilke borettslag har leiligheter med eksakt 4 rom?

-- for å teste spørring
INSERT INTO leilighet VALUES (5, 4, 9000, 2, 3, 5);

SELECT * FROM borettslag
RIGHT JOIN bygning ON borettslag.bolag_navn = bygning.bolag_navn
RIGHT JOIN leilighet ON bygning.bygn_id = leilighet.bygn_id
WHERE leilighet.ant_rom = 4;

DELETE FROM leilighet WHERE leil_nr = 5;

-- 13 Skriv ut en liste over antall andelseiere pr postnr og poststed, 
-- begrenset til de som bor i leiligheter tilknyttet et borettslag. 
-- Husk at postnummeret til disse er postnummeret til bygningen de bor i, og ikke postnummeret til borettslaget. 
-- Du trenger ikke ta med poststeder med 0 andelseiere

SELECT poststed.postnr, poststed.poststed, count(andelseier.and_eier_nr) FROM poststed
RIGHT JOIN bygning ON bygning.postnr = poststed.postnr
RIGHT JOIN leilighet ON leilighet.bygn_id = bygning.bygn_id
RIGHT JOIN andelseier ON leilighet.and_eier_nr = andelseier.and_eier_nr
GROUP BY poststed.postnr;