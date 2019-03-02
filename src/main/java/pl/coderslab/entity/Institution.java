package pl.coderslab.entity;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique=true)
    private String name;

    @NotBlank
    private String goal;

    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER)
    private List<InstitutionLocation> institutionLocations;

    @NotNull
    private InstitutionWhomHelpEnum institutionWhomHelpEnum;


    public Institution() {
    }

    @Override
    public String toString() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public List<InstitutionLocation> getInstitutionLocations() {
        return institutionLocations;
    }

    public void setInstitutionLocations(List<InstitutionLocation> institutionLocations) {
        this.institutionLocations = institutionLocations;
    }

    public InstitutionWhomHelpEnum getInstitutionWhomHelpEnum() {
        return institutionWhomHelpEnum;
    }

    public void setInstitutionWhomHelpEnum(InstitutionWhomHelpEnum institutionWhomHelpEnum) {
        this.institutionWhomHelpEnum = institutionWhomHelpEnum;
    }
}
