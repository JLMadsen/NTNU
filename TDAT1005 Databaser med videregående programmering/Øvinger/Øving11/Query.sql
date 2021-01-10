-- Oppgave 1
-- a List ut all informasjon om ordrer for leverandør nr 44.

SELECT * FROM ordredetalj
LEFT JOIN delinfo ON ordredetalj.delnr = delinfo.delnr
LEFT JOIN ordrehode ON ordrehode.ordrenr = ordredetalj.ordrenr
LEFT JOIN prisinfo ON ordredetalj.delnr = prisinfo.delnr AND ordrehode.levnr = prisinfo.levnr
WHERE ordrehode.levnr = 44;

-- b Finn navn og by ("LevBy") for leverandører som kan levere del nummer 1.

SELECT levinfo.navn, levinfo.levby FROM levinfo
RIGHT JOIN ordrehode ON ordrehode.levnr = levinfo.levnr
RIGHT JOIN ordredetalj ON ordredetalj.ordrenr = ordrehode.ordrenr
WHERE ordredetalj.delnr = 1;

-- c Finn nummer, navn og pris for den leverandør som kan levere del nummer 201 til billigst pris.

SELECT levinfo.levnr, levinfo.navn, pris FROM levinfo
RIGHT JOIN prisinfo ON prisinfo.levnr = levinfo.levnr
WHERE pris = (SELECT min(pris) FROM prisinfo WHERE delnr = 201);

-- d Lag fullstendig oversikt over ordre nr 16, med ordrenr, dato, delnr, beskrivelse, kvantum, (enhets-)pris og beregnet bel?p (=pris*kvantum).

SELECT ordrehode.ordrenr, ordrehode.dato, ordredetalj.delnr, delinfo.beskrivelse, ordredetalj.kvantum, prisinfo.pris, (kvantum*pris) FROM ordrehode
RIGHT JOIN ordredetalj ON ordrehode.ordrenr = ordredetalj.ordrenr
RIGHT JOIN delinfo ON ordredetalj.delnr = delinfo.delnr
RIGHT JOIN prisinfo ON ordredetalj.delnr = prisinfo.delnr AND ordrehode.levnr = prisinfo.levnr
WHERE ordrehode.ordrenr = 16;

-- e Finn delnummer og leverandørnummer for deler som har en pris som er høyere enn prisen for del med katalognr X7770.

SELECT delnr, levnr FROM prisinfo
WHERE pris > (SELECT pris FROM prisinfo WHERE katalognr = 'X7770');

-- f i, Lag en tabell med fylke og by

DROP TABLE IF EXISTS fylke;
CREATE TABLE fylke(
levby VARCHAR(30) NOT NULL,
fylke VARCHAR(30) NOT NULL,
CONSTRAINT fylke_pk PRIMARY KEY(levby));

INSERT INTO fylke('Oslo', 'Oslo');
INSERT INTO fylke('Ås', 'Østfold');
INSERT INTO fylke('Ål', 'Telemark');
INSERT INTO fylke('Trondheim', 'S-Trøndelag');

CREATE TABLE levinfo(
levnr   INTEGER, 
navn    VARCHAR(20) NOT NULL, 
adresse VARCHAR(20) NOT NULL, 
levby   VARCHAR(20) NOT NULL, 
postnr  INTEGER NOT NULL,
CONSTRAINT levinfo_pk PRIMARY KEY(levnr));

-- f ii

CREATE VIEW gammel_levinfo AS
SELECT * FROM levinfo
LEFT JOIN fylke ON levinfo.levby = fylke.levby;

-- g Anta at en vurderer å slette opplysningene om de leverandørene som ikke er representert i Prisinfo-tabellen.

-- DELETE FROM levinfo
-- WHERE levnr NOT IN (SELECT levnr FROM prisinfo);

SELECT levby FROM levinfo
WHERE levby NOT IN (SELECT DISTINCT levby FROM levinfo
                    RIGHT JOIN prisinfo ON levinfo.levnr = prisinfo.levnr);

-- h Finn leverandørnummer for den leverandør som kan levere ordre nr 18 til lavest totale beløp
-- ordre 18 : delnr 3 kvantum 2, delnr 4 kvantum 8

DROP VIEW v;
CREATE VIEW v 
AS SELECT ordrenr, levnr, count(p.delnr) as antall, sum(pris*kvantum) as beløp FROM prisinfo p 
JOIN ordredetalj od ON p.delnr  = od.delnr
WHERE ordrenr = 18
group by levnr;

SELECT * FROM v;

SELECT levnr, beløp FROM v 
WHERE beløp = (SELECT MIN(beløp) 
               FROM v
               WHERE antall = 2);

-- Oppgave 2
-- a

SELECT * FROM forlag WHERE left(telefon, 1) = 2
UNION
SELECT * FROM forlag WHERE left(telefon, 1) != 2
UNION
SELECT * FROM forlag WHERE telefon IS NULL;

-- SELECT * FROM forlag WHERE telefon LIKE '2%'

-- b gjennomsnitt

DROP VIEW age;
CREATE VIEW age 
AS SELECT (IFNULL(dod_aar, 2019)-fode_aar) AS alder
FROM forfatter 
WHERE fode_aar IS NOT NULL;

SELECT avg(alder) FROM age;

--  c hvor mange

SELECT count(alder) FROM age;