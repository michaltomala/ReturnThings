package pl.coderslab.services;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;


    public void startRegister(Model model, HttpServletRequest request){
        model.addAttribute("user", new User());
        model.addAttribute("formAction", request.getContextPath() + "/register");
    }

    public boolean saveUser(User user, Model model, HttpSession session) {

        if (!user.getPassword().equals(user.getRepeatedPassword())) {
            model.addAttribute("pwdErr", "Hasła muszą być takie same!");
            return true;
        }

        User checkUser = userRepository.findFirstByEmail(user.getEmail());
        if (checkUser != null) {
            model.addAttribute("emailErr", "Taki użytkownik już istnieje !");
            return true;
        }

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        session.setAttribute("user",user);
        return false;
    }

}
