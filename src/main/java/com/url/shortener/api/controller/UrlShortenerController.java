package com.url.shortener.api.controller;

import com.url.shortener.api.entity.CreateUrlRequest;
import com.url.shortener.api.entity.Url;
import com.url.shortener.api.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/create")
    public List<Url> createShortUrl(@RequestBody CreateUrlRequest request) {
        return urlShortenerService.createShortUrls(request);
    }

    @GetMapping("/{alias}")
    public ResponseEntity<?> redirectUrl(@PathVariable String alias) {
        return urlShortenerService.redirectUrl(alias);
    }
}
