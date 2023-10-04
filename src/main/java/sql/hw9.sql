CREATE TABLE IF NOT EXISTS shoes
(
    id           serial primary key,
    nameOfBrand  varchar(50) not null,
    usageOfShoes varchar(50) not null,
    price        int         not null
);
CREATE TABLE IF NOT EXISTS users
(
    id              serial primary key,
    nameOfUsers     varchar(50) not null,
    username        varchar(50) not null unique,
    passwordOfUsers varchar(50) not null

);
CREATE TABLE IF NOT EXISTS cart
(
    idCart                  serial primary key,
    idOfUser                int references users,
    idOfElectronicAppliance int references ElectronicAppliances,
    idOfShoe                int references shoes

);
CREATE TABLE IF NOT EXISTS ElectronicAppliances
(
    id                serial primary key,
    nameOfBrand       varchar(50) not null,
    usageForAppliance varchar(50) not null,
    price             int         not null
);


INSERT INTO shoes (nameOfBrand, usageOfShoes, price)
VALUES ('Nike', 'Running Shoes', 120),
       ('Adidas', 'Sneakers', 90),
       ('Puma', 'Casual Shoes', 70),
       ('Reebok', 'Athletic Shoes', 110),
       ('New Balance', 'Walking Shoes', 85);

INSERT INTO ElectronicAppliances (nameOfBrand, usageForAppliance, price)
VALUES ('Sony', 'Smart TV', 800),
       ('Samsung', 'Refrigerator', 1200),
       ('Apple', 'iPhone 13', 1000),
       ('LG', 'Washing Machine', 600),
       ('Dell', 'Laptop', 1100);

INSERT INTO users (nameOfUsers, username, passwordOfUsers)
VALUES ('John Doe', 'johndoe', 'password123'),
       ('Alice Smith', 'alicesmith', 'secret123'),
       ('Bob Johnson', 'bjohnson', 'pass1234');

INSERT INTO cart (idOfUser, idOfElectronicAppliance, idOfShoe)
VALUES (1, 1, 2),
       (1, 3, NULL),
       (2, NULL, 4);



