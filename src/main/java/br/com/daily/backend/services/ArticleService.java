package br.com.daily.backend.services;

import br.com.daily.backend.entities.Article;
import br.com.daily.backend.entities.dtos.ArticleDTO;
import br.com.daily.backend.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository repository;

    public List<ArticleDTO> getLastThreeArticles() {
        List<Article> lastArticles = repository.findTop3ByOrderByCreationDateDesc();
        List<ArticleDTO> response = new ArrayList<>();
        for (Article article : lastArticles) {
            ArticleDTO articleDTO = Article.mapToDTO(article);
            response.add(articleDTO);
        }
        return response;
    }

    public Page<Article> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ArticleDTO createArticle(ArticleDTO request){
        Article article = ArticleDTO.mapToDo(request);
        return Article.mapToDTO(repository.save(article));
    }
}
