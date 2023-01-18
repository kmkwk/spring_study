package com.study.spa.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ConcurrencyTest {

    private final ConcurrencyServiceTest concurrencyServiceTest = new ConcurrencyServiceTest();

    @Test
    public void concurrency(){
        log.info("concurrency start");

        Runnable test1 = () -> {
            concurrencyServiceTest.start(1);
        };

        Runnable test2 = () -> {
            concurrencyServiceTest.start(2);
        };

        Thread threadA = new Thread(test1);
        threadA.setName("thread-A");

        Thread threadB = new Thread(test2);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100);
        threadB.start();

        sleep(3000);
        log.info("concurrency end");
    }

    @Test
    public void concurrencySynchronized(){
        log.info("concurrency start");

        Runnable test1 = () -> {
            concurrencyServiceTest.startSynchronized(1);
        };

        Runnable test2 = () -> {
            concurrencyServiceTest.startSynchronized(2);
        };

        Thread threadA = new Thread(test1);
        threadA.setName("thread-A");

        Thread threadB = new Thread(test2);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100);
        threadB.start();

        sleep(3000);
        log.info("concurrency end");
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
