package pl.coderslab.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Bounty;


@Controller
public class FormController {

    @PostMapping("/form/step1")
    public String step1(Bounty bounty){


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
