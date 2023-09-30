package ipet.demo.api.service.member;

import ipet.demo.api.service.member.request.MemberJoinServiceRequest;
import ipet.demo.api.service.member.response.MemberResponse;
import ipet.demo.domain.member.Member;
import ipet.demo.domain.member.MemberRepository;
import ipet.demo.exception.BusinessLogicException;
import ipet.demo.support.IntegrationTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@Transactional
class MemberServiceTest extends IntegrationTestSupport {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @DisplayName("회원가입 테스트")
    @Test
    void join() {
        //given
        MemberJoinServiceRequest serviceRequest = MemberJoinServiceRequest.builder()
                .email("test@test.com")
                .password("test")
                .name("test")
                .build();
        //when
        MemberResponse memberResponse = memberService.join(serviceRequest);

        //then
        assertThat(memberResponse.getId()).isNotNull();
        assertThat(memberResponse)
                .extracting("email", "name")
                .contains("test", "test");
    }

    @DisplayName("회원 가입 시 이메일이 중복하면 예외가 발생한다")
    @Test
    void joinWithDuplicatedEmail() {
        //given
        Member member = Member.createMember("test@test.com", "test", "test");
        memberRepository.save(member);

        MemberJoinServiceRequest serviceRequest = MemberJoinServiceRequest.builder()
                .email("test@test.com")
                .password("test")
                .name("test")
                .build();

        //when //then
        assertThatThrownBy(() -> {
            memberService.join(serviceRequest);
        })
                .isInstanceOf(BusinessLogicException.class)
                .hasMessageContaining("이미 가입된 이메일입니다.");
    }

}