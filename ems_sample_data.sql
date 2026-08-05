USE ems;

-- ===========================
-- DEPARTMENTS
-- ===========================

INSERT INTO departments (department_name, location) VALUES
('Human Resources','Hyderabad'),
('Information Technology','Bangalore'),
('Finance','Mumbai'),
('Sales','Chennai'),
('Marketing','Pune');

-- ===========================
-- USERS (Employees)
-- Password hash = Mahesh@123
-- ===========================

INSERT INTO users (created_at,email,enabled,name,password,role) VALUES
(NOW(),'mahesh@ems.com',1,'Mahesh','$2a$10$b9nkXLFPBzAYlBLwXoDZcOKH4UG54c.16urxd7FHf8NgbGTrjC5Ei','ADMIN'),
(NOW(),'ravi@ems.com',1,'Ravi Kumar','$2a$10$b9nkXLFPBzAYlBLwXoDZcOKH4UG54c.16urxd7FHf8NgbGTrjC5Ei','EMPLOYEE'),
(NOW(),'suresh@ems.com',1,'Suresh Kumar','$2a$10$b9nkXLFPBzAYlBLwXoDZcOKH4UG54c.16urxd7FHf8NgbGTrjC5Ei','EMPLOYEE'),
(NOW(),'priya@ems.com',1,'Priya Sharma','$2a$10$b9nkXLFPBzAYlBLwXoDZcOKH4UG54c.16urxd7FHf8NgbGTrjC5Ei','EMPLOYEE'),
(NOW(),'anjali@ems.com',1,'Anjali Reddy','$2a$10$b9nkXLFPBzAYlBLwXoDZcOKH4UG54c.16urxd7FHf8NgbGTrjC5Ei','EMPLOYEE'),
(NOW(),'vijay@ems.com',1,'Vijay Kumar','$2a$10$b9nkXLFPBzAYlBLwXoDZcOKH4UG54c.16urxd7FHf8NgbGTrjC5Ei','EMPLOYEE'),
(NOW(),'kiran@ems.com',1,'Kiran Rao','$2a$10$b9nkXLFPBzAYlBLwXoDZcOKH4UG54c.16urxd7FHf8NgbGTrjC5Ei','EMPLOYEE'),
(NOW(),'deepa@ems.com',1,'Deepa Singh','$2a$10$b9nkXLFPBzAYlBLwXoDZcOKH4UG54c.16urxd7FHf8NgbGTrjC5Ei','EMPLOYEE'),
(NOW(),'rahul@ems.com',1,'Rahul Verma','$2a$10$b9nkXLFPBzAYlBLwXoDZcOKH4UG54c.16urxd7FHf8NgbGTrjC5Ei','EMPLOYEE'),
(NOW(),'neha@ems.com',1,'Neha Patel','$2a$10$b9nkXLFPBzAYlBLwXoDZcOKH4UG54c.16urxd7FHf8NgbGTrjC5Ei','EMPLOYEE');

-- ===========================
-- EMPLOYEES
-- department_id 1-5
-- user_id assumes admin is id=1
-- new employee users become ids 2-10
-- ===========================

INSERT INTO employees
(active,designation,first_name,joining_date,last_name,phone,profile_photo,salary,department_id,user_id)
VALUES
(1,'Software Engineer','Ravi','2024-01-15','Kumar','9876543210',NULL,55000,2,2),

(1,'Senior Developer','Suresh','2023-06-10','Kumar','9876543211',NULL,75000,2,3),

(1,'HR Executive','Priya','2024-03-05','Sharma','9876543212',NULL,45000,1,4),

(1,'HR Manager','Anjali','2022-11-01','Reddy','9876543213',NULL,70000,1,5),

(1,'Finance Analyst','Vijay','2023-02-20','Kumar','9876543214',NULL,65000,3,6),

(1,'Accountant','Kiran','2024-05-15','Rao','9876543215',NULL,50000,3,7),

(1,'Sales Executive','Deepa','2023-09-08','Singh','9876543216',NULL,48000,4,8),

(1,'Marketing Executive','Rahul','2024-04-11','Verma','9876543217',NULL,52000,5,9),

(1,'Marketing Manager','Neha','2022-08-19','Patel','9876543218',NULL,80000,5,10);



-- ==========================================
-- ATTENDANCE
-- ==========================================

INSERT INTO attendance
(attendance_date, check_in, check_out, status, working_hours, employee_id)
VALUES

('2026-07-28','09:01:00','18:03:00','PRESENT',9.0,1),
('2026-07-29','09:05:00','18:10:00','PRESENT',9.1,1),
('2026-07-30','09:45:00','18:00:00','LATE',8.2,1),
('2026-07-31','09:00:00','18:05:00','PRESENT',9.1,1),

