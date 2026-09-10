package com.example.urlshortener.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "url")
public class Url {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "fullUrl", columnDefinition = "VARCHAR(255)", nullable = false)
    private String fullUrl;

    @Id
    @Column(name = "alias", columnDefinition = "VARCHAR(255)")
    private String alias;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Instant createdAt;

}
