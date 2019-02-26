package pl.coderslab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.services.LoginService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final LoginService loginService;

    @Autowired
    public HomeController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("")
    public String homeRedirect(HttpSession session){
        if(loginService.isLogged(session)){
            return "redirect:/home";
        }
        return "redirect:/landingPage";
    }

    @GetMapping("/landingPage")
    public String landingPage (){
        return "landingPage";
    }


    @GetMapping("/home")
    public String home() { return "user/home"; }
}
