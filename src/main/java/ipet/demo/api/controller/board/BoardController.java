package ipet.demo.api.controller.board;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import ipet.demo.api.ApiResponseDto;
import ipet.demo.api.controller.board.request.BoardCreateRequest;
import ipet.demo.api.controller.board.request.BoardUpdateRequest;
import ipet.demo.api.service.board.BoardService;
import ipet.demo.api.service.board.response.BoardResponse;
import ipet.demo.auth.jwt.custom.JwtAuth;
import ipet.demo.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "Board", description = "게시판 관련 API")
@RequestMapping("/api/v1/boards")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;


    @Operation(summary = "게시글 생성", description = "게시글을 생성합니다.")
    @PostMapping()
    public ApiResponseDto<BoardResponse> createBoard(
                                                     @Parameter(name = "Authorization", description = "회원가입/로그인시 응답 헤더의 Authorization값을 넣어놓으시면 됩니다.\n" +
                                                             "제가 응답 헤더에도 값을 주고 응답에도 토큰 내용을 넣어뒀습니다.", in = ParameterIn.HEADER, example = "Authorization: Bearer abc...")
                                                     @JwtAuth Member member,
                                                     @RequestBody(description = "게시글 요청 형식입니다", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BoardCreateRequest.class)))
                                                     @org.springframework.web.bind.annotation.RequestBody BoardCreateRequest boardCreateRequest) {

        log.info("member: {}", member);
        BoardResponse newBoard = boardService.createBoard(boardCreateRequest.toServiceRequest(), member);
        return ApiResponseDto.ok(newBoard);
    }

    @Operation(summary = "게시글 목록 조회", description = "게시글 목록을 조회합니다.",
            parameters = {
                    @Parameter(name = "page", description = "페이지 번호. 0부터 시작해요.", in = ParameterIn.QUERY, example = "0"),
                    @Parameter(name = "size", description = "페이지 크기", in = ParameterIn.QUERY, example = "10"),
                    @Parameter(name = "sort", description = "정렬 방식", in = ParameterIn.QUERY, example = "createdDateTime,desc")
            })
    @GetMapping()
    public ApiResponseDto<Slice<BoardResponse>> getBoards(@PageableDefault(size = 10, sort = "createdDateTime", direction = Sort.Direction.DESC)Pageable pageable) {
        return ApiResponseDto.ok(boardService.getBoards(pageable));
    }

    @Operation(summary = "게시글 상세 조회", description = "게시글 상세를 조회합니다.",
            parameters = {
                    @Parameter(name = "id", description = "게시글 id", in = ParameterIn.PATH, example = "1")
            })
    @GetMapping("/{id}")
    public ApiResponseDto<BoardResponse> getBoard(@PathVariable Long id) {
        return ApiResponseDto.ok(boardService.getBoard(id));
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.",
            parameters = {
                    @Parameter(name = "id", description = "게시글 id", in = ParameterIn.PATH, example = "1")
            })
    @PostMapping("/{id}")
    public ApiResponseDto<BoardResponse> updateBoard(@JwtAuth Member member, @PathVariable Long id, @RequestBody BoardUpdateRequest boardUpdateRequest) {
        return ApiResponseDto.ok(boardService.updateBoard(member, id, boardUpdateRequest.toServiceRequest()));
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.",
            parameters = {
                    @Parameter(name = "id", description = "게시글 id", in = ParameterIn.PATH, example = "1")
            })
    @GetMapping("/{id}/delete")
    public ApiResponseDto<String> deleteBoard(@JwtAuth Member member, @PathVariable Long id) {
        LocalDate deletedAt = LocalDate.now();
        boardService.deleteBoard(member, id, deletedAt);
        return ApiResponseDto.ok("ok");
    }

    @Operation(summary = "게시글 복구", description = "게시글을 복구합니다.",
            parameters = {
                    @Parameter(name = "id", description = "게시글 id", in = ParameterIn.PATH, example = "1")
            })
    @GetMapping("/{id}/restore")
    public ApiResponseDto<String> restoreBoard(@JwtAuth Member member, @PathVariable Long id) {
        boardService.restoreBoard(member, id);
        return ApiResponseDto.ok("ok");
    }

}
