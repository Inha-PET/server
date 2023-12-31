package ipet.demo.api.service.board.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import ipet.demo.domain.board.Board;
import ipet.demo.domain.board.BoardType;
import ipet.demo.domain.file.Attachment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "게시글 응답 DTO")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class BoardResponse {

    @Schema(description = "게시글 id", example = "1")
    private Long id;

    @Schema(description = "제목", example = "제목입니다.")
    private String title;

    @Schema(description = "내용", example = "내용입니다.")
    private String content;

//    @Schema(description = "게시판 타입", example = "FREE/MISSING/SIGHTING")
//    private BoardType boardType;

    @Schema(description = "사건 시간", example = "2023-10-13T00:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime incidentDateTime;

    @Schema(description = "사건 장소", example = "서울특별시 강남구")
    private String location;

    @Schema(description = "작성자", example = "홍길동")
    private String name;

    @Schema(description = "작성자 이메일", example = "test@test.com")
    private String email;

    @Schema(description = "첨부파일 ID 목록", example = "[1,2,3]")
    private List<Long> attachmentIds;

    @Builder
    private BoardResponse(Long id, String title, String content, LocalDateTime incidentDateTime, String location, String name, String email, List<Long> attachmentIds) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.incidentDateTime = incidentDateTime;
        this.location = location;
        this.name = name;
        this.email = email;
        this.attachmentIds = attachmentIds;
    }

    public static BoardResponse fromEntity(Board board) {
        List<Long> attachmentIds = board.getAttachments().stream()
                .map(Attachment::getId)
                .toList();
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .incidentDateTime(board.getIncidentDateTime())
                .location(board.getLocation())
                .name(board.getMember().getName())
                .email(board.getMember().getEmail())
                .attachmentIds(attachmentIds)
                .build();
    }

}
