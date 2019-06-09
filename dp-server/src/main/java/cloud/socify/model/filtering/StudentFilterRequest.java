package cloud.socify.model.filtering;

import cloud.socify.model.Gender;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class StudentFilterRequest {

    private Integer minAge;

    private Integer maxAge;

    private Double minRating;

    private Double maxRating;

    private List<String> skills;

    private Long universityId;

    private Gender gender;
}
