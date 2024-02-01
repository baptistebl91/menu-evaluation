DROP TABLE IF EXISTS `protocole`;
DROP TABLE IF EXISTS `sous_menu`;
DROP TABLE IF EXISTS `action`;
DROP TABLE IF EXISTS `resultat`;

CREATE TABLE `protocole`
(
    `reference`       VARCHAR(36) NOT NULL,
    `id_action`       INT         NOT NULL,
    `description`     TEXT        NOT NULL,
    `action_correcte` INT         NOT NULL,
    PRIMARY KEY (`reference`)
);

CREATE TABLE `sous_menu`
(
    `id`             INT          NOT NULL AUTO_INCREMENT,
    `nom_sous_menu`  VARCHAR(255) NOT NULL,
    `sous_menu_pere` INT,
    PRIMARY KEY (`id`)
);

CREATE TABLE `action`
(
    `id`             INT          NOT NULL AUTO_INCREMENT,
    `nom_action`     VARCHAR(255) NOT NULL,
    `sous_menu_pere` INT,
    PRIMARY KEY (`id`)
);

CREATE TABLE `resultat`
(
    `reference_protocole` VARCHAR(36) NOT NULL,
    `id_action`           INT         NOT NULL,
    `id_session`          INT         NOT NULL,
    `resultat_action`     INT         NOT NULL,
    `type_action`         INT         NOT NULL
);

ALTER TABLE `protocole`
    ADD CONSTRAINT `protocole_fk_1` FOREIGN KEY (`id_action`) REFERENCES `action` (`id`);

ALTER TABLE `sous_menu`
    ADD CONSTRAINT `sous_menu_fk_1` FOREIGN KEY (`sous_menu_pere`) REFERENCES `sous_menu` (`id`);

ALTER TABLE `action`
    ADD CONSTRAINT `action_fk_1` FOREIGN KEY (`sous_menu_pere`) REFERENCES `sous_menu` (`id`);

ALTER TABLE `resultat`
    ADD CONSTRAINT `resultat_fk_1` FOREIGN KEY (`reference_protocole`) REFERENCES `protocole` (`reference`),
    ADD CONSTRAINT `resultat_fk_2` FOREIGN KEY (`id_action`) REFERENCES `action` (`id`);