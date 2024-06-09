package br.com.daily.backend.entities;

import br.com.daily.backend.entities.dtos.ArticleDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ARTICLE_INFO")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String text;
    private String autor;
    private LocalDateTime creationDate = LocalDateTime.now();

    public static ArticleDTO mapToDTO(Article article){
        ArticleDTO articleDTO = new ArticleDTO();

        articleDTO.setTitle(article.getTitle());
        articleDTO.setText(article.getText());
        articleDTO.setAutor(article.getAutor());
        articleDTO.setCreationDate(article.getCreationDate());

        return articleDTO;
    }
}
