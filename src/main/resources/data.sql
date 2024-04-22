INSERT INTO `MEMBER` (`email`, `name`, `password`, `phone`, `address`, `balance`, `createdAt`, `updatedAt`) VALUES
    ('member1@example.com', '회원 1', 'password1', '010-0000-0001', 'Address 1', 50000, NOW(), NOW()),
    ('member2@example.com', '회원 2', 'password2', '010-0000-0002', 'Address 2', 50000, NOW(), NOW()),
    ('member3@example.com', '회원 3', 'password3', '010-0000-0003', 'Address 3', 60000, NOW(), NOW()),
    ('member4@example.com', '회원 4', 'password4', '010-0000-0004', 'Address 4', 60000, NOW(), NOW()),
    ('member5@example.com', '회원 5', 'password5', '010-0000-0005', 'Address 5', 80000, NOW(), NOW()),
    ('member6@example.com', '회원 6', 'password6', '010-0000-0006', 'Address 6', 100000, NOW(), NOW()),
    ('member7@example.com', '회원 7', 'password7', '010-0000-0007', 'Address 7', 50000, NOW(), NOW()),
    ('member8@example.com', '회원 8', 'password8', '010-0000-0008', 'Address 8', 70000, NOW(), NOW());

INSERT INTO `PRODUCT` (`name`, `description`, `price`, `stock`, `createdAt`) VALUES
    ('제품1', '더미 제품1 입니다.', 7500, 100, NOW()),
    ('제품2', '더미 제품2 입니다.', 5000, 1, NOW()),
    ('제품3', '더미 제품3 입니다.', 10000, 5, NOW()),
    ('제품4', '더미 제품4 입니다.', 20000, 2, NOW());

INSERT INTO `ORDERS` (`memberId`, `status`, `totalPrice`, `createdAt`, `updatedAt`) VALUES
    ('1', 'COMPLETE', 7500, NOW(), NOW());

INSERT INTO `ORDER_DETAIL` (`orderId`, `productId`, `price`, `quantity`) VALUES
    ('1', '1', 7500, 1);

INSERT INTO `PAYMENT` (`orderId`, `amount`, `status`, `createdAt`, `updatedAt`) VALUES
    ('1', 7500, 'COMPLETE', NOW(), NOW());

INSERT INTO `CART` (`memberId`, `productId`, `quantity`, `createdAt`) VALUES
    ('1', '1', 1, NOW()),
    ('2', '2', 1, NOW()),
    ('3', '2', 1, NOW()),

    ('4', '3', 3, NOW()),
    ('5', '3', 4, NOW()),
    ('6', '3', 2, NOW()),

    ('7', '4', 3, NOW()),
    ('8', '4', 4, NOW());
