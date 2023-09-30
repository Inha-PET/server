package ipet.demo.api.controller.member;

import ipet.demo.api.controller.member.request.MemberJoinRequest;
import ipet.demo.api.controller.member.request.MemberLoginRequest;
import ipet.demo.support.ControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends ControllerTestSupport {

    @DisplayName("정상적인 회원가입 테스트")
    @Test
    @WithMockUser //security와 webmvctest 충돌 1. 인증된 사용자를 만들어서 테스트
    void join() throws Exception {
        //given
        MemberJoinRequest request = MemberJoinRequest.builder()
                .email("test@test.com")
                .password("test12345")
                .name("test")
                .build();
        //when //then
        mockMvc.perform(
                        post("/api/v1/members/join")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                                //sercurity와 webmvctest 충돌 2. 같이 사용할 때는 csrf()를 추가해줘야 한다.
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.name()))
                .andExpect(jsonPath("$.message").value(HttpStatus.OK.name()));

    }

    @DisplayName("정상적인 로그인 테스트")
    @Test
    @WithMockUser //security와 webmvctest 충돌 1. 인증된 사용자를 만들어서 테스트
    void login() throws Exception {
        //given
        MemberLoginRequest request = MemberLoginRequest.builder()
                .email("test@test.com")
                .password("test12345")
                .build();
        //when //then
        mockMvc.perform(
                        post("/api/v1/members/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                                //sercurity와 webmvctest 충돌 2. 같이 사용할 때는 csrf()를 추가해줘야 한다.
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.name()))
                .andExpect(jsonPath("$.message").value(HttpStatus.OK.name()));

    }

}