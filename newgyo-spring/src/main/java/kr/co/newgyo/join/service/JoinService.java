package kr.co.newgyo.join.service;

import kr.co.newgyo.user.dto.UserDto;
import kr.co.newgyo.user.entity.User;
import kr.co.newgyo.user.enums.LoginProvider;
import kr.co.newgyo.user.enums.Role;
import kr.co.newgyo.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    // 웹 회원가입 시 정보 저장 로직
    public void joinProcess(UserDto userDto) {
        // 유저와 비밀번호
        // 비밀번호는 암호화
        String username = userDto.getUsername();
        String password = bCryptPasswordEncoder.encode(userDto.getPassword());

        // 해당 아이디가 이미 있는지
        // 카카오 아이디로 먼저 회원가입 했다면 여기서 체크 될 것
        Boolean exists = userRepository.existsByUsername(username);

        if(exists){
            // 이미 아이디가 있다는 에러 보내고 리다이렉트 시키기
            return;
        }

        // 웹 회원은 카카오톡 엑세스토큰이 없다
        User userBuilder = User.builder()
                .token(null) // null 말고 달리 줄 방법?
                .username(username)
                .password(password)
                .role(Role.USER)
                .provider(LoginProvider.LOCAL)
                .build();

        userRepository.save(userBuilder);
    }
}
