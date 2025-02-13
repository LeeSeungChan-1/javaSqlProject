-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db_javasqlproject
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_javasqlproject
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_javasqlproject` DEFAULT CHARACTER SET utf8mb3 ;
USE `db_javasqlproject` ;

-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_company` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_company` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `companyName` VARCHAR(20) NOT NULL,
  `companyCode` CHAR(3) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_material`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_material` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_material` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `materialCode` CHAR(6) NOT NULL,
  `materialName` VARCHAR(50) NOT NULL,
  `materialUnit` CHAR(1) NOT NULL,
  `price` INT NOT NULL,
  `tbl_company_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_material_tbl_company1_idx` (`tbl_company_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_material_tbl_company1`
    FOREIGN KEY (`tbl_company_id`)
    REFERENCES `db_javasqlproject`.`tbl_company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_materialamount`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_materialamount` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_materialamount` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `materialAmount` INT NOT NULL,
  `tbl_material_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_materialAmount_tbl_material1_idx` (`tbl_material_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_materialAmount_tbl_material1`
    FOREIGN KEY (`tbl_material_id`)
    REFERENCES `db_javasqlproject`.`tbl_material` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_materialorder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_materialorder` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_materialorder` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `orderNumber` CHAR(11) NOT NULL,
  `orderDate` DATE NOT NULL,
  `orderer` CHAR(8) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_materialorderdetail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_materialorderdetail` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_materialorderdetail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `orderNumberDetail` CHAR(3) NOT NULL,
  `amount` INT NOT NULL,
  `tbl_materialOrder_id` BIGINT NOT NULL,
  `tbl_material_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_materialOrderDetail_tbl_materialOrder1_idx` (`tbl_materialOrder_id` ASC) VISIBLE,
  INDEX `fk_tbl_materialOrderDetail_tbl_material1_idx` (`tbl_material_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_materialOrderDetail_tbl_material1`
    FOREIGN KEY (`tbl_material_id`)
    REFERENCES `db_javasqlproject`.`tbl_material` (`id`),
  CONSTRAINT `fk_tbl_materialOrderDetail_tbl_materialOrder1`
    FOREIGN KEY (`tbl_materialOrder_id`)
    REFERENCES `db_javasqlproject`.`tbl_materialorder` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_materialwarehousing`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_materialwarehousing` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_materialwarehousing` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `warehousingNumber` CHAR(11) NOT NULL,
  `warehousingDate` DATE NOT NULL,
  `wearer` CHAR(8) NOT NULL,
  `tbl_materialOrder_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_materialWarehousing_tbl_materialOrder1_idx` (`tbl_materialOrder_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_materialWarehousing_tbl_materialOrder1`
    FOREIGN KEY (`tbl_materialOrder_id`)
    REFERENCES `db_javasqlproject`.`tbl_materialorder` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_materialwarehousingdetail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_materialwarehousingdetail` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_materialwarehousingdetail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `warehousingNumberDetail` CHAR(3) NOT NULL,
  `amount` INT NOT NULL,
  `tbl_material_id` BIGINT NOT NULL,
  `tbl_materialWarehousing_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_materialWarehousingDetail_tbl_material1_idx` (`tbl_material_id` ASC) VISIBLE,
  INDEX `fk_tbl_materialWarehousingDetail_tbl_materialWarehousing1_idx` (`tbl_materialWarehousing_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_materialWarehousingDetail_tbl_material1`
    FOREIGN KEY (`tbl_material_id`)
    REFERENCES `db_javasqlproject`.`tbl_material` (`id`),
  CONSTRAINT `fk_tbl_materialWarehousingDetail_tbl_materialWarehousing1`
    FOREIGN KEY (`tbl_materialWarehousing_id`)
    REFERENCES `db_javasqlproject`.`tbl_materialwarehousing` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_payment` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_payment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `paymentNumber` CHAR(11) NOT NULL,
  `register` CHAR(8) NOT NULL,
  `tbl_materialOrder_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_payment_tbl_materialOrder1_idx` (`tbl_materialOrder_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_payment_tbl_materialOrder1`
    FOREIGN KEY (`tbl_materialOrder_id`)
    REFERENCES `db_javasqlproject`.`tbl_materialorder` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_paymentdetail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_paymentdetail` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_paymentdetail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `paymentNumberDetail` CHAR(3) NOT NULL,
  `amount` INT NOT NULL,
  `price` INT NOT NULL,
  `tbl_payment_id` BIGINT NOT NULL,
  `tbl_material_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_paymentDetail_tbl_payment1_idx` (`tbl_payment_id` ASC) VISIBLE,
  INDEX `fk_tbl_paymentDetail_tbl_material1_idx` (`tbl_material_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_paymentDetail_tbl_material1`
    FOREIGN KEY (`tbl_material_id`)
    REFERENCES `db_javasqlproject`.`tbl_material` (`id`),
  CONSTRAINT `fk_tbl_paymentDetail_tbl_payment1`
    FOREIGN KEY (`tbl_payment_id`)
    REFERENCES `db_javasqlproject`.`tbl_payment` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_product` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `productCode` CHAR(8) NOT NULL,
  `productName` VARCHAR(50) NOT NULL,
  `productPrice` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_productamount`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_productamount` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_productamount` (
  `id` BIGINT NOT NULL,
  `productAmount` INT NOT NULL,
  `tbl_product_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_productAmount_tbl_product1_idx` (`tbl_product_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_productAmount_tbl_product1`
    FOREIGN KEY (`tbl_product_id`)
    REFERENCES `db_javasqlproject`.`tbl_product` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_productdetail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_productdetail` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_productdetail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `productCodeDetail` CHAR(3) NOT NULL,
  `amount` INT NOT NULL,
  `tbl_product_id` BIGINT NOT NULL,
  `tbl_material_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_productDetail_tbl_product1_idx` (`tbl_product_id` ASC) VISIBLE,
  INDEX `fk_tbl_productDetail_tbl_material1_idx` (`tbl_material_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_productDetail_tbl_material1`
    FOREIGN KEY (`tbl_material_id`)
    REFERENCES `db_javasqlproject`.`tbl_material` (`id`),
  CONSTRAINT `fk_tbl_productDetail_tbl_product1`
    FOREIGN KEY (`tbl_product_id`)
    REFERENCES `db_javasqlproject`.`tbl_product` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_productionplan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_productionplan` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_productionplan` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `planNumber` CHAR(11) NOT NULL,
  `planDate` DATE NOT NULL,
  `planner` CHAR(8) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_productionplandetail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_productionplandetail` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_productionplandetail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `planNumberDetail` CHAR(3) NOT NULL,
  `amount` INT NOT NULL,
  `tbl_product_id` BIGINT NOT NULL,
  `tbl_productionPlan_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_productionPlanDetail_tbl_product1_idx` (`tbl_product_id` ASC) VISIBLE,
  INDEX `fk_tbl_productionPlanDetail_tbl_productionPlan1_idx` (`tbl_productionPlan_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_productionPlanDetail_tbl_product1`
    FOREIGN KEY (`tbl_product_id`)
    REFERENCES `db_javasqlproject`.`tbl_product` (`id`),
  CONSTRAINT `fk_tbl_productionPlanDetail_tbl_productionPlan1`
    FOREIGN KEY (`tbl_productionPlan_id`)
    REFERENCES `db_javasqlproject`.`tbl_productionplan` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_productwarehousing`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_productwarehousing` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_productwarehousing` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `warehousingNumber` CHAR(11) NOT NULL,
  `warehousingDate` DATE NOT NULL,
  `wearer` CHAR(8) NOT NULL,
  `tbl_productionPlan_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_productWarehousing_tbl_productionPlan1_idx` (`tbl_productionPlan_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_productWarehousing_tbl_productionPlan1`
    FOREIGN KEY (`tbl_productionPlan_id`)
    REFERENCES `db_javasqlproject`.`tbl_productionplan` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_productwarehousingdetail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_productwarehousingdetail` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_productwarehousingdetail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `warehousingNumberDetail` CHAR(3) NOT NULL,
  `amount` INT NOT NULL,
  `tbl_productWarehousing_id` BIGINT NOT NULL,
  `tbl_product_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_productWarehousingDetail_tbl_productWarehousing1_idx` (`tbl_productWarehousing_id` ASC) VISIBLE,
  INDEX `fk_tbl_productWarehousingDetail_tbl_product1_idx` (`tbl_product_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_productWarehousingDetail_tbl_product1`
    FOREIGN KEY (`tbl_product_id`)
    REFERENCES `db_javasqlproject`.`tbl_product` (`id`),
  CONSTRAINT `fk_tbl_productWarehousingDetail_tbl_productWarehousing1`
    FOREIGN KEY (`tbl_productWarehousing_id`)
    REFERENCES `db_javasqlproject`.`tbl_productwarehousing` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_javasqlproject`.`tbl_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_javasqlproject`.`tbl_user` ;

CREATE TABLE IF NOT EXISTS `db_javasqlproject`.`tbl_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `idNumber` CHAR(8) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `name` VARCHAR(6) NOT NULL,
  `grade` CHAR(1) NOT NULL,
  `tbl_company_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_user_tbl_company1_idx` (`tbl_company_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_user_tbl_company1`
    FOREIGN KEY (`tbl_company_id`)
    REFERENCES `db_javasqlproject`.`tbl_company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Data for table `db_javasqlproject`.`tbl_company`
-- -----------------------------------------------------
START TRANSACTION;
USE `db_javasqlproject`;
INSERT INTO `db_javasqlproject`.`tbl_company` (`id`, `companyName`, `companyCode`) VALUES (1, '자재', '101');
INSERT INTO `db_javasqlproject`.`tbl_company` (`id`, `companyName`, `companyCode`) VALUES (2, '생산', '102');
INSERT INTO `db_javasqlproject`.`tbl_company` (`id`, `companyName`, `companyCode`) VALUES (3, 'A회사', '001');
INSERT INTO `db_javasqlproject`.`tbl_company` (`id`, `companyName`, `companyCode`) VALUES (4, 'B회사', '002');
INSERT INTO `db_javasqlproject`.`tbl_company` (`id`, `companyName`, `companyCode`) VALUES (5, 'C회사', '003');
INSERT INTO `db_javasqlproject`.`tbl_company` (`id`, `companyName`, `companyCode`) VALUES (6, 'D회사', '004');
INSERT INTO `db_javasqlproject`.`tbl_company` (`id`, `companyName`, `companyCode`) VALUES (7, 'E회사', '005');
INSERT INTO `db_javasqlproject`.`tbl_company` (`id`, `companyName`, `companyCode`) VALUES (8, 'F회사', '006');
INSERT INTO `db_javasqlproject`.`tbl_company` (`id`, `companyName`, `companyCode`) VALUES (9, 'G회사', '007');
INSERT INTO `db_javasqlproject`.`tbl_company` (`id`, `companyName`, `companyCode`) VALUES (10, 'H회사', '008');
INSERT INTO `db_javasqlproject`.`tbl_company` (`id`, `companyName`, `companyCode`) VALUES (11, 'I회사', '009');

COMMIT;


-- -----------------------------------------------------
-- Data for table `db_javasqlproject`.`tbl_material`
-- -----------------------------------------------------
START TRANSACTION;
USE `db_javasqlproject`;
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (1, '001001', '1번재료', '1', 1000, 3);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (2, '001002', '2번재료', '2', 2000, 3);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (3, '001003', '3번재료', '3', 3000, 3);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (4, '002001', '4번재료', '1', 1000, 4);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (5, '002002', '5번재료', '2', 2000, 4);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (6, '002003', '6번재료', '3', 3000, 4);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (7, '003001', '7번재료', '1', 1000, 5);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (8, '003002', '8번재료', '2', 2000, 5);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (9, '003003', '9번재료', '3', 3000, 5);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (10, '004001', '10번재료', '1', 1000, 6);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (11, '004002', '11번재료', '2', 2000, 6);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (12, '004003', '12번재료', '3', 3000, 6);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (13, '005001', '13번재료', '1', 1000, 7);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (14, '005002', '14번재료', '2', 2000, 7);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (15, '005003', '15번재료', '3', 3000, 7);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (16, '006001', '16번재료', '1', 1000, 8);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (17, '006002', '17번재료', '2', 2000, 8);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (18, '006003', '18번재료', '3', 3000, 8);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (19, '007001', '19번재료', '1', 1000, 9);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (20, '007002', '20번재료', '2', 2000, 9);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (21, '007003', '21번재료', '3', 3000, 9);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (22, '008001', '22번재료', '1', 1000, 10);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (23, '008002', '23번재료', '2', 2000, 10);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (24, '008003', '24번재료', '3', 3000, 10);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (25, '009001', '25번재료', '1', 1000, 11);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (26, '009002', '26번재료', '2', 2000, 11);
INSERT INTO `db_javasqlproject`.`tbl_material` (`id`, `materialCode`, `materialName`, `materialUnit`, `price`, `tbl_company_id`) VALUES (27, '009003', '27번재료', '3', 3000, 11);

COMMIT;


-- -----------------------------------------------------
-- Data for table `db_javasqlproject`.`tbl_materialamount`
-- -----------------------------------------------------
START TRANSACTION;
USE `db_javasqlproject`;
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (1, 100, 1);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (2, 200, 2);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (3, 300, 3);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (4, 100, 4);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (5, 200, 5);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (6, 300, 6);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (7, 100, 7);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (8, 200, 8);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (9, 300, 9);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (10, 100, 10);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (11, 200, 11);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (12, 300, 12);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (13, 100, 13);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (14, 200, 14);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (15, 300, 15);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (16, 100, 16);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (17, 200, 17);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (18, 300, 18);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (19, 100, 19);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (20, 200, 20);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (21, 300, 21);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (22, 100, 22);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (23, 200, 23);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (24, 300, 24);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (25, 100, 25);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (26, 200, 26);
INSERT INTO `db_javasqlproject`.`tbl_materialamount` (`id`, `materialAmount`, `tbl_material_id`) VALUES (27, 300, 27);

COMMIT;


-- -----------------------------------------------------
-- Data for table `db_javasqlproject`.`tbl_product`
-- -----------------------------------------------------
START TRANSACTION;
USE `db_javasqlproject`;
INSERT INTO `db_javasqlproject`.`tbl_product` (`id`, `productCode`, `productName`, `productPrice`) VALUES (1, '10000001', '1번제품', 10000);
INSERT INTO `db_javasqlproject`.`tbl_product` (`id`, `productCode`, `productName`, `productPrice`) VALUES (2, '10000002', '2번제품', 20000);
INSERT INTO `db_javasqlproject`.`tbl_product` (`id`, `productCode`, `productName`, `productPrice`) VALUES (3, '10000003', '3번제품', 30000);
INSERT INTO `db_javasqlproject`.`tbl_product` (`id`, `productCode`, `productName`, `productPrice`) VALUES (4, '10000004', '4번제품', 10000);
INSERT INTO `db_javasqlproject`.`tbl_product` (`id`, `productCode`, `productName`, `productPrice`) VALUES (5, '10000005', '5번제품', 20000);
INSERT INTO `db_javasqlproject`.`tbl_product` (`id`, `productCode`, `productName`, `productPrice`) VALUES (6, '10000006', '6번제품', 30000);
INSERT INTO `db_javasqlproject`.`tbl_product` (`id`, `productCode`, `productName`, `productPrice`) VALUES (7, '10000007', '7번제품', 10000);
INSERT INTO `db_javasqlproject`.`tbl_product` (`id`, `productCode`, `productName`, `productPrice`) VALUES (8, '10000008', '8번제품', 20000);
INSERT INTO `db_javasqlproject`.`tbl_product` (`id`, `productCode`, `productName`, `productPrice`) VALUES (9, '10000009', '9번제품', 30000);

COMMIT;


-- -----------------------------------------------------
-- Data for table `db_javasqlproject`.`tbl_productamount`
-- -----------------------------------------------------
START TRANSACTION;
USE `db_javasqlproject`;
INSERT INTO `db_javasqlproject`.`tbl_productamount` (`id`, `productAmount`, `tbl_product_id`) VALUES (1, 0, 1);
INSERT INTO `db_javasqlproject`.`tbl_productamount` (`id`, `productAmount`, `tbl_product_id`) VALUES (2, 0, 1);
INSERT INTO `db_javasqlproject`.`tbl_productamount` (`id`, `productAmount`, `tbl_product_id`) VALUES (3, 0, 1);
INSERT INTO `db_javasqlproject`.`tbl_productamount` (`id`, `productAmount`, `tbl_product_id`) VALUES (4, 0, 1);
INSERT INTO `db_javasqlproject`.`tbl_productamount` (`id`, `productAmount`, `tbl_product_id`) VALUES (5, 0, 1);
INSERT INTO `db_javasqlproject`.`tbl_productamount` (`id`, `productAmount`, `tbl_product_id`) VALUES (6, 0, 1);
INSERT INTO `db_javasqlproject`.`tbl_productamount` (`id`, `productAmount`, `tbl_product_id`) VALUES (7, 0, 1);
INSERT INTO `db_javasqlproject`.`tbl_productamount` (`id`, `productAmount`, `tbl_product_id`) VALUES (8, 0, 1);
INSERT INTO `db_javasqlproject`.`tbl_productamount` (`id`, `productAmount`, `tbl_product_id`) VALUES (9, 0, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `db_javasqlproject`.`tbl_productdetail`
-- -----------------------------------------------------
START TRANSACTION;
USE `db_javasqlproject`;
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (1, '001', 1, 1, 1);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (2, '002', 2, 1, 2);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (3, '003', 3, 1, 3);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (4, '001', 1, 2, 4);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (5, '002', 2, 2, 5);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (6, '003', 3, 2, 6);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (7, '001', 1, 3, 7);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (8, '002', 2, 3, 8);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (9, '003', 3, 2, 9);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (10, '001', 1, 4, 10);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (11, '002', 2, 4, 11);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (12, '003', 3, 4, 12);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (13, '001', 1, 5, 13);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (14, '002', 2, 5, 14);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (15, '003', 3, 5, 15);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (16, '001', 1, 6, 16);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (17, '002', 2, 6, 17);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (18, '003', 3, 6, 18);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (19, '001', 1, 7, 19);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (20, '002', 2, 7, 20);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (21, '003', 3, 7, 21);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (22, '001', 1, 8, 22);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (23, '002', 2, 8, 23);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (24, '003', 3, 8, 24);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (25, '001', 1, 9, 25);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (26, '002', 2, 9, 26);
INSERT INTO `db_javasqlproject`.`tbl_productdetail` (`id`, `productCodeDetail`, `amount`, `tbl_product_id`, `tbl_material_id`) VALUES (27, '003', 3, 9, 27);

COMMIT;


-- -----------------------------------------------------
-- Data for table `db_javasqlproject`.`tbl_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `db_javasqlproject`;
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (1, '20250201', '20250201', '홍길동', '1', 101);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (2, '20250202', '20250202', '홍길순', '2', 101);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (3, '20250203', '20250203', '홍길자', '3', 101);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (4, '20250204', '20250204', '홍길철', '4', 101);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (5, '20250205', '20250205', '전철민', '1', 102);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (6, '20250206', '20250206', '고철희', '2', 102);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (7, '20250207', '20250207', '권시용', '3', 102);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (8, '20250208', '20250208', '배형원', '4', 102);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (9, '20250209', '20250209', '한소훈', '1', 001);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (10, '20250210', '20250210', '표상희', '1', 002);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (11, '20250211', '20250211', '박용현', '1', 003);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (12, '20250212', '20250212', '장성미', '1', 004);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (13, '20250213', '20250213', '성은숙', '1', 005);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (14, '20250214', '20250214', '홍승주', '1', 006);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (15, '20250215', '20250215', '허태수', '1', 007);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (16, '20250216', '20250217', '탁시웅', '1', 008);
INSERT INTO `db_javasqlproject`.`tbl_user` (`id`, `idNumber`, `password`, `name`, `grade`, `tbl_company_id`) VALUES (17, '20250217', '20250217', '신대웅', '1', 009);

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
