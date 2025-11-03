package com.example.batch.batch;

import org.springframework.stereotype.Component;

@Component
public class SimpleTaskLet<I, O> implements Tasklet {

    private final ItemReader<I> itemReader;
    private final ItemProcessor<I, O> itemProcessor;
    private final ItemWriter<O> itemWriter;

    public SimpleTaskLet(ItemReader<I> itemReader, ItemProcessor<I, O> itemProcessor, ItemWriter<O> itemWriter) {
        this.itemReader = itemReader;
        this.itemProcessor = itemProcessor;
        this.itemWriter = itemWriter;
    }

    @Override
    public void excute() {

        int pageNo = 0;

        // 비즈니스 로직
        while (true) {

            // read
            final I read = itemReader.read();
            if (read == null) break;

            // process
            final O process = itemProcessor.process(read);
            if (process == null) continue;

            // write
            itemWriter.write(process);
        }
    }
}
