package com.talentbridge.talentbridge_backend.controller;

import com.talentbridge.talentbridge_backend.entity.Event;
import com.talentbridge.talentbridge_backend.repository.EventRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    // Get all events
    @GetMapping
    public List<Event> getAll() {
        return repository.findAll();
    }

    // Get one event by ID
    @GetMapping("/{id}")
    public Event getById(@PathVariable UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event not found"));
    }

    // Create a new event
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event create(@RequestBody Event item) {

        if (item.getTitle() == null || item.getTitle().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Event title is required");
        }

        if (item.getDate() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Event date is required");
        }

        if (item.getOrganizerId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Organizer ID is required");
        }

        if (item.getOrganizerName() == null
                || item.getOrganizerName().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Organizer name is required");
        }

        if (item.getVenue() == null || item.getVenue().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Event venue is required");
        }

        if (item.getCity() == null || item.getCity().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Event city is required");
        }

        if (item.getTalentCategory() == null
                || item.getTalentCategory().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Talent category is required");
        }

        // Let the database generate a new event ID
        item.setId(null);

        return repository.save(item);
    }

    // Update an existing event
    @PutMapping("/{id}")
    public Event update(
            @PathVariable UUID id,
            @RequestBody Event item) {

        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Event not found");
        }

        item.setId(id);
        return repository.save(item);
    }

    // Delete an event
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {

        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Event not found");
        }

        repository.deleteById(id);
    }
}