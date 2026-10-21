
package com.talentbridge.talentbridge_backend.repository;

import com.talentbridge.talentbridge_backend.entity.SavedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SavedEventRepository
        extends JpaRepository<SavedEvent, Long> {

    List<SavedEvent> findByUserId(UUID userId);

    Optional<SavedEvent> findByUserIdAndEventId(
            UUID userId, UUID eventId);
}
