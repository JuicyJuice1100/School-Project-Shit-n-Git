DROP TABLE IF EXISTS Deposit;
DROP TABLE IF EXISTS Loan;
DROP TABLE IF EXISTS Branch;
DROP TABLE IF EXISTS Customer;

CREATE TABLE Branch (
  bname VARCHAR(20) NOT NULL,
  city VARCHAR(20),
  assets DECIMAL(12,4),
  PRIMARY KEY(bname)
);
    
CREATE TABLE Customer (
  cname VARCHAR(20) NOT NULL,
  addr VARCHAR(30),
  city VARCHAR(20),
  PRIMARY KEY(cname)
);  

CREATE TABLE Deposit (
  daccnum INT NOT NULL AUTO_INCREMENT,
  cname VARCHAR(20),
  bname VARCHAR(20),
  balance DECIMAL(12,4),
  PRIMARY KEY(daccnum),
  CONSTRAINT FOREIGN KEY (cname) REFERENCES Customer (cname) 
	ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (bname) REFERENCES Branch (bname) 
	ON DELETE NO ACTION ON UPDATE CASCADE  
);  

CREATE TABLE Loan (
  laccnum INT NOT NULL AUTO_INCREMENT,
  cname VARCHAR(20),
  bname VARCHAR(20),
  amount DECIMAL(12,4),
  PRIMARY KEY(laccnum),
  CONSTRAINT FOREIGN KEY (cname) REFERENCES Customer (cname) 
	ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (bname) REFERENCES Branch (bname) 
	ON DELETE NO ACTION ON UPDATE CASCADE  
);

INSERT INTO Branch (bname, city, assets) VALUES
	('OshkoshMain', 'Oshkosh', 100000),
	('Oshkosh20th', 'Oshkosh', 200000),
	('Neenah', 'Neenah', 400000),
	('Menasha', 'Menasha', 100000),
	('GrandChuteAppleton', 'Appleton', 500000),
	('CollegeAppleton', 'Appleton', 1000000);

INSERT INTO Customer (cname, addr, city) VALUES
	('JaneDoe', '123  main st', 'Oshkosh'),
	('FredFlintstone', 'Taylor Hall', 'Oshkosh'),
	('BillJones', '456 commercial st', 'Neenah'),
	('HenryFord', '789 new st', 'Fond Du Lac'),
	('ThelmaJones', '456 commercial st', 'Neenah');

INSERT INTO Deposit (daccnum, cname, bname, balance) VALUES
	(1, 'JaneDoe', 'OshkoshMain', 1000),
	(2, 'JaneDoe', 'Oshkosh20th', 2000),
	(3, 'FredFlintstone', 'OshkoshMain', 40000),
	(4, 'FredFlintstone', 'Neenah', 10000),
	(5, 'FredFlintstone', 'GrandChuteAppleton', 50000),
	(6, 'BillJones', 'CollegeAppleton', 10000);

INSERT INTO Loan (laccnum, cname, bname, amount) VALUES
	(1, 'JaneDoe', 'OshkoshMain', 500),
	(2, 'FredFlintstone', 'OshkoshMain', 5000),
	(3, 'FredFlintstone', 'OshkoshMain', 6000),
	(4, 'FredFlintstone', 'GrandChuteAppleton', 5000),
	(5, 'ThelmaJones', 'CollegeAppleton', 5000);
