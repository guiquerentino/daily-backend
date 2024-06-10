package br.com.daily.backend.modules.articles;

import br.com.daily.backend.modules.articles.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {

    public List<Article> findTop3ByOrderByCreationDateDesc();

}
