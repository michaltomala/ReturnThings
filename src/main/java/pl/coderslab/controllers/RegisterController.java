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
import pl.coderslab.validator.ValidationRegisterUserGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String register(Model model,HttpServletRequest request){
        registerService.startRegister(model,request);
        return "auth/register";
    }

    @PostMapping("/register")
    public String saveUser(@Validated(ValidationRegisterUserGroup.class) User user, BindingResult errors, Model model, HttpSession session) {
        if (errors.hasErrors()) {
            return "auth/register";
        }
        if(registerService.saveUser(user,model,session)){
            return "auth/register";
        }
        return "redirect:/home";
    }







}
