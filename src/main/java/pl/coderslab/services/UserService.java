package pl.coderslab.services;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDetails;
import pl.coderslab.repository.UserDetailsRepository;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserService(UserRepository userRepository , UserDetailsRepository userDetailsRepository) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    public void edit(Model model, HttpServletRequest request, HttpSession session){
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("formAction", request.getContextPath() + "/user/settings");
    }


    public boolean update(User user, Model model, HttpSession session){

        User checkUser = userRepository.findFirstByEmail(user.getEmail());
        if (checkUser != null && !(checkUser.getEmail().equals(user.getEmail()))) {
            model.addAttribute("emailErr", "Taki użytkownik już istnieje !");
            return true;
        }
        if (!user.getPassword().equals(user.getRepeatedPassword())) {
            model.addAttribute("pwdErr", "Hasła muszą być takie same!");
            return true;
        }

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        User userFromSession = (User) session.getAttribute("user");
        user.setAdmin(userFromSession.getIsAdmin());
        userRepository.save(user);
        session.setAttribute("user",user);
        return false;

    }

    public void onlySaveUser(User user,HttpSession session){
        User userFromSession = (User) session.getAttribute("user");
        userFromSession.setEmail(user.getEmail());
        userRepository.save(userFromSession);
        session.setAttribute("user",userFromSession);
    }


    public void editUserDetails(Model model, HttpServletRequest request, HttpSession session){
        User user = (User) session.getAttribute("user");
        UserDetails userDetails = user.getUserDetails();
        model.addAttribute("userDetails",userDetails);
        model.addAttribute("formAction", request.getContextPath() + "/user/profile");
    }

    public void saveUserDetails(UserDetails userDetails , Model model , HttpSession session){
        userDetailsRepository.save(userDetails);

        User user = (User) session.getAttribute("user");
        user.setUserDetails(userDetails);
        model.addAttribute("user",user);
    }

}
