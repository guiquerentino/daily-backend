package br.com.daily.backend.modules.articles.domain.dto;

import br.com.daily.backend.modules.articles.domain.Article;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ArticleDTO {
    private String title;
    private String text;
    private String autor;
    private String bannerURL;
    private String minutesToRead;
    private String category;
    private LocalDateTime creationDate = LocalDateTime.now();

    public static Article mapToDo(ArticleDTO articleDTO) {
        Article article = new Article();

        article.setBannerURL(articleDTO.getBannerURL());
        article.setTitle(articleDTO.getTitle());
        article.setMinutesToRead(articleDTO.getMinutesToRead());
        article.setCategory(articleDTO.getCategory());
        article.setText(articleDTO.getText());
        article.setAutor(articleDTO.getAutor());
        article.setCreationDate(articleDTO.getCreationDate());

        return article;
    }
}
