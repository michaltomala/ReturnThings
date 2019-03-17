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
        model.addAttribute("user", new User());
        model.addAttribute("formAction", request.getContextPath() + "/register");
        return "auth/register";
    }

    @PostMapping("/register")
    public String saveUser(@Validated(ValidationRegisterUserGroup.class) User user, BindingResult errors,
                           Model model, HttpSession session) {
        if (errors.hasErrors()) {
            return "auth/register";
        }

        Err modelErr = new Err();

        registerService.checkPwd(user,modelErr);
        if(!modelErr.isEmpty()){
            model.addAttribute("pwdErr", "Hasła muszą być takie same!");
            return "auth/register";
        }

        registerService.checkIfEmailIsUnique(user,modelErr);
        if(!modelErr.isEmpty()){
            model.addAttribute("emailErr", "Taki użytkownik już istnieje !");
            return "auth/register";
        }

        registerService.saveUser(user);
        session.setAttribute("user",user);
        return "redirect:/home";
    }







}
