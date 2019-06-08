package cloud.socify.service;

import cloud.socify.model.User;

public interface IUserService {

    User getUser(String id);

    void saveUser(User user);
}
