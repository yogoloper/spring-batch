package com.example.batch.application;

import com.example.batch.batch.Job;
import com.example.batch.batch.SimpleTaskLet;
import com.example.batch.customer.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DormantBatchConfiguration {

    @Bean
    public Job dormantBatchJob( // 잡 생성
            DormantBatchItemReader itemReader,
            DormantBatchItemProcessor itemProcessor,
            DormantBatchItemWriter itemWriter,
            DormantBatchJobExecutionListener dormantBatchExecutionListener
    ) {
        final SimpleTaskLet<Customer, Customer> taskLet = new SimpleTaskLet<> ( // 비즈니스 작업 생성
                itemReader,
                itemProcessor,
                itemWriter
        );

        return new Job(
                taskLet,
                dormantBatchExecutionListener
        );
    }
}
