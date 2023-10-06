package com.example.demo.processing;

import com.example.demo.entity.CardRepresentation;
import com.example.demo.entity.Page;
import org.springframework.batch.core.Job;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class BatchConfiguration {
    @Value("${jobs-to-run}")
    int jobsToRun;

    @Bean(name="myTaskExecutor")
    public TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor("customerInfo");
    }

    @Bean
    public ItemReader<Page> reader() {
        return new SequenceReader(jobsToRun);
    }

    @Bean
    public ItemProcessor<Page, List<CardRepresentation>> processor() {
        return new CardProcessor();
    }

    @Bean
    public ItemWriter<List<CardRepresentation>> writer() {
        return new EmptyWriter();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository,
                             JobListener listener,
                             Step step1) {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<Page, List<CardRepresentation>>chunk(1, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }
}
