package pl.coderslab.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class FormController {


    @PostMapping("/step2")
    public String step2(){

        return "form/step2";
    }

    @PostMapping("/step3")
    public String step3(){

        return "form/step3";
    }

    @PostMapping("/step4")
    public String step4(){

        return "form/step4";
    }

    @PostMapping("/step5")
    public String step5(){

        return "form/step5";
    }

    @PostMapping("/step6")
    public String step6(){

        return "form/step6";
    }

    @PostMapping("/finallyStep")
    public String finallyStep(){

        return "form/finallyStep";
    }



}
