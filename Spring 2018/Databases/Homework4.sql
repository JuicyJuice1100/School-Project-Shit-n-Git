CREATE TABLE Pizzeria (
    pizzeria VARCHAR(20) NOT NULL
  	,addr VARCHAR(50) NOT NULL
 	,CONSTRAINT PRIMARY KEY (pizzeria)
);

INSERT INTO Pizzeria VALUES ("Joe's", "123 Joe's St");
INSERT INTO Pizzeria VALUES ("Sal's", "321 Sal's St");

CREATE TABLE Pizza (
	pizza VARCHAR(20) NOT NULL
    ,category VARCHAR(20) NOT NULL
    ,CONSTRAINT PRIMARY KEY (pizza)
);

INSERT INTO Pizza VALUES ("Hawaiian", "Best");
INSERT INTO Pizza VALUES ("Anchovies", "Fish");

CREATE TABLE Patron (
	patron VARCHAR(20) NOT NULL
    ,age INT NOT NULL
    ,CONSTRAINT PRIMARY KEY (patron)
);

INSERT INTO Patron VALUES ("Dude", 34);
INSERT INTO Patron VALUES ("Dudette", 27);

CREATE TABLE Serves (
	pizzeria VARCHAR(20) NOT NULL
    ,pizza VARCHAR(20) NOT NULL
    ,price DECIMAL(5, 2) DEFAULT 0
    ,CONSTRAINT PRIMARY KEY (pizzeria, pizza)
    ,CONSTRAINT FOREIGN KEY (pizzeria) REFERENCES Pizzeria (pizzeria) 
    	ON DELETE NO ACTION ON UPDATE CASCADE
    ,CONSTRAINT FOREIGN KEY (pizza) REFERENCES Pizza (pizza)
  		ON DELETE NO ACTION ON UPDATE CASCADE  		
);

INSERT INTO Serves VALUES ("Joe's", "Hawaiian", 3.99);
INSERT INTO Serves VALUES ("Sal's", "Anchovies", 2.99);

CREATE TABLE Frequents (
    patron VARCHAR(20) NOT NULL
    ,pizzeria VARCHAR(20) NOT NULL
    ,CONSTRAINT PRIMARY KEY(patron, pizzeria)
    ,CONSTRAINT FOREIGN KEY (patron) REFERENCES Patron (patron)
    	ON DELETE NO ACTION ON UPDATE CASCADE
    ,CONSTRAINT FOREIGN KEY (pizzeria) REFERENCES Pizzeria (pizzeria)
    	ON DELETE NO ACTION ON UPDATE CASCADE
);

INSERT INTO Frequents VALUES ("Dude", "Joe's");
INSERT INTO Frequents VALUES ("Dudette", "Joe's");
    
CREATE TABLE Likes (
    patron VARCHAR(20) NOT NULL
    ,pizza VARCHAR(20) NOT NULL
    ,CONSTRAINT PRIMARY KEY (patron, pizza)
    ,CONSTRAINT FOREIGN KEY (patron) REFERENCES Patron (patron)
        ON DELETE NO ACTION ON UPDATE CASCADE
    ,CONSTRAINT FOREIGN KEY (pizza) REFERENCES Pizza (pizza)
        ON DELETE NO ACTION ON UPDATE CASCADE
);

INSERT INTO Likes VALUES ("Dude", "Anchovies");
INSERT INTO Likes VALUES ("Dude", "Hawaiian");

