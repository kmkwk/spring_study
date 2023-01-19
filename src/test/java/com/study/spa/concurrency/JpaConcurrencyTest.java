package com.study.spa.concurrency;

import com.study.spa.entity.Concurrency;
import com.study.spa.repository.ConcurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaConcurrencyTest {

    private static int saveNumber;

    @Autowired
    private ConcurrencyRepository concurrencyRepository;

    @Test
    public void concurrency(){
        log.info("concurrency start");

        Runnable test1 = jpaConcurrency();
        Runnable test2 = jpaConcurrency();

        Thread threadA = new Thread(test1);
        threadA.setName("thread-A");

        Thread threadB = new Thread(test2);
        threadB.setName("thread-B");

        threadA.start();
        log.info("변경 count = {}",saveNumber);
        sleep(100);
        threadB.start();
        log.info("변경 count = {}",saveNumber);

        sleep(3000);
        log.info("concurrency end");
    }

    @Transactional
    public Runnable jpaConcurrency(){
        log.info("jpaConcurrency start");
        Concurrency concurrency = concurrencyRepository.findById(1L).get();
        log.info("현재 count = {}" , concurrency.getCount());
        concurrency.setCount();
        sleep(1000);
        saveNumber++;
        log.info("jpaConcurrency end");

        return null;
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
