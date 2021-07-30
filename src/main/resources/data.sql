insert into room(code) values (101)
insert into room(code) values (102)
insert into room(code) values (103)
insert into room(code) values (104)
insert into room(code) values (105)


insert into user(name, email, hash_password, role) values ('admin', 'admin@conferenes.com', '$2y$12$wdLgIHr1B4h.91T7LaUgxOE5A8c5HDjY53lOKRvEpFj.ef0KIksYq', 'ADMIN')
insert into user(name, email, hash_password, role) values ('Dana', 'dana@gmail.com', '$2y$12$WkkoDspR8Dy1YR9ze2WwjuMvi2jtudfmP352kTlvwxrxOVhkUNrz6', 'PRESENTER')
insert into user(name, email, hash_password, role) values ('Diana', 'piauaw@gmail.com', '$2y$12$8fsitozlTOV4sM9bfe2S8e0ET7/2Vr6z2cinf2bPSiFiS60SIbpPW', 'LISTENER')
insert into user(name, email, hash_password, role) values ('Dina', 'dina@gmail.com', '$2y$12$xdR6gJl4u64BLIzvyz4A/uDsMuuMSKxkjUqAwGbrp9r9ywuEsSj/e', 'LISTENER')

insert into presentation(name) values ('Java Core')
insert into presentation(name) values ('JavaEE')

insert into schedule(date, start, end, presentation_id, room_id) values ('2021-07-30', '17:00', '18:30', 2, 2)
insert into schedule(date, start, end, presentation_id, room_id) values ('2021-07-31', '17:00', '18:30', 1, 2)
insert into schedule(date, start, end, presentation_id, room_id) values ('2021-07-29', '18:00', '19:30', 1, 3)

insert into presentation_user(presentation_id, user_id) values (1,2)
insert into presentation_user(presentation_id, user_id) values (1,3)
insert into presentation_user(presentation_id, user_id) values (2,2)
