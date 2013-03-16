-- Note that comment lines need to end with a semicolon for CreateDB.java to work;

-- The primary keys (id) should really be CHAR(40), not VARCHAR(40), but;
-- to make life easier in testing, I've placed them as VARCHAR(40) for now;

-- First drop everything (order matters here for foreign keys!);


ALTER TABLE employee DROP FOREIGN KEY assignedstore;
DROP TABLE storeprod;
DROP TABLE generalledger;
DROP TABLE cproduct;
DROP TABLE pproduct;
DROP TABLE product;
DROP TABLE sale;
DROP TABLE revsource;
DROP TABLE commission;
DROP TABLE employee;
DROP TABLE customer;
DROP TABLE debitcredit;
DROP TABLE journalentry;
DROP TABLE payment;
DROP TABLE store;
DROP TABLE transaction;
DROP TABLE businessobject;



-- BUSINESSOBJECT TABLE (everything extends this);
CREATE TABLE businessobject (
  id           VARCHAR(40) PRIMARY KEY,
  botype       VARCHAR(250)
);

--General Ledger Table;
CREATE TABLE generalledger(
  id             VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  type           VARCHAR(40),
  accountname    VARCHAR(250),
  balance        NUMERIC(8,2)
);
--Employee Table;
CREATE TABLE employee (
  id             VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  birthdate      DATE DEFAULT NULL,
  salary         NUMERIC(8,2) DEFAULT 0,
  favoritenumber INT NOT NULL DEFAULT 0,
  iq             INT NOT NULL DEFAULT 0,
  distance       INT NOT NULL DEFAULT 0,
  firstname      VARCHAR(250),
  lastname       VARCHAR(250),
  phone          VARCHAR(100),
  commissionrate NUMERIC(5,4) DEFAULT 0,
  username       VARCHAR(8),
  hiredate       DATE DEFAULT NULL,
  assignedstore  VARCHAR(40)
);
INSERT INTO businessobject(id, botype) VALUES ('employee1', 'edu.byu.isys414.jmcmurdi.IntexII.Employee');
--INSERT INTO person(id, firstname, lastname, phone) VALUES ('employee1', 'Bart', 'Simpson', '801-555-0222');
INSERT INTO employee(id, firstname, lastname, phone, username) VALUES ('employee1','Justin', 'McMurdie', '(601)555-5555', 'jmcmurdi');
INSERT INTO businessobject(id, botype) VALUES ('employee2', 'edu.byu.isys414.jmcmurdi.IntexII.Employee');
--INSERT INTO person(id, firstname, lastname, phone) VALUES ('employee2', 'Homer', 'Simpson', '801-555-3456');
INSERT INTO employee(id, firstname, lastname, phone,username) VALUES ('employee2', 'Bret', 'Markowski', '801-555-3456', 'bretm23');

-- STORE Table;
CREATE TABLE store(
  id            VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  managerid     VARCHAR(40) REFERENCES employee(id),
  location      VARCHAR(250),
  address       VARCHAR(250),
  phone         VARCHAR(50),
  salestaxrate  NUMERIC(5,4) DEFAULT 0,
  subnetid      VARCHAR(250)
);
ALTER TABLE employee ADD CONSTRAINT assignedstore FOREIGN KEY (assignedstore) REFERENCES store(id);

INSERT INTO businessobject(id, botype) VALUES ('store1', 'edu.byu.isys414.jmcmurdi.IntexII.Store');
INSERT INTO store(id, managerid, salestaxrate, location) VALUES ('store1', 'employee1', 0.06, 'University Mall');

UPDATE employee SET assignedstore = 'store1' WHERE id = 'employee1';






-- Customer Table;
CREATE TABLE customer (
id            VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
firstname     VARCHAR(250),
lastname      VARCHAR(250),
email         VARCHAR(250),
address       VARCHAR(250),
state         VARCHAR(50),
zip           VARCHAR(25),
password      VARCHAR(50),
verified      BOOLEAN,
validation    VARCHAR(40),
phone         VARCHAR(50)
);

INSERT INTO businessobject(id, botype) VALUES ('customer1', 'edu.byu.isys414.jmcmurdi.IntexII.Customer');
INSERT INTO customer(id, firstname, lastname ) VALUES ('customer1', 'Adam', 'Bilodeau');

--Journal Entry table;
CREATE TABLE journalentry (
id            VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
transdate     DATE DEFAULT NULL
);

INSERT INTO businessobject(id, botype) VALUES ('journalentry1', 'edu.byu.isys414.jmcmurdi.IntexII.JournalEntry');
INSERT INTO journalentry (id, transdate) VALUES ('journalentry1', '2012-01-01 13:40:01');

