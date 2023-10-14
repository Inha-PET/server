package ipet.demo.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardType {

    FREE("자유"),
    MISSING("신고"),
    SIGHTING("목격");

    private final String text;
}
