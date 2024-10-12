package com.example.springcore.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService beanStatefulService1 = ac.getBean(StatefulService.class);
        StatefulService beanStatefulService2 = ac.getBean(StatefulService.class);

        beanStatefulService1.order("userA", 10000);

        beanStatefulService1.order("userB", 20000);

        int price = beanStatefulService1.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(beanStatefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}