package pl.coderslab.entity;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;


@Entity
public class InstitutionListOfWhomHelp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String whomHelp;

    @ManyToMany(mappedBy = "whomHelp",fetch = FetchType.EAGER)
    private List<Institution> intitution;

    public InstitutionListOfWhomHelp() {
    }


    @Override
    public String toString() {
        return this.whomHelp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWhomHelp() {
        return whomHelp;
    }

    public void setWhomHelp(String whomHelp) {
        this.whomHelp = whomHelp;
    }

    public List<Institution> getIntitution() {
        return intitution;
    }

    public void setIntitution(List<Institution> intitution) {
        this.intitution = intitution;
    }
}
