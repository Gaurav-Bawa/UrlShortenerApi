package com.url.shortener.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUrlRequest {

    private String url;

    private double numberOfUrl;

}
