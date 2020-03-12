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
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  