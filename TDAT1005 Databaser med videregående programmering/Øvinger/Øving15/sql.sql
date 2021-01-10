DROP TABLE IF EXISTS jobbhistorikk;
DROP TABLE IF EXISTS oppdrag;
DROP TABLE IF EXISTS bedrift;
DROP TABLE IF EXISTS kvalkand;
DROP TABLE IF EXISTS kvalifikasjon;
DROP TABLE IF EXISTS kandidat;

CREATE TABLE kandidat(
    kandidat_id INTEGER AUTO_INCREMENT,
    PRIMARY KEY(kandidat_id),
    fornavn VARCHAR(30) NOT NULL,
    etternavn VARCHAR(30) NOT NULL,
    telefon INTEGER NOT NULL,
    epost VARCHAR(30)
);

CREATE TABLE bedrift(
    bedrift_id INTEGER AUTO_INCREMENT,
    PRIMARY KEY(bedrift_id),
    navn VARCHAR(30) NOT NULL,
    telefon INTEGER NOT NULL,
    epost VARCHAR(30) NOT NULL
);

CREATE TABLE kvalifikasjon(
    kvalifikasjon_id INTEGER AUTO_INCREMENT,
    PRIMARY KEY(kvalifikasjon_id),
    beskrivelse VARCHAR(100)
);

CREATE TABLE kvalkand(
    kandidat_id INTEGER NOT NULL,
    FOREIGN KEY(kandidat_id) REFERENCES kandidat(kandidat_id),
    kvalifikasjon_id INTEGER NOT NULL,
    FOREIGN KEY(kvalifikasjon_id) REFERENCES kvalifikasjon(kvalifikasjon_id),
    PRIMARY KEY(kandidat_id, kvalifikasjon_id)
);

CREATE TABLE oppdrag(
    oppdrag_id INTEGER AUTO_INCREMENT,
    PRIMARY KEY(oppdrag_id),
    bedrift_id INTEGER NOT NULL,
    FOREIGN KEY(bedrift_id) REFERENCES bedrift(bedrift_id),
    kandidat_id INTEGER,
    FOREIGN KEY(kandidat_id) REFERENCES kandidat(kandidat_id),
    kvalifikasjon_id INTEGER NOT NULL,
    FOREIGN KEY(kvalifikasjon_id) REFERENCES kvalifikasjon(kvalifikasjon_id),
    bedriftnavn VARCHAR(30) NOT NULL,
    datostart DATE NOT NULL,
    datoslutt DATE NOT NULL
);

CREATE TABLE jobbhistorikk(
    oppdrag_id INTEGER NOT NULL,
    PRIMARY KEY(oppdrag_id),
    FOREIGN KEY(oppdrag_id) REFERENCES oppdrag(oppdrag_id),
    kandidat_id INTEGER NOT NULL,
    FOREIGN KEY(kandidat_id) REFERENCES kandidat(kandidat_id),
    datostart DATE NOT NULL,
    datoslutt DATE NOT NULL
);