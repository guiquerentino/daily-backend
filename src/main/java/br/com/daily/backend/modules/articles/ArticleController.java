package br.com.daily.backend.modules.articles;

import br.com.daily.backend.modules.articles.domain.Article;
import br.com.daily.backend.modules.articles.domain.dto.ArticleRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    @Autowired
    ArticleService service;

    @GetMapping
    public ResponseEntity<List<ArticleRecord>> fetchLastThreeArticles() {
        return new ResponseEntity<>(service.getLastThreeArticles(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleRecord>> listAll(Pageable pageable) {
        return new ResponseEntity<>(service.listAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArticleRecord> createArticle(@RequestBody ArticleRecord request) {
        return new ResponseEntity<>(service.createArticle(request), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ArticleRecord> updateArticle(@RequestBody ArticleRecord request) {
        return new ResponseEntity<>(service.updateArticle(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ArticleRecord> deleteArticle(@RequestParam Long id) {
        service.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
