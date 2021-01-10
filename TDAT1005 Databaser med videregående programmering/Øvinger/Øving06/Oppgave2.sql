-- 2 a
-- projeksjon
SELECT DISTINCT forlag_navn 
FROM forlag;

-- 2 b
SELECT * FROM forlag 
WHERE forlag_id 
NOT IN (SELECT forlag_id 
        FROM bok);

-- 2 c
SELECT fornavn, etternavn 
FROM forfatter 
WHERE (fode_aar = 1948);

-- 2 d
SELECT forlag_navn, adresse 
FROM forlag 
WHERE (SELECT forlag_id 
       FROM bok 
       WHERE(tittel = "Generation X")) = forlag_id;

-- 2 e
SELECT tittel 
FROM bok 
NATURAL JOIN bok_forfatter 
WHERE forfatter_id = (SELECT forfatter_id 
                      FROM forfatter 
                      WHERE etternavn = "Hamsun");

-- 2 f
SELECT bok.tittel, bok.utgitt_aar, forlag.forlag_navn, forlag.adresse, forlag.telefon 
FROM bok 
RIGHT OUTER JOIN forlag
ON (bok.forlag_id = forlag.forlag_id);