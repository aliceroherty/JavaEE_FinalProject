USE ats;

INSERT INTO employees 
(FirstName, LastName, SIN, HourlyRate, isDeleted, CreatedAt, UpdatedAt, DeletedAt) 
VALUES ("John", "Doe", 123456789, 15.25, false, sysdate(), NULL, NULL);