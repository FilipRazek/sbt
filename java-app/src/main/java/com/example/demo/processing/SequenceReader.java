package com.example.demo.processing;

import com.example.demo.entity.Page;
import org.springframework.batch.item.ItemReader;

public class SequenceReader implements ItemReader<Page> {
    private int count = 0;
    private int max = 100;

    public SequenceReader(int max) {
        this.max = max;
    }

    public Page read() {
        if (count < max) {
            count++;
            return new Page(count);
        }
        return null;
    }
}
