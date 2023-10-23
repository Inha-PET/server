package ipet.demo.api.controller.image;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ipet.demo.api.service.image.facade.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Image", description = "이미지 관련 API")
@RequestMapping("/api/v1/images")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/test")
    public String test() {
        return imageService.test();
    }

    @Operation(
            summary = "이미지 업로드",
            description = """
                    이미지를 업로드한다.
                    
                    `form-data`형식으로
                    `category`에는 업로드할 이미지의 카테고리를 넣어준다.
                    `file`에는 이미지를 첨부한다.
                    첨부파일은 최대 10개까지 가능하다.
                    첨부파일은 최대 20MB까지 가능하다.
                    """,
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "성공"
                    ),
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestParam("category") String category, @RequestPart("file") List<MultipartFile> multipartFiles) {
        long now = System.currentTimeMillis();
        return imageService.upload(category, multipartFiles, now);
    }
}
