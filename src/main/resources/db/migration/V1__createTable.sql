create table user
(
    id       bigint not null primary key,
    email    varchar(255) null,
    password varchar(255) null,
    username varchar(255) null,
    unique (email),
    unique (username)
);

create table role
(
    id   bigint auto_increment primary key,
    name varchar(255) null
);

create table seat
(
    id     bigint auto_increment primary key,
    booked bit not null
);

create table ticket
(
    id      bigint not null primary key,
    seat_id bigint null,
    user_id bigint null,
    foreign key (user_id) references user (id)
);

create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references user (id),
    foreign key (role_id) references role (id)
);

create table user_tickets
(
    user_id    bigint not null,
    tickets_id bigint not null,
    unique (tickets_id),
    foreign key (tickets_id) references ticket (id),
    foreign key (user_id) references user (id)
);








