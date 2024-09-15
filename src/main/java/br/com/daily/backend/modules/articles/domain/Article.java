package br.com.daily.backend.modules.articles.domain;

import br.com.daily.backend.modules.articles.domain.dto.ArticleRecord;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "text", nullable = false, length = 30000)
    private String text;

    @Column(name = "autor", nullable = false, length = 70)
    private String autor;

    @Column(name = "banner_url", nullable = false)
    private String bannerURL;

    @Column(name = "minutes_to_read", nullable = false)
    private String minutesToRead;

    @Column(name = "category", nullable = false)
    private String category;

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

    public static ArticleRecord mapToRecord(Article article) {
        return new ArticleRecord(article.getId(),article.getTitle(), article.getText(), article.getAutor(), article.getBannerURL(), article.getMinutesToRead(), article.getCategory(), article.getCreatedAt());
    }
}
