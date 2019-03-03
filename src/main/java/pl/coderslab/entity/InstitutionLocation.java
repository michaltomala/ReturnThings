package pl.coderslab.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Entity
public class InstitutionLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Lokalizacja nie może być pusta!")
    private String location;

    @ManyToMany(mappedBy = "institutionLocations",fetch = FetchType.EAGER)
    private List<Institution> institution;

    public InstitutionLocation() {
    }

    @Override
    public String toString() {
        return this.location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Institution> getInstitution() {
        return institution;
    }

    public void setInstitution(List<Institution> institution) {
        this.institution = institution;
    }

}





