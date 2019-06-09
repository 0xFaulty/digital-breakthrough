package cloud.socify.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CompanyInfo {
    @Id
    private Long userId;
    private String name;
    private String email;
    private Long inn;
    private String description;
    private double rating;
}
