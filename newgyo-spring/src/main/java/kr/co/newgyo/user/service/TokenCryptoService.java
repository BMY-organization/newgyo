package kr.co.newgyo.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class TokenCryptoService {
    //final 필드는 생성자 끝나기 전에 반드시 초기화
//    final 필드는 생성자 본문에서 초기화해도 컴파일 오류가 발생합니다.
//    final은 선언 시 또는 생성자 시그니처에서 초기화해야 합니다.
    private final BytesEncryptor encryptor;

    public TokenCryptoService(
            @Value("${encryption.passphrase}") String passphrase,
            @Value("${encryption.salt}") String salt) {

        if (passphrase.isBlank() || salt.isBlank()) {
            throw new IllegalStateException("암호화 키( passphrase / salt )가 설정되지 않았습니다");
        }

        this.encryptor = Encryptors.stronger(passphrase, salt);
    }

    // 저장용
    public String encryptRefreshToken(String refreshToken) {
        byte[] encryptedBytes = encryptor.encrypt(refreshToken.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);  // DB에 이걸 저장
    }

    // 조회용
    public String decryptRefreshToken(String encryptedBase64) {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);
        byte[] decryptedBytes = encryptor.decrypt(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
