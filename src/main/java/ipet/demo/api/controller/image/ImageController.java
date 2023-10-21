package ipet.demo.api.controller.image;

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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestParam("category") String category, @RequestPart("file") List<MultipartFile> multipartFiles) {
        long now = System.currentTimeMillis();
        return imageService.upload(category, multipartFiles, now);
    }
}
