package pl.coderslab.entity;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Bounty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Musisz zaznaczyć co chcesz oddać!")
    private String name;

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
        return this.name;
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

    public Long getQuantityOfBags() {
        return quantityOfBags;
    }

    public void setQuantityOfBags(Long quantityOfBags) {
        this.quantityOfBags = quantityOfBags;
    }
}
