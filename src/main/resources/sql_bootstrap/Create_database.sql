-- create database
CREATE DATABASE IF NOT EXISTS wishlist;
USE wishlist;

-- create tables
-- users
CREATE TABLE IF NOT EXISTS users
(
    email varchar(50),
    userName  varchar(50),
    primary key (email)
);
-- giftLists
CREATE TABLE IF NOT EXISTS giftLists
(
    listID int AUTO_INCREMENT,
    email  varchar(50),
    listName varchar(50),
    PRIMARY KEY (listID),
    FOREIGN KEY (email) REFERENCES users(email) ON DELETE CASCADE
);
-- gifts
CREATE TABLE IF NOT EXISTS gifts
(
    giftID int AUTO_INCREMENT,
    listID int,
    giftName varchar(50),
    price double,
    url varchar(50),
    PRIMARY KEY (giftID),
    FOREIGN KEY (listID) REFERENCES giftLists(listID) ON DELETE CASCADE
);

-- remove all elements
# TRUNCATE users;
# TRUNCATE gifts;
# TRUNCATE giftLists;