 -- rom hvor det er plass til 50

 SELECT romid, romnavn FROM rom
 JOIN romtype USING(typeid)
 WHERE ant_personer > 50;

 -- hvor mange reservasjoner per rom

 SELECT romid, COUNT(resid) FROM rom 
 JOIN reservasjon USING(romid)
 GROUP BY romid;

 -- finne rom med ingen reservasjoner

SELECT romid FROM rom 
WHERE romid NOT IN (SELECT DISTINCT romid FROM reservasjon);

-- finne bygg med flest rom

SELECT bygnnavn FROM bygning
JOIN rom USING(bygnnavn)
GROUP BY bygnnavn
HAVING COUNT(romid)
ORDER BY COUNT(romid) DESC
LIMIT 1;

-- hvor mange rom per bygg

SELECT bygnnavn, COUNT(romid) FROM bygning
JOIN rom USING(bygnnavn)
GROUP BY bygnnavn;

-- hvor mange rom av type

SELECT beskrivelse, COUNT(romid) FROM romtype, rom
WHERE romtype.typeid = rom.typeid
GROUP BY beskrivelse;

-- 