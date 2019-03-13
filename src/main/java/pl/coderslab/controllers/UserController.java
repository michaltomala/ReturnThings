package pl.coderslab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.User;
import pl.coderslab.services.UserService;
import pl.coderslab.services.admin.AdminUserService;
import pl.coderslab.validator.ValidationRegisterUserGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;
    private final AdminUserService adminUserService;

    @Autowired
    public UserController(UserService userService,AdminUserService adminUserService) {

        this.userService = userService;
        this.adminUserService = adminUserService;
    }

    @GetMapping("settings")
    public String settings(HttpServletRequest request, Model model, HttpSession session){
        userService.edit(model, request,session);
        return "user/settings";
    }

    /**
     * Method update is for edit part of our User - only email / only password or email and password
     * I validated for all options
     * @param user
     * @param errors
     * @param req
     * @param model
     * @param session
     * @return
     */

    @PostMapping("settings")
    public String update(@Validated(ValidationRegisterUserGroup.class) User user, BindingResult errors,
                         HttpServletRequest req, Model model, HttpSession session){

        if(user.getPassword().equals("")){
            if(adminUserService.checkEmail(user,model)){
                userService.edit(model,req,session);
                return "user/settings";
            }else{
                userService.onlySaveUser(user,session);
                return "redirect:/user/settings";
            }
        }

        if (errors.hasErrors()) {
            if(adminUserService.checkEmail(user,model)){
                userService.edit(model,req,session);
                return "user/settings";
            }

            return "user/settings";
        }

        if(userService.update(user,model,session)){
            return "user/settings";
        }

        return "redirect:"+req.getContextPath()+"/user/settings";
    }


    @GetMapping("profile")
    public String profile(Model model , HttpServletRequest request , HttpSession session){
        userService.editUserDetails(model , request , session);
        return "user/profile";
    }

    @PostMapping("profile")
    public String saveProfile(User user,HttpSession session, HttpServletRequest request){

        userService.saveUserDetails(user,session);
        return "redirect:"+request.getContextPath()+"/user/profile";
    }

}
