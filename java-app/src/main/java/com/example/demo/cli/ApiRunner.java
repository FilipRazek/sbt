package com.example.demo.cli;

import com.example.demo.client.CardClient;
import com.example.demo.entity.Card;
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

    @Override
    public void run(String... args) {
        log.info("Fetching cards");
        List<Card> cards = cardClient.getCards(1);
        log.info(String.format("Found %d cards", cards.size()));
        cards.forEach(card -> log.info(card.toString()));
    }
}
