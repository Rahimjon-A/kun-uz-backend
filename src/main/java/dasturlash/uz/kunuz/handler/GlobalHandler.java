package dasturlash.uz.kunuz.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.ExecutionException;

@ControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ExecutionException.class})
    public ResponseEntity<?> handleEntityNotFound(ExecutionException e) {
        return ResponseEntity.notFound().build();
    }
}
