package pl.coderslab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.User;
import pl.coderslab.services.RegisterService;
import pl.coderslab.validator.ValidationUserGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    public String register(Model model,HttpServletRequest request){
        registerService.startRegister(model,request);
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@Validated(ValidationUserGroup.class) User user, BindingResult errors,Model model) {
        if (errors.hasErrors()) {
            return "register";
        }
        if(registerService.saveUser(user,model)){
            return "register";
        }
        return "redirect:/";
    }







}
