package com.example.batch.batch;

import lombok.Builder;

public class Step {

    private final TaskLet taskLet;

    public Step(TaskLet taskLet) {
        this.taskLet = taskLet;
    }

    @Builder
    public Step(ItemReader<?> itemReader, ItemProcessor<?, ?> itemProcessor, ItemWriter<?> itemWriter) {
        this.taskLet = new SimpleTaskLet(itemReader, itemProcessor, itemWriter);
    }

    public void execute() {
        taskLet.excute();
    }
}
