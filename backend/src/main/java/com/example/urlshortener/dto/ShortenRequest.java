package com.example.urlshortener.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortenRequest {
    private String fullUrl;
    private String customAlias;


    public ShortenRequest(String fullUrl, String customAlias) {
        this.fullUrl = fullUrl;
        this.customAlias = customAlias;
    }
}

