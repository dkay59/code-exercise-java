package com.example.urlshortener.service;

import com.example.urlshortener.model.Url;
import com.example.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public List<Url> getAll() {
        return urlRepository.findAll();
    }

    public Optional<Url> getByAlias(String alias) {
        return urlRepository.findByUrlAlias(alias);
    }

    public Url create(String url, String alias, String title) {
        Url entity = new Url();
        entity.setUrl(url);
        entity.setUrlAlias(alias);
        entity.setUrlTitle(title);
        return urlRepository.save(entity);
    }
}
