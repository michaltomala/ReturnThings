package pl.coderslab.entity;


import javax.persistence.*;
import java.util.List;


@Entity
public class BountyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @ManyToMany(mappedBy = "bountyType",fetch = FetchType.EAGER)
    private List<Bounty> bounty;

    public BountyType() {
    }

    @Override
    public String toString() {
        return this.type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Bounty> getBounty() {
        return bounty;
    }

    public void setBounty(List<Bounty> bounty) {
        this.bounty = bounty;
    }
}
