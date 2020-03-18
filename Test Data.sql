USE ats;

DELETE FROM employeetasks;
DELETE FROM teammembers;
DELETE FROM jobtasks;
DELETE FROM jobs;
DELETE FROM employees;
DELETE FROM tasks;
DELETE FROM teams;

CALL Team_Insert(@teamID, "Team 1", 0, SYSDATE(), NULL, 0, NULL);

INSERT INTO employees 
(FirstName, LastName, SIN, HourlyRate, isDeleted, CreatedAt, UpdatedAt, DeletedAt) 
VALUES ("John", "Doe", 123456789, 15.25, false, sysdate(), NULL, NULL);
SET @JohnDoeID = LAST_INSERT_ID();
CALL TeamMember_Insert(@teamID, @JohnDoeID);

INSERT INTO employees 
(FirstName, LastName, SIN, HourlyRate, isDeleted, CreatedAt, UpdatedAt, DeletedAt) 
VALUES ("Jane", "Doe", 987654321, 17.25, false, sysdate(), NULL, NULL);

SET @JaneDoeID = LAST_INSERT_ID();

CALL TeamMember_Insert(@teamID, @JaneDoeID);

INSERT INTO employees 
(FirstName, LastName, SIN, HourlyRate, isDeleted, CreatedAt, UpdatedAt, DeletedAt) 
VALUES ("Bob", "Doe", 453654787, 13.25, false, sysdate(), NULL, NULL);

INSERT INTO employees 
(FirstName, LastName, SIN, HourlyRate, isDeleted, CreatedAt, UpdatedAt, DeletedAt) 
VALUES ("Jim", "Morrison", 989764234, 17.50, false, sysdate(), NULL, NULL);

INSERT INTO employees 
(FirstName, LastName, SIN, HourlyRate, isDeleted, CreatedAt, UpdatedAt, DeletedAt) 
VALUES ("Neil", "Young", 187637054, 14.75, false, sysdate(), NULL, NULL);

INSERT INTO employees 
(FirstName, LastName, SIN, HourlyRate, isDeleted, CreatedAt, UpdatedAt, DeletedAt) 
VALUES ("Maynard", "Keenan", 655275435, 18.35, false, sysdate(), NULL, NULL);

INSERT INTO employees 
(FirstName, LastName, SIN, HourlyRate, isDeleted, CreatedAt, UpdatedAt, DeletedAt) 
VALUES ("James", "Chancellor", 657442835, 16.65, false, sysdate(), NULL, NULL);

INSERT INTO employees 
(FirstName, LastName, SIN, HourlyRate, isDeleted, CreatedAt, UpdatedAt, DeletedAt) 
VALUES ("Danny", "Carey", 543785090, 17.65, false, sysdate(), NULL, NULL);

INSERT INTO employees 
(FirstName, LastName, SIN, HourlyRate, isDeleted, CreatedAt, UpdatedAt, DeletedAt) 
VALUES ("Adam", "Jones", 542838955, 15.45, false, sysdate(), NULL, NULL);

INSERT INTO employees 
(FirstName, LastName, SIN, HourlyRate, isDeleted, CreatedAt, UpdatedAt, DeletedAt) 
VALUES ("James", "Hetfield", 587632099, 13.40, false, sysdate(), NULL, NULL);

INSERT INTO employees 
(FirstName, LastName, SIN, HourlyRate, isDeleted, CreatedAt, UpdatedAt, DeletedAt) 
VALUES ("Kirk", "Hammett", 554675129, 19.50, false, sysdate(), NULL, NULL);

INSERT INTO employees 
(FirstName, LastName, SIN, HourlyRate, isDeleted, CreatedAt, UpdatedAt, DeletedAt) 
VALUES ("Lars", "Ulrich", 656424989, 18.60, false, sysdate(), NULL, NULL);

INSERT INTO employees 
(FirstName, LastName, SIN, HourlyRate, isDeleted, CreatedAt, UpdatedAt, DeletedAt) 
VALUES ("Jimmy", "McGill", 885765224, 25.60, false, sysdate(), NULL, NULL);

INSERT INTO tasks 
(Name, Description, Duration)
VALUES ("Test Task 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent leo purus, euismod a rhoncus ultricies, condimentum quis tortor. Integer scelerisque ac justo non sollicitudin. Sed finibus nulla sit amet cursus varius.", 30);

INSERT INTO employeetasks (TaskID, EmployeeID) VALUES (LAST_INSERT_ID(), @JohnDoeID);
INSERT INTO employeetasks (TaskID, EmployeeID) VALUES (LAST_INSERT_ID(), @JaneDoeID);

INSERT INTO tasks 
(Name, Description, Duration)
VALUES ("Test Task 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent leo purus, euismod a rhoncus ultricies, condimentum quis tortor. Integer scelerisque ac justo non sollicitudin. Sed finibus nulla sit amet cursus varius.", 30);

INSERT INTO employeetasks (TaskID, EmployeeID) VALUES (LAST_INSERT_ID(), @JohnDoeID);
INSERT INTO employeetasks (TaskID, EmployeeID) VALUES (LAST_INSERT_ID(), @JaneDoeID);

INSERT INTO tasks 
(Name, Description, Duration)
VALUES ("Test Task 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent leo purus, euismod a rhoncus ultricies, condimentum quis tortor. Integer scelerisque ac justo non sollicitudin. Sed finibus nulla sit amet cursus varius.", 30);

INSERT INTO employeetasks (TaskID, EmployeeID) VALUES (LAST_INSERT_ID(), @JohnDoeID);
INSERT INTO employeetasks (TaskID, EmployeeID) VALUES (LAST_INSERT_ID(), @JaneDoeID);

INSERT INTO tasks 
(Name, Description, Duration)
VALUES ("Test Task 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent leo purus, euismod a rhoncus ultricies, condimentum quis tortor. Integer scelerisque ac justo non sollicitudin. Sed finibus nulla sit amet cursus varius.", 30);

CALL Employee_GetAll();