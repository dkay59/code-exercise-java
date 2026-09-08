package com.example.urlshortener.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlInfoResponse {
    private String alias;
    private String fullUrl;
    private String shortUrl;

    public UrlInfoResponse(String alias, String fullUrl, String shortUrl) {
        this.alias = alias;
        this.fullUrl = fullUrl;
        this.shortUrl = shortUrl;
    }

    // getters, setters, constructor
}

