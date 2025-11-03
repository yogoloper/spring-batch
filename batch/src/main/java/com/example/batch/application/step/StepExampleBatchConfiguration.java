package com.example.batch.application.step;

import com.example.batch.batch.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Configuration
public class StepExampleBatchConfiguration {

    @Bean
    public Job stepExampleBatchJob(
            Step step1,
            Step step2,
            Step step3
    ) {
        return new StepJobBuilder()
                .start(step1)
                .next(step2)
                .next(step3)
                .build();
    }

    @Bean
    public Step step1() {
        return new Step(() -> System.out.println("Step1"));
    }

    @Bean
    public Step step2() {
        return new Step(() -> System.out.println("Step2"));
    }

    @Bean
    public Step step3() {
        return new Step(() -> System.out.println("Step3"));
    }
}
