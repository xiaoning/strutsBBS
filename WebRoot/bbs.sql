SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
CREATE SCHEMA IF NOT EXISTS `BBS` ;
USE `mydb` ;
USE `BBS` ;

-- -----------------------------------------------------
-- Table `BBS`.`forum`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BBS`.`forum` (
  `forumid` INT NOT NULL ,
  `forumname` VARCHAR(45) NULL ,
  `manager` VARCHAR(45) NULL ,
  `topicNum` INT NULL DEFAULT NULL ,
  `lastTopicId` INT NULL DEFAULT NULL ,
  `lastTopicTitle` VARCHAR(45) NULL ,
  `lastTopicAuthor` VARCHAR(45) NULL ,
  `lastTopicTime` VARCHAR(45) NULL )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BBS`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BBS`.`user` (
  `id` INT NOT NULL ,
  `username` VARCHAR(45) NULL ,
  `password` VARCHAR(45) NULL ,
  `sex` VARCHAR(45) NULL ,
  `email` VARCHAR(45) NULL ,
  `qq` VARCHAR(45) NULL ,
  `signature` VARCHAR(500) NULL ,
  `grade` VARCHAR(45) NULL ,
  `topicNum` INT NULL DEFAULT 0 )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BBS`.`topic`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BBS`.`topic` (
  `topicid` INT NOT NULL ,
  `title` VARCHAR(100) NULL ,
  `content` VARCHAR(10000) NULL ,
  `author` VARCHAR(45) NULL ,
  `submittime` VARCHAR(45) NULL ,
  `forumid` INT NULL DEFAULT NULL ,
  `viewNum` INT NULL DEFAULT 0 ,
  `responseNum` INT NULL DEFAULT 0 ,
  `modifytime` VARCHAR(45) NULL ,
  `forumname` VARCHAR(45) NULL ,
  `grade` VARCHAR(45) NULL ,
  `authorid` INT NULL DEFAULT NULL )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BBS`.`response`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BBS`.`response` (
  `responseid` INT NOT NULL ,
  `title` VARCHAR(100) NULL ,
  `content` VARCHAR(10000) NULL ,
  `author` VARCHAR(45) NULL ,
  `submittime` VARCHAR(45) NULL ,
  `topicid` INT NULL DEFAULT NULL ,
  `grade` VARCHAR(45) NULL ,
  `modifytime` VARCHAR(45) NULL ,
  `forumname` VARCHAR(100) NULL ,
  `forumid` INT NULL DEFAULT NULL ,
  `authorid` INT NULL DEFAULT NULL )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BBS`.`message`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BBS`.`message` (
  `messageid` INT NOT NULL ,
  `fromid` INT NULL ,
  `fromname` VARCHAR(45) NULL ,
  `toid` INT NULL ,
  `toname` VARCHAR(45) NULL ,
  `title` VARCHAR(45) NULL ,
  `content` VARCHAR(300) NULL ,
  `isread` INT NULL ,
  `submittime` VARCHAR(45) NULL )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
