DROP DATABASE IF EXISTS `ats`;

CREATE DATABASE ats;

CREATE TABLE `ats`.`employees` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(20) NOT NULL,
  `LastName` VARCHAR(30) NOT NULL,
  `SIN` INT NOT NULL,
  `HourlyRate` DECIMAL(19,2) NOT NULL,
  `isDeleted` BOOLEAN NOT NULL,
  `CreatedAt` DATETIME NOT NULL,
  `UpdatedAt` DATETIME NULL,
  `DeletedAt` DATETIME NULL,
  PRIMARY KEY (`ID`));

CREATE TABLE `ats`.`tasks` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) NOT NULL,
  `Description` VARCHAR(255) NOT NULL,
  `Duration` INT NOT NULL,
  PRIMARY KEY (`ID`));
  
CREATE TABLE `ats`.`teams` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) NOT NULL,
  `IsOnCall` BOOLEAN NOT NULL,
  `CreatedAt` DATETIME NOT NULL,
  `UpdatedAt` DATETIME NULL,
  `IsDeleted` BOOLEAN NOT NULL,
  `DeletedAt` DATETIME NULL,
  PRIMARY KEY (`ID`));
  
CREATE TABLE `ats`.`jobs` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Description` VARCHAR(255) NOT NULL,
  `ClientName` VARCHAR(50) NOT NULL,
  `Cost` DECIMAL(19,2) NOT NULL,
  `Revenue` DECIMAL(19,2) NOT NULL,
  `StartTime` DATETIME NOT NULL,
  `EndTime` DATETIME NOT NULL,
  `TeamID` INT NOT NULL,
  PRIMARY KEY (`ID`),
	FOREIGN KEY (`TeamID`)
	REFERENCES `ats`.`teams` (`ID`));
    
CREATE TABLE `ats`.`jobtasks` (
  `TaskID` INT NOT NULL,
  `JobID` INT NOT NULL,
  PRIMARY KEY (`TaskID`, `JobID`),
    FOREIGN KEY (`TaskID`)
    REFERENCES `ats`.`tasks` (`ID`),
    FOREIGN KEY (`JobID`)
    REFERENCES `ats`.`jobs` (`ID`));
    
CREATE TABLE `ats`.`employeetasks` (
  `TaskID` INT NOT NULL,
  `EmployeeID` INT NOT NULL,
  PRIMARY KEY (`TaskID`, `EmployeeID`),
    FOREIGN KEY (`TaskID`)
    REFERENCES `ats`.`tasks` (`ID`),
    FOREIGN KEY (`EmployeeID`)
    REFERENCES `ats`.`employees` (`ID`));
    
CREATE TABLE `ats`.`teammembers` (
  `EmployeeID` INT NOT NULL,
  `TeamID` INT NOT NULL,
  PRIMARY KEY (`EmployeeID`, `TeamID`),
    FOREIGN KEY (`EmployeeID`)
    REFERENCES `ats`.`employees` (`ID`),
    FOREIGN KEY (`TeamID`)
    REFERENCES `ats`.`teams` (`ID`));

DELIMITER ;
	
USE `ats`;

DROP PROCEDURE IF EXISTS `Task_Insert`;

DELIMITER $$
USE `ats`$$
CREATE PROCEDURE `Task_Insert` (
OUT TaskID INT,
IN Name VARCHAR(50),
IN Description VARCHAR(255),
IN Duration INT
)
BEGIN
INSERT INTO `ats`.`tasks`
(`ID`, `Name`, `Description`, `Duration`)
VALUES
(TaskID, Name, Description, Duration);
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Team_Insert`;

DELIMITER $$
CREATE PROCEDURE `Team_Insert` (
OUT TeamID INT,
IN Name VARCHAR(50),
IN IsOnCall BOOLEAN,
IN CreatedAt DATETIME,
IN UpdatedAt DATETIME,
IN isDeleted BOOLEAN,
IN DeletedAt Datetime
)
BEGIN
INSERT INTO `ats`.`teams`
(`ID`, `Name`, `IsOnCall`, `CreatedAt`, `UpdatedAt`,
`IsDeleted`, `DeletedAt`)
VALUES
(ID, Name, IsOnCall, CreatedAt, UpdatedAt,
IsDeleted, DeletedAt);
SET TeamID = LAST_INSERT_ID();
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `TeamMember_Insert`;

