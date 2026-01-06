package kr.co.newgyo.article.service;

import kr.co.newgyo.article.dto.SummaryRequest;
import kr.co.newgyo.article.dto.SummaryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 *  파이썬 서버로 요약 요청
 * */
@Slf4j
@Service
public class ArticleSummaryService {
    private final WebClient webClient;

    public ArticleSummaryService(WebClient.Builder webClientBuilder){
            this.webClient = webClientBuilder
                    .baseUrl("http://localhost:8000")
                    .build();
    }

    // AI 요약 요청
    public List<SummaryResponse> getSummary(List<SummaryRequest> summary){
        try{
            List<SummaryResponse> response = webClient.post()
                    .uri("/api/summary")
                    .bodyValue(summary)
                    .retrieve()
                    .bodyToFlux(SummaryResponse.class)
                    .collectList()
                    .block();

            log.info("[article summary] {} ", response);
            return response;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}