package pl.coderslab.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//   todo pozycjonowanie w headerze na stronie głównej (najeżdzający się obrazek z oddaj rzeczy itp)

    @GetMapping("")
    public String homeRedirect(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

}
