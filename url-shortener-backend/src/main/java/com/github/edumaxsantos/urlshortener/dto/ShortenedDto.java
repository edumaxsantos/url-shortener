package com.github.edumaxsantos.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.edumaxsantos.urlshortener.model.ShortenedUrlModel;

public record ShortenedDto(@JsonProperty String key,
                           @JsonProperty("long_url") String longUrl,
                           @JsonProperty("short_url") String shortUrl) {

    public static ShortenedDto from(ShortenedUrlModel model) {
        return new ShortenedDto(model.getKey(), model.getLongUrl(), model.getShortUrl());
    }
}
