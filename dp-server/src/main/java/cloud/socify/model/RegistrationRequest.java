package cloud.socify.model;

import cloud.socify.entity.UserType;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class RegistrationRequest {
    @Email
    @NotNull
    private String email;
    @Size(min = 8, max = 20)
    @NotNull
    private String password;
    @NotNull
    private UserType userType;
}
