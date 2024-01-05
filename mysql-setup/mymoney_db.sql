
  CREATE TABLE `customer_info` (
    `customer_id` int NOT NULL AUTO_INCREMENT,
    `customerFName` varchar(45) DEFAULT NULL,
    `customerLName` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`customer_id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `bankdetails` (
  `bank_Id` int NOT NULL AUTO_INCREMENT,
  `bank_Name` varchar(45) DEFAULT NULL,
  `bank_Location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`bank_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


    CREATE TABLE `mymoney_db`.`customer_account` (
      `id` INT NOT NULL,
      `accountNumber` INT NULL,
      `bankId` INT NULL,
      `customerId` INT NULL,
      `accountType` VARCHAR(45) NULL,
      PRIMARY KEY (`id`));

      ALTER TABLE `mymoney_db`.`customer_account`
      ADD INDEX `fk_bankId_idx` (`bankId` ASC) VISIBLE,
      ADD INDEX `fk_customerId_idx` (`customerId` ASC) VISIBLE;

    ALTER TABLE `mymoney_db`.`customer_account`
    CHANGE COLUMN `accountNumber` `accountNumber` INT NOT NULL ,
    ADD UNIQUE INDEX `accountNumber_UNIQUE` (`accountNumber` ASC) VISIBLE;
    ;
      ALTER TABLE `mymoney_db`.`customer_account`
      ADD CONSTRAINT `fk_bankId`
        FOREIGN KEY (`bankId`)
        REFERENCES `mymoney_db`.`bankdetails` (`bankId`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
      ADD CONSTRAINT `fk_customerId`
        FOREIGN KEY (`customerId`)
        REFERENCES `mymoney_db`.`customer_info` (`customerId`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

        CREATE TABLE `mymoney_db`.`account_txn` (
          `txnId` INT NOT NULL,
          `accountNumber` INT NULL,
          `txnType` VARCHAR(45) NULL,
          `amount` VARCHAR(45) NULL,
          PRIMARY KEY (`txnId`));

ALTER TABLE `mymoney_db`.`account_txn`
ADD INDEX `fk_accountNumber_idx` (`accountNumber` ASC) VISIBLE;
;
ALTER TABLE `mymoney_db`.`account_txn`
ADD CONSTRAINT `fk_accountNumber`
  FOREIGN KEY (`accountNumber`)
  REFERENCES `mymoney_db`.`customer_account` (`accountNumber`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;



