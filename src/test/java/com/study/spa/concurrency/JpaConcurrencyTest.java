package com.study.spa.concurrency;

import com.study.spa.service.ConcurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@Slf4j
public class JpaConcurrencyTest {

    @Autowired
    private ConcurrencyService concurrencyService;

    @Test
    public void concurrency(){
        log.info("concurrency start");

        Runnable test1 = () -> {
            concurrencyService.jpaConcurrency(1L);
        };

        Runnable test2 = () -> {
            concurrencyService.jpaConcurrency(1L);
        };

        Thread threadA = new Thread(test1);
        threadA.setName("thread-A");

        Thread threadB = new Thread(test2);
        threadB.setName("thread-B");

        threadA.start();
        sleep(200);
        threadB.start();

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
