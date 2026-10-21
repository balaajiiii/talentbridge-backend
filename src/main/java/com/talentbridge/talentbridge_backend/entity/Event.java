package com.talentbridge.talentbridge_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "events", schema = "public")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description = "";

    @Column(name = "organizer_name", nullable = false)
    private String organizerName;

    @Column(name = "organizer_id")
    private UUID organizerId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    // PostgreSQL's events.time column is of type TIME, so use LocalTime here.
    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "venue", nullable = false)
    private String venue;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "budget", nullable = false)
    private String budget = "";

    @Column(name = "talent_category", nullable = false)
    private String talentCategory;

    @Column(name = "available_slots", nullable = false)
    private Integer availableSlots = 1;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    public Event() {
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOrganizerName() { return organizerName; }
    public void setOrganizerName(String organizerName) { this.organizerName = organizerName; }

    public UUID getOrganizerId() { return organizerId; }
    public void setOrganizerId(UUID organizerId) { this.organizerId = organizerId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getBudget() { return budget; }
    public void setBudget(String budget) { this.budget = budget; }

    public String getTalentCategory() { return talentCategory; }
    public void setTalentCategory(String talentCategory) { this.talentCategory = talentCategory; }

    public Integer getAvailableSlots() { return availableSlots; }
    public void setAvailableSlots(Integer availableSlots) { this.availableSlots = availableSlots; }

    public String getPosterUrl() { return posterUrl; }
    public void setPosterUrl(String posterUrl) { this.posterUrl = posterUrl; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
