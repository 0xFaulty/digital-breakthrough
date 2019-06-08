package cloud.socify.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserIdentity {

    private String idNumber;
    private String idAddress;
    private String registrationPlace;
}
