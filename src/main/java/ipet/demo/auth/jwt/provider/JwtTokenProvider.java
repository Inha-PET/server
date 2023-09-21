package ipet.demo.auth.jwt.provider;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import ipet.demo.auth.jwt.dto.TokenInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {
    private final Key key;
    public static final String TOKEN_GRANT_TYPE = "Bearer";
    public static final String HEADER = "Authorization";
    private static final int ACCESS_EXP = 1000 * 60 * 60 * 24;
    private static final int REFRESH_EXP = 1000 * 60 * 60 * 24;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 회원 정보를 가지고 accessToken, refreshToken을 생성하는 메서드
    public TokenInfo generateToken(Authentication authentication) {
        //Authentication 객체에서 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.joining(","));
        //branch test
        //branch test2

        return null;
    }
}
