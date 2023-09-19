CREATE TABLE Users(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
users_unique_id varchar(50) UNIQUE,
name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
created TIMESTAMP,
modified TIMESTAMP,
last_login TIMESTAMP,
token VARCHAR(255),
isactive char(1)
);

CREATE TABLE Phones(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
number VARCHAR(10),
citycode VARCHAR(5),
users_id INTEGER,
FOREIGN KEY (users_id) REFERENCES Users(id)
)