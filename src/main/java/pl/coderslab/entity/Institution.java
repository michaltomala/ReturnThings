package pl.coderslab.entity;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import pl.coderslab.dto.BountyDetails;

import javax.persistence.*;
import java.util.List;

@Entity
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nazwa organizacji nie może być pusta!")
    @Column(unique=true)
    private String name;

    @NotBlank(message = "Opis celu i misji organizacji nie może być pusty !")
    private String goal;

    @NotEmpty(message = "Lokalizacja musi zostać zaznaczona!")
    @ManyToMany(fetch = FetchType.EAGER)
    private List<InstitutionLocation> institutionLocations;

    @NotEmpty(message = "Musisz zaznaczyć komu organizacja ma pomagać!!")
    @ManyToMany
    private List<InstitutionListOfWhomHelp> whomHelp;

    @OneToMany
    private List<BountyDetails> bountyDetails;

    public Institution() {
    }

    public void addToListBountyDetails(BountyDetails bountyDetails){ this.bountyDetails.add(bountyDetails); }



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

    public List<InstitutionListOfWhomHelp> getWhomHelp() {
        return whomHelp;
    }

    public void setWhomHelp(List<InstitutionListOfWhomHelp> whomHelp) {
        this.whomHelp = whomHelp;
    }

    public List<BountyDetails> getBountyDetails() {
        return bountyDetails;
    }

    public void setBountyDetails(List<BountyDetails> bountyDetails) {
        this.bountyDetails = bountyDetails;
    }
}
