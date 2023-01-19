package com.study.spa.controller;

import com.study.spa.service.ConcurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("con")
@Slf4j
public class ConcurrencyController {

    private final ConcurrencyService concurrencyService;

    @PutMapping("{id}")
    public ResponseEntity<Object> concurrencyJpa(@PathVariable Long id){
        log.info("concurrency start");
        Runnable test1 = () -> {
            concurrencyService.jpaConcurrency(id);
        };

        Runnable test2 = () -> {
            concurrencyService.jpaConcurrency(id);
        };

        Thread threadA = new Thread(test1);
        threadA.setName("thread-A");

        Thread threadB = new Thread(test2);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100);
        threadB.start();

        log.info("concurrency end");

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
