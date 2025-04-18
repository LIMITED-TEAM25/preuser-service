package com.sparta.limited.preuser_service;

import com.sparta.limited.preuser_service.preuser.application.service.PreuserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class PresuserApplyTest {


    @Autowired
    private PreuserService preuserService;

    private ExecutorService executorService;

    private CountDownLatch latch;

    private UUID preuserId;

    @BeforeEach
    void setUp() {
        preuserId = UUID.fromString("0a48416b-7534-43c8-982e-7e24d16d10df");
    }


    @Test
    void applyPreuserEventConcurrencyTest() throws InterruptedException {
        int requestCount = 10000;
        AtomicInteger success = new AtomicInteger();
        AtomicInteger fail = new AtomicInteger();
        executorService = Executors.newFixedThreadPool(1000);
        latch = new CountDownLatch(requestCount);
        long start = System.currentTimeMillis();

        for (int i = 0; i < requestCount; i++) {
            final long userId = i + 1;
            executorService.submit(() -> {
                try {
                    preuserService.applyPreuserEvents(preuserId, userId);
                    success.incrementAndGet();

                } catch (Exception e) {
                    fail.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        long end = System.currentTimeMillis();
        System.out.println("성공 수: " + success.get());
        System.out.println("실패 수: " + fail.get());
        System.out.println("총 신청 시도 수: " + (success.get() + fail.get()));
        System.out.println("총 처리 시간: " + (end - start) + " ms");
    }


    @Test
    public void testApplyWithFourConcurrentUsers() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            try {
                preuserService.applyPreuserEvents(preuserId, 1L);
                System.out.println("신청 1: success");
            } catch (Exception e) {
                System.out.println("신청 1: failed - " + e.getMessage());
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                preuserService.applyPreuserEvents(preuserId, 2L);
                System.out.println("신청 요청 2: success");
            } catch (Exception e) {
                System.out.println("신청 요청 2: failed - " + e.getMessage());
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                preuserService.applyPreuserEvents(preuserId, 3L);
                System.out.println("신청 요청 3: success");
            } catch (Exception e) {
                System.out.println("신청 요청 3: failed - " + e.getMessage());
            }
        });

        Thread thread4 = new Thread(() -> {
            try {
                preuserService.applyPreuserEvents(preuserId, 4L);
                System.out.println("신청 요청 4: success");
            } catch (Exception e) {
                System.out.println("신청 요청 4: failed - " + e.getMessage());
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();


        long end = System.currentTimeMillis();
        System.out.println("총 테스트 소요 시간: " + (end - start) + " ms");
    }


}


