package pl.coderslab.controllers.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.services.admin.UserService;


@Controller
@RequestMapping("/admin/user/")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("users")
    public String users(Model model){
        userService.start(model);
        return "admin/users";
    }




}
