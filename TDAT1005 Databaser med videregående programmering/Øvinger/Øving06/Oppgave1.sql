-- 1 a
-- seleksjon
SELECT * FROM bok;
-- projeksjon
SELECT tittel FROM bok;

-- 1 b
-- produkt
-- Gir alle mulighe kombinasjoner
SELECT * FROM bok, forlag;

-- 1 c

-- likhetsforening
SELECT * FROM forlag, bok 
WHERE bok.forlag_id = forlag.forlag_id;

-- natural join
-- SELECT * FROM forlag NATURAL JOIN bok;
SELECT * FROM forlag 
JOIN bok 
ON(forlag.forlag_id = bok.forlag_id);

-- 1 d
-- Alle personer involvert
-- Forfatter, fornavn og etternavn
-- Konsulent, fornavn og etternavn

SELECT fornavn, etternavn
FROM forfatter
UNION
SELECT fornavn, etternavn
FROM konsulent;