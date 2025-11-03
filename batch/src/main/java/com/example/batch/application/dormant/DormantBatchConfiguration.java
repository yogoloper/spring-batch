package com.example.batch.application.dormant;

import com.example.batch.batch.Job;
import com.example.batch.batch.Step;
import com.example.batch.batch.StepJobBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DormantBatchConfiguration {

    @Bean
    public Job dormantBatchJob( // 잡 생성
            Step preDormantBatchStep,
            Step dormantBatchStep,
            DormantBatchJobExecutionListener listener
    ) {

        return new StepJobBuilder()
                .start(preDormantBatchStep)
                .next(dormantBatchStep)
                .build();
    }

    @Bean
    public Step dormantBatchStep(
            AllCustomerItemReader itemReader,
            DormantBatchItemProcessor itemProcessor,
            DormantBatchItemWriter itemWriter
    ) {
        return Step.builder()
                .itemReader(itemReader)
                .itemProcessor(itemProcessor)
                .itemWriter(itemWriter)
                .build();
    }

    @Bean
    public Step preDormantBatchStep(
            AllCustomerItemReader itemReader,
            PreDormantBatchItemProcessor itemProcessor,
            PreDormantBatchItemWriter itemWriter
    ) {
        return Step.builder()
                .itemReader(itemReader)
                .itemProcessor(itemProcessor)
                .itemWriter(itemWriter)
                .build();
    }

    // 휴면 전환 예정 1주일 전인 사람에게 이메일을 발송한다.
}
