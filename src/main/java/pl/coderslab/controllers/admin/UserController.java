package pl.coderslab.controllers.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.User;
import pl.coderslab.services.admin.UserService;
import pl.coderslab.validator.ValidationEditUserGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin/user/")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("users")
    public String users(Model model){
        userService.addListOfUsers(model);
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
        userService.addListOfUsers(model);
        userService.edit(id,model,request);
        return "admin/users";
    }

    @PostMapping("edit/{id}")
    public String update(User user, HttpServletRequest req, Model model, HttpSession session){

        if(userService.update(user,model,session)){
            return "admin/users";
        }

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
        userService.addListOfUsers(model);
        userService.confirmDeleteUser(model,id);
        return "admin/users";
    }

    @GetMapping("delete/{id}")
    public String delete(User user,HttpServletRequest request){

        userService.deleteUser(user);
        return "redirect:"+request.getContextPath()+"/admin/user/users";
    }


}
