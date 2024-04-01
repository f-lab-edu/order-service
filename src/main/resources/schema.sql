DROP TABLE IF EXISTS PAYMENT;
DROP TABLE IF EXISTS CART;
DROP TABLE IF EXISTS ORDER_DETAIL;
DROP TABLE IF EXISTS ORDERS;
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS MEMBER;

CREATE TABLE `MEMBER`
(
    `id`        VARCHAR(255) NOT NULL,
    `email`     VARCHAR(255),
    `name`      VARCHAR(255),
    `password`  VARCHAR(255),
    `phone`     VARCHAR(255),
    `address`   VARCHAR(255),
    `balance`   DECIMAL(10, 2) DEFAULT 0,
    `createdAt` DATETIME,
    `updatedAt` DATETIME,
    PRIMARY KEY (`id`)
);

CREATE TABLE `PRODUCT`
(
    `id`          VARCHAR(255) NOT NULL,
    `name`        VARCHAR(255),
    `description` VARCHAR(255),
    `price`       DECIMAL(10, 2),
    `stock`       INT,
    `createdAt`   DATETIME,
    PRIMARY KEY (`id`)
);


CREATE TABLE `ORDERS`
(
    `id`         VARCHAR(255) NOT NULL,
    `memberId`   VARCHAR(255) NOT NULL,
    `status`     ENUM('PENDING', 'COMPLETE', 'SHIPPING', 'DELIVERED', 'CANCELLED'),
    `totalPrice` DECIMAL(10, 2),
    `createdAt`  DATETIME,
    `updatedAt`  DATETIME,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`memberId`) REFERENCES `MEMBER` (`id`)
);

CREATE TABLE `ORDER_DETAIL`
(
    `orderId`   VARCHAR(255) NOT NULL,
    `productId` VARCHAR(255) NOT NULL,
    `price`     DECIMAL(10, 2),
    `quantity`  INT,
    PRIMARY KEY (`orderId`, `productId`),
    FOREIGN KEY (`orderId`) REFERENCES `ORDERS` (`id`),
    FOREIGN KEY (`productId`) REFERENCES `PRODUCT` (`id`)
);

CREATE TABLE `CART`
(
    `memberId`  VARCHAR(255) NOT NULL,
    `productId` VARCHAR(255) NOT NULL,
    `quantity`  INT,
    `createdAt` DATETIME,
    PRIMARY KEY (`memberId`, `productId`),
    FOREIGN KEY (`memberId`) REFERENCES `MEMBER` (`id`),
    FOREIGN KEY (`productId`) REFERENCES `PRODUCT` (`id`)
);

CREATE TABLE `PAYMENT`
(
    `id`        VARCHAR(255) NOT NULL,
    `orderId`   VARCHAR(255) NOT NULL,
    `amount`    DECIMAL(10, 2),
    `status`    ENUM('PENDING', 'COMPLETE', 'CANCELLED'),
    `createdAt` DATETIME,
    `updatedAt` DATETIME,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`orderId`) REFERENCES `ORDERS` (`id`)
);
