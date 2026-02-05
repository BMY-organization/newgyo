package kr.co.newgyo.subscribe.controller;

import kr.co.newgyo.security.CustomUserDetails;
import kr.co.newgyo.subscribe.dto.KeywordSubscribeRequest;
import kr.co.newgyo.subscribe.service.SubscribeService;
import kr.co.newgyo.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class SubscribeController {

    private final SubscribeService subscribeService;

    public SubscribeController(UserRepository userRepository, SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    // 처음 구독시 동작
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody KeywordSubscribeRequest request,
                                            @AuthenticationPrincipal CustomUserDetails userDetails){
        // 사용자 구독 데이터 변경 false -> true (구독시 화면에 구독 버튼이 아닌 카테도리 변경 아이콘 띄우기 - 예정)
        subscribeService.firstSubscribe(userDetails);
        // 리스트로 들어온 키워드 데이터 가지고 유저키워드 값 저장하기
        subscribeService.savaUserKeyword(userDetails, request);
        return ResponseEntity.ok("구독이 완료되었습니다.");
    }

    // 카테고리 변경시 (예정)
    // 구독 취소시 (예정)
}
