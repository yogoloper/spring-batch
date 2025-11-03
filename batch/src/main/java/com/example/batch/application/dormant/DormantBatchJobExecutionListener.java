package com.example.batch.application.dormant;

import com.example.batch.EmailProvider;
import com.example.batch.batch.JobExecution;
import com.example.batch.batch.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchJobExecutionListener implements JobExecutionListener {

    private final EmailProvider emailProvider;

    public DormantBatchJobExecutionListener() {
        this.emailProvider = new EmailProvider.Fake();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // 비즈니스 로직
         emailProvider.send("admin@gmail.com", "배치 완료 알림", "DormantBatchJob이 수행되었습니다. status: " + jobExecution.getStatus());
    }
}
