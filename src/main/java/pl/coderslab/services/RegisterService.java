package pl.coderslab.services;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.User;
import pl.coderslab.model.Err;
import pl.coderslab.repository.UserRepository;


@Service
public class RegisterService {

    private final UserRepository userRepository;

    @Autowired
    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
    }

    public void checkPwd (User user, Err modelErr) {

        if (!user.getPassword().equals(user.getRepeatedPassword())) {
            String str = "Hasła muszą być takie same!";
            modelErr.addErr(str);
        }
    }

    public void checkIfEmailIsUnique(User user, Err modelErr){

        User checkUser = userRepository.findFirstByEmail(user.getEmail());
        if (checkUser != null) {
            modelErr.addErr("Taki użytkownik już istnieje !");
        }
    }


}
