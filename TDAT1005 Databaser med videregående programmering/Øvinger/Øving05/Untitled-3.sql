SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS housingComplex;
DROP TABLE IF EXISTS houses;
DROP TABLE IF EXISTS apartments;
DROP TABLE IF EXISTS members;

CREATE TABLE housingComplex(
    complex_id INTEGER AUTO_INCREMENT,
    PRIMARY KEY(complex_id),
    boretslag_name VARCHAR(30) NOT NULL,
    adress VARCHAR(30) NOT NULL,
    anno INTEGER NOT NULL
);

CREATE TABLE houses(
    house_id INTEGER AUTO_INCREMENT,
    PRIMARY KEY(house_id),
    complex_id INTEGER NOT NULL,
    FOREIGN KEY(complex_id) REFERENCES housingComplex(complex_id),
    numApartments INTEGER NOT NULL,
    numFloors INTEGER NOT NULL
);

CREATE TABLE apartments(
    apt_id INTEGER AUTO_INCREMENT,
    PRIMARY KEY(apt_id),
    apt_nr INTEGER NOT NULL,
    house_id INTEGER NOT NULL,
    FOREIGN KEY(house_id) REFERENCES houses(house_id),
    member_id INTEGER,
    FOREIGN KEY(member_id) REFERENCES members(member_id),
    numRooms INTEGER NOT NULL,
    squareFeet INTEGER NOT NULL
);

CREATE TABLE members(
    member_id INTEGER AUTO_INCREMENT,
    PRIMARY KEY(member_id),
    firstname VARCHAR(30) NOT NULL,  
    lastname VARCHAR(30) NOT NULL,
    share INTEGER NOT NULL
);
