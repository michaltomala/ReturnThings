package pl.coderslab.services;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.User;
import pl.coderslab.model.Err;
import pl.coderslab.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user){ userRepository.save(user); }



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
