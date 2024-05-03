INSERT INTO `MEMBER` (`email`, `name`, `password`, `phone`, `address`, `balance`, `createdAt`, `updatedAt`) VALUES
    ('member1@example.com', '회원 1', 'password1', '010-0000-0001', 'Address 1', 50000, NOW(), NOW()),
    ('member2@example.com', '회원 2', 'password2', '010-0000-0002', 'Address 2', 50000, NOW(), NOW()),
    ('member3@example.com', '회원 3', 'password3', '010-0000-0003', 'Address 3', 60000, NOW(), NOW()),
    ('member4@example.com', '회원 4', 'password4', '010-0000-0004', 'Address 4', 60000, NOW(), NOW()),
    ('member5@example.com', '회원 5', 'password5', '010-0000-0005', 'Address 5', 80000, NOW(), NOW()),
    ('member6@example.com', '회원 6', 'password6', '010-0000-0006', 'Address 6', 100000, NOW(), NOW()),
    ('member7@example.com', '회원 7', 'password7', '010-0000-0007', 'Address 7', 50000, NOW(), NOW()),
    ('member8@example.com', '회원 8', 'password8', '010-0000-0008', 'Address 8', 70000, NOW(), NOW()),
    ('member9@example.com', '회원 9', 'password9', '010-0000-0009', 'Address 9', 50000, NOW(), NOW()),
    ('member10@example.com', '회원 10', 'password10', '010-0000-0010', 'Address 10', 50000, NOW(), NOW()),

-- 상품 재고 동시성 테스트 시나리오 4
    ('member11@example.com', '회원 11', 'password11', '010-0000-0011', 'Address 11', 30000, NOW(), NOW()),
    ('member12@example.com', '회원 12', 'password12', '010-0000-0012', 'Address 12', 30000, NOW(), NOW()),
    ('member13@example.com', '회원 13', 'password13', '010-0000-0013', 'Address 13', 30000, NOW(), NOW()),
    ('member14@example.com', '회원 14', 'password14', '010-0000-0014', 'Address 14', 30000, NOW(), NOW()),
    ('member15@example.com', '회원 15', 'password15', '010-0000-0015', 'Address 15', 30000, NOW(), NOW()),
    ('member16@example.com', '회원 16', 'password16', '010-0000-0016', 'Address 16', 30000, NOW(), NOW()),
    ('member17@example.com', '회원 17', 'password17', '010-0000-0017', 'Address 17', 30000, NOW(), NOW()),
    ('member18@example.com', '회원 18', 'password18', '010-0000-0018', 'Address 18', 30000, NOW(), NOW()),
    ('member19@example.com', '회원 19', 'password19', '010-0000-0019', 'Address 19', 30000, NOW(), NOW()),
    ('member20@example.com', '회원 20', 'password20', '010-0000-0020', 'Address 20', 30000, NOW(), NOW());

INSERT INTO `PRODUCT` (`name`, `description`, `price`, `stock`, `createdAt`) VALUES
    ('제품1', '더미 제품1 입니다.', 7500, 100, NOW()),
    ('제품2', '더미 제품2 입니다.', 5000, 1, NOW()),
    ('제품3', '더미 제품3 입니다.', 10000, 5, NOW()),
    ('제품4', '더미 제품4 입니다.', 20000, 2, NOW()),
    ('제품5', '더미 제품5 입니다.', 9900, 6, NOW());

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
    ('8', '4', 4, NOW()),

-- 상품 재고 동시성 테스트 시나리오 4
    ('11', '5', 1, NOW()),
    ('12', '5', 1, NOW()),
    ('13', '5', 1, NOW()),
    ('14', '5', 1, NOW()),
    ('15', '5', 1, NOW()),
    ('16', '5', 1, NOW()),
    ('17', '5', 1, NOW()),
    ('18', '5', 1, NOW()),
    ('19', '5', 1, NOW()),
    ('20', '5', 1, NOW());
