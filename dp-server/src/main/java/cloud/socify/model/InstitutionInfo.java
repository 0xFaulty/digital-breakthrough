package cloud.socify.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InstitutionInfo {
    private String name;
    private String email;
    private Long userId;
    private String description;
    private double rating;
}