--Debitcredit table;
CREATE TABLE debitcredit (
id            VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
journalentryid VARCHAR(40) REFERENCES journalentry(id),
isdebit       boolean,
glname        VARCHAR(50),
amount        NUMERIC(12,2) DEFAULT 0
);

INSERT INTO businessobject(id, botype) VALUES ('debitcredit1', 'edu.byu.isys414.jmcmurdi.IntexII.DebitCredit');
INSERT INTO debitcredit(id,journalentryid, isdebit, glname, amount) VALUES ('debitcredit1', 'journalentry1', TRUE, 'cash', 150.00);
INSERT INTO businessobject(id, botype) VALUES ('debitcredit2', 'edu.byu.isys414.jmcmurdi.IntexII.DebitCredit');
INSERT INTO debitcredit(id, journalentryid, isdebit, glname, amount) VALUES ('debitcredit2','journalentry1', FALSE, 'inventory', 150.00);
INSERT INTO businessobject(id, botype) VALUES ('debitcredit3', 'edu.byu.isys414.jmcmurdi.IntexII.DebitCredit');
INSERT INTO debitcredit(id, journalentryid, isdebit, glname, amount) VALUES ('debitcredit3','journalentry1', FALSE, 'salestaxpayable', 9.00);
INSERT INTO businessobject(id, botype) VALUES ('debitcredit4', 'edu.byu.isys414.jmcmurdi.IntexII.DebitCredit');
INSERT INTO debitcredit(id, journalentryid, isdebit, glname, amount) VALUES ('debitcredit4','journalentry1', TRUE, 'salestaxexpense', 9.00);



--Payment Table;
CREATE TABLE payment (
id            VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
payamount     NUMERIC(12,2) DEFAULT 0,
paychange     NUMERIC(8,2) DEFAULT 0,
type          VARCHAR(250)
);

INSERT INTO businessobject(id, botype) VALUES ('payment1', 'edu.byu.isys414.jmcmurdi.IntexII.Payment');
INSERT INTO payment(id, payamount, paychange, type) VALUES ('payment1', 160.00, 1.00, 'cash');

--Commission Table;
CREATE TABLE commission (
id            VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
empid         VARCHAR(40) REFERENCES employee(id),
amount        NUMERIC(8,2) DEFAULT 0,
comdate       DATE DEFAULT NULL
);

INSERT INTO businessobject(id, botype) VALUES ('commission1', 'edu.byu.isys414.jmcmurdi.IntexII.Commission');
INSERT INTO commission(id, empid, comdate) VALUES ('commission1', 'employee2', '2012-01-01 13:40:01');


--Revenue Source Table;
CREATE TABLE revsource(
id            VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
chargeamount  NUMERIC(8,2) DEFAULT 0, 
revtype       VARCHAR(250)
);

INSERT INTO businessobject(id, botype) VALUES ('revsource1', 'edu.byu.isys414.jmcmurdi.IntexII.RevSource');
INSERT INTO revsource(id, chargeamount, revtype) VALUES('revsource1', 150.00, 'Sale');

-- Product Table;
CREATE TABLE product(
id            VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
prodprice     NUMERIC(8,2) DEFAULT 0,
prodtype      VARCHAR(50),
name          VARCHAR(60),
prodnum       INTEGER
);



-- Sale Table;
CREATE TABLE sale(
id            VARCHAR(40) PRIMARY KEY REFERENCES revsource(id),
quantity      INTEGER NOT NULL DEFAULT 0,
storeid       VARCHAR(40) REFERENCES store(id),
prodid        VARCHAR(40) REFERENCES product(id)
);

INSERT INTO sale(id, storeid, prodid) VALUES ('revsource1', 'store1', 'prod1');

--conceptual product table;
CREATE TABLE cproduct(
id            VARCHAR(40) PRIMARY KEY REFERENCES product(id),
prodname      VARCHAR(250),
description   VARCHAR(250),
manufacturer  VARCHAR(250),
avgcost       NUMERIC(8,2) DEFAULT 0,
cprodcomrate  NUMERIC(5,4) DEFAULT 0
);

INSERT INTO businessobject(id, botype) VALUES ('prod1', 'edu.byu.isys414.jmcmurdi.IntexII.CProduct');
INSERT INTO product(id, prodprice, prodtype, prodnum, name) VALUES ('prod1', 150.00, 'cproduct', 1, 'camera');
INSERT INTO cproduct(id, prodname, description, cprodcomrate) VALUES ('prod1', 'Canon Rebel version1', 'test dec', .025);

