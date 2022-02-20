package io.github.patternatlas.auth.pattern.repositories;

import io.github.patternatlas.auth.pattern.entities.PatternLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatternLanguageRepository extends JpaRepository<PatternLanguage, UUID> {
    
    Optional<PatternLanguage> findById(UUID id);
}
