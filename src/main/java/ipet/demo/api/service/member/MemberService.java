package ipet.demo.api.service.member;

import ipet.demo.api.service.member.request.MemberJoinServiceRequest;
import ipet.demo.api.service.member.request.MemberLoginServiceRequest;
import ipet.demo.api.service.member.response.MemberResponse;
import ipet.demo.auth.jwt.dto.TokenInfo;
import ipet.demo.auth.jwt.provider.JwtTokenProvider;
import ipet.demo.domain.member.Member;
import ipet.demo.domain.member.MemberRepository;
import ipet.demo.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static ipet.demo.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResponse join(MemberJoinServiceRequest serviceRequest) {
        //이메일 중복 확인
        memberRepository.findByEmail(serviceRequest.getEmail())
                .ifPresent(member -> {
                    throw new BusinessLogicException(EMAIL_DUPLICATION);
                });

        //닉네임 중복 확인 넣을까?

        //회원 가입
        String beforePassword = serviceRequest.getPassword();
        serviceRequest.encodePassword(passwordEncoder.encode(serviceRequest.getPassword()));
        Member savedMember = memberRepository.save(serviceRequest.toEntity());

        //token 생성
        TokenInfo tokenInfo = generateToken(savedMember.getEmail(), beforePassword);

        return MemberResponse.fromEntity(savedMember, tokenInfo);
    }

    public MemberResponse login(MemberLoginServiceRequest serviceRequest) {
        TokenInfo tokenInfo = generateToken(serviceRequest.getEmail(), serviceRequest.getPassword());

        return MemberResponse.fromEntity(memberRepository.findByEmail(serviceRequest.getEmail())
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_FOUND))
        , tokenInfo);
    }

    private TokenInfo generateToken(String email, String password) {
        // 1. Login EMAIL/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 MyUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        return jwtTokenProvider.generateToken(authentication);
    }
}
