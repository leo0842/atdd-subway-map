package wooteco.subway.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SubwayExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleUncheckedException(RuntimeException e) {
        return ResponseEntity.internalServerError().body("서버에 오류가 있으니 잠시 후 다시 시도해주세요.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleCheckedException(Exception e) {
        return ResponseEntity.internalServerError().body("확인되지 않은 오류가 있습니다. 잠시 후 다시 시도해주세요.");
    }
}
