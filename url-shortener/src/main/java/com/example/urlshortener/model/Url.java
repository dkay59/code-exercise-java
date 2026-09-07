package com.example.urlshortener.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Getter
@Entity
@Table(name = "url")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String url;

    @Column(name = "url_alias", columnDefinition = "TEXT")
    private String urlAlias;

    @Column(name = "url_title", columnDefinition = "TEXT")
    private String urlTitle;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Instant createdAt;

    // getters and setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlAlias(String urlAlias) {
        this.urlAlias = urlAlias;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

}
