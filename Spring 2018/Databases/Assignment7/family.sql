-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 01, 2018 at 12:20 AM
-- Server version: 5.5.56-MariaDB
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `espirj96`
--

-- --------------------------------------------------------

--
-- Table structure for table `Event`
--

CREATE TABLE IF NOT EXISTS `Event` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `personId1` int(11) NOT NULL,
  `personId2` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Event`
--

INSERT INTO `Event` (`id`, `date`, `name`, `location`, `personId1`, `personId2`) VALUES
(3, '1927-04-01', 'marriage', 'oshkosh', 1, 2),
(4, '1951-04-03', 'marriage', 'oshkosh', 3, 6),
(5, '1960-07-02', 'marriage', 'oshkosh', 4, 7),
(6, '1969-03-09', 'marriage', 'neenah', 5, 8),
(7, '1973-10-01', 'marriage', 'milwaukee', 9, 11),
(8, '1989-06-07', 'marriage', 'milwaukee', 9, 12),
(9, '2010-06-01', 'marriage', 'chicago', 14, 26),
(10, '1994-02-02', 'marriage', 'neenah', 17, 18),
(11, '2009-05-05', 'marriage', 'sheboygan', 19, 20),
(12, '2015-04-05', 'marriage', 'green bay', 22, 25),
(15, '1980-04-01', 'divorce', 'milwaukee', 9, 11);

-- --------------------------------------------------------

--
-- Table structure for table `Person`
--

CREATE TABLE IF NOT EXISTS `Person` (
  `id` int(11) NOT NULL,
  `bio` varchar(1000) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `middleName` varchar(20) DEFAULT NULL,
  `lastName` varchar(20) DEFAULT NULL,
  `lastKnownAddress` varchar(1000) DEFAULT NULL,
  `lastKnownPhoneNumber` varchar(12) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `birthLocation` varchar(50) DEFAULT NULL,
  `death` date DEFAULT NULL,
  `deathLocation` varchar(50) DEFAULT NULL,
  `parent1` int(11) DEFAULT NULL,
  `parent2` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Person`
--

