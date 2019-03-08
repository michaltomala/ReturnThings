package pl.coderslab.entity;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Bounty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = "Musisz zaznaczyć co chcesz oddać!")
    @ManyToMany(fetch = FetchType.EAGER)
    private List<BountyType> bountyType;

    @NotNull
    private Long quantityOfBags;

    public Bounty() {
    }


    public static List<String> listOfBountyNames (){
        return new ArrayList<String>() {
            {
                add("ubrania, które nadają się do ponownego użycia");
                add("ubrania do wyrzucenia");
                add("zabawki");
                add("książki");
                add("inne");
            }
        };
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