INSERT INTO businessobject(id, botype) VALUES ('prod3', 'edu.byu.isys414.jmcmurdi.IntexII.CProduct');
INSERT INTO product(id, prodprice, prodtype, prodnum, name) VALUES ('prod3', 5.00, 'cproduct', 1, 'camera case');
INSERT INTO cproduct(id, prodname, description, cprodcomrate) VALUES ('prod3', 'Canon Rebel Camera Case', 'its a cool case', .025);
--INSERT INTO businessobject(id, botype) VALUES ('prod3', 'edu.byu.isys414.jmcmurdi.IntexII.CProduct');
--INSERT INTO product(id, prodprice, prodtype, prodnum, name ) VALUES ('prod3', 5.00, 'cproduct', 3, 'camera case');
--INSERT INTO cproduct(id, prodname, description, cprodcomrate) VALUES ('prod3', 'Canon Rebel Camera Case', 'its a cool case', .025);

--physical product table;
CREATE TABLE pproduct(
id            VARCHAR(40) PRIMARY KEY REFERENCES product(id),
serialnum     VARCHAR(250),
shelflocation VARCHAR(250),
datepuchased  DATE DEFAULT NULL,
cost          NUMERIC(8,2) DEFAULT 0,
status        VARCHAR(250),
pprodcomrate  NUMERIC(5,4) DEFAULT 0,
ppname          VARCHAR(60),
cprodid       VARCHAR(40) REFERENCES cproduct(id),
storeid       VARCHAR(40) REFERENCES store(id)
);


INSERT INTO businessobject(id, botype) VALUES ('prod2', 'edu.byu.isys414.jmcmurdi.IntexII.PProduct');
INSERT INTO product(id, prodprice, name, prodnum, prodtype) VALUES ('prod2', 150.00, 'camera', 2, 'pproduct');
INSERT INTO pproduct(id, serialnum, datepuchased, cost, pprodcomrate, cprodid, storeid, ppname) VALUES ('prod2', 'tk427', '2012-01-01 13:40:01', 80.59, .025, 'prod1', 'store1', 'Canon Rebel v1 24309283420385235409');

--store product association class table;
CREATE TABLE storeprod(
id            VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
storeid       VARCHAR(40) REFERENCES store(id),
cprodid       VARCHAR(40) REFERENCES cproduct(id),
quantityleft  INTEGER NOT NULL DEFAULT 0,
shelflocation VARCHAR(250)
);

INSERT INTO businessobject (id, botype) VALUES ('storeprod1', 'edu.byu.isys414.jmcmurdi.IntexII.StoreProd');
INSERT INTO storeprod (id, storeid, cprodid, quantityleft, shelflocation) VALUES ('storeprod1', 'store1', 'prod1', 7, 'Isle 3 North side behind glass pane');

INSERT INTO businessobject (id, botype) VALUES ('storeprod2', 'edu.byu.isys414.jmcmurdi.IntexII.StoreProd');
INSERT INTO storeprod (id, storeid, cprodid, quantityleft, shelflocation) VALUES ('storeprod2', 'store1', 'prod3', 7, 'Lobby in bin');

--transaction TABLE;
CREATE TABLE transaction(
id            VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
paymentid     VARCHAR(40) REFERENCES payment(id),
journalentryid VARCHAR(40) REFERENCES journalentry(id),
customerid    VARCHAR(40) REFERENCES customer(id),
commid        VARCHAR(40) REFERENCES commission(id),
revid         VARCHAR(40) REFERENCES revsource(id),
empid         VARCHAR(40) REFERENCES employee(id),
storeid       VARCHAR(40) REFERENCES store(id),
transdate     DATE DEFAULT NULL,
subtotal      NUMERIC(8,2) DEFAULT 0,
tax           NUMERIC(8,2) DEFAULT 0,
comtotal      NUMERIC(8,2) DEFAULT 0,
total         NUMERIC(8,2) DEFAULT 0
);

INSERT INTO businessobject(id, botype) VALUES ('transaction1', 'edu.byu.isys414.jmcmurdi.IntexII.Transaction');
INSERT INTO transaction(id, paymentid, journalentryid, customerid, commid, revid, empid, storeid, transdate, subtotal, tax, total) VALUES ('transaction1', 'payment1', 'journalentry1', 'customer1', 'commission1', 'revsource1', 'employee2', 'store1', '2012-01-01 13:40:01', 150.00, 9.00, 159.00 );