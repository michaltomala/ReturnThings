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
@RequestMapping("/admin/")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("admins")
    public String admins(Model model){

        return "admin/admins";
    }


    /**
     * methods for edit admins
     * @param id
     * @param model
     * @param request
     * @return
     */

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model , HttpServletRequest request){

        model.addAttribute("editingAdmin", userService.findUser(id));
        model.addAttribute("formAction", request.getContextPath() + "/admin/edit/{id}"+id);
        return "admin/admins";
    }


    @PostMapping("edit/{id}")
    public String update(User user, HttpServletRequest req, Model model, HttpSession session){

        Err modelErr = new Err();

        userService.checkEmail(user,modelErr);
        if(!modelErr.isEmpty()) {
            model.addAttribute("emailErr", modelErr.getErrors().get(0));

            model.addAttribute("user", session.getAttribute("user"));
            User editingUser = userService.findUser(user.getId());
            model.addAttribute("editingAdmin", editingUser);
            return "admin/admins";
        }

        userService.saveUser(user);
        return "redirect:"+req.getContextPath()+"/admin/admins";
    }


    /**
     * methods for deleting admins
     * @param model
     * @param id
     * @return
     */

    @GetMapping("confirm/{id}")
    public String confirm(Model model, @PathVariable Long id){

        User user = userService.findUser(id);
        model.addAttribute("deletingUser",user);
        model.addAttribute("confirm",user);
        return "admin/admins";
    }

    @GetMapping("delete/{id}")
    public String delete(User user,HttpServletRequest request){

        userService.deleteUser(user);
        return "redirect:"+request.getContextPath()+"/admin/admins";
    }


    @ModelAttribute("admins")
    public List<User> listOfAdmins(Model model){ return userService.returnListOfAdmins(); }


}




