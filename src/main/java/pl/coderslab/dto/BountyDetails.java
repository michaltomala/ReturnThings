package pl.coderslab.dto;

import pl.coderslab.entity.Bounty;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.Reception;

import javax.persistence.*;
import java.util.Date;


@Entity
public class BountyDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean received=false;
    private Date dateReceived;

    @OneToOne
    private Bounty bounty;

    @OneToOne
    private Institution institution;

    @OneToOne
    private Reception reception;

    public BountyDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Bounty getBounty() {
        return bounty;
    }

    public void setBounty(Bounty bounty) {
        this.bounty = bounty;
    }

    public Institution getIntitution() {
        return institution;
    }

    public void setIntitution(Institution institution) {
        this.institution = institution;
    }

    public Reception getReception() {
        return reception;
    }

    public void setReception(Reception reception) {
        this.reception = reception;
    }
}
