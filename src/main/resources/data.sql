INSERT INTO `MEMBER` (`id`, `email`, `name`, `password`, `phone`, `address`, `balance`, `createdAt`, `updatedAt`) VALUES
    ('1', 'member@example.com', '김멤버', '1234', '123-456-7890', '서울시 중구', 100000, NOW(), NOW());

INSERT INTO `PRODUCT` (`id`, `name`, `description`, `price`, `stock`, `createdAt`) VALUES
    ('1', '제품1', '더미 제품1 입니다.', 7500, 100, NOW());

INSERT INTO `ORDERS` (`id`, `memberId`, `status`, `totalPrice`, `createdAt`, `updatedAt`) VALUES
    ('1', '1', 'COMPLETE', 7500, NOW(), NOW());

INSERT INTO `ORDER_DETAIL` (`orderId`, `productId`, `price`, `quantity`) VALUES
    ('1', '1', 7500, 1);

INSERT INTO `PAYMENT` (`id`, `orderId`, `amount`, `status`, `createdAt`, `updatedAt`) VALUES
    ('1', '1', 7500, 'COMPLETE', NOW(), NOW());

INSERT INTO `CART` (`memberId`, `productId`, `quantity`, `createdAt`) VALUES
    ('1', '1', 1, NOW());
