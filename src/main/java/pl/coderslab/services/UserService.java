package pl.coderslab.services;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.User;
import pl.coderslab.model.Err;
import pl.coderslab.repository.UserRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user){ userRepository.save(user); }

    public void deleteUser(User user){ userRepository.delete(user); }

    public User findUser(Long id){ return userRepository.findOne(id); }

    public List<User> returnListOfUsers(){ return userRepository.findAllByIsAdmin(false); }

    public List<User> returnListOfAdmins(){ return userRepository.findAllByIsAdmin(true); }


    public void checkIfEmailIsUnique(User user,Err modelErr){

        User checkUser = userRepository.findFirstByEmail(user.getEmail());
        if (checkUser != null && !checkUser.getId().equals(user.getId())) {
            modelErr.addErr("Wrong email!");
        }
    }

    public void checkIfRepeatedPasswordIsTheSameAsPassword(User user, Err modelErr){

        if (!user.getPassword().equals(user.getRepeatedPassword())) {
            modelErr.addErr("Wrong password!");
        }
    }

    public void checkEmail(User user, Err modelErr) {
//      Checking if email is empty
        if(user.getEmail().equals("")){
            modelErr.addErr("Email nie może być pusty !");
            return;
        }

//      Checking if email is email
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(user.getEmail());

        if(!matcher.matches()){
            modelErr.addErr("Niepoprawny format!");
            return;
        }

//      Checking if email is unique
        User checkUser = userRepository.findFirstByEmail(user.getEmail());
        if (checkUser != null && !checkUser.getId().equals(user.getId())) {
            modelErr.addErr("Email musi być unikalny !");
        }
    }


    public void saveChangedSettings(User user,User userFromSession){

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setAdmin(userFromSession.getIsAdmin());
        userRepository.save(user);
    }

    public void saveUserDetails(User user,User userFromSession){

        userFromSession.setName(user.getName());
        userFromSession.setSurname(user.getSurname());
        userRepository.save(userFromSession);
    }


}
