package com.example.urlshortener.repository;

import com.example.urlshortener.model.Url;
import com.example.urlshortener.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.assertj.core.api.Assertions.assertThat;

//import org.springframework.boot.jpa.test.autoconfigure.DataJpaTest;

//@DataJpaTest
class UrlRepositoryTest {

    @Autowired
    private UrlRepository urlRepository;

    @Test
    void testSaveAndFindByAlias() {
        Url url = new Url();
        url.setUrl("https://example.com");
        url.setUrlAlias("example");
        url.setUrlTitle("Example Site");

        urlRepository.save(url);

        Url found = urlRepository.findByUrlAlias("example").orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getUrl()).isEqualTo("https://example.com");
    }
}
