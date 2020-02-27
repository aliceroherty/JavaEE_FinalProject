DROP SCHEMA IF EXISTS ats;

CREATE SCHEMA ats;

CREATE TABLE `ats`.`employeesemployees` (
  `ID` INT NOT NULL,
  `FirstName` VARCHAR(20) NOT NULL,
  `LastName` VARCHAR(30) NOT NULL,
  `SIN` INT NOT NULL,
  `HourlyRate` DECIMAL(19,2) NOT NULL,
  `isDeleted` TINYINT(1) NOT NULL,
  `CreatedAt` DATETIME NOT NULL,
  `UpdatedAt` DATETIME NULL,
  `DeletedAt` DATETIME NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC),
  UNIQUE INDEX `SIN_UNIQUE` (`SIN` ASC));

CREATE TABLE `ats`.`tasks` (
  `ID` INT(11) NOT NULL,
  `Name` VARCHAR(50) NOT NULL,
  `Description` VARCHAR(255) NOT NULL,
  `Duration` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC));
  
CREATE TABLE `ats`.`teams` (
  `ID` INT(11) NOT NULL,
  `Name` VARCHAR(50) NOT NULL,
  `IsOnCall` TINYINT(1) NOT NULL,
  `CreatedAt` DATETIME NOT NULL,
  `UpdatedAt` DATETIME NULL,
  `IsDeleted` TINYINT(1) NOT NULL,
  `DeletedAt` DATETIME NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC));
  
  CREATE TABLE `ats`.`jobs` (
  `ID` INT NOT NULL,
  `Description` VARCHAR(255) NOT NULL,
  `ClientName` VARCHAR(50) NOT NULL,
  `Cost` DECIMAL(19,2) NOT NULL,
  `Revenue` DECIMAL(19,2) NOT NULL,
  `StartTime` DATETIME NOT NULL,
  `EndTIme` DATETIME NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC),
  CONSTRAINT `ID`
    FOREIGN KEY (`ID`)
    REFERENCES `ats`.`teams` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  