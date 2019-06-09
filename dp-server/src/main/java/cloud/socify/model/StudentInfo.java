package cloud.socify.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfo {
    private String firstName;
    private String secondName;
    private String middleName;
    private Long userId;
    private String photo;
    private Gender gender;
    private LocalDate birthDate;
}
