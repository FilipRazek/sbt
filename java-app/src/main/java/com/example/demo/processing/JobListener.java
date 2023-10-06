package com.example.demo.processing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JobListener implements JobExecutionListener {
    long startTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = new Date().getTime();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            long executionTime = new Date().getTime() - startTime;
            log.info(String.format("Batch job finished after %d ms", executionTime));
        }
    }
}
