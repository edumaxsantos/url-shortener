package com.github.edumaxsantos.urlshortener.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "shortened_url")
@Data
public class ShortenedUrlModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "hashed")
    private String key;

    @Column(name = "long_url")
    private String longUrl;

    @Column(name = "short_url")
    private String shortUrl;
}
