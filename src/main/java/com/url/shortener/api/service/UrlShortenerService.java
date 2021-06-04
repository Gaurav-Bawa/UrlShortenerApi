package com.url.shortener.api.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.url.shortener.api.entity.CreateUrlRequest;
import com.url.shortener.api.entity.Url;
import com.url.shortener.api.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlShortenerService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Value("${url.shortener.length}")
    private int urlCount;

    public List<Url> createShortUrls(CreateUrlRequest request) {
        List<Url> list = new ArrayList<>();
        for (double i = 0; i < request.getNumberOfUrl(); i++) {
            Url url = new Url();
            url.setUrl(request.getUrl());
            url.setAlias(generateAlias());
            list.add(url);
        }
        list.stream().forEach(obj -> dynamoDBMapper.save(obj));
        return list;
    }

    public ResponseEntity<?> redirectUrl(String alias) {
        Url redirectUrl = dynamoDBMapper.load(Url.class, alias);
        Optional.ofNullable(redirectUrl).orElseThrow(() -> new BadRequestException("Alias " + alias + "not found"));
        try {
            URI uri = new URI(redirectUrl.getUrl());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uri);
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public String generateAlias() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(urlCount)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
