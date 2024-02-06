create  database vikash;
use vikash;
create table Student(
student_id int primary key auto_increment,
student_name varchar(25),
mobile_no long);
insert into Student(student_name,mobile_no) values("Vikashini",9442252888),("Prasath",9442258882);
select * from Student;
truncate Student;
select * from Student;

