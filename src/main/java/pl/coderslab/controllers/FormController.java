package pl.coderslab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.Bounty;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.Reception;
import pl.coderslab.model.Err;
import pl.coderslab.services.FormService;
import pl.coderslab.services.InstitutionLocationService;
import pl.coderslab.services.InstitutionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;


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
    public String step3(Model model,HttpServletRequest request,HttpSession session){

        return AddAttributeModelsToStep3(model, request,session);
    }

    @PostMapping("/form/step4")
    public String step4(Institution institution,Model model,
                                HttpServletRequest request,HttpSession session){

        formService.setLocationsToEmptyStringWhenNull(institution);

        Err modelErr = new Err();
        formService.checkInstitution(institution,modelErr);
        if(!modelErr.isEmpty()){
            model.addAttribute("institutionErr","Aby wyszukać instytucję musisz " +
                    "wyznaczyć jakiekolwiek parametry!");
            return AddAttributeModelsToStep3(model, request,session);
        }

        try{
            Set<Institution> institutions = formService.findInstitutions(institution);
            session.setAttribute("institutions",institutions);
            if(institutions.isEmpty()){
                model.addAttribute("institutionErr","Nie znaleziono instytucji o podanych kryteriach \n " +
                        "W takich sytuacjach zalecamy wybranie tylko lokalizacji - pomoże to w dobraniu " +
                        "odpowiedniej instytucji");
                return AddAttributeModelsToStep3(model, request,session);
            }
        }catch(NullPointerException e){
            model.addAttribute("institutionErr","Nie znaleziono instytucji o podanych kryteriach \n " +
                    "W takich sytuacjach zalecamy wybranie tylko lokalizacji - pomoże to w dobraniu " +
                    "odpowiedniej instytucji");
            return AddAttributeModelsToStep3(model,request,session);
        }

        session.setAttribute("institution",institution);
        return "form/step4";
    }

//   todo - Formfiltr  żeby nie można było przejść np od razu na step4

    /**
     * This mapping is only for callback from step5
     * @return
     */
    @GetMapping("/form/step4")
    public String step4(){

        return "form/step4";
    }

    @GetMapping("/form/step4/{name}")
    public String getInstitution(@PathVariable String name,HttpSession session){

        session.setAttribute("chosenInstitution",institutionService.findInstitutionByName(name));
        return "redirect:/form/step5";
    }

    @GetMapping("/form/step5")
    public String step5(Model model,HttpServletRequest request,HttpSession session){

//       todo - callback from step 6
        if(session.getAttribute("reception") == null){
            model.addAttribute("reception",new Reception());
        } else {
            model.addAttribute("reception",session.getAttribute("reception"));
        }
        model.addAttribute("formAction", request.getContextPath() + "/form/step5");
        return "form/step5";
    }

    @PostMapping("/form/step5")
    public String postFormStep5(@Valid Reception reception, BindingResult errors, HttpSession session,
                                Model model,HttpServletRequest request){

        if(errors.hasErrors()){
            model.addAttribute("formAction", request.getContextPath() + "/form/step5");
            return "form/step5";
        }
//       todo - przestawienie numeru z 123456789 na 123-456-789
        session.setAttribute("reception",reception);
        return "redirect:/form/step6";
    }

    @GetMapping("/form/step6")
    public String step6(){

        return "form/step6";
    }

    @GetMapping("/form/saveForm")
    public String saveForm(){

//   todo - tutaj zapisujemy wszystko gdze trzeba i nastepnie przestawiamy w sesji na null
        return "redirect:/form/finallyStep";
    }


    @GetMapping("/form/finallyStep")
    public String finallyStep(HttpSession session){

        LoginController.setSessionAttributesNull(session);
        return "form/finallyStep";
    }




    private String AddAttributeModelsToStep3(Model model, HttpServletRequest request,HttpSession session) {

        if(session.getAttribute("institution") == null){
            model.addAttribute("institution", new Institution());
        } else {
            model.addAttribute("institution",session.getAttribute("institution"));
        }
        model.addAttribute("whomHelp", institutionService.returnWhomHelpList());
        model.addAttribute("locations", institutionLocationService.returnListOfLocations());
        model.addAttribute("formAction", request.getContextPath() + "/form/step4");
        return "form/step3";
    }

}
