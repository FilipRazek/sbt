package com.example.demo.cli;

import com.example.demo.client.CardClient;
import com.example.demo.entity.Card;
import com.example.demo.entity.CardRepresentation;
import com.example.demo.entity.Page;
import com.example.demo.processing.CardProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ApiRunner implements CommandLineRunner {
    @Autowired
    CardClient cardClient;

    @Autowired
    CardProcessor cardProcessor;

    @Override
    public void run(String... args) {
        List<CardRepresentation> cards = cardProcessor.process(new Page(1));
        cards.forEach(card -> log.info(card.getRepresentation()));
    }
}
