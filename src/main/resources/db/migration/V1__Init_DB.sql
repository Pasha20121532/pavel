create sequence hibernate_sequence start 1 increment 1;
create table usr(
                    id int8 not null,
                    activation_code varchar(255),
                    active boolean not null,
                    email varchar(255),
                    password varchar(255) not null,
                    username varchar(255) not null,
                    primary key (id)
);

create table student (
                         student_id int8 not null,
                         avatarname varchar(255),
                         groupp int4,
                         name varchar(100),
                         user_id int8,
                         primary key (student_id),
                         foreign key (user_id) references usr(id)
);
create table user_role(
                          user_id int8 not null,
                          roles varchar(255),
                          foreign key (user_id) references usr(id)
);
