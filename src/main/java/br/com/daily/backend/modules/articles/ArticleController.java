package br.com.daily.backend.modules.articles;

import br.com.daily.backend.modules.articles.domain.Article;
import br.com.daily.backend.modules.articles.domain.dto.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<List<ArticleDTO>> fetchLastThreeArticles() {
        return new ResponseEntity<>(service.getLastThreeArticles(), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<Page<Article>> list(Pageable pageable){
        return new ResponseEntity<>(service.listAll(pageable),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO request){
        return new ResponseEntity<>(service.createArticle(request),HttpStatus.CREATED);
    }
}
