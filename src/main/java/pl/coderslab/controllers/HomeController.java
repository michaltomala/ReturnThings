package pl.coderslab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.entity.Bounty;
import pl.coderslab.services.FormService;
import pl.coderslab.services.InstitutionLocationService;
import pl.coderslab.services.InstitutionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final FormService formService;
    private final InstitutionService institutionService;
    private final InstitutionLocationService institutionLocationService;

    @Autowired
    public HomeController(FormService formService, InstitutionService institutionService,
                          InstitutionLocationService institutionLocationService) {

        this.formService = formService;
        this.institutionService = institutionService;
        this.institutionLocationService = institutionLocationService;
    }


    @GetMapping("/landingPage")
    public String landingPage (){
        return "landingPage";
    }


    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request, HttpSession session) {

        model.addAttribute("bountyTypes", formService.returnListOfBountyTypes());
        model.addAttribute("formAction", request.getContextPath() + "/form/step1");
        if(session.getAttribute("bounty") == null){
            model.addAttribute("bounty",new Bounty());
        }else { model.addAttribute("bounty",session.getAttribute("bounty")); }

//        model.addAttribute("institution",new Institution());
//        model.addAttribute("whomHelp" , institutionService.returnWhomHelpList());
//        model.addAttribute("locations", institutionLocationService.returnListOfLocations());

        return "user/home";
    }

}

