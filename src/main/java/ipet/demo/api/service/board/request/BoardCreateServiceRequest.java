package ipet.demo.api.service.board.request;

import ipet.demo.domain.board.Board;
import ipet.demo.domain.board.BoardType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class BoardCreateServiceRequest {

    private String title;
    private String content;
    private BoardType boardType;
    private LocalDateTime incidentDateTime;
    private String location;

    @lombok.Builder
    private BoardCreateServiceRequest(String title, String content, BoardType boardType, LocalDateTime incidentDateTime, String location) {
        this.title = title;
        this.content = content;
        this.boardType = boardType;
        this.incidentDateTime = incidentDateTime;
        this.location = location;
    }


    public Board toEntity() {
        return Board.createNewBoardNoMember(title, content, boardType, incidentDateTime, location);
    }
}