DELIMITER $$
CREATE PROCEDURE `TeamMember_Insert` (
	IN EmployeeID INT,
	IN TeamID INT
)
BEGIN
	INSERT INTO teammembers (
		`EmployeeID`,
		`TeamID`
	)
	VALUES (
		EmployeeID,
        TeamID
    );
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS `Employee_Insert`;

DELIMITER $$
CREATE PROCEDURE `Employee_Insert` (
	OUT ID INT,
	IN FirstName VARCHAR(20),
    IN LastName VARCHAR(30),
    IN SIN INT,
    IN HourlyRate DECIMAL,
    IN isDeleted BOOLEAN,
    IN CreatedAt DATETIME,
    IN UpdatedAt DATETIME,
    IN DeletedAt DATETIME
)
BEGIN
	INSERT INTO `employees`
		(
            `FirstName`,
            `LastName`,
            `SIN`,
            `HourlyRate`,
            `isDeleted`,
            `CreatedAt`,
            `UpdatedAt`,
            `DeletedAt`
        )
	VALUES
		(
            FirstName,
            LastName,
            SIN,
            HourlyRate,
            isDeleted,
            CreatedAt,
            UpdatedAt,
            DeletedAt
        );
	
    SET ID = LAST_INSERT_ID();
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Employee_GetByID`;

DELIMITER $$
CREATE PROCEDURE `Employee_GetByID` (
	IN EmployeeID INT
)
BEGIN
	SELECT * FROM `ats`.`employees` WHERE ID = EmployeeID;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Employee_GetAll`;

DELIMITER $$
CREATE PROCEDURE `Employee_GetAll`()
BEGIN
	SELECT * FROM employees;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Employee_GetSkills`;

DELIMITER $$
CREATE PROCEDURE `Employee_GetSkills`(IN EmployeeID INT)
BEGIN
	SELECT 
		tasks.ID,
        tasks.Name,
        tasks.Description,
        tasks.Duration
    FROM 
		employeetasks 
	INNER JOIN tasks 
		ON employeetasks.TaskID = tasks.ID AND employeetasks.EmployeeID = EmployeeID;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Employee_Delete`;

DELIMITER $$
CREATE PROCEDURE `Employee_Delete`(IN EmployeeID INT)
BEGIN
	DELETE FROM employees WHERE ID = EmployeeID;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Employee_GetNumberOfTeams`;

DELIMITER $$
CREATE PROCEDURE `Employee_GetNumberOfTeams`(IN ID INT)
BEGIN
	SELECT COUNT(*) AS `NumberOfTeams` FROM teammembers WHERE EmployeeID = ID;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Employee_Update`;

DELIMITER $$
CREATE PROCEDURE `Employee_Update`(
	IN EmployeeID INT,
	IN FirstName VARCHAR(20),
    IN LastName VARCHAR(30),
    IN SIN INT,
    IN HourlyRate DECIMAL,
    IN isDeleted BOOLEAN,
    IN CreatedAt DATETIME,
    IN UpdatedAt DATETIME,
    IN DeletedAt DATETIME
)
BEGIN
	UPDATE employees SET 
		FirstName = FirstName,
        LastName = LastName,
        SIN = SIN,
        HourlyRate = HourlyRate,
        isDeleted = isDeleted,
        createdAt = createdAt,
        UpdatedAt = UpdatedAt,
        DeletedAt = DeletedAt
	WHERE ID = EmployeeID;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Tasks_GetTasks`;

DELIMITER $$
CREATE PROCEDURE `Tasks_GetTasks`()
BEGIN
	SELECT * FROM tasks;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Tasks_GetTask`;

