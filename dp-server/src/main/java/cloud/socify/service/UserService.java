package cloud.socify.service;

import cloud.socify.entity.User;
import cloud.socify.exception.UserAlreadyExistException;
import cloud.socify.exception.UserNotFoundException;
import cloud.socify.model.RegistrationRequest;
import cloud.socify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public void registerUser(@Valid RegistrationRequest registrationRequest) {
        String email = registrationRequest.getEmail();
        boolean userAlreadyExist = userRepository.existsByEmail(email);
        if (userAlreadyExist) {
            throw new UserAlreadyExistException(email);
        }

        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());

        User user = new User()
                .setEmail(email)
                .setPassword(encodedPassword)
                .setUserType(registrationRequest.getUserType());

        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("isAuthenticated()")
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
