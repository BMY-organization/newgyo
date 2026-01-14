package kr.co.newgyo.user.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA라면 필수
@AllArgsConstructor
@Table(name = "TOKEN")
public class Token {

    @Id
    @Column(name = "TOKEN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 리프레쉬 토큰을 암호화 저장
    // 카카오 보낼 때 복호화 후 엑세스 토큰 받아서 보내게
    @Column(length = 1024, nullable = false)
    private String refreshToken;

}
