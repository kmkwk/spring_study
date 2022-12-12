package com.study.spa.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();
    }

    @Test
    void strategyV2(){
        Strategy strategyLogic1 = () -> log.info("비즈니스 로직1 실행");
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        log.info("strategyLogic1={}",strategyLogic1.getClass());
        contextV1.execute();

        Strategy strategyLogic2 = () -> log.info("비즈니스 로직1 실행");
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        log.info("strategyLogic2={}",strategyLogic2.getClass());
        contextV2.execute();
    }
}
