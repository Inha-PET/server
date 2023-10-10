package ipet.demo.api.controller.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import ipet.demo.api.service.member.request.MemberLoginServiceRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Schema(description = "회원 로그인 요청 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLoginRequest {

    @Schema(description = "이메일", example = "test123@test.com")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "비밀번호", example = "12345678")
    @NotBlank
    @Length(min = 8, max = 15)
    //regular expression, ^:문자열 시작, \S:공백을 제외한 모든 문자, $:문자열의 끝
    @Pattern(regexp = "^\\S+$", message = "공백을 포함할 수 없습니다.")
    private String password;

    @lombok.Builder
    private MemberLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public MemberLoginServiceRequest toServiceRequest() {
        return MemberLoginServiceRequest.builder()
                .email(email)
                .password(password)
                .build();
    }

}