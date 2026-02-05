package kr.co.newgyo.user.repository;

import kr.co.newgyo.user.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {

}
