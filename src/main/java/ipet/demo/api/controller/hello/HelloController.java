package ipet.demo.api.controller.hello;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "테스트 콘트롤러", description = "통신 테스트용 API")
@RequestMapping("/hello")
@RestController
@RequiredArgsConstructor
@Slf4j
public class HelloController {

    @Operation(
            summary = "hello",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "권한 없음"),
                    @ApiResponse(responseCode = "403", description = "접근 금지"),
                    @ApiResponse(responseCode = "404", description = "찾을 수 없음"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            }
    )
    @GetMapping("/world")
    public String index() {

        log.info("hello 호출은 됐음");
        return "Hello world!";
    }

    @GetMapping("/")
    public String index2() {
        return "hhhhhhey";
    }
}
