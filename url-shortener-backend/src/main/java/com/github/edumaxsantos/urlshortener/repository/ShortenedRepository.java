package com.github.edumaxsantos.urlshortener.repository;

import com.github.edumaxsantos.urlshortener.model.ShortenedUrlModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortenedRepository extends JpaRepository<ShortenedUrlModel, Long> {
    Optional<ShortenedUrlModel> findByKey(String key);
}
