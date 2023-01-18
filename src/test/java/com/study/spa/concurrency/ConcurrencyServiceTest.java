package com.study.spa.concurrency;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcurrencyServiceTest {

    private int saveNumber;

    public int start(int number) {
        log.info("number start");
        log.info("현재 number = {} , 저장 saveNumber = {} ", number, saveNumber);
        saveNumber = number;
        sleep(1000);
        log.info("조회 saveNumber = {}",saveNumber);

        return saveNumber;
    }

    public synchronized int startSynchronized(int number) {
        log.info("number start");
        log.info("현재 number = {} , 저장 saveNumber = {} ", number, saveNumber);
        saveNumber = number;
        sleep(1000);
        log.info("조회 saveNumber = {}",saveNumber);

        return saveNumber;
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
