package pl.coderslab.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void start(Model model){
        model.addAttribute("users",userRepository.findAll());
    }

    public void edit(Long id, Model model, HttpServletRequest request){
        User editingUser = userRepository.findOne(id);
        model.addAttribute("user",editingUser);
        model.addAttribute("formAction", request.getContextPath() + "/admin/user/edit/{id}"+id);
    }

    public boolean update(User user, Model model, HttpSession session){

        User checkUser = userRepository.findFirstByEmail(user.getEmail());
        if (checkUser != null && !checkUser.getId().equals(user.getId())) {
//           todo zrobić porządek z nazwami !!!
            model.addAttribute("emailErr", "Email musi być unikalny !");
            model.addAttribute("user",session.getAttribute("user"));
            model.addAttribute("editingUser",user);
//            model.addAttribute("user",session.getAttribute("userFromSession"));
//            model.addAttribute("editingUser",user);
            return true;
        }

        userRepository.save(user);
        return false;
    }

    public void confirmDeleteUser(Model model, Long id){

        User user = userRepository.findOne(id);
        model.addAttribute("deletingUser",user);
        model.addAttribute("confirm",user);
    }

    public void deleteUser(User user){

        userRepository.delete(user);
    }


}
