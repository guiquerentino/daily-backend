package br.com.daily.backend.modules.articles.domain;

import br.com.daily.backend.modules.articles.domain.dto.ArticleDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
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
