package ipet.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    AUTH_NOT_FOUND(UNAUTHORIZED, "인가 정보를 찾을 수 없습니다."),

    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저를 찾을 수 없습니다."),

    BOARD_NOT_AUTHORIZED(FORBIDDEN, "해당 게시글에 대한 권한이 없습니다."),

    BOARD_NOT_FOUND(NOT_FOUND, "해당 게시글을 찾을 수 없습니다."),

    IMAGE_NOT_FOUND(NOT_FOUND, "해당 이미지를 찾을 수 없습니다."),

    FILE_IO_EXCEPTION(INTERNAL_SERVER_ERROR, "파일 입출력 에러가 발생했습니다."),

    EMAIL_DUPLICATION(CONFLICT, "이미 가입된 이메일입니다.");

    private final HttpStatus status;
    private final String message;
}
