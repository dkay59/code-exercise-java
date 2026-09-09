package com.example.urlshortener.service;

import com.example.urlshortener.model.Url;
import com.example.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@Service
public class UrlService {

    private final UrlRepository repo;

    public UrlService(UrlRepository repo) {
        this.repo = repo;
    }

    public Url create(String fullUrl, String customAlias) {
        // Normalize missing protocol
        if (!fullUrl.startsWith("http://") && !fullUrl.startsWith("https://"))
            fullUrl = "https://" + fullUrl;

        String alias = (customAlias != null && !customAlias.isBlank())
                ? customAlias
                : UUID.randomUUID().toString().substring(0, 8);

        if (repo.findByAlias(alias).isPresent()) {
            throw new IllegalArgumentException("Alias already taken");
        }

        Url url = new Url();
        url.setFullUrl(fullUrl);
        url.setAlias(alias);

        return repo.save(url);
    }

    @Transactional(readOnly = true)
    public Optional<Url> get(String alias) {
        return repo.findByAlias(alias);
    }

    @Transactional
    public void delete(String alias) {
        if (!repo.findByAlias(alias).isPresent()) {
            throw new NoSuchElementException("Alias not found");
        }
        repo.deleteByAlias(alias);
    }

    @Transactional(readOnly = true)
    public List<Url> list() {
        return repo.findAll();
    }
}

