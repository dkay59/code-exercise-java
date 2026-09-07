package com.example.urlshortener.controller;

import com.example.urlshortener.model.Url;
import com.example.urlshortener.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping
    public List<Url> getAll() {
        return urlService.getAll();
    }

    @GetMapping("/{alias}")
    public ResponseEntity<Url> getByAlias(@PathVariable String alias) {
        return urlService.getByAlias(alias)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Url> create(@RequestBody Url request) {
        Url created = urlService.create(
                request.getUrl(),
                request.getUrlAlias(),
                request.getUrlTitle()
        );
        return ResponseEntity
                .created(URI.create("/api/urls/" + created.getUrlAlias()))
                .body(created);
    }
}
