package com.example.demo.cli;

import com.example.demo.client.CardClient;
import com.example.demo.entity.Card;
import com.example.demo.entity.CardRepresentation;
import com.example.demo.entity.Page;
import com.example.demo.processing.CardProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ApiRunner implements CommandLineRunner {
    @Value("${jobs-to-run}")
    int jobsToRun;
    @Autowired
    CardClient cardClient;

    @Autowired
    CardProcessor cardProcessor;

    @Override
    public void run(String... args) {
        long startTime = new Date().getTime();
        List<CardRepresentation> result = new ArrayList<>();
        for (int i = 0; i < jobsToRun; i++){
            List<CardRepresentation> cards = cardProcessor.process(new Page(i));
            result.addAll(cards);
        }
        log.info(String.format("Sequential result finished after %d ms", new Date().getTime() - startTime));
    }
}
