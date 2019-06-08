package cloud.socify.service;

import cloud.socify.entity.User;
import cloud.socify.model.RegistrationRequest;

public interface IUserService {

    void registerUser(RegistrationRequest registrationRequest);

    User getUserById(Long id);
}
