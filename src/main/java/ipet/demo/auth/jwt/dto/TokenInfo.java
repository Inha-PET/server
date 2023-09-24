package ipet.demo.auth.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class TokenInfo {
    private String grantType;
    private String accessToken;
    private Date accessTokenExpiresIn;
    private String refreshToken;
    private Date refreshTokenExpiresIn;
}
