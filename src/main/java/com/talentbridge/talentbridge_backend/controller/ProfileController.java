package com.talentbridge.talentbridge_backend.controller;

import com.talentbridge.talentbridge_backend.entity.Profile;
import com.talentbridge.talentbridge_backend.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/profiles")
@CrossOrigin(origins = "*")
public class ProfileController {
    private final ProfileRepository repository;

    public ProfileController(ProfileRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Profile> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Profile getById(@PathVariable UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Profile create(@RequestBody Profile item) {
        if (item.getId() == null) { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profile id must be the Supabase auth user UUID"); }
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public Profile update(@PathVariable UUID id, @RequestBody Profile item) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
        }
        item.setId(id);
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
        }
        repository.deleteById(id);
    }
}
