package ipet.demo.exception;

import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException {

    private final ExceptionCode code;

    public BusinessLogicException(ExceptionCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public BusinessLogicException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessLogicException(ExceptionCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessLogicException(ExceptionCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }
}
