package pl.coderslab.entity;


import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    public InstitutionWhomHelpEnum getInstitutionWhomHelpEnum() {
        return institutionWhomHelpEnum;
    }

    public void setInstitutionWhomHelpEnum(InstitutionWhomHelpEnum institutionWhomHelpEnum) {
        this.institutionWhomHelpEnum = institutionWhomHelpEnum;
    }
}
