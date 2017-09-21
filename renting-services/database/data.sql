-- -----------------------------------------------------
-- Data for table `knightrider`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `knightrider`;
INSERT INTO `knightrider`.`user` (`email`, `password`) VALUES ('alex@something.com', 'password');
INSERT INTO `knightrider`.`user` (`email`, `password`) VALUES ('test', 'test');
INSERT INTO `knightrider`.`user` (`email`, `password`) VALUES ('demo', '.demo8$');

COMMIT;


-- -----------------------------------------------------
-- Data for table `knightrider`.`customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `knightrider`;
INSERT INTO `knightrider`.`customer` (`email`, `name`, `surname`, `birthdate`, `mobile`, `driving_license_id`, `driving_license_date`) VALUES ('alex@something.com', 'alejandro', 'hernandez', '1991-10-02', '610264916', '73014985j', '2018-10-10');
INSERT INTO `knightrider`.`customer` (`email`, `name`, `surname`, `birthdate`, `mobile`, `driving_license_id`, `driving_license_date`) VALUES ('test', 'are', 'test', '1992-10-02', '666666666', '43543543k', '2020-11-11');
INSERT INTO `knightrider`.`customer` (`email`, `name`, `surname`, `birthdate`, `mobile`, `driving_license_id`, `driving_license_date`) VALUES ('demo', 'javier', 'sanchez', '1992-10-02', '666666656', '43543643k', '2018-10-10');

COMMIT;


-- -----------------------------------------------------
-- Data for table `knightrider`.`customer_address`
-- -----------------------------------------------------
START TRANSACTION;
USE `knightrider`;
INSERT INTO `knightrider`.`customer_address` (`id`, `email`, `address`, `number`, `floor`, `postal_code`, `locality`, `province`, `state`) VALUES (DEFAULT, 'alex@something.com', 'condes de aragon', '16', '1', '50009', 'zaragoza', 'zaragoza', 'Spain');

COMMIT;


-- -----------------------------------------------------
-- Data for table `knightrider`.`customer_card`
-- -----------------------------------------------------
START TRANSACTION;
USE `knightrider`;
INSERT INTO `knightrider`.`customer_card` (`id`, `email`, `number`, `name`, `cvs`, `date_expired`, `active`) VALUES (null, 'alex@something.com', '1234567898765432', 'alejandro hernandez pardos', 000, '2020-10-10', true);

COMMIT;


-- -----------------------------------------------------
-- Data for table `knightrider`.`rol`
-- -----------------------------------------------------
START TRANSACTION;
USE `knightrider`;
INSERT INTO `knightrider`.`rol` (`rol`) VALUES ('customer');

COMMIT;


-- -----------------------------------------------------
-- Data for table `knightrider`.`user_rol`
-- -----------------------------------------------------
START TRANSACTION;
USE `knightrider`;
INSERT INTO `knightrider`.`user_rol` (`email`, `rol`) VALUES ('alex@something.com', 'customer');
INSERT INTO `knightrider`.`user_rol` (`email`, `rol`) VALUES ('test', 'customer');
INSERT INTO `knightrider`.`user_rol` (`email`, `rol`) VALUES ('demo', 'customer');

COMMIT;


-- -----------------------------------------------------
-- Data for table `knightrider`.`vehicle`
-- -----------------------------------------------------
START TRANSACTION;
USE `knightrider`;
INSERT INTO `knightrider`.`vehicle` (`id`, `vin`, `model`, `color`, `state`, `rent_state`, `battery_level`, `battery_range`, `latitude`, `longitude`, `sun_roof`) VALUES (null, '1010DKR', 'S', 'red', 'online', 'unblocked', 40, 99, 41.6563497, -0.876566, true);
INSERT INTO `knightrider`.`vehicle` (`id`, `vin`, `model`, `color`, `state`, `rent_state`, `battery_level`, `battery_range`, `latitude`, `longitude`, `sun_roof`) VALUES (DEFAULT, '2020RRS', 'S', 'green', 'online', 'unblocked', 75, 310, 41.6620, -0.8713, false);
INSERT INTO `knightrider`.`vehicle` (`id`, `vin`, `model`, `color`, `state`, `rent_state`, `battery_level`, `battery_range`, `latitude`, `longitude`, `sun_roof`) VALUES (DEFAULT, '1818GGJ', 'X', 'blue', 'online', 'unblocked', 90, 454, 41.65021, -0.8799, true);
INSERT INTO `knightrider`.`vehicle` (`id`, `vin`, `model`, `color`, `state`, `rent_state`, `battery_level`, `battery_range`, `latitude`, `longitude`, `sun_roof`) VALUES (DEFAULT, '2323CVB', '3', 'red', 'online', 'unblocked', 25, 41.659, 41.659, -0.899, true);

COMMIT;

