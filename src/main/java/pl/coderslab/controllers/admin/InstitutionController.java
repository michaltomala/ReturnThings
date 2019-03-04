package pl.coderslab.controllers.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.InstitutionLocation;
import pl.coderslab.services.admin.InstitutionService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/institutions")
public class InstitutionController {

    private final InstitutionService institutionService;

    @Autowired
    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }


    @GetMapping("/")
    public String institutions(Model model){
        institutionService.addListOfInstitutions(model);
        return "admin/institution/institutions";

    }

    /**
     * CRUD Institution
     */

    @GetMapping("/create")
    public String addInstitution(Model model , HttpServletRequest request) {
        institutionService.startAddingInstitution(model,request);
        return "admin/institution/createInstitution";
    }

    @PostMapping("/create")
    private String saveInstitution(@Valid Institution institution,BindingResult errors){
        // walidacja
        if(errors.hasErrors()){
            return "admin/institution/createInstitution";
        }
        //zapis
        institutionService.saveInstitution(institution);
        return "redirect:/admin/institutions/";
    }
}
