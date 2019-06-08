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
public class UserIdentityEntity {

    private long userId;
    private String idNumber;
    private String idAddress;
    private String registrationPlace;
}
