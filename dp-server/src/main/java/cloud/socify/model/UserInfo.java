package cloud.socify.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String email;
    private String userType;
    private String firstName;
    private String secondName;
    private String middleName;
    private String photo;
    private String gender;
    private Date birthday;
}
