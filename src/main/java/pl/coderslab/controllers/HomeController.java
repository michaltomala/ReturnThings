package pl.coderslab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.services.BountyService;
import pl.coderslab.services.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final BountyService bountyService;

    @Autowired
    public HomeController(BountyService bountyService) {
        this.bountyService = bountyService;
    }


    @GetMapping("/landingPage")
    public String landingPage (){
        return "landingPage";
    }


    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {

      
        bountyService.prepareForm(model,request);
        return "user/home";
    }

}
