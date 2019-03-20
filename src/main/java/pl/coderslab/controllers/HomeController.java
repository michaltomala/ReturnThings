package pl.coderslab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.entity.Bounty;
import pl.coderslab.entity.Institution;
import pl.coderslab.services.BountyService;
import pl.coderslab.services.InstitutionLocationService;
import pl.coderslab.services.InstitutionService;
import pl.coderslab.services.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final BountyService bountyService;
    private final InstitutionService institutionService;
    private final InstitutionLocationService institutionLocationService;

    @Autowired
    public HomeController(BountyService bountyService,InstitutionService institutionService,
                          InstitutionLocationService institutionLocationService) {

        this.bountyService = bountyService;
        this.institutionService = institutionService;
        this.institutionLocationService = institutionLocationService;
    }


    @GetMapping("/landingPage")
    public String landingPage (){
        return "landingPage";
    }


    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {

        model.addAttribute("bountyTypes",bountyService.returnListOfBountyTypes());
        model.addAttribute("bounty",new Bounty());
        model.addAttribute("institution",new Institution());
        model.addAttribute("formAction", request.getContextPath() + "/bountyForm");
        model.addAttribute("whomHelp" , institutionService.returnWhomHelpList());
        model.addAttribute("locations", institutionLocationService.returnListOfLocations());

        return "user/home";
    }

}

