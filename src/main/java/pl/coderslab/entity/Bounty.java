package pl.coderslab.entity;


import org.hibernate.validator.constraints.NotEmpty;
import pl.coderslab.validator.form.ValidationFormStep1;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
public class Bounty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(groups = ValidationFormStep1.class ,message = "Musisz zaznaczyć co chcesz oddać!")
    @ManyToMany
    private List<BountyType> bountyType;

    @NotNull
    private Long quantityOfBags;

    public Bounty() {
    }


    @Override
    public String toString() {
        return "Bounty{" +
                "id=" + id +
                ", bountyType=" + bountyType +
                ", quantityOfBags=" + quantityOfBags +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BountyType> getBountyType() {
        return bountyType;
    }

    public void setBountyType(List<BountyType> bountyType) {
        this.bountyType = bountyType;
    }

    public Long getQuantityOfBags() {
        return quantityOfBags;
    }

    public void setQuantityOfBags(Long quantityOfBags) {
        this.quantityOfBags = quantityOfBags;
    }
}
