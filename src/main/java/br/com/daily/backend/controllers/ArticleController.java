package br.com.daily.backend.controllers;

import br.com.daily.backend.entities.Article;
import br.com.daily.backend.entities.dtos.ArticleDTO;
import br.com.daily.backend.services.ArticleService;
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

    //get post put delete
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
