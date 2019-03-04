package pl.coderslab.controllers.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * CRUD Institution Location
     */

    @GetMapping("/locations")
    public String locations(Model model){
        institutionService.addListOfLocationInstitutions(model);
        return "admin/institution/addLocation";
    }

    @GetMapping("/addLocation")
    public String addLocation(Model model){
        institutionService.startAddLocation(model);
        institutionService.addListOfLocationInstitutions(model);
        return "admin/institution/addLocation";
    }

    @PostMapping("/addLocation")
    private String saveLocation(InstitutionLocation institutionLocation,Model model){

        if(institutionService.ifLocationIsNotEmptyAndUnique(institutionLocation,model)){
            return "admin/institution/addLocation";
        }
        institutionService.saveLocation(institutionLocation);
        return "redirect:/admin/institutions/locations";
    }

    @GetMapping("/editLocation/{id}")
    public String editLocation(Model model , @PathVariable Long id){
        institutionService.addListOfLocationInstitutions(model);
        institutionService.editLocation(model,id);
        return "admin/institution/addLocation";
    }

    @PostMapping("/editLocation/{id}")
    public String saveChangeLocation(InstitutionLocation location , Model model){

        if(institutionService.ifLocationIsNotEmptyAndUniqueDuringEditing(location,model)){
            return "admin/institution/addLocation";
        }

        institutionService.saveLocation(location);
        return "redirect:/admin/institutions/locations";
    }

    @GetMapping("/deleteLocation/{id}")
    private String deleteLocation(@PathVariable Long id){
        institutionService.deleteLocation(id);
        return "redirect:/admin/institutions/locations";
    }

//   todo sprawdzić czy działa - usuwać można tylko wtedy gdy nie ma powiązania z instytucjami

    /**
     * CRUD Institution
     */

    @GetMapping("/create")
    public String addInstitution(Model model , HttpServletRequest request) {
        institutionService.startAddingInstitution(model,request);
        return "admin/institution/createInstitution";
    }
}