('2026-07-28','09:02:00','18:01:00','PRESENT',9.0,2),
('2026-07-29','09:00:00','18:15:00','PRESENT',9.2,2),
('2026-07-30','09:10:00','18:00:00','PRESENT',8.8,2),
('2026-07-31','09:00:00','18:00:00','PRESENT',9.0,2),

('2026-07-28','09:15:00','18:00:00','PRESENT',8.8,3),
('2026-07-29','09:00:00','13:00:00','HALF_DAY',4.0,3),
('2026-07-30',NULL,NULL,'ABSENT',0,3),
('2026-07-31','09:00:00','18:00:00','PRESENT',9.0,3),

('2026-07-28','09:00:00','18:00:00','PRESENT',9.0,4),
('2026-07-29','09:00:00','18:00:00','PRESENT',9.0,4),
('2026-07-30','09:25:00','18:05:00','LATE',8.6,4),
('2026-07-31','09:00:00','18:10:00','PRESENT',9.2,4),

('2026-07-28','09:05:00','18:05:00','PRESENT',9.0,5),
('2026-07-29','09:02:00','18:00:00','PRESENT',8.9,5),
('2026-07-30','09:00:00','18:00:00','PRESENT',9.0,5),
('2026-07-31','09:50:00','18:05:00','LATE',8.1,5),

('2026-07-28','09:00:00','18:00:00','PRESENT',9.0,6),
('2026-07-29','09:00:00','18:00:00','PRESENT',9.0,6),
('2026-07-30','09:00:00','18:00:00','PRESENT',9.0,6),
('2026-07-31','09:00:00','18:00:00','PRESENT',9.0,6),

('2026-07-28','09:20:00','18:00:00','LATE',8.7,7),
('2026-07-29','09:00:00','18:00:00','PRESENT',9.0,7),
('2026-07-30','09:00:00','18:05:00','PRESENT',9.1,7),
('2026-07-31',NULL,NULL,'ABSENT',0,7),

('2026-07-28','09:00:00','18:00:00','PRESENT',9.0,8),
('2026-07-29','09:05:00','18:10:00','PRESENT',9.1,8),
('2026-07-30','09:00:00','18:00:00','PRESENT',9.0,8),
('2026-07-31','09:00:00','18:00:00','PRESENT',9.0,8),

('2026-07-28','09:10:00','18:00:00','PRESENT',8.8,9),
('2026-07-29','09:00:00','18:00:00','PRESENT',9.0,9),
('2026-07-30','09:00:00','18:00:00','PRESENT',9.0,9),
('2026-07-31','09:30:00','18:00:00','LATE',8.5,9);

-- ==========================================
-- LEAVE REQUESTS
-- ==========================================

INSERT INTO leave_requests
(start_date, end_date, reason, status, employee_id)
VALUES

('2026-08-05','2026-08-06','Medical Leave','APPROVED',1),

('2026-08-12','2026-08-14','Family Function','PENDING',2),

('2026-08-18','2026-08-18','Personal Work','APPROVED',3),

('2026-08-20','2026-08-21','Vacation','REJECTED',4),

('2026-08-25','2026-08-26','Medical Leave','APPROVED',5),

('2026-08-10','2026-08-11','Festival Leave','APPROVED',6),

('2026-08-28','2026-08-30','Out of Station','PENDING',7),

('2026-08-16','2026-08-17','Personal Work','APPROVED',8),

('2026-08-22','2026-08-23','Medical Leave','PENDING',9);

-- ==========================================
-- PAYROLL
-- ==========================================

INSERT INTO payroll
(month, basic_salary, bonus, deduction, net_salary, payment_date, employee_id)
VALUES

('July 2026',60000,5000,1000,64000,'2026-07-31',1),

('July 2026',55000,3000,500,57500,'2026-07-31',2),

('July 2026',45000,2000,1000,46000,'2026-07-31',3),

('July 2026',70000,8000,2000,76000,'2026-07-31',4),

('July 2026',50000,2500,700,51800,'2026-07-31',5),

('July 2026',52000,3500,1200,54300,'2026-07-31',6),

('July 2026',48000,2000,800,49200,'2026-07-31',7),

('July 2026',65000,6000,1500,69500,'2026-07-31',8),

('July 2026',47000,2500,900,48600,'2026-07-31',9);

-- ==========================================
-- PERFORMANCE
-- ==========================================

INSERT INTO performance
(rating, remarks, review_date, employee_id)
VALUES

(5,'Excellent leadership and teamwork.','2026-07-31',1),

(4,'Very good performance and punctual.','2026-07-31',2),

(3,'Needs improvement in communication.','2026-07-31',3),

(5,'Outstanding technical skills.','2026-07-31',4),

(4,'Consistent performer.','2026-07-31',5),

(5,'Excellent contribution to projects.','2026-07-31',6),

(3,'Average performance. Needs training.','2026-07-31',7),

(4,'Very dedicated employee.','2026-07-31',8),

(5,'Top performer of the month.','2026-07-31',9);