package ipet.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    AUTH_NOT_FOUND(UNAUTHORIZED, "인가 정보를 찾을 수 없습니다."),

    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
