package pl.coderslab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.Bounty;
import pl.coderslab.entity.Institution;
import pl.coderslab.model.Err;
import pl.coderslab.services.FormService;
import pl.coderslab.services.InstitutionLocationService;
import pl.coderslab.services.InstitutionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class FormController {

    private final FormService formService;
    private final InstitutionService institutionService;
    private final InstitutionLocationService institutionLocationService;

    @Autowired
    public FormController(FormService formService,
                          InstitutionService institutionService,
                          InstitutionLocationService institutionLocationService) {

        this.formService = formService;
        this.institutionService = institutionService;
        this.institutionLocationService = institutionLocationService;
    }


    @PostMapping("/form/step1")
    public String step1(Bounty bounty , Model model, HttpServletRequest request, HttpSession session){

        Err modelErr = new Err();

        formService.checkIfNotEmptyBountyType(bounty,modelErr);
        if(!modelErr.isEmpty()){

            model.addAttribute("bountyTypes",formService.returnListOfBountyTypes());
            model.addAttribute("formAction", request.getContextPath() + "/form/step1");
            model.addAttribute("bountyErr", "Musisz zaznaczyć co chcesz oddać !");
            return "user/home";
        }

        Bounty bountyFromSession = (Bounty) session.getAttribute("bounty");
        if(bountyFromSession==null){
            session.setAttribute("bounty",bounty);
        }else{
            bounty.setQuantityOfBags(bountyFromSession.getQuantityOfBags());
            session.setAttribute("bounty",bounty);
        }
        return "redirect:/form/step2";
    }
// todo - placeholders to keep values in all steps

    @GetMapping("/form/step2")
    public String step2(Model model,HttpServletRequest request,HttpSession session){

        model.addAttribute("bounty",session.getAttribute("bounty"));
        model.addAttribute("formAction", request.getContextPath() + "/form/step2");
        return "form/step2";
    }

    @PostMapping("/form/step2")
    public String postFormStep2(Bounty bounty ,HttpSession session,Model model){

        Err modelErr = new Err();

        formService.checkIfNotEmptyQuantityOfBags(bounty,modelErr);
        if(!modelErr.isEmpty()){
            model.addAttribute("bountyErr", "Liczba worków nie może być pusta !");
            return "form/step2";
        }

        if(bounty.getBountyType().isEmpty()){
            Bounty bountyFromSession = (Bounty) session.getAttribute("bounty");
            bounty.setBountyType(bountyFromSession.getBountyType());
        }

        session.setAttribute("bounty",bounty);
        return "redirect:/form/step3";
    }

    @GetMapping("/form/step3")
    public String step3(Model model,HttpServletRequest request){

        model.addAttribute("institution",new Institution());
        model.addAttribute("whomHelp" , institutionService.returnWhomHelpList());
        model.addAttribute("locations", institutionLocationService.returnListOfLocations());
        model.addAttribute("formAction", request.getContextPath() + "/form/step3");
        return "form/step3";
    }

    @PostMapping("/form/step3")
    public String postFormStep3(Institution institution,Model model){
//       todo - walidacja  - co najmniej jedna opcja musi być spełniona z pierwszych dwóch

//       todo przesłać obiekt do formSerwisu - ma on zwrócić listę instytucji
//       todo - jeśli lista bedzie pusta - zwrócić komunikat,że nie znaleziono
//        - w takim wypadku zaleca się wybranie np.tylko lokalizacji
        model.addAttribute("institutions",formService.findInstitutions(institution));
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
