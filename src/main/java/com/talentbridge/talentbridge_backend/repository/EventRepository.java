package com.talentbridge.talentbridge_backend.repository;

import com.talentbridge.talentbridge_backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
}
