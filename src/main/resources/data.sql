SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE Stock;
SET REFERENTIAL_INTEGRITY TRUE;

ALTER TABLE Stock ALTER COLUMN id RESTART WITH 1;

INSERT INTO Stock(name, current_price, last_update) VALUES('JP Morgan Chase', 200.0, '2021-06-27T12:30:23.733Z');
INSERT INTO Stock(name, current_price, last_update) VALUES('HSBC Holdings', 100.0, '2021-06-27T12:30:23.733Z');
INSERT INTO Stock(name, current_price, last_update) VALUES('AirBnB', 150.0, '2021-06-27T12:30:23.733Z');
INSERT INTO Stock(name, current_price, last_update) VALUES('Mercedes', 125.0, '2021-06-27T12:30:23.733Z');
INSERT INTO Stock(name, current_price, last_update) VALUES('Toyota', 75.0, '2021-06-27T12:30:23.733Z');