package pl.coderslab.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.entity.InstitutionLocation;
import pl.coderslab.entity.User;
import pl.coderslab.model.Err;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminUserService {

    private final UserRepository userRepository;

    @Autowired
    public AdminUserService(UserRepository userRepository) { this.userRepository = userRepository; }


    public void saveUser(User user){ userRepository.save(user); }

    public void deleteUser(User user){ userRepository.delete(user); }


    public User findUser(Long id){ return userRepository.findOne(id); }

    public List<User> returnListOfUsers(){ return userRepository.findAllByIsAdmin(false); }

    public List<User> returnListOfAdmins(){ return userRepository.findAllByIsAdmin(true); }



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


}
