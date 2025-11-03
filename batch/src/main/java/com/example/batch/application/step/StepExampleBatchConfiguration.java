package com.example.batch.application.step;

import com.example.batch.batch.Job;
import com.example.batch.batch.Step;
import com.example.batch.batch.StepJob;
import com.example.batch.batch.TaskLet;
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
        final List<Step> steps =  Arrays.asList(step1, step2, step3);
        return new StepJob(steps, null);
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
