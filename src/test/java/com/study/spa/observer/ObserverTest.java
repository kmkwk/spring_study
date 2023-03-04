package com.study.spa.observer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ObserverTest {

    @Test
    public void test() {
        Topic topic = new Topic();

        Observer a = new TopicSubscriber("a", topic);
        Observer b = new TopicSubscriber("b", topic);
        Observer c = new TopicSubscriber("c", topic);

        topic.register(a);
        topic.register(b);
        topic.register(c);

        topic.postMessage("observer test");
    }

}
