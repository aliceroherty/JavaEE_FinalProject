USE `ats`;

DROP PROCEDURE IF EXISTS `Task_Insert`;

DELIMITER $$
USE `ats`$$
CREATE PROCEDURE `Task_Insert` (
IN TaskID INT,
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
DROP PROCEDURE IF EXISTS `Tasks_GetTaskemployees`;

DELIMITER $$
CREATE PROCEDURE `Tasks_GetTaskemployees`(IN EmployeeID INT)
BEGIN
	SELECT * FROM tasks WHERE ID = EmployeeID;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `Tasks_GetTaskemployees`;

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