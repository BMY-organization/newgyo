package kr.co.newgyo.article.repository;

import kr.co.newgyo.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Boolean existsByUrl(String url);

    Optional<Article> findById(Long id);
}
