DROP TABLE IF EXISTS town;
CREATE TABLE town(
town_id INT AUTO_INCREMENT NOT NULL,
silver INT NOT NULL,
iron INT NOT NULL,
wood INT NOT NULL,
spearman INT NOT NULL,
townhall INT NOT NULL,
barrack INT NOT NULL,
raidinghut INT NOT NULL,
woodcutter INT NOT NULL,
ironmine INT NOT NULL,
farm INT NOT NULL,
x INT NOT NULL,
y INT NOT NULL,
townname varchar(30) NOT NULL,
user_id INT NOT NULL,
PRIMARY KEY (town_id),
FOREIGN KEY (user_id) references user(user_id)
);

DROP TABLE IF EXISTS attack;
CREATE TABLE attack(
attack_id INT NOT NULL,
attackerTown INT NOT NULL,
defenderTown INT NOT NULL,
eta INT NOT NULL,
PRIMARY KEY (attack_id),
FOREIGN KEY (attackerTown) references town(town_id),
FOREIGN KEY (defenderTown) references town(town_id)
);


INSERT INTO town(town_id, silver, spearman, townhall, barrack, raidinghut, x, y, townname, user_id)
VALUES (DEFAULT, 100,5,1,0,1,10,10,'Kopervik',3);