package kr.co.newgyo.article.repository;

import kr.co.newgyo.article.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,Integer> {

    // id를 가지고 Article 데이터 가져오기
    Article findById(Long id);
}
