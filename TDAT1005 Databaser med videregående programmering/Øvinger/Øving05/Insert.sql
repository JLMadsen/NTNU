INSERT INTO housingComplex VALUES (null, "Sorgenfri Boretslag", "her 123", 1970);
INSERT INTO housingComplex VALUES (null, "Jegvetikke Boretslag", "der 123", 1980);

INSERT INTO houses VALUES (null, 1, 2, 1);
INSERT INTO houses VALUES (null, 1, 2, 1);
INSERT INTO houses VALUES (null, 1, 4, 2);
INSERT INTO houses VALUES (null, 2, 2, 1);

INSERT INTO members VALUES (null, "Per", "Hansen");
INSERT INTO members VALUES (null, "Ole", "Nilsen");
INSERT INTO members VALUES (null, "Jon", "Solberg");

INSERT INTO apartments VALUES (null, 1002, 1, 1, 3, 120);
INSERT INTO apartments VALUES (null, 1002, 2, 2, 4, 130);

-- Insert setning som bryter med entigritet og relasjon, det vil bli konflikt pga flere like primærnøkkler
INSERT INTO houses VALUES(1, 2, 2, 2);
