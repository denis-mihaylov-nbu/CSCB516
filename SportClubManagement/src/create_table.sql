CREATE SCHEMA `scm` ;

CREATE TABLE `scm`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(250) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `clubid` INT NULL DEFAULT -1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC));

CREATE TABLE `scm`.`club` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `type` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

CREATE TABLE `scm`.`club_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

ALTER TABLE `scm`.`club` 
ADD INDEX `pk_club_type_idx` (`type` ASC);
ALTER TABLE `scm`.`club` 
ADD CONSTRAINT `pk_club_type`
  FOREIGN KEY (`type`)
  REFERENCES `scm`.`club_type` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
ALTER TABLE `scm`.`user` 
ADD COLUMN `type` INT NOT NULL AFTER `clubid`;

INSERT INTO `scm`.`club_type` (`id`, `name`) VALUES ('1', 'Football');
INSERT INTO `scm`.`club_type` (`id`, `name`) VALUES ('2', 'Tennis');
