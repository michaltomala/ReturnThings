package pl.coderslab.dto;

import pl.coderslab.entity.Bounty;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.Reception;
import pl.coderslab.entity.User;

import javax.persistence.*;
import java.util.Date;


@Entity
public class BountyDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean received=false;
    private boolean archived=false;
    private Date dateReceived;

    @OneToOne
    private Bounty bounty;

    @ManyToOne
    private Institution institution;

    @OneToOne
    private Reception reception;

    @ManyToOne
    private User user;

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

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Reception getReception() {
        return reception;
    }

    public void setReception(Reception reception) {
        this.reception = reception;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