INSERT INTO `Person` (`id`, `bio`, `gender`, `firstName`, `middleName`, `lastName`, `lastKnownAddress`, `lastKnownPhoneNumber`, `birth`, `birthLocation`, `death`, `deathLocation`, `parent1`, `parent2`) VALUES
(1, 'veteran; farmer', 'm', 'William', '', 'Wells', '123 main st oshkosh', NULL, '1901-08-09', 'oshkosh', '1989-04-05', 'milwaukee', NULL, NULL),
(2, 'some notes here', 'f', 'Lucy', '', 'Lawrence', NULL, NULL, '1909-12-12', 'oshkosh', '1965-07-07', 'oshkosh', NULL, NULL),
(3, NULL, 'm', 'Leroy', '', 'Wells', NULL, NULL, '1928-01-02', 'oshkosh', '2010-06-01', 'oshkosh', 1, 2),
(4, NULL, 'f', 'Tonya', '', 'Evans', NULL, '9201112222', '1934-06-04', 'oshkosh', NULL, NULL, 1, 2),
(5, 'teacher', 'm', 'Clyde', '', 'Wells', NULL, NULL, '1937-12-11', 'oshkosh', NULL, NULL, 1, 2),
(6, NULL, 'f', 'Anne', '', 'Allison', NULL, NULL, '1931-03-07', 'de pere', NULL, NULL, NULL, NULL),
(7, NULL, 'm', 'Ross', '', 'Evans', '456 main st milwaukee', NULL, '1933-05-17', 'milwaukee', NULL, NULL, NULL, NULL),
(8, 'some notes here', 'f', 'Nina', '', 'Adkins', NULL, NULL, '1941-11-01', 'neenah', NULL, NULL, NULL, NULL),
(9, 'farmer', 'm', 'Oliver', '', 'Wells', NULL, NULL, '1952-05-05', 'oshkosh', NULL, NULL, 3, 6),
(10, NULL, 'm', 'Gerald', '', 'Wells', NULL, NULL, '1955-06-08', 'oshkosh', NULL, NULL, 3, 6),
(11, NULL, 'f', 'Emma', '', 'Rhodes', NULL, '4142221111', '1952-05-02', NULL, NULL, NULL, NULL, NULL),
(12, NULL, 'f', 'Melody', '', 'Mccormick', NULL, NULL, '1965-08-09', 'milwaukee', NULL, NULL, NULL, NULL),
(13, 'some notes here', 'f', 'Marion', '', 'Wells', NULL, NULL, '1974-01-01', 'milwaukee', '2008-11-01', 'Dallas, TX', 9, 11),
(14, NULL, 'm', 'Bruce', '', 'Wells', 'Address goes here', NULL, '1978-03-05', 'milwaukee', NULL, NULL, 9, 11),
(15, NULL, 'm', 'William', '', 'Wells', NULL, NULL, '1990-07-07', 'milwaukee', NULL, NULL, 9, 12),
(16, NULL, 'm', 'Marshall', '', 'Wells', NULL, NULL, '1985-08-08', 'new york', NULL, NULL, 10, NULL),
(17, 'some notes here', 'f', 'Judy', '', 'May', NULL, NULL, '1970-01-01', 'neenah', NULL, NULL, 5, 8),
(18, NULL, 'm', 'Noah', '', 'May', NULL, NULL, '1972-03-30', 'neenah', NULL, NULL, NULL, NULL),
(19, NULL, 'm', 'Ronald', '', 'Wells', NULL, NULL, '2010-06-01', 'neenah', NULL, NULL, 5, 8),
(20, 'some notes here', 'f', 'April', '', 'Larson', NULL, NULL, '1973-06-30', 'madison', NULL, NULL, NULL, NULL),
(21, NULL, 'm', 'Ryan', '', 'Wells', NULL, NULL, '2010-06-01', 'neenah', NULL, NULL, 19, 20),
(22, NULL, 'm', 'Victor', '', 'May', NULL, NULL, '1995-01-01', 'neenah', NULL, NULL, 17, 18),
(23, NULL, 'm', 'Bryon', '', 'May', NULL, NULL, '2002-04-18', 'neenah', NULL, NULL, 17, 18),
(24, NULL, 'f', 'Edna', '', 'May', NULL, NULL, '2004-05-20', 'neenah', NULL, NULL, 17, 18),
(25, NULL, 'f', 'Lucille', '', 'Webster', NULL, '1234567890', '1997-06-22', 'green bay', NULL, NULL, NULL, NULL),
(26, NULL, 'f', 'Tonya', '', 'Dexter', NULL, NULL, '1980-11-04', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `Relation`
--

CREATE TABLE IF NOT EXISTS `Relation` (
  `id` int(11) NOT NULL,
  `pid1` int(11) NOT NULL,
  `pid2` int(11) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `isArchived` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Relation`
--

INSERT INTO `Relation` (`id`, `pid1`, `pid2`, `description`, `isArchived`) VALUES
(1, 1, 2, 'married', 0),
(2, 3, 6, 'married', 0),
(3, 4, 7, 'married', 0),
(4, 5, 8, 'married', 0),
(5, 9, 11, 'married', 1),
(6, 9, 12, 'married', 0),
(7, 14, 26, 'married', 0),
(8, 17, 18, 'married', 0),
(9, 19, 20, 'married', 0),
(10, 22, 25, 'married', 0),
(11, 9, 11, 'divorced', 0);

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `username` varchar(12) NOT NULL,
  `password` varchar(12) NOT NULL,
  `permission` tinyint(1) NOT NULL COMMENT '0 = read, 1 = write, 3=admin'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`username`, `password`, `permission`) VALUES
('admin', '123456', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Event`
--
ALTER TABLE `Event`
  ADD PRIMARY KEY (`id`),
  ADD KEY `personId1_2` (`personId1`),
  ADD KEY `personId1` (`personId1`),
  ADD KEY `personId2` (`personId2`);

--
-- Indexes for table `Person`
--
ALTER TABLE `Person`
  ADD PRIMARY KEY (`id`),
  ADD KEY `parent1` (`parent1`),
  ADD KEY `parent1_2` (`parent1`),
  ADD KEY `parent2` (`parent2`);

--
-- Indexes for table `Relation`
--
ALTER TABLE `Relation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pid1` (`pid1`),
  ADD KEY `pid2` (`pid2`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Event`
--
ALTER TABLE `Event`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `Person`
--
ALTER TABLE `Person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `Relation`
--
ALTER TABLE `Relation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Event`
--
ALTER TABLE `Event`
  ADD CONSTRAINT `person2` FOREIGN KEY (`personId2`) REFERENCES `Person` (`id`),
  ADD CONSTRAINT `person1` FOREIGN KEY (`personId1`) REFERENCES `Person` (`id`);

--
-- Constraints for table `Person`
--
ALTER TABLE `Person`
  ADD CONSTRAINT `parent1` FOREIGN KEY (`parent1`) REFERENCES `Person` (`id`),
  ADD CONSTRAINT `parent2` FOREIGN KEY (`parent2`) REFERENCES `Person` (`id`);

--
-- Constraints for table `Relation`
--
ALTER TABLE `Relation`
  ADD CONSTRAINT `Relation_ibfk_1` FOREIGN KEY (`pid1`) REFERENCES `Person` (`id`),
  ADD CONSTRAINT `Relation_ibfk_2` FOREIGN KEY (`pid2`) REFERENCES `Person` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
