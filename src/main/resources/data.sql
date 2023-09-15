insert into course_details(id, full_name, created_date, last_updated_date)
values (10001, 'JPA in 50 Steps', now(), now());
insert into course_details(id, full_name, created_date, last_updated_date)
values (10002, 'JDBC in 100 Steps', now(), now());
insert into course_details(id, full_name, created_date, last_updated_date)
values (10003, 'DevOps in 50 Steps', now(), now());
insert into course_details(id, full_name, created_date, last_updated_date)
values (10004, 'Project Reactor in 50 Steps', now(), now());
insert into course_details(id, full_name, created_date, last_updated_date)
values (10005, 'Lambdas in 100 Steps', now(), now());
insert into course_details(id, full_name, created_date, last_updated_date)
values (10006, 'RabbitMQ in 50 Steps', now(), now());
insert into course_details(id, full_name, created_date, last_updated_date)
values (10007, 'Kafka in 50 Steps', now(), now());
insert into course_details(id, full_name, created_date, last_updated_date)
values (10008, 'REST in 100 Steps', now(), now());
insert into course_details(id, full_name, created_date, last_updated_date)
values (10009, 'Microservices in 50 Steps', now(), now());

insert into passport(id, number)
values (40001, 'E1234567');
insert into passport(id, number)
values (40002, 'N9876533');
insert into passport(id, number)
values (40003, 'L9894834');

insert into student(id, name, passport_id)
values (20001, 'Ranga', 40001);
insert into student(id, name, passport_id)
values (20002, 'Adam', 40002);
insert into student(id, name, passport_id)
values (20003, 'Jane', 40003);

insert into review(id, rating, description, course_id)
values (50001, '5', 'Great Course', 10001);
insert into review(id, rating, description, course_id)
values (50002, '4', 'Wonderful Course', 10001);
insert into review(id, rating, description, course_id)
values (50003, '5', 'Awesome Course', 10003);


insert into student_course(student_id, course_id)
values (20001, 10001);
insert into student_course(student_id, course_id)
values (20002, 10001);
insert into student_course(student_id, course_id)
values (20003, 10001);
insert into student_course(student_id, course_id)
values (20001, 10003);

