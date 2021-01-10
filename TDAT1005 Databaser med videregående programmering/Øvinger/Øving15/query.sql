-- alle bedrifter

SELECT * FROM bedrift;

-- alle oppdrag pluss bedrift info

SELECT o.*, b.telefon, b.epost FROM oppdrag o 
JOIN bedrift b ON b.bedrift_id = o.bedrift_id;

-- alle kandidater med kvalifikasjoner

SELECT * FROM kandidat k
JOIN kvalkand kk USING(kandidat_id)
JOIN kvalifikasjon kkk ON kk.kvalifikasjon_id = kkk.kvalifikasjon_id;

-- kandidater med og uten kvalifikasjoner

SELECT * FROM kandidat k
LEFT JOIN kvalkand kk ON k.kandidat_id = kk.kandidat_id
LEFT JOIN kvalifikasjon kkk ON kk.kvalifikasjon_id = kkk.kvalifikasjon_id;

-- jobb historie tli kandidat

SELECT * FROM kandidat k
JOIN jobbhistorikk j ON k.kandidat_id = j.kandidat_id
JOIN oppdrag o ON o.oppdrag_id = j.oppdrag_id
WHERE j.kandidat_id = 1;