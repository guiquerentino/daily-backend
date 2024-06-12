package br.com.daily.backend.controllers;

import br.com.daily.backend.entities.dtos.ArticleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/goals")
public class GoalsController {

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> fetchGoals(){

    }

}
