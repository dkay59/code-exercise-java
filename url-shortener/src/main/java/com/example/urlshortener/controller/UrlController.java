package com.example.urlshortener.controller;

import com.example.urlshortener.dto.ShortenRequest;
import com.example.urlshortener.dto.ShortenResponse;
import com.example.urlshortener.dto.UrlInfoResponse;
import com.example.urlshortener.model.Url;
import com.example.urlshortener.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    // POST /shorten
    @PostMapping("/shorten")
    public ResponseEntity<?> shorten(@RequestBody ShortenRequest request) {
        try {
            Url url = service.create(request.getFullUrl(), request.getCustomAlias());
            String shortUrl = "http://localhost:8080/" + url.getAlias();
            return ResponseEntity.status(201).body(new ShortenResponse(shortUrl));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Alias already taken");
        }
    }

    // GET /{alias}
    @GetMapping("/{alias}")
    public ResponseEntity<?> redirect(@PathVariable String alias) {
        return service.get(alias)
                .map(
                        u -> ResponseEntity.status(302).header("Location", u.getFullUrl())
                                .build()
                )
                .orElse(ResponseEntity.status(404).body(null));
    }

    @GetMapping("/get/{alias}")
    public ResponseEntity<?> get(@PathVariable String alias) {
        return service.get(alias)
                .map(
                        u -> ResponseEntity.status(302)
                                .body(new ShortenRequest(u.getFullUrl(),u.getAlias()))
                )
                .orElse(ResponseEntity.status(404).body(null));
    }

    // DELETE /{alias}
    @DeleteMapping("/{alias}")
    public ResponseEntity<?> delete(@PathVariable String alias) {
        try {
            service.delete(alias);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body("Alias not found");
        }
    }

    // GET /urls
    @GetMapping("/urls")
    public List<UrlInfoResponse> list() {
        return service.list().stream()
                .map(u -> new UrlInfoResponse(
                        u.getAlias(),
                        u.getFullUrl(),
                        "http://localhost:8080/" + u.getAlias()
                ))
                .toList();
    }
}
