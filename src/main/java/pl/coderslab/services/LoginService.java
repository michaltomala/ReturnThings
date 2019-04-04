package pl.coderslab.services;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.User;
import pl.coderslab.model.Err;
import pl.coderslab.repository.UserRepository;


@Service
public class LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User returnUserFromRepository(User user){
        return userRepository.findFirstByEmail(user.getEmail());
    }

    /**
     * This method check if email exist and if exist - check is password correct
     * @param user
     * @param modelErr
     */

    public void checkEmailAndPassword(User user, Err modelErr){
        User userToCheck = userRepository.findFirstByEmail(user.getEmail());

        if(userToCheck== null){
            modelErr.addErr("Wrong email!");
        }else{
            if (!(BCrypt.checkpw(user.getPassword(), userToCheck.getPassword()) )) {
                modelErr.addErr("Wrong password!");
            }
        }
    }

    public boolean isAdmin(User user){
        return user.getIsAdmin();
    }

    public boolean isBlocked(User user){
        User userToCheck = userRepository.findFirstByEmail(user.getEmail());
        return userToCheck.getIsBlocked();
    }


}
