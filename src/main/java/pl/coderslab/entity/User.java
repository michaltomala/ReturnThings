package pl.coderslab.entity;



import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.validator.ValidationEditUserGroup;
import pl.coderslab.validator.ValidationLoginUserGroup;
import pl.coderslab.validator.ValidationRegisterUserGroup;

import javax.persistence.*;
import javax.swing.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = { ValidationLoginUserGroup.class,
                        ValidationRegisterUserGroup.class,
                        ValidationEditUserGroup.class} , message = "Email nie może być pusty!")
    @Email(groups = {ValidationRegisterUserGroup.class, ValidationEditUserGroup.class}, message = "Niepoprawny Email!")
    @Column(unique = true)
    private String email;

    @NotBlank(groups = {ValidationLoginUserGroup.class} , message = "Hasło nie może być puste!")
    @Size(min = 8, groups = ValidationRegisterUserGroup.class, message = "Hasło musi mieć minimum 8 znaków!")

//   todo regex nie przepuszcza żadnego hasła

//    @Pattern(regexp = "(?=.*[a-z])",
//            groups = ValidationRegisterUserGroup.class,
//            message="Hasło musi zawierać minimum jedną duzą i małą literę, cyfrę oraz znak specjalny")

//      (?=.*\d) - to też do minimum jednej cyfry
//    @Pattern.List({
//            @Pattern(regexp = "(?=.*[0-9])",groups = ValidationRegisterUserGroup.class, message = "Hasło musi zawierać minimum jedną cyfrę!"),
//            @Pattern(regexp = "(?=.*[a-z])",groups = ValidationRegisterUserGroup.class, message = "Hasło musi zawierać co najmniej jedną małą literę!"),
//            @Pattern(regexp = "(?=.*[A-Z])",groups = ValidationRegisterUserGroup.class, message = "Hasło musi zawierać co najmniej jendną dużą literę!"),
//            @Pattern(regexp = "(?=.*[!@#$%^&*+=?-_()/\"\\.,<>~`;:]).+",groups = ValidationRegisterUserGroup.class, message ="Hasło musi mieć minimum jeden znak specjalny!"),
//            @Pattern(regexp = "(?=\\S+$)",groups = ValidationRegisterUserGroup.class, message = "Hasło nie może zawierać znaków białych (spacji,znaków tabulacji) !")
//    })
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
    public void setIsAdmin(boolean admin) { isAdmin = admin; }
}
