package ipet.demo.api.controller.hello;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ipet.demo.api.service.image.storage.util.LocalFileUtils;
import ipet.demo.domain.board.Board;
import ipet.demo.exception.BusinessLogicException;
import ipet.demo.exception.ExceptionCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

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
        log.info("HelloController의 경로: {}", Paths.get("dd"+ File.separator +"fileName").toAbsolutePath().toString());
        log.info("hello 호출은 됐음");
        return "Hello world!";
    }

    @GetMapping("/image")
    public ByteArrayResource image(HttpServletResponse response) {


        response.setHeader("Content-Disposition", "attachment; filename=1700198256610_cat.jpg");
        try (InputStream fileInputStream = new FileInputStream("C:\\Users\\c0512\\OneDrive-INHA\\DeskTop\\git\\capstone\\demo\\src\\main\\resources\\static\\images\\1700198256610_cat.jpg")){
            return new ByteArrayResource(fileInputStream.readAllBytes());
        } catch (IOException e) {
            throw new BusinessLogicException(ExceptionCode.FILE_IO_EXCEPTION, e);
        }

    }

    @GetMapping("/")
    public String index2() {
        return "hhhhhhey";
    }

    @PostMapping("/boardtest")
    public Board boardTest(@RequestBody Board board) {
        return board;
    }
}
