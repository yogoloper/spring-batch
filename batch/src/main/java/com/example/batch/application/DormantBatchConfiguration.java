package com.example.batch.application;

import com.example.batch.batch.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DormantBatchConfiguration {

    @Bean
    public Job dormantBatchJob(
            DormantBatchTaskLet dormantBatchTaskLet,
            DormantBatchExecutionListener dormantBatchExecutionListener
    ) {
        return new Job(
                dormantBatchTaskLet,
                dormantBatchExecutionListener
        );
    }
}
