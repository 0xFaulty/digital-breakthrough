package cloud.socify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudentInfo {
    @Id
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;
    private String firstName;
    private String secondName;
    private String middleName;
    private Long universityId;
    private String photo;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birthDate;
    private Double rating;
}
