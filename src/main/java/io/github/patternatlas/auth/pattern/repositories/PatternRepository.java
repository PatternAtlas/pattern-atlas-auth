package io.github.patternatlas.auth.pattern.repositories;

import io.github.patternatlas.auth.pattern.entities.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatternRepository extends JpaRepository<Pattern, UUID> {
    
    Optional<Pattern> findById(UUID id);
}
