package com.example.batch.application.dormant;

import com.example.batch.customer.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PreDormantBatchItemProcessorTest {

    private PreDormantBatchItemProcessor preDormantBatchItemProcessor;

    @BeforeEach
    void setup() {
        preDormantBatchItemProcessor = new PreDormantBatchItemProcessor();
    }

    @Test
    @DisplayName("로그인 날짜가 오늘로부터 358일 전이면 customer를 반환해야 한다.")
    void test1() {

        // given
        final Customer customer = new Customer("aaa", "aaa@gmail.com");
        customer.setLoginAt(LocalDateTime.now().minusDays(358));

        // when
        final Customer result = preDormantBatchItemProcessor.process(customer);

        // then
        Assertions.assertEquals(result, customer);
        Assertions.assertNotNull(result);

    }

    @Test
    @DisplayName("로그인 날짜가 오늘로부터 358일 전이 아니면 null을 반환해야 한다.")
    void test2() {

        // given
        final Customer customer = new Customer("aaa", "aaa@gmail.com");

        // when
        final Customer result = preDormantBatchItemProcessor.process(customer);

        // then
        Assertions.assertNull(result);
    }

}