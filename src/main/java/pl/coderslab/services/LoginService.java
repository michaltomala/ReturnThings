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
public class LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void startLogin(Model model, HttpServletRequest request){
        model.addAttribute("user", new User());
        model.addAttribute("formAction", request.getContextPath() + "/login");
    }


    public boolean loginUser(User user,Model model,HttpSession session){
        User userToCheck = userRepository.findFirstByEmail(user.getEmail());

        if(userToCheck== null){
            model.addAttribute("emailErr", "Email jest nieprawidłowy!");
            return true;
        }

        if (!(BCrypt.checkpw(user.getPassword(), userToCheck.getPassword()) )) {
            model.addAttribute("pwdErr", "Hasło się nie zgadza,spróbuj jeszcze raz");
            return true;
        }

        session.setAttribute("user", userToCheck);
        return false;
    }

    public boolean isLogged(HttpSession session){
        User user = (User)session.getAttribute("user");
        return user != null;
    }

    public boolean isAdmin(HttpSession session){
        User user = (User)session.getAttribute("user");
        return user.getIsAdmin();
    }

    public boolean isBlocked(User user){
        User userToCheck = userRepository.findFirstByEmail(user.getEmail());
        return userToCheck.getIsBlocked();
    }

    public void logout(HttpSession session){
        session.setAttribute("user", null);
    }

}
