package kr.co.newgyo.subscribe.repository;


import kr.co.newgyo.user.entity.UserKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<UserKeyword,Integer> {

}
