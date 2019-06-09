package cloud.socify.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Accessors(chain = true)
@Entity
public class InstitutionInfo {
    @Id
    private Long userId;
    private String name;
    private String email;
    private String description;
    private double rating;
}
