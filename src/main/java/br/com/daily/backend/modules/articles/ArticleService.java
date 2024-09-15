package br.com.daily.backend.modules.articles;

import br.com.daily.backend.core.exceptions.GenericException;
import br.com.daily.backend.modules.articles.domain.Article;
import br.com.daily.backend.modules.articles.domain.dto.ArticleRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository repository;

    public List<ArticleRecord> getLastThreeArticles() {

        List<Article> lastArticles = repository.findTop3ByOrderByCreatedAtDesc();
        List<ArticleRecord> response = new ArrayList<>();

        for (Article article : lastArticles) {
            response.add(Article.mapToRecord(article));
        }

        return response;
    }

    public List<ArticleRecord> listAll() {

        List<Article> allArticles = repository.findAll();

        List<ArticleRecord> response = new ArrayList<>();

        for (Article article : allArticles) {
            response.add(Article.mapToRecord(article));
        }

        return response;
    }

    public ArticleRecord createArticle(ArticleRecord request) {
        Article article = new Article();

        article.setMinutesToRead(request.minutesToRead());
        article.setText(request.text());
        article.setTitle(request.title());
        article.setAutor(request.autor());
        article.setBannerURL(request.bannerURL());
        article.setCategory(request.category());

        return Article.mapToRecord(repository.save(article));
    }

    public ArticleRecord updateArticle(ArticleRecord request) {

        Optional<Article> articleDB = repository.findById(request.id());

        if (articleDB.isPresent()) {

            Article article = articleDB.get();
            article.setMinutesToRead(request.minutesToRead());
            article.setText(request.text());
            article.setTitle(request.title());
            article.setAutor(request.autor());
            article.setBannerURL(request.bannerURL());
            article.setCategory(request.category());

            return Article.mapToRecord(repository.save(article));
        }

        throw new GenericException("ARTICLE_NOT_FOUND", HttpStatus.NOT_FOUND);

    }

    public void deleteArticle(Long id) {
        Optional<Article> articleDB = repository.findById(id);

        if (articleDB.isPresent()) {
            repository.delete(articleDB.get());
        } else {
            throw new GenericException("ARTICLE_NOT_FOUND", HttpStatus.NOT_FOUND);
        }
    }
}
