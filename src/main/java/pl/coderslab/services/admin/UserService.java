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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void addListOfUsers(Model model){
        model.addAttribute("users",userRepository.findAll());
    }

    public void edit(Long id, Model model, HttpServletRequest request){
        User editingUser = userRepository.findOne(id);
        model.addAttribute("editingUser",editingUser);
        model.addAttribute("formAction", request.getContextPath() + "/admin/user/edit/{id}"+id);
    }

    public boolean update(User user, Model model, HttpSession session){

//      Checking if email is empty
        if(user.getEmail().equals("")){
            model.addAttribute("emailErr", "Email nie może być pusty !");
            return addModelAttributesDuringEditingUser(user, model, session);
        }

//      Checking if email is email
//        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(user.getEmail());
//
//
//        if(!matcher.find()){
//            model.addAttribute("emailErr", "Niepoprawny format!");
//            model.addAttribute("user", session.getAttribute("user"));
//            return true;
//        }

//      Checking if email is unique
        User checkUser = userRepository.findFirstByEmail(user.getEmail());
        if (checkUser != null && !checkUser.getId().equals(user.getId())) {
            model.addAttribute("emailErr", "Email musi być unikalny !");
            return addModelAttributesDuringEditingUser(user, model, session);
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



    private boolean addModelAttributesDuringEditingUser(User user, Model model, HttpSession session) {
        model.addAttribute("user",session.getAttribute("user"));
        User editingUser =userRepository.findOne(user.getId());
        model.addAttribute("editingUser",editingUser);
        addListOfUsers(model);
        return true;
    }

}
