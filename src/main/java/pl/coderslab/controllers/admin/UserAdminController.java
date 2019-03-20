package pl.coderslab.controllers.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.User;
import pl.coderslab.model.Err;
import pl.coderslab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/admin/user/")
public class UserAdminController {

    private final UserService userService;

    @Autowired
    public UserAdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("users")
    public String users(Model model){

        return "admin/users";
    }

    /**
     * methods for edit user by admin
     * @param id
     * @param model
     * @param request
     * @return
     */

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model , HttpServletRequest request){

        model.addAttribute("editingUser", userService.findUser(id));
        model.addAttribute("formAction", request.getContextPath() + "/admin/user/edit/{id}"+id);
        return "admin/users";
    }

    @PostMapping("edit/{id}")
    public String update(User user, HttpServletRequest req, Model model, HttpSession session){

        Err modelErr = new Err();

        userService.checkEmail(user,modelErr);
        if(!modelErr.isEmpty()) {
            model.addAttribute("emailErr", modelErr.getErrors().get(0));

            model.addAttribute("user",session.getAttribute("user"));
            User editingUser = userService.findUser(user.getId());
            model.addAttribute("editingUser",editingUser);
            return "admin/users";
        }

        userService.saveUser(user);
        return "redirect:"+req.getContextPath()+"/admin/user/users";
    }


    /**
     * methods for deleting users
     * @param model
     * @param id
     * @return
     */

    @GetMapping("confirm/{id}")
    public String confirm(Model model, @PathVariable Long id){

        User user = userService.findUser(id);
        model.addAttribute("deletingUser",user);
        model.addAttribute("confirm",user);
        return "admin/users";
    }

    @GetMapping("delete/{id}")
    public String delete(User user,HttpServletRequest request){

        userService.deleteUser(user);
        return "redirect:"+request.getContextPath()+"/admin/user/users";
    }

    @ModelAttribute("users")
    public List<User> listOfAdmins(Model model){ return userService.returnListOfUsers(); }


}
