package ipet.demo.api.controller.board.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import ipet.demo.api.service.board.request.BoardCreateServiceRequest;
import ipet.demo.domain.board.BoardType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "게시판 생성 요청 DTO")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class BoardCreateRequest {

    @Schema(description = "제목", example = "제목입니다.", minLength = 1, maxLength = 30)
    @NotBlank
    @Length(min = 1, max = 30)
    private String title;

    @Schema(description = "내용", example = "내용입니다.", minLength = 1, maxLength = 1000)
    @NotBlank
    @Length(min = 1, max = 1000)
    private String content;

//    @Schema(description = "게시판 타입", example = "FREE/MISSING/SIGHTING")
//    private BoardType boardType;

    @Schema(description = "사건 시간", example = "2023-10-13T00:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime incidentDateTime;

    @Schema(description = "사건 장소", example = "서울특별시 강남구")
    private String location;

    @Schema(description = "첨부파일 ID 목록", example = "[1,2,3]")
    private List<Long> attachmentIds = new ArrayList<>();

    @lombok.Builder
    private BoardCreateRequest(String title, String content, LocalDateTime incidentDateTime, String location, List<Long> attachmentIds) {
        this.title = title;
        this.content = content;
        this.incidentDateTime = incidentDateTime;
        this.location = location;
        this.attachmentIds = attachmentIds;
    }

    public BoardCreateServiceRequest toServiceRequest() {
        return BoardCreateServiceRequest.builder()
                .title(title)
                .content(content)
                .incidentDateTime(incidentDateTime)
                .location(location)
                .attachmentIds(attachmentIds)
                .build();
    }
}
