DROP TABLE IF EXISTS PAYMENT;
DROP TABLE IF EXISTS CART;
DROP TABLE IF EXISTS ORDER_DETAIL;
DROP TABLE IF EXISTS ORDERS;
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS MEMBER;

CREATE TABLE `MEMBER`
(
    `id`        BIGINT PRIMARY KEY AUTO_INCREMENT,
    `email`     VARCHAR(254),
    `name`      VARCHAR(50),
    `password`  VARCHAR(60),
    `phone`     VARCHAR(20),
    `address`   VARCHAR(200),
    `balance`   INT DEFAULT 0,
    `createdAt` DATETIME,
    `updatedAt` DATETIME
);

CREATE TABLE `PRODUCT`
(
    `id`          BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name`        VARCHAR(100),
    `description` VARCHAR(200),
    `price`       INT,
    `stock`       INT,
    `createdAt`   DATETIME
);


CREATE TABLE `ORDERS`
(
    `id`         BIGINT PRIMARY KEY AUTO_INCREMENT,
    `memberId`   BIGINT NOT NULL,
    `status`     ENUM('PENDING', 'COMPLETE', 'SHIPPING', 'DELIVERED', 'CANCELLED'),
    `totalPrice` INT,
    `createdAt`  DATETIME,
    `updatedAt`  DATETIME,
    FOREIGN KEY (`memberId`) REFERENCES `MEMBER` (`id`)
);

CREATE TABLE `ORDER_DETAIL`
(
    `orderId`   BIGINT NOT NULL,
    `productId` BIGINT NOT NULL,
    `price`     INT,
    `quantity`  INT,
    PRIMARY KEY (`orderId`, `productId`),
    FOREIGN KEY (`orderId`) REFERENCES `ORDERS` (`id`),
    FOREIGN KEY (`productId`) REFERENCES `PRODUCT` (`id`)
);

CREATE TABLE `CART`
(
    `memberId`  BIGINT NOT NULL,
    `productId` BIGINT NOT NULL,
    `quantity`  INT,
    `createdAt` DATETIME,
    PRIMARY KEY (`memberId`, `productId`),
    FOREIGN KEY (`memberId`) REFERENCES `MEMBER` (`id`),
    FOREIGN KEY (`productId`) REFERENCES `PRODUCT` (`id`)
);

CREATE TABLE `PAYMENT`
(
    `id`        BIGINT PRIMARY KEY AUTO_INCREMENT,
    `orderId`   BIGINT NOT NULL,
    `amount`    INT,
    `status`    ENUM('PENDING', 'COMPLETE', 'CANCELLED'),
    `createdAt` DATETIME,
    `updatedAt` DATETIME,
    FOREIGN KEY (`orderId`) REFERENCES `ORDERS` (`id`)
);
