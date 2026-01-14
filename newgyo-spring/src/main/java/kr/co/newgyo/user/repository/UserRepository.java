package kr.co.newgyo.user.repository;


import kr.co.newgyo.user.entity.User;
import kr.co.newgyo.user.enums.LoginProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    Boolean existsByUsername(String username);

    User findByUsername(String username);

    List<User> findByProvider(LoginProvider provider);
}
