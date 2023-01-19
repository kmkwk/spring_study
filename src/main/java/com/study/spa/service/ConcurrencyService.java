package com.study.spa.service;

import com.study.spa.entity.Concurrency;
import com.study.spa.repository.ConcurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConcurrencyService {

    private final ConcurrencyRepository concurrencyRepository;

    @Transactional
    public Concurrency jpaConcurrency(Long id){
        log.info("jpaConcurrency start");
        Concurrency concurrency = concurrencyRepository.findById(id).get();
        log.info("현재 count = {}" , concurrency.getCount());
        concurrency.setCount();
        sleep(1000);
        log.info("변경 count = {} ",concurrency.getCount());

        return concurrency;
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
