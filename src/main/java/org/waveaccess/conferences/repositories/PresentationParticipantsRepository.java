package org.waveaccess.conferences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.waveaccess.conferences.models.PresentationParticipants;

public interface PresentationParticipantsRepository extends JpaRepository<PresentationParticipants, Long> {

    void deleteByPresentationId(Long id);

    void deleteByUserId(Long id);
}
