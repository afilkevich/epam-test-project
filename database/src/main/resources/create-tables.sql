DROP TABLE IF EXISTS app_depo;
CREATE TABLE app_depo(
depo_id  INT NOT NULL ,
name     VARCHAR(255) NOT NULL,
PRIMARY KEY(depo_id)
);

DROP TABLE IF EXISTS app_wagon;
CREATE TABLE app_wagon(
wagon_id INT NOT NULL,
type VARCHAR NOT NULL,
d_id INT NOT NULL,
count_seats INT NOT NULL,
date_build DATE NOT NULL,
PRIMARY KEY(wagon_id),
FOREIGN KEY(d_id) REFERENCES app_depo(depo_id)
);