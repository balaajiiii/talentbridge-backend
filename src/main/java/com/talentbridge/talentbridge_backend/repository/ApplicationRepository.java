
package com.talentbridge.talentbridge_backend.repository;

import com.talentbridge.talentbridge_backend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApplicationRepository
        extends JpaRepository<Application, Long> {

    boolean existsByUserIdAndEventId(UUID userId, UUID eventId);
}
