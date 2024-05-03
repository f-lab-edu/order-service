package com.flab.order.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderServiceTest {

    Logger logger = LoggerFactory.getLogger(OrderServiceTest.class);

    @Autowired
    private OrderService orderService;

    private ExecutorService executorService;

    @BeforeEach
    void setUp() {
        executorService = Executors.newFixedThreadPool(10);
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

        int successCount = 0;

        try {
            future1.get();
            logger.info("[TEST] 회원 2가 성공적으로 상품 2를 주문했습니다.");
            successCount++;
        } catch (Exception e) {
            logger.info("[TEST] 회원 2가 상품 2 주문에 실패했습니다. " + e.getMessage());
        }

        try {
            future2.get();
            logger.info("[TEST] 회원 3이 성공적으로 상품 2를 주문했습니다.");
            successCount++;
        } catch (Exception e) {
            logger.info("[TEST] 회원 3이 상품 2 주문에 실패했습니다. " + e.getMessage());
        }

        assertEquals(1, successCount, "상품 재고 동시성 테스트에 실패했습니다.");
    }

    @Test
    @DisplayName("[시나리오 2] 2명의 회원이 동시에 재고가 5인 상품을 각각 3개, 4개 주문 시도") // 상품 재고가 -1 이하가 되어서는 안됨
    void testOrderConcurrency2() {
        Long memberId1 = 4L;
        Long memberId2 = 5L;

        Future<?> future1 = executorService.submit(() -> orderService.orderCartItems(memberId1));
        Future<?> future2 = executorService.submit(() -> orderService.orderCartItems(memberId2));

        int successCount = 0;

        try {
            future1.get();
            logger.info("[TEST] 회원 4가 성공적으로 상품 3을 주문했습니다.");
            successCount++;
        } catch (Exception e) {
            logger.info("[TEST] 회원 4가 상품 3 주문에 실패했습니다. " + e.getMessage());
        }

        try {
            future2.get();
            logger.info("[TEST] 회원 5가 성공적으로 상품 3을 주문했습니다.");
            successCount++;
        } catch (Exception e) {
            logger.info("[TEST] 회원 5가 상품 3 주문에 실패했습니다. " + e.getMessage());
        }

        assertEquals(1, successCount, "상품 재고 동시성 테스트에 실패했습니다.");
    }

    @Test
    @DisplayName("[시나리오 3] 2명의 회원이 동시에 재고가 5인 상품을 각각 3개, 2개 주문 시도")
    void testOrderConcurrency3() {
        Long memberId1 = 4L;
        Long memberId2 = 6L;

        Future<?> future1 = executorService.submit(() -> orderService.orderCartItems(memberId1));
        Future<?> future2 = executorService.submit(() -> orderService.orderCartItems(memberId2));

        int successCount = 0;

        try {
            future1.get();
            logger.info("[TEST] 회원 4가 성공적으로 상품 3을 주문했습니다.");
            successCount++;
        } catch (Exception e) {
            logger.info("[TEST] 회원 4가 상품 3 주문에 실패했습니다. " + e.getMessage());
        }

        try {
            future2.get();
            logger.info("[TEST] 회원 6이 성공적으로 상품 3을 주문했습니다.");
            successCount++;
        } catch (Exception e) {
            logger.info("[TEST] 회원 6이 상품 3 주문에 실패했습니다. " + e.getMessage());
        }

        assertEquals(2, successCount, "상품 재고 동시성 테스트에 실패했습니다.");
    }

    @Test
    @DisplayName("[시나리오 4] 10명의 회원이 동시에 재고가 6인 상품을 1개씩 주문 시도")
    void testOrderConcurrency4() throws ExecutionException, InterruptedException {
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 11; i <= 20; i++) {
            Long memberId = (long) i; // 회원 11 ~ 20
            Future<Integer> future = executorService.submit(() -> {
                try {
                    orderService.orderCartItems(memberId);
                    return 1; // 주문 성공
                } catch (Exception e) {
                    logger.info("[TEST] 주문 처리 실패: 회원 ID = {}", memberId);
                    return 0; // 주문 실패
                }
            });
            futures.add(future);
        }

        int successCount = 0;
        for (Future<Integer> future : futures) {
            successCount += future.get();
        }

        assertEquals(6, successCount, "상품 재고 동시성 테스트에 실패했습니다.");
    }
}
