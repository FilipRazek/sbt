package com.example.demo.processing;

import com.example.demo.entity.CardRepresentation;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class EmptyWriter implements ItemWriter<List<CardRepresentation>> {

    @Override
    public void write(Chunk<? extends List<CardRepresentation>> chunk)  {
        // Do nothing
    }
}
