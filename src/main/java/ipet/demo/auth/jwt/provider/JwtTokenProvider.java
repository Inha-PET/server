package ipet.demo.auth.jwt.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import ipet.demo.auth.jwt.dto.TokenInfo;
import ipet.demo.auth.jwt.userdetails.MyUserDetails;
import ipet.demo.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {
    private final Key key;
    public static final String TOKEN_GRANT_TYPE = "Bearer";
    public static final String HEADER = "Authorization";
    private static final int ACCESS_EXP = 1000 * 60 * 60 * 2;
    private static final int REFRESH_EXP = 1000 * 60 * 60 * 24 * 5;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 회원 정보를 가지고 accessToken, refreshToken을 생성하는 메서드
    public TokenInfo generateToken(Authentication authentication) {
        long now = (new Date()).getTime();

        //access token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_EXP);
        String accessToken = generateAccessToken(authentication, accessTokenExpiresIn);

        //refresh token 생성
        Date refreshTokenExpiresIn = new Date(now + REFRESH_EXP);
        String refreshToken = generateRefreshToken(authentication, refreshTokenExpiresIn);

        return TokenInfo.builder()
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpiresIn)
                .refreshToken(refreshToken)
                .refreshTokenExpiresIn(refreshTokenExpiresIn)
                .build();
    }

    private String generateRefreshToken(Authentication authentication, Date refreshTokenExpiresIn) {
        String authorities = getAuthorities(authentication);
        Member member = getMember(authentication);
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("id", member.getId())
                .claim("auth", authorities)
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateAccessToken(Authentication authentication, Date accessTokenExpiresIn) {
        String authorities = getAuthorities(authentication);
        Member member = getMember(authentication);
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("id", member.getId())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //Authentication 객체에서 권한 가져오기
    private String getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    private Member getMember(Authentication authentication) {
        MyUserDetails principal = (MyUserDetails) authentication.getPrincipal();
        return principal.getMember();
    }
}
