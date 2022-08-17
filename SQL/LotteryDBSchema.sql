-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema lottery
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lottery
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lottery` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `lottery` ;

-- -----------------------------------------------------
-- Table `lottery`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lottery`.`customer` (
                                                    `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                    `username` VARCHAR(255) NULL DEFAULT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(32) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UC_customer` (`id` ASC, `email` ASC) VISIBLE,
    UNIQUE INDEX `id` (`id` ASC, `email` ASC) VISIBLE,
    UNIQUE INDEX `email` (`email` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 23
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lottery`.`lot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lottery`.`lot` (
                                               `id` BIGINT NOT NULL AUTO_INCREMENT,
                                               `name` VARCHAR(45) NULL DEFAULT NULL,
    `description` VARCHAR(450) NULL DEFAULT NULL,
    `start_price` DECIMAL(19,4) NULL DEFAULT NULL,
    `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `customer_id` BIGINT NOT NULL,
    `offers` INT NULL DEFAULT '0',
    `is_active` TINYINT NULL DEFAULT '1',
    PRIMARY KEY (`id`),
    INDEX `fk_lot_customer1_idx` (`customer_id` ASC) VISIBLE,
    CONSTRAINT `fk_lot_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `lottery`.`customer` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
    ENGINE = InnoDB
    AUTO_INCREMENT = 16
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lottery`.`lot_offer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lottery`.`lot_offer` (
                                                     `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `description` VARCHAR(450) NULL DEFAULT NULL,
    `suggested_price` DECIMAL(19,4) NULL DEFAULT NULL,
    `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `customer_id` BIGINT NOT NULL,
    `lot_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_lot_offer_customer_idx` (`customer_id` ASC) VISIBLE,
    INDEX `fk_lot_offer_lot1_idx` (`lot_id` ASC) VISIBLE,
    CONSTRAINT `fk_lot_offer_customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `lottery`.`customer` (`id`)
    ON UPDATE RESTRICT,
    CONSTRAINT `fk_lot_offer_lot1`
    FOREIGN KEY (`lot_id`)
    REFERENCES `lottery`.`lot` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 11
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

USE `lottery`;

DELIMITER $$
USE `lottery`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `lottery`.`ai_lot_update_price`
AFTER INSERT ON `lottery`.`lot_offer`
FOR EACH ROW
begin

update lottery.lot set start_price = new.suggested_price where id = new.lot_id;

end$$

USE `lottery`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `lottery`.`bi_lot_offer_verify_price`
BEFORE INSERT ON `lottery`.`lot_offer`
FOR EACH ROW
begin
	declare current_lot_price double;

    set current_lot_price = (select start_price from lot where id = new.lot_id);

	if(new.suggested_price <= current_lot_price) then
		signal sqlstate '45001' set message_text = "Suggested price must be bigger than the current price";
end if;
update lottery.lot set offers = (offers+1) where id = new.lot_id;
end$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;