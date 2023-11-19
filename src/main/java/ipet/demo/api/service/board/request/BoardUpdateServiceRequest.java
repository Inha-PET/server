package ipet.demo.api.service.board.request;

import ipet.demo.domain.board.Board;
import ipet.demo.domain.board.BoardType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@ToString
public class BoardUpdateServiceRequest {

    private String title;
    private String content;
    private BoardType boardType;
    private LocalDateTime incidentDateTime;
    private String location;
    private List<Long> attachmentIds = new ArrayList<>();

    @lombok.Builder
    private BoardUpdateServiceRequest(String title, String content, BoardType boardType, LocalDateTime incidentDateTime, String location, List<Long> attachmentIds) {
        this.title = title;
        this.content = content;
        this.boardType = boardType;
        this.incidentDateTime = incidentDateTime;
        this.location = location;
        this.attachmentIds = attachmentIds;
    }


    public Board toEntity() {
        return Board.createNewBoardNoMember(title, content, incidentDateTime, location);
    }
}
