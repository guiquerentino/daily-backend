package br.com.daily.backend.modules.annotations.domain;

import br.com.daily.backend.modules.accounts.domain.User;
import br.com.daily.backend.modules.annotations.AnnotationRepository;
import br.com.daily.backend.modules.annotations.domain.dto.AnnotationRecord;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "annotations")
public class Annotation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "text")
    private String text;

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

    public static AnnotationRecord mapToRecord(Annotation annotation) {
        return new AnnotationRecord(annotation.getId(), annotation.getUserId(), annotation.getText(), annotation.getCreatedAt());
    }
}
