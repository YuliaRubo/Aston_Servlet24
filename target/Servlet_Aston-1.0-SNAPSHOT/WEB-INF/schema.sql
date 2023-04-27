create database studentOnCourse;

use studentoncourse;

create table teacher(
                        id int AUTO_INCREMENT primary key,
                        name VARCHAR(20),
                        surname VARCHAR(20)
);
create table course(
                       id int AUTO_INCREMENT primary key,
                       name_course VARCHAR(15),
                       id_teacher int,
                       foreign key (id_teacher) references teacher(id)


);


create table student(
                        id int AUTO_INCREMENT primary key ,
                        name VARCHAR(20),
                        surname VARCHAR(20),
                        age int,
                        gender CHAR(1)

);
create table student_course(
                               id_course int ,
                               id_student int,
                               foreign key (id_student) references student(id)
    ,
                               foreign key (id_course) references course(id)

);