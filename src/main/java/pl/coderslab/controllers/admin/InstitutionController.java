package pl.coderslab.controllers.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.services.admin.InstitutionService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/institutions/")
public class InstitutionController {

    private final InstitutionService institutionService;

    @Autowired
    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }


    @GetMapping("")
    public String institutions(Model model){
        institutionService.addListOfInstitutions(model);
        return "admin/institution/institutions";

    }

    /**
     * Add Location
     */

    @GetMapping("/addLocation")
    public String addLocation(){
        return "admin/institution/institutionLocationAdd";
    }

    /**
     * CRUD Institution
     */

    @GetMapping("/create")
    public String addInstitution(Model model , HttpServletRequest request) {
        institutionService.startAddingInstitution(model,request);
        return "admin/institution/createInstitution";
    }
}
