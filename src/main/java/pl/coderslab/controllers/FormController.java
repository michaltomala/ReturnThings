package pl.coderslab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.Bounty;
import pl.coderslab.services.BountyService;
import pl.coderslab.validator.form.ValidationFormStep1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class FormController {

    private final BountyService bountyService;

    @Autowired
    public FormController(BountyService bountyService) {

        this.bountyService = bountyService;
    }


    @PostMapping("/form/step1")
    public String step1(@Validated(ValidationFormStep1.class) Bounty bounty , BindingResult errors,
                        Model model, HttpServletRequest request, HttpSession session){

        if(errors.hasErrors()){
            model.addAttribute("bountyTypes",bountyService.returnListOfBountyTypes());
            model.addAttribute("bounty",new Bounty());
            model.addAttribute("formAction", request.getContextPath() + "/form/step1");
//          todo - wyświetlić błąð w jsp
            return "user/home";
        }
        session.setAttribute("bounty",bounty);
        return "redirect:/form/step2";
    }


    @GetMapping("/form/step2")
    public String step2(){

        return "form/step2";
    }

    @PostMapping("/form/step2")
    public String postFormStep2(){

        return "redirect:/form/step3";
    }

    @GetMapping("/form/step3")
    public String step3(){

        return "form/step3";
    }

    @PostMapping("/form/step3")
    public String postFormStep3(){

        return "redirect:/form/step4";
    }

    @GetMapping("/form/step4")
    public String step4(){

        return "form/step4";
    }

    @PostMapping("/form/step4")
    public String postFormStep4(){

        return "redirect:/form/step5";
    }

    @GetMapping("/form/step5")
    public String step5(){

        return "form/step5";
    }

    @PostMapping("/form/step5")
    public String postFormStep5(){

        return "redirect:/form/step6";
    }

    @GetMapping("/form/step6")
    public String step6(){

        return "form/step6";
    }

    @PostMapping("/form/step6")
    public String postFormStep6(){

        return "redirect:/form/finallyStep";
    }


    @GetMapping("/form/finallyStep")
    public String finallyStep(){

        return "form/finallyStep";
    }



}
