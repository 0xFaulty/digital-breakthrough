package cloud.socify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String email;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastVisitAt;
}
