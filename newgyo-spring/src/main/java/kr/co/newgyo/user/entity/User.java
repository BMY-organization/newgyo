package kr.co.newgyo.user.entity;

import jakarta.persistence.*;
import kr.co.newgyo.user.enums.LoginProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USER")
public class User {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "TOKEN_ID")
    private Token token;

    private String username;
    private String password;
    private String role;
    private Boolean isSubscribed;

    // 사용자가 어떤 로그인 방식으로 가입했는지
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoginProvider provider;

    public void assignToken(Token token){
        this.token = token;
    }
}
