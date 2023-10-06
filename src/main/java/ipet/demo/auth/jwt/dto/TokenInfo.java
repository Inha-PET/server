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
    @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTIzQHRlc3QuY29tIi")
    private String accessToken;
    @Schema(description = "액세스 토큰 만료 시간", example = "2023-09-26T11:44:30.327959")
    private Date accessTokenExpiresIn;
    @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTIzQHRlc3QuY29tIi")
    private String refreshToken;
    @Schema(description = "리프레시 토큰 만료 시간", example = "2023-09-26T11:44:30.327959")
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
