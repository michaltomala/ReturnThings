package pl.coderslab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.User;
import pl.coderslab.services.LoginService;
import pl.coderslab.validator.ValidationLoginUserGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String startlogin(Model model, HttpServletRequest request){
        loginService.startLogin(model,request);
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginUser(@Validated(ValidationLoginUserGroup.class) User user , BindingResult errors,Model model,HttpSession session){
        if (errors.hasErrors()) {
            return "auth/login";
        }

        if(loginService.loginUser(user,model,session)){
            return "auth/login";
        }

        if(loginService.isBlocked(user)){
            loginService.logout(session);
            return "redirect:/blocked";
        }

        if(loginService.isAdmin(session)){
            return "redirect:/admin/dashboard";
        }

        return "redirect:/home";
    }

    @GetMapping("/blocked")
    public String blocked(){
        return "auth/blocked";
    }

    @GetMapping("/noAccess")
    public String noAccess(){
        return "auth/noAccess";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        loginService.logout(session);
        return "redirect:/landingPage";
    }


}
