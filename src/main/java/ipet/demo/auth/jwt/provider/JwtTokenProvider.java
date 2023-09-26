package ipet.demo.auth.jwt.provider;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import ipet.demo.auth.jwt.dto.TokenInfo;
import ipet.demo.auth.jwt.userdetails.MyUserDetails;
import ipet.demo.domain.member.Member;
import ipet.demo.exception.BusinessLogicException;
import ipet.demo.exception.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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

    public Long getMemberId(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get("id") == null) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }

        return claims.get("id", Long.class);
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }

        //클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = getAuthorityList(claims);

        //UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /**
     * 토큰 정보를 검증하는 메서드
     */
    public boolean validateToken(String accessToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
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

    /**
     * Authentication 객체에서 권한 가져오기
     * @Return: ex) ROLE_USER, ROLE_ADMIN
     */
    private String getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
    private Member getMember(Authentication authentication) {
        MyUserDetails principal = (MyUserDetails) authentication.getPrincipal();
        return principal.getMember();
    }
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private List<SimpleGrantedAuthority> getAuthorityList(Claims claims) {
        return Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
