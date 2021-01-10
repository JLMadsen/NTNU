DELETE FROM kandidat;
DELETE FROM bedrift;
DELETE FROM kvalifikasjon;
DELETE FROM kvalkand;
DELETE FROM oppdrag;
DELETE FROM jobbhistorikk;

INSERT INTO kandidat VALUES (null, 'Marius', 'Torbjornsen', 666, 'gmail@gmail.com');
INSERT INTO kandidat VALUES (null, 'Birk', 'Stoveland', 420, 'gmail@hotmail.com');
INSERT INTO kandidat VALUES (null, 'jan', 'huskerikkeetternavn', 911, 'gmail@outlook.com');
INSERT INTO kandidat values (null, 'jakob', 'madsen', 4324, 'hotmail@outlook.com');

INSERT INTO bedrift VALUES (null, 'ABB', 481342, 'abb@mail.com');
INSERT INTO bedrift VALUES (null, 'Samson', 50541, 'samson@mail.com');

INSERT INTO kvalifikasjon VALUES (null, 'Nerd');
INSERT INTO kvalifikasjon VALUES (null, 'Super nerd');

INSERT INTO kvalkand VALUES (1, 1);
INSERT INTO kvalkand VALUES (1, 2);
INSERT INTO kvalkand VALUES (2, 1);
INSERT INTO kvalkand VALUES (3, 2);

INSERT INTO oppdrag VALUES (null, 1, null, 1, 'ABB', DATE('2003-10-18'), DATE('2003-11-18'));
INSERT INTO oppdrag VALUES (null, 2, null, 2, 'Samson', DATE('2003-10-18'), DATE('2003-11-18'));
INSERT INTO oppdrag VALUES (null, 1, null, 1, 'ABB', DATE('2003-10-18'), DATE('2003-11-18'));

INSERT INTO jobbhistorikk VALUES (3, 1, DATE('2003-10-18'), DATE('2003-12-18'));











