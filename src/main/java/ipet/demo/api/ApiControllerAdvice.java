package ipet.demo.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ApiResponseDto<?> bindException(BindException e) {
        return ApiResponseDto.of(
                HttpStatus.BAD_REQUEST,
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                null
        );
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ApiResponseDto<?> usernameNotFoundException(UsernameNotFoundException e) {
        return ApiResponseDto.of(
                HttpStatus.UNAUTHORIZED,
                e.getMessage(),
                null
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponseDto<?> exception(Exception e) {
        return ApiResponseDto.of(
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage(),
                null
        );
    }
}
