package ipet.demo.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardStatus {

    NORMAL("일반"),
    DELETED("삭제");

    private final String description;
}
