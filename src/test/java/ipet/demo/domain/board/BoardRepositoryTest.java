package ipet.demo.domain.board;

import ipet.demo.domain.member.Member;
import ipet.demo.domain.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void findAllByBoardType() {
        //given
        Member member = Member.createMember("name", "email", "password");
        memberRepository.save(member);

        LocalDateTime now = LocalDateTime.now();
        Board board1 = Board.createNewBoardNoMember("title1", "content1", BoardType.FREE, now, "location");
        Board board2 = Board.createNewBoardNoMember("title2", "content2", BoardType.FREE, now, "location");
        Board board3 = Board.createNewBoardNoMember("title3", "content3", BoardType.FREE, now, "location");
        Board board4 = Board.createNewBoardNoMember("title4", "content4", BoardType.MISSING, now, "location");

        board1.attachMember(member);
        board2.attachMember(member);
        board3.attachMember(member);
        board4.attachMember(member);

        boardRepository.saveAll(List.of(board1, board2, board3, board4));

        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.DESC, "id");

        //when
        Slice<Board> boards = boardRepository.findAllByBoardType(BoardType.FREE, pageable);

        //then
        boards.forEach(board -> log.info("board: {}", board.getTitle()));
        assertEquals(2, boards.getContent().size());

    }

    @Test
    void findAllByBoardTypeAndIdLessThan() {
    }

    @Test
    void findAllByBoardTypeAndIdGreaterThan() {
    }

    @Test
    void findAllByBoardTypeAndIdLessThanEqual() {
    }

    @Test
    void findAllByBoardTypeAndIdGreaterThanEqual() {
    }

    @Test
    void findAllByBoardTypeAndIdBetween() {
    }

    @Test
    void findAllByBoardTypeAndIdIn() {
    }

    @Test
    void findAllByBoardTypeAndIdNotIn() {
    }

    @Test
    void findAllByBoardTypeAndIdIsNotNull() {
    }
}