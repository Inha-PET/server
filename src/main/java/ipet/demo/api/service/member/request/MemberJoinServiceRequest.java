package ipet.demo.api.service.member.request;

import ipet.demo.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class MemberJoinServiceRequest {

    private String email;
    private String password;
    private String name;

    @lombok.Builder
    private MemberJoinServiceRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Member toEntity() {
        return Member.createMember(email, password, name);
    }
}
