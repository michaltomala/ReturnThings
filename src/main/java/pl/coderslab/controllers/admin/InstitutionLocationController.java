package pl.coderslab.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.InstitutionLocation;
import pl.coderslab.services.admin.InstitutionLocationService;


@Controller
@RequestMapping("/admin/institutions")
public class InstitutionLocationController {

    private final InstitutionLocationService institutionLocationService;

    @Autowired
    public InstitutionLocationController(InstitutionLocationService institutionLocationService) {
        this.institutionLocationService = institutionLocationService;
    }

    @GetMapping("/locations")
    public String locations(Model model){
        institutionLocationService.addListOfLocationInstitutions(model);
        return "admin/institution/addLocation";
    }

    /**
     * CRUD Institution Location
     */

    @GetMapping("/addLocation")
    public String addLocation(Model model){
        institutionLocationService.startAddLocation(model);
        institutionLocationService.addListOfLocationInstitutions(model);
        return "admin/institution/addLocation";
    }

    @PostMapping("/addLocation")
    private String saveLocation(InstitutionLocation institutionLocation, Model model){

        if(institutionLocationService.ifLocationIsNotEmptyAndUnique(institutionLocation,model)){
            return "admin/institution/addLocation";
        }
        institutionLocationService.saveLocation(institutionLocation);
        return "redirect:/admin/institutions/locations";
    }

    @GetMapping("/editLocation/{id}")
    public String editLocation(Model model , @PathVariable Long id){
        institutionLocationService.addListOfLocationInstitutions(model);
        institutionLocationService.editLocation(model,id);
        return "admin/institution/addLocation";
    }

    @PostMapping("/editLocation/{id}")
    public String saveChangeLocation(InstitutionLocation location , Model model){

        if(institutionLocationService.ifLocationIsNotEmptyAndUniqueDuringEditing(location,model)){
            return "admin/institution/addLocation";
        }

        institutionLocationService.saveLocation(location);
        return "redirect:/admin/institutions/locations";
    }

    //   todo sprawdzić czy działa - usuwać można tylko wtedy gdy nie ma powiązania z instytucjami
    @GetMapping("/deleteLocation/{id}")
    private String deleteLocation(@PathVariable Long id){
        institutionLocationService.deleteLocation(id);
        return "redirect:/admin/institutions/locations";
    }



}
