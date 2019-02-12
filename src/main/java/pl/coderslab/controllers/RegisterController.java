package pl.coderslab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.User;
import pl.coderslab.services.RegisterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    public String register(Model model){
        registerService.startRegister(model);
        return "register";
    }



    @PostMapping("/register")
    public String saveUser(User user) {
        registerService.saveUser(user);
        return "redirect:/";
    }







}
