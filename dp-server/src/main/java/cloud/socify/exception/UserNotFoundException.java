package cloud.socify.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserNotFoundException extends RuntimeException {
    @Getter
    private final Long id;
}
