package com.github.edumaxsantos.urlshortener.controller;

import com.github.edumaxsantos.urlshortener.dto.ShortenedDto;
import com.github.edumaxsantos.urlshortener.dto.UrlPayload;
import com.github.edumaxsantos.urlshortener.hash.Hash;
import com.github.edumaxsantos.urlshortener.model.ShortenedUrlModel;
import com.github.edumaxsantos.urlshortener.repository.ShortenedRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ShortenerController {

    private ShortenedRepository repository;
    private Hash hashAlgo;

    @Autowired
    public ShortenerController(ShortenedRepository repository, Hash hashAlgo) {
        this.repository = repository;
        this.hashAlgo = hashAlgo;
    }

    @PostMapping
    public ResponseEntity<ShortenedDto> create(@Valid @RequestBody UrlPayload requestBody) {
        String hashed = hashAlgo.hash(requestBody.url());
        ShortenedUrlModel saved = repository.findByKey(hashed).orElseGet(() -> {
            ShortenedUrlModel urlModel = new ShortenedUrlModel();
            urlModel.setLongUrl(requestBody.url());
            urlModel.setKey(hashed);
            urlModel.setShortUrl(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + "/" + hashed);
            return repository.save(urlModel);
        });

        ShortenedDto responseBody = ShortenedDto.from(saved);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{hashed}")
    public ResponseEntity redirectUrl(@PathVariable String hashed) {
        return repository.findByKey(hashed)
                .map(model -> ResponseEntity.status(HttpStatus.FOUND).location(URI.create(model.getLongUrl())).build())
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("URL not found"));
    }

    @DeleteMapping("/{hashed}")
    public ResponseEntity delete(@PathVariable String hashed) {
        return repository.findByKey(hashed)
                .map(model -> {
                    repository.delete(model);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("URL not found"));
    }
}
