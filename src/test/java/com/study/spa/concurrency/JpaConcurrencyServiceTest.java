package com.study.spa.concurrency;

import com.study.spa.entity.Concurrency;
import com.study.spa.repository.ConcurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
public class JpaConcurrencyServiceTest {

    @Autowired
    private ConcurrencyRepository concurrencyRepository;

    public int start() {
        log.info("number start");
        Concurrency concurrency = concurrencyRepository.findById(1L).get();
        log.info("현재 number = {}", concurrency.getCount());
        concurrency.setCount();
        sleep(1000);
        log.info("조회 saveNumber = {}",concurrency.getCount());

        return 1;
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
