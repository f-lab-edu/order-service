INSERT INTO `MEMBER` (`email`, `name`, `password`, `phone`, `address`, `balance`, `createdAt`, `updatedAt`) VALUES
    ('member@example.com', '김멤버', '1234', '123-456-7890', '서울시 중구', 100000, NOW(), NOW()),
    ('member2@example.com', '회원 2', 'password2', '010-0000-0002', 'Address 1', 50000, NOW(), NOW()),
    ('member3@example.com', '회원 3', 'password3', '010-0000-0003', 'Address 2', 60000, NOW(), NOW());

INSERT INTO `PRODUCT` (`name`, `description`, `price`, `stock`, `createdAt`) VALUES
    ('제품1', '더미 제품1 입니다.', 7500, 100, NOW()),
    ('제품2', '더미 제품2 입니다.', 5000, 1, NOW());

INSERT INTO `ORDERS` (`memberId`, `status`, `totalPrice`, `createdAt`, `updatedAt`) VALUES
    ('1', 'COMPLETE', 7500, NOW(), NOW());

INSERT INTO `ORDER_DETAIL` (`orderId`, `productId`, `price`, `quantity`) VALUES
    ('1', '1', 7500, 1);

INSERT INTO `PAYMENT` (`orderId`, `amount`, `status`, `createdAt`, `updatedAt`) VALUES
    ('1', 7500, 'COMPLETE', NOW(), NOW());

INSERT INTO `CART` (`memberId`, `productId`, `quantity`, `createdAt`) VALUES
    ('1', '1', 1, NOW()),
    ('2', '2', 1, NOW()),
    ('3', '2', 1, NOW());
