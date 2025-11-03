package com.example.batch;

import com.example.batch.customer.Customer;
import com.example.batch.customer.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DormantBatchJobTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DormantBatchJob dormantBatchJob;

    @BeforeEach
    public void setup() {
        customerRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 시간이 1년을 경과한 고객이 3명이고, 1년 이내에 로그인한 고객이 다섯명이면 3명의 고객이 휴면전환 대상이다.")
    void test1() {
        // given
        saveCustomer(366);
        saveCustomer(366);
        saveCustomer(366);

        saveCustomer(364);
        saveCustomer(364);
        saveCustomer(364);
        saveCustomer(364);
        saveCustomer(364);

        // when
        dormantBatchJob.excute();

        // then
        final long dormantCount = customerRepository.findAll()
                .stream()
                .filter(it -> it.getStatus() == Customer.Status.DORMANT)
                .count();
        Assertions.assertEquals(dormantCount, 3);
    }

    @Test
    @DisplayName("고객이 10명이 있지만, 모두 다 휴면전환 대상이면 휴면전환 대상은 10명이다.")
    void test2() {
        // given
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);


        // when
        dormantBatchJob.excute();

        // then
        final long dormantCount = customerRepository.findAll()
                .stream()
                .filter(it -> it.getStatus() == Customer.Status.DORMANT)
                .count();
        Assertions.assertEquals(dormantCount, 10);
    }

    @Test
    @DisplayName("고객이 없는 경우에도 배치는 정상동작해야 한다.")
    void test3(){
// when
        dormantBatchJob.excute();

        // then
        final long dormantCount = customerRepository.findAll()
                .stream()
                .filter(it -> it.getStatus() == Customer.Status.DORMANT)
                .count();
        Assertions.assertEquals(dormantCount, 0);
    }

    private void saveCustomer(long loginMinusDays) {
        final String uuid =  UUID.randomUUID().toString();
        final Customer test = new Customer(uuid, uuid+"@gmail.com");
        test.setLoginAt(LocalDateTime.now().minusDays(loginMinusDays));
        customerRepository.save(test);
    }
}