insert into usr (id, username, password, active)
values ( 1, 'admin', '$2a$08$TuC.dO/f2aH1.Jii4BRyce7O7agdeXGoQ6DkSZqhLpbt5OroaYNbO', true ),
       (2, 'user1', '$2a$04$v3q8oK2M0/OlzSZHlhiPr.eUJS8aFeuK9Ld38V3GM5cdjjH0j8Ci.', true),
       (3, 'user2', '$2a$04$v3q8oK2M0/OlzSZHlhiPr.eUJS8aFeuK9Ld38V3GM5cdjjH0j8Ci.', true),
       (4, 'user3', '$2a$04$v3q8oK2M0/OlzSZHlhiPr.eUJS8aFeuK9Ld38V3GM5cdjjH0j8Ci.', true),
       (5, 'user4', '$2a$04$v3q8oK2M0/OlzSZHlhiPr.eUJS8aFeuK9Ld38V3GM5cdjjH0j8Ci.', true),
       (6, 'user5', '$2a$04$v3q8oK2M0/OlzSZHlhiPr.eUJS8aFeuK9Ld38V3GM5cdjjH0j8Ci.', true);

insert into user_role (user_id, roles) VALUES ( 1, 'USER' ), ( 1, 'ADMIN' ),
(2, 'USER'),
(3, 'USER'),
(4, 'USER'),
(5, 'USER'),
(6, 'USER');

