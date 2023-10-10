package ipet.demo.auth.jwt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Schema(description = "토큰 정보")
@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class TokenInfo {
    @Schema(description = "토큰 타입", example = "Bearer")
    private String grantType;
    @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTIzQHRlc3QuY29tIiwiaWQiOjEsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2OTY3NjU2Nzh9.ld_dhXj4eWtdksPUYNiQjeQvfsibBjvzSayOyce_4hs")
    private String accessToken;
    @Schema(description = "액세스 토큰 만료 시간", example = "2023-10-08T11:47:58.188+00:00")
    private Date accessTokenExpiresIn;
    @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTIzQHRlc3QuY29tIiwiaWQiOjEsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2OTcxOTA0Nzh9.dv7q-HITzRGK6czUBhU3ksTKLmnZhN247bZLjPGfp6c")
    private String refreshToken;
    @Schema(description = "리프레시 토큰 만료 시간", example = "2023-10-13T09:47:58.188+00:00")
    private Date refreshTokenExpiresIn;

    @Builder
    private TokenInfo(String grantType, String accessToken, Date accessTokenExpiresIn, String refreshToken, Date refreshTokenExpiresIn) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

}
