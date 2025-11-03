package com.example.batch.application.dormant;

import com.example.batch.EmailProvider;
import com.example.batch.batch.ItemWriter;
import com.example.batch.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PreDormantBatchItemWriter implements ItemWriter<Customer> {

    private final EmailProvider emailProvider;

    @Autowired
    public PreDormantBatchItemWriter() {
        this.emailProvider = new EmailProvider.Fake();
    }

    public PreDormantBatchItemWriter(EmailProvider emailProvider) {
        this.emailProvider = emailProvider;
    }

    @Override
    public void write(Customer customer) {
        emailProvider.send(
                customer.getEmail(),
                "곧 휴면계정으로 전환이 됩니다.",
                "휴면 계정을 원치 않으시면 일주일 내에 로그인 하세요."
        );
    }
}
