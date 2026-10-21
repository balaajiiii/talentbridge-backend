
package com.talentbridge.talentbridge_backend.controller;

import com.talentbridge.talentbridge_backend.entity.SavedEvent;
import com.talentbridge.talentbridge_backend.repository.SavedEventRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/saved-events")
public class SavedEventController {

    private final SavedEventRepository repository;

    public SavedEventController(SavedEventRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<SavedEvent> getAll(
            @RequestParam(required = false) UUID userId) {

        if (userId != null) {
            return repository.findByUserId(userId);
        }

        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SavedEvent create(@RequestBody SavedEvent item) {

        if (item.getUserId() == null || item.getEventId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "userId and eventId are required"
            );
        }

        if (repository.findByUserIdAndEventId(
                item.getUserId(), item.getEventId()).isPresent()) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "This event is already saved"
            );
        }

        item.setId(null);

        if (item.getCreatedAt() == null) {
            item.setCreatedAt(OffsetDateTime.now());
        }

        return repository.save(item);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @RequestParam UUID userId,
            @RequestParam UUID eventId) {

        SavedEvent savedEvent = repository
            .findByUserIdAndEventId(userId, eventId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Saved event not found"
            ));

        repository.delete(savedEvent);
    }
}
