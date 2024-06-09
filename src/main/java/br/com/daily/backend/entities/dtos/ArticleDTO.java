package br.com.daily.backend.entities.dtos;

import br.com.daily.backend.entities.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDTO {
    private String title;
    private String text;
    private String autor;
    private LocalDateTime creationDate = LocalDateTime.now();

    public static Article mapToDo(ArticleDTO articleDTO) {
        Article article = new Article();

        article.setTitle(articleDTO.getTitle());
        article.setText(articleDTO.getText());
        article.setAutor(articleDTO.getAutor());
        article.setCreationDate(articleDTO.getCreationDate());

        return article;
    }
}
