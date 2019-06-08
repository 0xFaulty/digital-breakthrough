package cloud.socify.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAlreadyExistException extends RuntimeException {
    @Getter
    private final String email;
}
