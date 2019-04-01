package pl.coderslab.entity;



import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.dto.BountyDetails;
import pl.coderslab.validator.ValidationEditUserGroup;
import pl.coderslab.validator.ValidationLoginUserGroup;
import pl.coderslab.validator.ValidationRegisterUserGroup;

import javax.persistence.*;
import javax.swing.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;


    @NotBlank(groups = { ValidationLoginUserGroup.class,
                        ValidationRegisterUserGroup.class,
                        ValidationEditUserGroup.class} , message = "Email nie może być pusty!")
    @Email(groups = {ValidationRegisterUserGroup.class, ValidationEditUserGroup.class}, message = "Niepoprawny Email!")
    @Column(unique = true)
    private String email;

    @NotBlank(groups = {ValidationLoginUserGroup.class} , message = "Hasło nie może być puste!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])" +
           "(?=.*[\\!@\\]#\\[$%^&*()_+={}\\\\|;:'\",<.>/?`~-])(?=\\S+$).{8,}$" ,
           groups = ValidationRegisterUserGroup.class,
           message = "Hasło musi zawierać minimum 8znaków, mieć przynajmniej " +
                   "jedną cyfrę,małą i dużą literę oraz znak specjalny! \n"+
                   " Dodatkowo nie może zawierać znaków białych(spacji,tabulacji etc.)")
    private String password;

    @Transient
    private String repeatedPassword;

    private boolean isBlocked = false;

    private boolean isAdmin = false;


    private String city;

    private String street;

    @Pattern(regexp="(^$|[0-9][0-9]-[0-9][0-9][0-9])" , message = "Nieprawidłowy format !")
    private String postCode;

    @Pattern(regexp="(^$|[0-9]{9})" , message = "Nieprawidłowy format !")
    private String phone;


    @OneToMany
    private List<BountyDetails> bountyDetails;

    public User() {
    }

    @Override
    public String toString() {
        return this.email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public void setIsAdmin(boolean admin) { isAdmin = admin; }

    public boolean isBlocked() { return isBlocked; }
    public boolean getIsBlocked() { return isBlocked; }

    public void setBlocked(boolean blocked) { isBlocked = blocked; }
    public void setIsBlocked(boolean blocked) { isBlocked = blocked; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<BountyDetails> getBountyDetails() {
        return bountyDetails;
    }

    public void setBountyDetails(List<BountyDetails> bountyDetails) {
        this.bountyDetails = bountyDetails;
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
}
