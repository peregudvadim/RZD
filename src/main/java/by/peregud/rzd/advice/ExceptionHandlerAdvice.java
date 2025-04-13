package by.peregud.rzd.advice;

import by.peregud.rzd.exception.InvalidRzdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(InvalidRzdException.class)
    public ResponseStatusException invalidTaskExceptionHandler(InvalidRzdException e) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
