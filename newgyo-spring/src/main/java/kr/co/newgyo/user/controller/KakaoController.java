package kr.co.newgyo.user.controller;

import kr.co.newgyo.user.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService service;

    @GetMapping("/login/kakao")
    public String kakao(@RequestParam("code") String code, Model model) {
        String jwt = service.authenticateWithKakao(code);
        // 우리 사이트에 추가로 회원가입을 시킬만큼 정보가 필요한가? (사용지 권한)
        // 카카오 로그인으로 로그인하지않고 사이트에 가입한 사용자와 이메일이 같은지 확인 필요

        // jwt 토큰을 발급하고 그 토큰을 localstoreg에 저장
        // json으로 클아이언트에 토큰보내기
        model.addAttribute("token", jwt);
        return "kakao-callback";
    }

    @PostMapping("/send-to-me")
    public ResponseEntity<String> sendToMe(@RequestBody Map<String, String> request) {
        String accessToken = request.get("accessToken");
        String message = request.getOrDefault("message", "안녕! 토이 프로젝트 테스트 메시지야~");

        boolean success = service.sendTextToMe(accessToken, message);

        return success
                ? ResponseEntity.ok("메시지 발송 성공!")
                : ResponseEntity.badRequest().body("발송 실패");
    }
}

