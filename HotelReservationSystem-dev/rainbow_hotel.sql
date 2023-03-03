-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jul 12, 2021 at 05:20 PM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rainbow_hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `AdminID` varchar(20) NOT NULL,
  `FName` varchar(50) NOT NULL,
  `LName` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `MobileNumber` int(11) NOT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`AdminID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`AdminID`, `FName`, `LName`, `Email`, `MobileNumber`, `Password`) VALUES
('Admin', 'testing', 'testing', 'testing@gmail.com', 774248681, 'admin123');

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
CREATE TABLE IF NOT EXISTS `bill` (
  `BillID` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date DEFAULT NULL,
  `Amount` float DEFAULT NULL,
  `CustomerID` int(11) NOT NULL,
  PRIMARY KEY (`BillID`),
  KEY `CustomerID` (`CustomerID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`BillID`, `Date`, `Amount`, `CustomerID`) VALUES
(1, '2021-07-09', 3000, 1),
(2, '2021-07-09', 12250, 1);

-- --------------------------------------------------------

--
-- Table structure for table `bill_event`
--

DROP TABLE IF EXISTS `bill_event`;
CREATE TABLE IF NOT EXISTS `bill_event` (
  `Bill_id` int(11) NOT NULL,
  `Event_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill_event`
--

INSERT INTO `bill_event` (`Bill_id`, `Event_id`) VALUES
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `bill_reservation`
--

DROP TABLE IF EXISTS `bill_reservation`;
CREATE TABLE IF NOT EXISTS `bill_reservation` (
  `Bill_id` int(11) NOT NULL,
  `Reservation_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill_reservation`
--

INSERT INTO `bill_reservation` (`Bill_id`, `Reservation_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `CustomerID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `VehicleNum` varchar(50) NOT NULL,
  `TP` int(10) NOT NULL,
  `NIC` varchar(50) NOT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CustomerID`, `Name`, `Address`, `Email`, `VehicleNum`, `TP`, `NIC`) VALUES
(1, 'tester', 'tester ge gedara', 'testing@gmail.com', 'gh-4567', 774248561, '123456789v');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `EventID` int(11) NOT NULL AUTO_INCREMENT,
  `MenuType` varchar(50) DEFAULT NULL,
  `total` float DEFAULT NULL,
  `Discount` float DEFAULT NULL,
  `CustomerID` int(11) NOT NULL,
  `number_of_plates` int(11) NOT NULL,
  `Time` varchar(4) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`EventID`),
  KEY `CustomerID` (`CustomerID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`EventID`, `MenuType`, `total`, `Discount`, `CustomerID`, `number_of_plates`, `Time`, `date`) VALUES
(1, '1', 12850, 10000, 1, 100, 'Day', '2021-07-14');

-- --------------------------------------------------------

--
-- Table structure for table `ledger`
--

DROP TABLE IF EXISTS `ledger`;
CREATE TABLE IF NOT EXISTS `ledger` (
  `LedgerID` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(50) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `BillID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`LedgerID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `BillID` (`BillID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `ReservationID` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(50) DEFAULT NULL,
  `RoomNo` int(20) DEFAULT NULL,
  `NumOfGuests` int(20) DEFAULT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `Check_in_date` date DEFAULT NULL,
  `Check_in_time` time DEFAULT NULL,
  `Check_out_date` date DEFAULT NULL,
  `Check_out_time` time DEFAULT NULL,
  PRIMARY KEY (`ReservationID`),
  KEY `CustomerID` (`CustomerID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`ReservationID`, `Type`, `RoomNo`, `NumOfGuests`, `CustomerID`, `Check_in_date`, `Check_in_time`, `Check_out_date`, `Check_out_time`) VALUES
(1, 'nonac', 8, 4, 1, '2021-07-09', '13:23:34', '2021-07-14', '16:23:34');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
