package com.example.spring_batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
//@Configuration
public class JobConfiguration {

    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("job-chunk", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    @JobScope // 빈이 생성된 후에 애플리케이션 파라미터를 주입하기 때문에 LateBean 설정을 해야함
    public Step step(
            JobRepository jobRepository,
            PlatformTransactionManager platformTransactionManager,
            @Value("#{jobParameters['name']}") String name // 애플리케이션 파라미터 가져오기
    ) {
        log.info("name: {}", name);
        return new StepBuilder("step", jobRepository)
                .tasklet((a, b) -> {
                    return RepeatStatus.FINISHED;
                }, platformTransactionManager)
                .build();
    }

//    @Bean
//    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        final ItemReader<Integer> itemReader = new ItemReader<>() {
//            private int count = 0;
//
//            @Override
//            public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//                count++;
//
//                log.info("Read {}", count);
//                if (count == 15) {
//                    return null;
//                }
//                return count;
//            }
//        };
//
//        final SkipPolicy skipPolicy = new SkipPolicy() {
//            @Override
//            public boolean shouldSkip(Throwable t, long skipCount) throws SkipLimitExceededException {
//                return t instanceof IllegalStateException && skipCount < 5;
//            }
//        };
//
//        return new StepBuilder("step", jobRepository)
//                .chunk(10, platformTransactionManager)
//                .reader(itemReader)
////                .processor()
//                .writer(read -> {})
//                .listener()
//                .faultTolerant()
////                .skip(IllegalStateException.class)
////                .skipLimit(3)
//                .skipPolicy(skipPolicy)
//                .build();
//    }
}
