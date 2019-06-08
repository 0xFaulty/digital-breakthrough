package cloud.socify.repository;

import cloud.socify.entity.UserInfoEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfoEntity, Long> {

}

