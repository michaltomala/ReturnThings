package pl.coderslab.entity;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

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

// todo - walidate date and time with message and good position in step5.jsp

//    @Future(message = "Data musi być aktualna" )
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

//    @Future(message = "Data musi być aktualna" )
//    @DateTimeFormat(pattern = "HH:mm" )
    private LocalTime time;

    private String comments;

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
}
