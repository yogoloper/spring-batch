package com.example.batch.batch;

import lombok.Builder;

import java.time.LocalDateTime;

public class TaskLetJob implements Job {

    private final TaskLet tasklet;
    private final JobExecutionListener jobExecutionListener;

    public TaskLetJob(TaskLet tasklet) {
        this(tasklet, null);
    }

    @Builder
    public TaskLetJob(ItemReader<?> itemReader, ItemProcessor<?, ?> itemProcessor, ItemWriter<?> itemWriter, JobExecutionListener jobExecutionListener) {
        this(new SimpleTaskLet(itemReader, itemProcessor, itemWriter), jobExecutionListener);
    }

    public TaskLetJob(TaskLet tasklet, JobExecutionListener jobExecutionListener) {
        this.tasklet = tasklet;
        this.jobExecutionListener = new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {

            }

            @Override
            public void afterJob(JobExecution jobExecution) {

            }
        };
    }

    @Override
    public JobExecution excute() {

        final JobExecution jobExecution = new JobExecution();
        jobExecution.setStatus(BatchStatus.STARTING);
        jobExecution.setStartTime(LocalDateTime.now());

        jobExecutionListener.beforeJob(jobExecution);

        try {

            tasklet.excute();
            jobExecution.setStatus(BatchStatus.COMPLETED);
        } catch (Exception e) {
            jobExecution.setStatus(BatchStatus.FAILED);
        }

        jobExecution.setEndTime(LocalDateTime.now());

        jobExecutionListener.afterJob(jobExecution);

        return jobExecution;
    }
}
