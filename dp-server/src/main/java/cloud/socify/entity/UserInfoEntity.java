package cloud.socify.entity;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoEntity {

    private long userId;
    private String firstName;
    private String secondName;
    private String middleName;
    private String idNumber;
}
