DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM dishes_daily_menu;
DELETE FROM dishes;
DELETE FROM menus;
DELETE FROM restaurants;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO restaurants (name)
VALUES ('Fish and house'),
       ('Kebab 24'),
       ('Italian soul');
-- restaurant_id: 100002 - 100004
INSERT INTO dishes (name, price, restaurant_id)
VALUES ('Fish', 200, 100002),
       ('Chips', 100, 100002),
       ('Rice', 100, 100002),
       ('Pizza', 250, 100004),
       ('Beef', 400, 100003),
       ('Beef', 500, 100004),
       ('Kebab', 150, 100003),
       ('Pork', 200, 100004),
       ('Tea', 50, 100003);
--dish_id: 100005 - 100013

INSERT INTO menus (date, restaurant_id)
VALUES ('2020-03-24', 100002),
       ('2020-03-24', 100003),
       ('2020-03-24', 100004);
--menu_id: 100014 - 100016

INSERT INTO dishes_daily_menu (dish_id, menu_id)
VALUES (100005,100014),
       (100007,100014),
       (100009,100015),
       (100011,100015),
       (100008,100016),
       (100012,100016);


INSERT INTO votes (date_time, user_id, restaurant_id)
VALUES ('2020-03-24 10:00:00', 100000,100002),
       ('2020-03-24 11:00:00', 100000,100004),
       ('2020-03-24 10:00:00', 100001,100004);


