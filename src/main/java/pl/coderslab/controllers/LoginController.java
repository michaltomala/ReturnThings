package pl.coderslab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.User;
import pl.coderslab.model.Err;
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
    public String startLogin(Model model, HttpServletRequest request){

        model.addAttribute("user", new User());
        model.addAttribute("formAction", request.getContextPath() + "/login");
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginUser(@Validated(ValidationLoginUserGroup.class) User user , BindingResult errors,
                            Model model,HttpSession session){
        if (errors.hasErrors()) {
            return "auth/login";
        }

        Err modelErr = new Err();

        loginService.checkEmailAndPassword(user,modelErr);
        if(!modelErr.isEmpty()){
            model.addAttribute("authErr", "Email albo hasło się nie zgadza!");
            return "auth/login";
        }

        session.setAttribute("user", loginService.returnUserFromRepository(user));

        if(loginService.isBlocked(user)){
            session.setAttribute("user", null);
            return "redirect:/blocked";
        }


        if(loginService.isAdmin((User) session.getAttribute("user"))){
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

        session.setAttribute("user", null);
        return "redirect:/landingPage";
    }


}
