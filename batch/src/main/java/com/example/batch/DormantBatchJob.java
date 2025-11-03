package com.example.batch;

import com.example.batch.customer.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchJob {

    private final CustomerRepository customerRepository;
    private final EmailProvider emailProvider;

    public DormantBatchJob(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.emailProvider = new EmailProvider.Fake();
    }

    public void excute() {
        // 1. 유저를 조회한다.

        // 2. 휴면 계정 대상을 추출 및 변환한다.

        // 3. 휴면 계정으로 상태를 변경 한다.

        // 4. 메일을 보낸다.
    }
}
