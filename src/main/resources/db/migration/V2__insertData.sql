-- Insert 19 Seats
insert into seat (id, booked)
values (1, false),
       (2, false),
       (3, false),
       (4, false),
       (5, false),
       (6, false),
       (7, false),
       (8, false),
       (9, false),
       (10, false),
       (11, false),
       (12, false),
       (13, false),
       (14, false),
       (15, false),
       (16, false),
       (17, false),
       (18, false),
       (19, false);


-- Insert 2 Roles
insert into role (id, name)
values (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

-- Insert an default user
insert into user (id, email, password, username)
values (1, "milton@welldev.io", "$2a$12$slZujAlFdCvMQydPpstVhOHJYHnozgaMRf80ZXKaN2FT4iUf9QuES", "milton");

-- Set both roles to the default user
insert into user_roles (user_id, role_id)
values (1, 1),
       (1, 2);