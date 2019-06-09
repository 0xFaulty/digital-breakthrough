package cloud.socify.controller;

import cloud.socify.model.RegistrationRequest;
import cloud.socify.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final IUserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        userService.registerUser(registrationRequest);
    }
}
