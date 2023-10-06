package com.example.demo.processing;

import com.example.demo.client.CardClient;
import com.example.demo.entity.Card;
import com.example.demo.entity.CardRepresentation;
import com.example.demo.entity.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class CardProcessor implements ItemProcessor<Page, List<CardRepresentation>> {
    CardClient cardClient;

    @Override
    public List<CardRepresentation> process(Page page) throws Exception {
        List<Card> cards = cardClient.getCards(page.getNumber());
        return cards.stream().map(this::convertCard).collect(Collectors.toList());
    }

    private CardRepresentation convertCard(Card card) {
        String firstName = card.getName().toUpperCase();
        String cardId = card.getCardId();

        CardRepresentation cardRepresentation = new CardRepresentation();
        cardRepresentation.setRepresentation(firstName + "|" + cardId);

        log.info("Converting (" + card + ") into (" + cardRepresentation + ")");

        return cardRepresentation;
    }
}