package com.example.batch.batch;

import lombok.Builder;

import java.time.LocalDateTime;

public class TaskLetJob extends AbstractJob {

    private final TaskLet tasklet;

    public TaskLetJob(TaskLet tasklet) {
        super(null);
        this.tasklet = tasklet;
    }

    @Builder
    public TaskLetJob(ItemReader<?> itemReader, ItemProcessor<?, ?> itemProcessor, ItemWriter<?> itemWriter, JobExecutionListener jobExecutionListener) {
        super(jobExecutionListener);
        this.tasklet = new SimpleTaskLet(itemReader, itemProcessor, itemWriter);
    }

    @Override
    public void doExecute() {
        tasklet.excute();
    }
}
