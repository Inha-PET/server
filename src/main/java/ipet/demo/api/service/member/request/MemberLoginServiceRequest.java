package ipet.demo.api.service.member.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class MemberLoginServiceRequest {

    private String email;
    private String password;

    @lombok.Builder
    private MemberLoginServiceRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
