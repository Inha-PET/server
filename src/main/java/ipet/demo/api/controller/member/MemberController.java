package ipet.demo.api.controller.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ipet.demo.api.ApiResponseDto;
import ipet.demo.api.controller.member.request.MemberJoinRequest;
import ipet.demo.api.controller.member.request.MemberLoginRequest;
import ipet.demo.api.service.member.response.MemberResponse;
import ipet.demo.api.service.member.MemberService;
import ipet.demo.auth.jwt.dto.TokenInfo;
import ipet.demo.auth.jwt.provider.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//@OpenAPIDefinition(
//        tags = {
//                @Tag(name = "Member", description = "회원 관련 API")
//        }
//)
@Tag(name = "Member", description = "회원 관련 API")
@RequestMapping("/api/v1/members")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @Operation(
            summary = "회원 가입",
            description = "회원 가입을 요청한다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "회원 가입 요청 DTO", required = true,
                    content = @Content(schema = @Schema(implementation = MemberJoinRequest.class))),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공"),
                    @ApiResponse(
                            responseCode = "409",
                            description = "이메일 중복",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(example = "{" +
                                            "\"code\": 409, " +
                                            "\"status\": \"409 CONFLICT\", " +
                                            "\"message\": \"이미 가입된 이메일입니다.\", " +
                                            "\"data\": null" +
                                            "}")))
//                    @ApiResponse(responseCode = "401", description = "권한 없음"),
//                    @ApiResponse(responseCode = "403", description = "접근 금지"),
//                    @ApiResponse(responseCode = "404", description = "찾을 수 없음"),
//                    @ApiResponse(responseCode = "500", description = "서버 오류")
            }
//            parameters = {
//                    @Parameter(description = "회원 가입 요청 DTO", required = true, in = ParameterIn.HEADER,
//                            content = @Content(schema = @Schema(implementation = MemberJoinRequest.class)))
//            }
    )
    @PostMapping("/join")
    public ApiResponseDto<MemberResponse> join(@Validated @RequestBody MemberJoinRequest request, HttpServletResponse response) {
        MemberResponse memberResponse = memberService.join(request.toServiceRequest());

        //Controller 테스트 작성 시 memberResponse.getTokenInfo()가 null이 되어서 테스트가 실패한다.
        //그래서 아래와 같이 null 체크를 해줘야 한다.
        if  (memberResponse != null && memberResponse.getTokenInfo() != null) {
            setAuthHeader(response, memberResponse.getTokenInfo());
        }

        return ApiResponseDto.ok(memberResponse);
    }

    @Operation(
            summary = "로그인",
            description = "로그인을 요청한다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "로그인 요청 DTO", required = true,
                    content = @Content(schema = @Schema(implementation = MemberLoginRequest.class))),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "로그인 실패",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(example = "{" +
                                            "\"code\": 401, " +
                                            "\"status\": \"401 UNAUTHORIZED\", " +
                                            "\"message\": \"로그인에 실패했습니다.\", " +
                                            "\"data\": null" +
                                            "}")))}
    )
    @PostMapping("/login")
    public ApiResponseDto<MemberResponse> login(@Validated @RequestBody MemberLoginRequest request, HttpServletResponse response) {

        MemberResponse memberResponse = memberService.login(request.toServiceRequest());

        //Controller 테스트 작성 시 memberResponse.getTokenInfo()가 null이 되어서 테스트가 실패한다.
        //그래서 아래와 같이 null 체크를 해줘야 한다.
        if  (memberResponse != null && memberResponse.getTokenInfo() != null) {
            setAuthHeader(response, memberResponse.getTokenInfo());
        }

        return ApiResponseDto.ok(memberResponse);
    }

    private void setAuthHeader(HttpServletResponse response, TokenInfo tokenInfo) {
        response.addHeader(JwtTokenProvider.ACCESS_HEADER, JwtTokenProvider.TOKEN_GRANT_TYPE + tokenInfo.getAccessToken());
        response.addHeader(JwtTokenProvider.REFRESH_HEADER, tokenInfo.getRefreshToken());
    }

}
