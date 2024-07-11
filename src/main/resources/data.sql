

insert into users (first_name, last_name, age, email, password)
values ('admin', 'admin', 1, 'admin@mail.ru', '$2a$12$ve2VBNIFLRTa5CLqBgO3vuc2y8XvMu0Q5EghPJSUl7Bqi63PMAkW2');

insert into users (first_name, last_name, age, email, password)
values ('user', 'user', 1, 'user@mai.ru', '$2a$12$rZpAi.sTyElFPz7wrPESoeMbSGzRHUMXsoPCNdONstmy0O2ECTUti');

insert into roles (role_name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into users_roles (users_id, roles_id)
values ((select id from users where email = 'admin@mail.ru'),
        (select id from roles where role_name = 'ROLE_ADMIN'));

insert into users_roles (users_id, roles_id)
values ((select id from users where email = 'admin@mail.ru'),
        (select id from roles where role_name = 'ROLE_USER'));

insert into users_roles (users_id, roles_id)
values ((select id from users where email = 'user@mai.ru'),
        (select id from roles where role_name = 'ROLE_USER'));

