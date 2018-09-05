create TABLE Person(
  id bigint auto_increment PRIMARY KEY,
  name varchar(200) NOT NULL,
  age INTEGER not NULL,
  dateOfBirth DATE,
  created TIMESTAMP,
  alive BOOLEAN NOT NULL
);
