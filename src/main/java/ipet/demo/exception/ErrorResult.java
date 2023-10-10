package ipet.demo.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorResult {

    private Map<String, String> details;
}
