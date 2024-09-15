package br.com.daily.backend.modules.meditation.domain;

import br.com.daily.backend.modules.meditation.domain.dto.MeditationRecord;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "meditations")
public class Meditation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "name", unique = true)
    private String name;

    @Column(nullable = false, name = "photo_url", unique = true)
    private String photoUrl;

    @Column(nullable = false, name = "type")
    private String type;

    @Column(nullable = false, name = "text", length = 30000)
    private String text;

    @Column(nullable = false, name = "audio_file")
    private String audioFile;

    @Column(nullable = false, name = "duration")
    private String duration;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = true, updatable = true)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = true, updatable = true)
    private LocalDateTime deletedAt;

    @PrePersist
    private void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public static MeditationRecord mapToRecord(Meditation meditation) {
        return new MeditationRecord(meditation.getId(), meditation.getName(), meditation.getPhotoUrl(), meditation.getType(), meditation.getType(), meditation.getAudioFile(), meditation.getDuration());
    }


}
