package cloud.socify.controller;

import cloud.socify.exception.UserAlreadyExistException;
import cloud.socify.exception.UserNotFoundException;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler({Exception.class})
//    public ResponseEntity<Object> handleUnexpectedError(Exception ex) {
//        logger.error(ex);
//        ErrorDto internalServerError = new ErrorDto().setMessage("InternalServerError");
//        return new ResponseEntity<>(internalServerError, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        ErrorDto internalServerError = new ErrorDto().setMessage("User with email " + ex.getEmail() + " already exist");
        return new ResponseEntity<>(internalServerError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorDto internalServerError = new ErrorDto().setMessage("Пользователь с id" + ex.getId() + " не найден");
        return new ResponseEntity<>(internalServerError, HttpStatus.NOT_FOUND);
    }

    @Data
    @Accessors(chain = true)
    static class ErrorDto {
        private String message;
    }
}
