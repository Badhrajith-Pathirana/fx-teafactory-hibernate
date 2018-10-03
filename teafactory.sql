-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.17-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for teafactory
CREATE DATABASE IF NOT EXISTS `teafactory` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `teafactory`;

-- Dumping structure for table teafactory.bank
CREATE TABLE IF NOT EXISTS `bank` (
  `bankid` int(11) NOT NULL AUTO_INCREMENT,
  `bankName` varchar(100) NOT NULL,
  PRIMARY KEY (`bankid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table teafactory.bank: ~4 rows (approximately)
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` (`bankid`, `bankName`) VALUES
	(1, 'Commercial Credit'),
	(3, 'HSBC'),
	(4, 'DFCC'),
	(5, 'HNB');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;

-- Dumping structure for table teafactory.branch
CREATE TABLE IF NOT EXISTS `branch` (
  `branchid` int(11) NOT NULL AUTO_INCREMENT,
  `bankid` int(11) DEFAULT NULL,
  `branchName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`branchid`),
  KEY `FK_branch_bank` (`bankid`),
  CONSTRAINT `FK_branch_bank` FOREIGN KEY (`bankid`) REFERENCES `bank` (`bankid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table teafactory.branch: ~5 rows (approximately)
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` (`branchid`, `bankid`, `branchName`) VALUES
	(2, 1, 'Walampuri'),
	(3, 1, 'Wallawaththa'),
	(4, 3, 'Kasagahawaththa'),
	(5, 4, 'Gampaha'),
	(6, 3, 'kelaniya');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;

-- Dumping structure for table teafactory.credit
CREATE TABLE IF NOT EXISTS `credit` (
  `creditid` int(11) NOT NULL AUTO_INCREMENT,
  `supplierid` int(11) NOT NULL,
  `credit_type` int(11) NOT NULL,
  `date` date NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`creditid`),
  KEY `FK_credit_supplier` (`supplierid`),
  KEY `FK_credit_credit_type` (`credit_type`),
  CONSTRAINT `FK_credit_credit_type` FOREIGN KEY (`credit_type`) REFERENCES `credit_type` (`typeid`),
  CONSTRAINT `FK_credit_supplier` FOREIGN KEY (`supplierid`) REFERENCES `supplier` (`supplierno`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table teafactory.credit: ~3 rows (approximately)
/*!40000 ALTER TABLE `credit` DISABLE KEYS */;
INSERT INTO `credit` (`creditid`, `supplierid`, `credit_type`, `date`, `amount`) VALUES
	(3, 3, 1, '2018-06-29', 20000.00),
	(4, 3, 1, '2018-06-29', 200.00),
	(5, 3, 1, '2018-06-30', 122.00);
/*!40000 ALTER TABLE `credit` ENABLE KEYS */;

-- Dumping structure for table teafactory.credit_type
CREATE TABLE IF NOT EXISTS `credit_type` (
  `typeid` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`typeid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table teafactory.credit_type: ~1 rows (approximately)
/*!40000 ALTER TABLE `credit_type` DISABLE KEYS */;
INSERT INTO `credit_type` (`typeid`, `type_name`) VALUES
	(1, 'PAYMENT');
/*!40000 ALTER TABLE `credit_type` ENABLE KEYS */;

-- Dumping structure for table teafactory.debit
CREATE TABLE IF NOT EXISTS `debit` (
  `debitid` int(11) NOT NULL AUTO_INCREMENT,
  `purchaseid` int(11) NOT NULL DEFAULT '0',
  `debitdate` date NOT NULL,
  `supplierid` int(11) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`debitid`),
  KEY `FK_debit_supplier` (`supplierid`),
  KEY `FK_debit_purchase` (`purchaseid`),
  CONSTRAINT `FK_debit_purchase` FOREIGN KEY (`purchaseid`) REFERENCES `purchase` (`purchase_id`),
  CONSTRAINT `FK_debit_supplier` FOREIGN KEY (`supplierid`) REFERENCES `supplier` (`supplierno`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table teafactory.debit: ~3 rows (approximately)
/*!40000 ALTER TABLE `debit` DISABLE KEYS */;
INSERT INTO `debit` (`debitid`, `purchaseid`, `debitdate`, `supplierid`, `amount`) VALUES
	(4, 4, '2018-06-28', 3, 1960.00),
	(5, 5, '2018-06-28', 3, 3136.00),
	(7, 7, '2018-06-29', 3, 1960.00);
/*!40000 ALTER TABLE `debit` ENABLE KEYS */;

-- Dumping structure for table teafactory.purchase
CREATE TABLE IF NOT EXISTS `purchase` (
  `purchase_id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_date` date NOT NULL,
  `supplierid` int(11) NOT NULL,
  `akg` decimal(10,3) NOT NULL,
  `bkg` decimal(10,3) NOT NULL,
  PRIMARY KEY (`purchase_id`),
  KEY `FK_purchase_supplier` (`supplierid`),
  CONSTRAINT `FK_purchase_supplier` FOREIGN KEY (`supplierid`) REFERENCES `supplier` (`supplierno`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table teafactory.purchase: ~3 rows (approximately)
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` (`purchase_id`, `purchase_date`, `supplierid`, `akg`, `bkg`) VALUES
	(4, '2018-06-28', 3, 10.000, 10.000),
	(5, '2018-06-28', 3, 22.000, 10.000),
	(7, '2018-06-29', 3, 10.000, 10.000);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;

-- Dumping structure for table teafactory.rate
CREATE TABLE IF NOT EXISTS `rate` (
  `rateMonth` date NOT NULL,
  `akgper` decimal(10,2) DEFAULT NULL,
  `bkgper` decimal(10,2) DEFAULT NULL,
  `travelling` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`rateMonth`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table teafactory.rate: ~4 rows (approximately)
/*!40000 ALTER TABLE `rate` DISABLE KEYS */;
INSERT INTO `rate` (`rateMonth`, `akgper`, `bkgper`, `travelling`) VALUES
	('2018-06-27', 100.00, 100.00, 2.00),
	('2018-07-03', 200.00, 240.00, 22.00),
	('2018-08-01', 123.00, 122.00, 21.00),
	('2018-09-01', 12.33, 120.55, 2.55);
/*!40000 ALTER TABLE `rate` ENABLE KEYS */;

-- Dumping structure for table teafactory.route
CREATE TABLE IF NOT EXISTS `route` (
  `routeid` int(11) NOT NULL AUTO_INCREMENT,
  `routename` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`routeid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table teafactory.route: ~1 rows (approximately)
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` (`routeid`, `routename`) VALUES
	(1, 'Pelenda');
/*!40000 ALTER TABLE `route` ENABLE KEYS */;

-- Dumping structure for table teafactory.supplier
CREATE TABLE IF NOT EXISTS `supplier` (
  `supplierno` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `route` int(11) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`supplierno`),
  KEY `FK_supplier_route` (`route`),
  CONSTRAINT `FK_supplier_route` FOREIGN KEY (`route`) REFERENCES `route` (`routeid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table teafactory.supplier: ~1 rows (approximately)
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` (`supplierno`, `name`, `route`, `phone`, `address`) VALUES
	(3, 'Sangakkara Palihawadana', 1, '1484511', 'Ampara');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;

-- Dumping structure for table teafactory.supplier_bank
CREATE TABLE IF NOT EXISTS `supplier_bank` (
  `branchid` int(11) NOT NULL,
  `supplierid` int(11) NOT NULL,
  `acc_no` varchar(75) NOT NULL,
  PRIMARY KEY (`branchid`,`supplierid`),
  KEY `FK_supplier_bankdetails_supplier` (`supplierid`),
  CONSTRAINT `FK_supplier_bankdetails_branch` FOREIGN KEY (`branchid`) REFERENCES `branch` (`branchid`),
  CONSTRAINT `FK_supplier_bankdetails_supplier` FOREIGN KEY (`supplierid`) REFERENCES `supplier` (`supplierno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table teafactory.supplier_bank: ~0 rows (approximately)
/*!40000 ALTER TABLE `supplier_bank` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier_bank` ENABLE KEYS */;

-- Dumping structure for table teafactory.user
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `acc_type` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table teafactory.user: ~1 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`username`, `password`, `acc_type`) VALUES
	('admin', 'admin', 'Admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
