package cloud.socify.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudentInfo {
    @Id
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
