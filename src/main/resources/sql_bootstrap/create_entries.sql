USE wishlist;

-- add entries
-- users
INSERT INTO users (email, userName) VALUES ('email a', 'a');
INSERT INTO users (email, userName) VALUES ('email b', 'b');
-- giftLists
INSERT INTO wishlists (listID, email, listName) VALUES (1, 'email a', 'list 1');
INSERT INTO wishlists (listID, email, listName) VALUES (2, 'email b', 'list 2');
-- gifts
-- list 1
INSERT INTO gifts (giftID, listID, giftName, price, url) VALUES (1, 1, 'item 1', 20, 'an url');
INSERT INTO gifts (giftID, listID, giftName, price, url) VALUES (2, 1, 'item 2', 30, 'an url');
-- list 2
INSERT INTO gifts (giftID, listID, giftName, price, url) VALUES (3, 2, 'item 3', 20, 'an url');
INSERT INTO gifts (giftID, listID, giftName, price, url) VALUES (4, 2, 'item 4', 20, 'an url');

-- other SCRATCHPAD
INSERT INTO wishlists (email, listName) VALUES ('email b', 'list 2');


SELECT * FROM wishlists WHERE email = 'email';

UPDATE gifts
SET isReserved = 1
WHERE giftID BETWEEN 0 AND 10;


-- delete gifts
DELETE FROM gifts WHERE giftID=10;
-- delete giftLists (delete all gifts for list)
DELETE FROM wishlists WHERE listID=1;
-- delete user (delete all lists and gifts for user)
DELETE FROM users WHERE email='email b';


INSERT INTO users VALUES (?,?);
