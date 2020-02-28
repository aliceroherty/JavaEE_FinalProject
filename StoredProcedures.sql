USE `ats`;
DROP procedure IF EXISTS `Employee_Insert`;

DELIMITER $$
USE `ats`$$
CREATE PROCEDURE `Employee_Insert` (
	IN EmployeeID INT,
	IN FirstName VARCHAR(20),
    IN LastName VARCHAR(30),
    IN SIN INT,
    IN HourlyRate DECIMAL,
    IN isDeleted TINYINT(1),
    IN CreatedAt DATETIME,
    IN UpdatedAt DATETIME,
    IN DeletedAt DATETIME
    
)

DELIMITER ;

USE `ats`;
DROP procedure IF EXISTS `Task_Insert`;

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

USE `ats`;
DROP procedure IF EXISTS `Team_Insert`;

DELIMITER $$
USE `ats`$$
CREATE PROCEDURE `Team_Insert` (
IN TeamID INT,
IN Name VARCHAR(50),
IN IsOnCall TINYINT(1),
IN CreatedAt DATETIME,
IN UpdatedAt DATETIME,
IN isDeleted TINYINT(1),
IN DeletedAt Datetime
)
BEGIN
INSERT INTO `ats`.`teams`
(`ID`, `Name`, `IsOnCall`, `CreatedAt`, `UpdatedAt`,
`IsDeleted`, `DeletedAt`)
VALUES
(ID, Name, IsOnCall, CreatedAt, UpdatedAt,
IsDeleted, DeletedAt);
END$$

DELIMITER ;

USE `ats`;
DROP procedure IF EXISTS `Employee_GetByID`;

DELIMITER $$
USE `ats`$$
CREATE PROCEDURE `Employee_GetByID` (
IN EmployeeID INT
)
BEGIN
	SELECT * FROM `ats`.`employees` WHERE ID = EmployeeID;
END$$

DELIMITER ;


