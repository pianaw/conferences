package org.waveaccess.conferences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.waveaccess.conferences.models.Presentation;

import java.util.List;
import java.util.Optional;

public interface PresentationRepository extends JpaRepository<Presentation, Long> {

    Optional<Presentation> findById(Long id);

    @Query(value = "FROM Presentation pres LEFT JOIN FETCH pres.participants part WHERE part.id = :userId AND pres.id = :id")
    Optional<Presentation> findByIdAndUserId(Long id, Long userId);

}
