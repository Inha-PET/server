package ipet.demo.domain.board;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Slice<Board> findAllByBoardType(BoardType boardType, Pageable pageable);

    Slice<Board> findAllByBoardTypeAndIdLessThan(BoardType boardType, Long id);

    Slice<Board> findAllByBoardTypeAndIdGreaterThan(BoardType boardType, Long id);

    Slice<Board> findAllByBoardTypeAndIdLessThanEqual(BoardType boardType, Long id);

    Slice<Board> findAllByBoardTypeAndIdGreaterThanEqual(BoardType boardType, Long id);

    Slice<Board> findAllByBoardTypeAndIdBetween(BoardType boardType, Long startId, Long endId);

    Slice<Board> findAllByBoardTypeAndIdIn(BoardType boardType, Iterable<Long> ids);

    Slice<Board> findAllByBoardTypeAndIdNotIn(BoardType boardType, Iterable<Long> ids);

    Slice<Board> findAllByBoardTypeAndIdIsNotNull(BoardType boardType);

    Slice<Board> findAllByBoardStatus(BoardStatus boardStatus, Pageable pageable);
}
