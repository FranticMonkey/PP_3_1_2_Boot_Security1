

insert into users (name, age, password)
values ('admin', 1, '$2a$12$ve2VBNIFLRTa5CLqBgO3vuc2y8XvMu0Q5EghPJSUl7Bqi63PMAkW2');

insert into users (name, age, password)
values ('user', 1, '$2a$12$rZpAi.sTyElFPz7wrPESoeMbSGzRHUMXsoPCNdONstmy0O2ECTUti');

insert into roles (role_name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into users_roles (users_id, roles_id)
values ((select id from users where name = 'admin'),
        (select id from roles where role_name = 'ROLE_ADMIN'));

insert into users_roles (users_id, roles_id)
values ((select id from users where name = 'admin'),
        (select id from roles where role_name = 'ROLE_USER'));

insert into users_roles (users_id, roles_id)
values ((select id from users where name = 'user'),
        (select id from roles where role_name = 'ROLE_USER'));

