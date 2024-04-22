package com.flab.order.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderServiceTest {

    Logger logger = LoggerFactory.getLogger(OrderServiceTest.class);

    @Autowired
    private OrderService orderService;

    private ExecutorService executorService;

    @BeforeEach
    void setUp() {
        executorService = Executors.newFixedThreadPool(2);
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        executorService.shutdown();
        if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
            executorService.shutdownNow();
        }
    }

    @Test
    @DisplayName("[시나리오 1] 2명의 회원이 동시에 재고가 1인 상품 주문 시도") // 상품 재고가 -1 이하가 되어서는 안됨
    void testOrderConcurrency1() {
        Long memberId1 = 2L;
        Long memberId2 = 3L;

        Future<?> future1 = executorService.submit(() -> orderService.orderCartItems(memberId1));
        Future<?> future2 = executorService.submit(() -> orderService.orderCartItems(memberId2));

        int exceptionCount = 0;

        try {
            future1.get();
            logger.info("[TEST] 회원 2가 성공적으로 상품 2를 주문했습니다.");
        } catch (Exception e) {
            logger.info("[TEST] 회원 2가 상품 2 주문에 실패했습니다. " + e.getMessage());
            exceptionCount++;
        }

        try {
            future2.get(); // 두 번째 작업의 완료와 예외 검사
            logger.info("[TEST] 회원 3이 성공적으로 상품 2를 주문했습니다.");
        } catch (Exception e) {
            logger.info("[TEST] 회원 3이 상품 2 주문에 실패했습니다. " + e.getMessage());
            exceptionCount++;
        }

        assertEquals(1, exceptionCount, "상품 재고 동시성 테스트에 실패했습니다.");
    }
}
