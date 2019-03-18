package pl.coderslab.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.InstitutionLocation;
import pl.coderslab.model.Err;
import pl.coderslab.services.admin.InstitutionLocationService;

import java.util.List;


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

        return "admin/institution/locationForm";
    }

    /**
     * CRUD Institution Location
     */

    @GetMapping("/addLocation")
    public String addLocation(Model model){

        model.addAttribute("addLocation",new InstitutionLocation());
        return "admin/institution/locationForm";
    }

    @PostMapping("/addLocation")
    private String saveLocation(InstitutionLocation location, Model model){

        Err modelErr = new Err();

        institutionLocationService.checkIfLocationIsNotEmpty(location,modelErr);
        if(!modelErr.isEmpty()){
            model.addAttribute("addLocation",new InstitutionLocation());
            model.addAttribute("locationErr","Lokalizacja nie może być pusta!");
            return "admin/institution/locationForm";
        }

        institutionLocationService.checkIfLocationIsUnique(location,modelErr);
        if(!modelErr.isEmpty()){
            model.addAttribute("addLocation",new InstitutionLocation());
            model.addAttribute("locationErr","Podana lokalizacja już istnieje!");
            return "admin/institution/locationForm";
        }

        institutionLocationService.saveLocation(location);
        return "redirect:/admin/institutions/locations";
    }

    @GetMapping("/editLocation/{id}")
    public String editLocation(Model model , @PathVariable Long id){

        model.addAttribute("editLocation",institutionLocationService.findLocation(id));
        return "admin/institution/locationForm";
    }

    @PostMapping("/editLocation/{id}")
    public String saveChangeLocation(InstitutionLocation location , Model model){

        Err modelErr = new Err();

        institutionLocationService.checkIfLocationIsNotEmpty(location,modelErr);
        if(!modelErr.isEmpty()){
            model.addAttribute("editLocation",institutionLocationService.findLocation(location.getId()));
            model.addAttribute("locationErr","Lokalizacja nie może być pusta!");
            return "admin/institution/locationForm";
        }

        institutionLocationService.checkIfLocationIsUniqueDuringEditing(location,modelErr);
        if(!modelErr.isEmpty()){
            model.addAttribute("editLocation",institutionLocationService.findLocation(location.getId()));
            model.addAttribute("locationErr","Podana lokalizacja już istnieje!");
            return "admin/institution/locationForm";
        }

        institutionLocationService.saveLocation(location);
        return "redirect:/admin/institutions/locations";
    }

    @GetMapping("/deleteLocation/{id}")
    private String deleteLocation(@PathVariable Long id,Model model){

        Err modelErr = new Err();

        institutionLocationService.checkIfDeleteLocationIsPossible(id,modelErr);
        if(!modelErr.isEmpty()) {
            model.addAttribute("deleteLocationErr", "Usunięcie jest możliwe " +
                    "dopiero w przypadku gdy nie ma żadnej organizacji przypisanej do danej lokalizacji!");
            return "admin/institution/locationForm";
        }

        institutionLocationService.deleteLocation(id);
        return "redirect:/admin/institutions/locations";
    }

    @ModelAttribute("locations")
    public List<InstitutionLocation> listOfLocations(Model model){
        return institutionLocationService.returnListOfLocations(); }

    @ModelAttribute("locationsEnableToDelete")
    public List<InstitutionLocation>listOfLocationsEnableToDelete(Model model){
        return institutionLocationService.returnListOfLocationsEnableToDelete(); }


}
