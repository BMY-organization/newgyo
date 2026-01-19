package kr.co.newgyo.article.repository;

import kr.co.newgyo.article.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword,Integer> {
    Keyword findByKeyword(String keyword);
}
