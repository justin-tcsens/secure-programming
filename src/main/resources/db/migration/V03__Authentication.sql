create table user_profile(
    id int not null AUTO_INCREMENT,
    login_id varchar(20) not null,
    user_name varchar(120) not null,
    hash_pwd varchar(255) not null,
    CONSTRAINT pk_user_profile_id primary key (id)
);

create table user_role(
    id int not null AUTO_INCREMENT,
    user_profile_id int not null,
    role_name varchar(100) not null,
    CONSTRAINT pk_user_role_id primary key (id),
    CONSTRAINT fk_user_role_id FOREIGN KEY (user_profile_id) REFERENCES user_profile (id)
);

insert into user_profile (login_id, user_name, hash_pwd) values 
('james_ma', 'James Ma', '$2a$10$OiX/JUHQXjjgbfdxIIIyM.m/qUwku8rLstCbbDi7FYbjgKtp4ErJO'),
('norhashima', 'Norhashima Sayidatun','$2a$10$EhZ2d7o79xoD2MCJMCWg5OjBJ2BmstvVQELlVcNDgC0GCqNpfNaDm'),
('noraini', 'Noraini Shamsuddin','$2a$10$yDQIUFB2xAqUVmqmBf.qt.cq85tr/oSkbOz.SB1Ko87wYLDCAxTOy'),
('shamir', 'Shamir Baharuddin','$2a$10$7B2Y1u3sQeG3.IqKy1pFt.ykvxBnZ0LT8JbmuVoZSdApA5Vzvmixy');

insert into user_role(user_profile_id, role_name) values
((select id from user_profile where login_id = 'james_ma'), 'SYS_ADMIN'),
((select id from user_profile where login_id = 'norhashima'), 'USER'),
((select id from user_profile where login_id = 'noraini'), 'ADMIN'),
((select id from user_profile where login_id = 'shamir'), 'USER');