DELIMITER $$
CREATE PROCEDURE `Tasks_GetTask`(IN TaskID INT)
BEGIN
	SELECT * FROM tasks WHERE ID = TaskID;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Tasks_GetTaskemployees`;

DELIMITER $$
CREATE PROCEDURE `Tasks_GetTaskemployees`(IN EmployeeID INT)
BEGIN
	SELECT * FROM tasks WHERE ID = EmployeeID;
END$$

USE `ats`;
DROP procedure IF EXISTS `Task_Delete`;

DELIMITER $$
USE `ats`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Task_Delete`(
IN TaskID INT
)
BEGIN
	DELETE FROM `ats`.`tasks` WHERE (`ID` = TaskID);
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS `JobTasks_Insert`;

DELIMITER $$
CREATE PROCEDURE `JobTasks_Insert`(
	IN TaskID INT,
    IN JobID INT
)
BEGIN
	INSERT INTO jobtasks (
		`TaskID`,
        `JobID`
    )
    VALUES (
		TaskID,
        JobID
    );
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Jobs_Insert`;

DELIMITER $$
CREATE PROCEDURE `Jobs_Insert`(
	OUT JobID INT,
    IN Description VARCHAR(255),
    IN ClientName VARCHAR(50),
    IN Cost DECIMAL(19, 2),
    IN Revenue DECIMAL(19, 2),
    IN StartTime DATETIME,
    IN EndTime DATETIME,
    IN TeamID INT
)
BEGIN
	INSERT INTO jobs (
		`Description`,
        `ClientName`,
        `Cost`,
        `Revenue`,
        `StartTime`,
        `EndTime`,
        `TeamID`
    ) 
    VALUES (
		Description,
        ClientName,
        Cost,
        Revenue,
        StartTime,
        EndTime,
        TeamID
    );
    
    SET JobID = LAST_INSERT_ID();
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Jobs_GetTasks`;

DELIMITER $$
CREATE PROCEDURE `Jobs_GetTasks`(IN JobID INT)
BEGIN
	SELECT 
		tasks.ID,
        tasks.Name,
        tasks.Description,
        tasks.Duration
    FROM 
		jobtasks 
	INNER JOIN tasks 
		ON jobtasks.TaskID = tasks.ID AND jobtasks.JobID = JobID;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Jobs_GetAll`;

DELIMITER $$
CREATE PROCEDURE `Jobs_GetAll`()
BEGIN
	SELECT * FROM jobs;
END$$

DELIMITER ;

DROP procedure IF EXISTS `ats`.`TeamMember_Insert`;

DELIMITER $$

CREATE PROCEDURE `TeamMember_Insert`(
IN TeamID INT,
IN EmployeeID INT
)
BEGIN
INSERT INTO `ats`.`teammembers`
(`TeamID`, `EmployeeID`)
VALUES
(TeamID, EmployeeID);
END$$

DELIMITER ;

DELIMITER ;
DROP PROCEDURE IF EXISTS `Team_GetTeamMembers`;

DELIMITER $$
CREATE PROCEDURE `Team_GetTeamMembers`(IN TeamID INT)
BEGIN
	SELECT 
		employees.ID, 
        employees.FirstName, 
        employees.LastName, 
        employees.SIN, 
        employees.HourlyRate, 
        employees.isDeleted, 
        employees.CreatedAt, 
        employees.UpdatedAt, 
        employees.DeletedAt 
    FROM 
		teammembers 
	INNER JOIN employees 
		ON teammembers.EmployeeID = employees.ID AND teammembers.TeamID = TeamID;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Team_GetAll`;

DELIMITER $$
CREATE PROCEDURE `Team_GetAll`()
BEGIN
	SELECT * FROM teams;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Team_GetByID`;

DELIMITER $$
CREATE PROCEDURE `Team_GetByID`(IN TeamID INT)
BEGIN
	SELECT * FROM teams WHERE ID = TeamID;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Team_GetJobs`;

DELIMITER $$
CREATE PROCEDURE `Team_GetJobs`(IN ID INT)
BEGIN
	SELECT * FROM jobs WHERE TeamID = ID;
END$$

DELIMITER ;

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
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  