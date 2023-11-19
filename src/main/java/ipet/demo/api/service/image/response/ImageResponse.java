package ipet.demo.api.service.image.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import ipet.demo.domain.file.Attachment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "이미지 응답 DTO")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ImageResponse {
    @Schema(description = "이미지 id", example = "1")
    private Long id;

    //프론트 요청으로 인해 주석처리
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//    @Schema(description = "저장 시각", example = "2023-11-13T00:00")
//    private LocalDateTime createdAt;

    @Builder
    private ImageResponse(Long id) {
        this.id = id;

    }

    public static ImageResponse fromEntity(Attachment attachment) {
        return ImageResponse.builder()
                .id(attachment.getId())
                .build();
    }
}
