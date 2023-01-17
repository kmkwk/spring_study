package com.study.spa.concurrency;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcurrencyServiceTest {

    private int saveNumber;

    public int fail(int number, String name) {
        log.info("{} number start",name);
        log.info("{} 현재 number = {} , 저장 saveNumber = {} ",name, number, saveNumber);
        saveNumber = number;
        sleep(1000);
        log.info("{} 조회 saveNumber = {}",name,saveNumber);

        return saveNumber;
    }

    public synchronized int success(int number, String name) {
        log.info("{} number start",name);
        log.info("{} 현재 number = {} , 저장 saveNumber = {} ",name, number, saveNumber);
        saveNumber = number;
        sleep(1000);
        log.info("{} 조회 saveNumber = {}",name,saveNumber);

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
