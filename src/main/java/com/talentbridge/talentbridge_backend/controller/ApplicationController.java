
package com.talentbridge.talentbridge_backend.controller;

import com.talentbridge.talentbridge_backend.entity.Application;
import com.talentbridge.talentbridge_backend.repository.ApplicationRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationRepository repository;

    public ApplicationController(ApplicationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Application> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Application getById(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Application not found"
            ));
    }

    @PostMapping
    public Application create(@RequestBody Application item) {

        if (item.getUserId() == null || item.getEventId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "userId and eventId are required"
            );
        }

        if (repository.existsByUserIdAndEventId(
                item.getUserId(), item.getEventId())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "You have already applied to this event"
            );
        }

        item.setId(null);

        if (item.getStatus() == null || item.getStatus().isBlank()) {
            item.setStatus("pending");
        }

        if (item.getCreatedAt() == null) {
            item.setCreatedAt(OffsetDateTime.now());
        }

        return repository.save(item);
    }

    @PutMapping("/{id}")
    public Application update(
            @PathVariable Long id,
            @RequestBody Application item) {

        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Application not found"
            );
        }

        item.setId(id);
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Application not found"
            );
        }

        repository.deleteById(id);
    }
}
