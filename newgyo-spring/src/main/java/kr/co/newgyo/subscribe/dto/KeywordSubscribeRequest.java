package kr.co.newgyo.subscribe.dto;

import java.util.List;

public record KeywordSubscribeRequest(String content,
                                      List<String> includedCategories) {
    // 선택적: 간단한 검증 로직 추가 가능
    public KeywordSubscribeRequest {
        if (content == null || !content.equals("subscribe")) {
            throw new IllegalArgumentException("content는 'subscribe'여야 합니다.");
        }
        if (includedCategories == null || includedCategories.isEmpty()) {
            throw new IllegalArgumentException("최소 1개 이상의 카테고리를 선택해야 합니다.");
        }
    }
}
