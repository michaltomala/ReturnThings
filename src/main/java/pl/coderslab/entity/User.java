package pl.coderslab.entity;



import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.validator.ValidationUserGroup;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = ValidationUserGroup.class , message = "Email nie może być pusty!")
    @Email(groups = ValidationUserGroup.class, message = "Niepoprawny Email!")
    @Column(unique = true)
    private String email;

    @Size(min = 6, groups = ValidationUserGroup.class, message = "Hasło musi mieć minimum 6 znaków")
    private String password;

    @Transient
    private String repeatedPassword;


    private boolean isAdmin = false;


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

    public boolean getisAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
