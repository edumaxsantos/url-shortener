package com.github.edumaxsantos.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record UrlPayload(@JsonProperty @NotNull(message = "Missing field: url") String url) {
}
