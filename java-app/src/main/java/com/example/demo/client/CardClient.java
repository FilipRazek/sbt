package com.example.demo.client;

import com.example.demo.entity.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cards", url = "${card-backend-url}")
public interface CardClient {
    @GetMapping(path = "users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Card> getCards(@RequestParam("_page") int page);
}
