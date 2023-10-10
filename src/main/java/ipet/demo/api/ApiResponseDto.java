package ipet.demo.api;

import io.swagger.v3.oas.annotations.media.Schema;
import ipet.demo.exception.ExceptionCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Schema(description = "API 응답 DTO")
@Getter
public class ApiResponseDto<T> {

    private final int code;
    private final HttpStatus status;
    private final String message;
    private final T data;

    @Builder
    private ApiResponseDto(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponseDto<T> of(HttpStatus status, String message, T data) {
        return ApiResponseDto.<T>builder()
                .status(status)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponseDto<T> of(HttpStatus status, T data) {
        return of(status, status.name(), data);
    }

    public static <T> ApiResponseDto<T> ok(T data) {
        return of(HttpStatus.OK, HttpStatus.OK.name(), data);
    }

    //NOT_FOUND, BAD_REQUEST, CONFLICT, UNAUTHORIZED, FORBIDDEN, INTERNAL_SERVER_ERROR
    public static <T> ApiResponseDto<T> error(ExceptionCode code) {
        return of(code.getStatus(), code.getMessage(), null);
    }

}
