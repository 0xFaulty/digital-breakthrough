package cloud.socify.service;

import cloud.socify.entity.UserEntity;
import cloud.socify.model.User;
import cloud.socify.repository.UserIdentityRepository;
import cloud.socify.repository.UserInfoRepository;
import cloud.socify.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserIdentityRepository userIdentityRepository;

    @Override
    public User getUser(String id) {
        Optional<UserEntity> userOpt = userRepository.findById(Long.valueOf(id));
        if (userOpt.isPresent()) {
            UserEntity userEntity = userOpt.get();
            User user = new User(userEntity.getLogin(), userEntity.getPassword());
            return user;
        } else
            return null;
    }

    @Override
    public void saveUser(User user) {

    }
}
