package ipet.demo.api.service.member.response;

import io.swagger.v3.oas.annotations.media.Schema;
import ipet.demo.auth.jwt.dto.TokenInfo;
import ipet.demo.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "회원 정보 응답 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {

    @Schema(description = "회원 id", example = "1")
    private Long id;

    @Schema(description = "이메일", example = "test123@test.com")
    private String email;

    @Schema(description = "닉네임", example = "준영맨")
    private String name;

    @Schema(description = "회원 생성 시간", example = "2023-09-26T11:44:30.327959")
    private LocalDateTime createdDate;

    @Schema(description = "토큰 정보")
    private TokenInfo tokenInfo;

    @Builder
    private MemberResponse(Long id, String email, String name, LocalDateTime createdDate, TokenInfo tokenInfo) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.createdDate = createdDate;
        this.tokenInfo = tokenInfo;
    }

    public static MemberResponse fromEntity(Member member, TokenInfo tokenInfo) {
        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .createdDate(member.getCreatedDateTime())
                .tokenInfo(tokenInfo)
                .build();
    }

}