DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS student_course;

create table teacher
(
    id      int AUTO_INCREMENT primary key,
    name    VARCHAR(20),
    surname VARCHAR(20)
);
create table course
(
    id          int AUTO_INCREMENT primary key,
    name_course VARCHAR(15),
    id_teacher  int,
    foreign key (id_teacher) references teacher (id)
);

create table student
(
    id      int AUTO_INCREMENT primary key,
    name    VARCHAR(20),
    surname VARCHAR(20),
    age     int,
    gender  CHAR(1)

);
create table student_course
(
    id_course  int,
    id_student int,
    foreign key (id_student) references student (id),
    foreign key (id_course) references course (id)

);

insert into teacher(name, surname)
values ('Morgan', 'Free'),
       ('Simon', 'Vesta'),
       ('Leon', 'Laky');
insert into course(name_course, id_teacher)
values ('Math', 1),
       ('Graphic', 2),
       ('Logic', 2);
insert into student(name, surname, age, gender)
values ('One', 'Red', 24, 'M'),
       ('Two', 'Green', 34, 'F'),
       ('Three', 'Blue', 26, 'M'),
       ('Four', 'Red', 45, 'F'),
       ('Five', 'Red', 27, 'M');
insert into student_course(id_course, id_student)
values (1, 2),
       (2, 1),
       (3, 3),
       (2, 5),
       (3, 3),
       (3, 3),
       (1, 4);
