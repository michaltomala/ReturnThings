package pl.coderslab.controllers.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.InstitutionListOfWhomHelp;
import pl.coderslab.entity.InstitutionLocation;
import pl.coderslab.model.Err;
import pl.coderslab.services.InstitutionLocationService;
import pl.coderslab.services.InstitutionService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/institutions")
public class InstitutionController {

    private final InstitutionService institutionService;
    private final InstitutionLocationService institutionLocationService;

    @Autowired
    public InstitutionController(InstitutionService institutionService,
                                 InstitutionLocationService institutionLocationService) {
        this.institutionService = institutionService;
        this.institutionLocationService = institutionLocationService;
    }


    @GetMapping("/")
    public String institutions(Model model){

        model.addAttribute("institutions",institutionService.returnListOfInstitution());
        return "admin/institution/institutions";
    }

    /**
     * CRUD Institution
     */

    @GetMapping("/create")
    public String addInstitution(Model model , HttpServletRequest request) {

        model.addAttribute("institution", new Institution());
        return addModelAttributesDuringCreating(model, request);
    }


    @PostMapping("/create")
    public String saveInstitution(@Valid Institution institution,BindingResult errors,Model model,HttpServletRequest request){

        if(errors.hasErrors()){
            return addModelAttributesDuringCreating(model, request);
        }

        Err modelErr = new Err();

        institutionService.checkIfInstitutionIsUnique(institution,modelErr);
        if(!modelErr.isEmpty()){
            model.addAttribute("nameErr","Nazwa organizacji musi być unikalna!");
            return addModelAttributesDuringCreating(model, request);
        }

        institutionService.saveInstitution(institution);
        return "redirect:/admin/institutions/";
    }

    @GetMapping("/edit/{id}")
    public String editInstitution(@PathVariable Long id,Model model,HttpServletRequest request){

        model.addAttribute("tagHeader","Edytuj organizację");
        model.addAttribute("formAction", request.getContextPath() + "/admin/institutions/edit/"+id);
        model.addAttribute("institution",institutionService.findInstitution(id));
        return "admin/institution/institutionForm";
    }

    @PostMapping("/edit/{id}")
    public String saveChangedInstitution(@Valid Institution institution,BindingResult errors,Model model,HttpServletRequest request){

        if(errors.hasErrors()){
            return addModelAttributesDuringEditing(institution, model, request);
        }

        Err modelErr = new Err();
        institutionService.checkIfInstitutionIsUniqueDuringEditing(institution,modelErr);
        if(!modelErr.isEmpty()){
            model.addAttribute("nameErr","Podana instytucja już istnieje!");
            return addModelAttributesDuringEditing(institution, model, request);
        }

        institutionService.saveInstitution(institution);
        return "redirect:/admin/institutions/";
    }


    @GetMapping("/delete/{id}")
    public String deleteInstitution(@PathVariable Long id){
//      todo - sprawdzić czy nie ma powiązania z darami
        institutionService.deleteInstitution(id);
        return "redirect:/admin/institutions/";
    }




    @ModelAttribute("whomHelp")
    public List<InstitutionListOfWhomHelp> whomHelpList(Model model){
        return institutionService.returnWhomHelpList(); }

    @ModelAttribute("locations")
    public List<InstitutionLocation> listOfLocations(Model model){
        return institutionLocationService.returnListOfLocations(); }



    private String addModelAttributesDuringCreating(Model model, HttpServletRequest request) {
        model.addAttribute("tagHeader", "Dodaj nową organizację");
        model.addAttribute("formAction", request.getContextPath() + "/admin/institutions/create");
        return "admin/institution/institutionForm";
    }

    private String addModelAttributesDuringEditing(@Valid Institution institution, Model model, HttpServletRequest request) {
        model.addAttribute("tagHeader", "Edytuj organizację");
        model.addAttribute("institution", institution);
        model.addAttribute("formAction", request.getContextPath() +
                "/admin/institutions/edit/" + institution.getId());

        return "admin/institution/institutionForm";
    }

}
