insert into usr (id, username, password, active)
values ( 1, 'admin', '$2a$08$TuC.dO/f2aH1.Jii4BRyce7O7agdeXGoQ6DkSZqhLpbt5OroaYNbO', true );

insert into user_role (user_id, roles) VALUES ( 1, 'USER' ), ( 1, 'ADMIN' );