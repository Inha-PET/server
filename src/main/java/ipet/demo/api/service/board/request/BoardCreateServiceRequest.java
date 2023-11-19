package ipet.demo.api.service.board.request;

import ipet.demo.domain.board.Board;
import ipet.demo.domain.board.BoardType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class BoardCreateServiceRequest {

    private String title;
    private String content;
//    private BoardType boardType;
    private LocalDateTime incidentDateTime;
    private String location;
    private List<Long> attachmentIds = new ArrayList<>();

    @lombok.Builder
    private BoardCreateServiceRequest(String title, String content, LocalDateTime incidentDateTime, String location, List<Long> attachmentIds) {
        this.title = title;
        this.content = content;
        this.incidentDateTime = incidentDateTime;
        this.location = location;
        this.attachmentIds = attachmentIds;
    }


    public Board toEntity() {
        return Board.createNewBoardNoMember(title, content, incidentDateTime, location);
    }
}
