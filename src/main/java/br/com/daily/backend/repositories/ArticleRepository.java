package br.com.daily.backend.repositories;

import br.com.daily.backend.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {

    public List<Article> findTop3ByOrderByCreationDateDesc();

}
