USE `attend-db`;

create table role (
    id int primary key auto_increment,
    name varchar(50) not null
);

insert into role (name) values ('admin'), ('student'), ('lecturer');

create table user (
    id int primary key auto_increment,
    name varchar(50) not null,
    surname varchar(50) not null,
    username varchar(50) not null,
    password varchar(50) not null,
    email varchar(50) not null,
    studentNumber varchar(50),
    role int not null,
    foreign key (role) references role(id)
);

create table faculty (
    id int primary key auto_increment,
    name varchar(50) not null
);

create table user_faculty
(
    userId       int not null primary key,
    facultyId    int not null primary key,
    foreign key (userId) references user (id),
    foreign key (facultyId) references faculty (id)
);

create table course (
    id int primary key auto_increment,
    name varchar(50) not null,
    lecturerId int not null primary key,
    universityId int not null primary key,
    foreign key (lecturerId) references user(id),
    foreign key (universityId) references faculty(id)
);

create table user_course
(
    userId       int not null primary key,
    courseId     int not null primary key,
    foreign key (userId) references user (id),
    foreign key (courseId) references course (id)
);