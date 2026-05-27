package org.example.taskmanagerbackend2.exception;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({InvalidLoginException.class})
    public ResponseEntity<Map<String, String>> handleInvalidLogin(InvalidLoginException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(this.error(ex.getMessage()));
    }

    @ExceptionHandler({TaskNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<Map<String, String>> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.error(ex.getMessage()));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(this.error(ex.getMessage()));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Map<String, String>> handleRuntime(RuntimeException ex) {
        return ResponseEntity.badRequest().body(this.error(ex.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
        log.error("Unhandled exception", ex);
        ResponseEntity.BodyBuilder var10000 = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        String var10002 = ex.getClass().getSimpleName();
        return var10000.body(this.error(var10002 + ": " + ex.getMessage()));
    }

    private Map<String, String> error(String message) {
        return Map.of("message", message);
    }
}

