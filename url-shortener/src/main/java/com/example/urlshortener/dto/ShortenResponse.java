package com.example.urlshortener.dto;

import lombok.Getter;

@Getter
public class ShortenResponse {
    private String shortUrl;

    public ShortenResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }


}

