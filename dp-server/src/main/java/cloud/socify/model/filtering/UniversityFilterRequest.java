package cloud.socify.model.filtering;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UniversityFilterRequest {

    private Double rating;
}
