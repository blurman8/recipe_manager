DROP TABLE IF EXISTS `ingredient`;
CREATE TABLE `ingredient` (
  `ID` int(11) AUTO_INCREMENT PRIMARY KEY,
  `Nameingredient` varchar(45) NOT NULL,
  `Description` varchar(500) NOT NULL
);
DROP TABLE IF EXISTS `recipe`;
CREATE TABLE `recipe` (
  `ID` int(11) AUTO_INCREMENT PRIMARY KEY,
  `Namerecipe` varchar(45) NOT NULL,
  `Description` varchar(500) NOT NULL
);
DROP TABLE IF EXISTS `predictcost`;
CREATE TABLE `predictcost` (
  `ID` int(11) AUTO_INCREMENT PRIMARY KEY,
  `RID` int(11) NOT NULL,
  `Total` double NOT NULL
);
DROP TABLE IF EXISTS `recipemenu`;
CREATE TABLE `recipemenu` (
  `ID` int(11) AUTO_INCREMENT PRIMARY KEY,
  `RID` int(11) NOT NULL,
  `INID` int(11) NOT NULL,
  `Total` double NOT NULL,
  `UID` int(11) NOT NULL
);
DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit` (
  `ID` int(11) AUTO_INCREMENT PRIMARY KEY,
  `Nameunit` varchar(45) NOT NULL,
  `Total1` double NOT NULL,
  `UID2` varchar(45) NOT NULL,
  `Total2` double NOT NULL
);
DROP TABLE IF EXISTS `ingredientcost`;
CREATE TABLE `ingredientcost` (
  `INID` int(11) NOT NULL PRIMARY KEY,
  `Nameingredient` varchar(45) NOT NULL,  
  `Total` double NOT NULL,
  `IUID` int(11) NOT NULL,
  `Total2` double NOT NULL,
  `UID` int(11) NOT NULL,
  `Nameunit` varchar(45) NOT NULL,  
  `Cost` double NOT NULL ,
  `Countitem` double NOT NULL,
  `Allcost` double NOT NULL 
);