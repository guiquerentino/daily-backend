package br.com.daily.backend.modules.tests.domain;

import br.com.daily.backend.core.utils.QuestionsConverter;
import br.com.daily.backend.modules.tests.domain.dto.TestRecord;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "TESTS_INFO")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String text;

    private String bannerUrl;

    @Column(columnDefinition = "TEXT")
    private String questions;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

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

    public static TestRecord mapToRecord(Test test) {
        return new TestRecord(test.getId(), test.getTitle(), test.getText(), test.getBannerUrl(), test.getQuestions());
    }
}
