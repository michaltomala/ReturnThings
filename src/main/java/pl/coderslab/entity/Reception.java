package pl.coderslab.entity;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.dto.BountyDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Reception {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "To pole nie może być puste!")
    private String city;

    @NotBlank(message = "To pole nie może być puste!")
    private String street;

    @NotBlank(message = "To pole nie może być puste!")
    @Pattern(regexp="(^$|[0-9][0-9]-[0-9][0-9][0-9])" , message = "Nieprawidłowy format !")
    private String postCode;

    @NotBlank(message = "To pole nie może być puste!")
    @Pattern(regexp="(^$|[0-9]{9})" , message = "Nieprawidłowy format !")
    private String phone;

    @NotNull(message = "To pole nie może być puste!")
    private LocalDate date;

    @NotNull(message = "To pole nie może być puste!")
    private LocalTime time;

    private String comments;

    @OneToOne(mappedBy = "reception")
    private BountyDetails bountyDetails;

    public Reception() {
    }


    @Override
    public String toString() {
        return "Reception{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BountyDetails getBountyDetails() {
        return bountyDetails;
    }

    public void setBountyDetails(BountyDetails bountyDetails) {
        this.bountyDetails = bountyDetails;
    }
}
