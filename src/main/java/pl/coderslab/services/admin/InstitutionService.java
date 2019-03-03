package pl.coderslab.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.InstitutionLocation;
import pl.coderslab.repository.InstitutionLocationRepository;
import pl.coderslab.repository.InstitutionRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final InstitutionLocationRepository institutionLocationRepository;

    @Autowired
    public InstitutionService(InstitutionRepository institutionRepository, InstitutionLocationRepository institutionLocationRepository) {
        this.institutionRepository = institutionRepository;
        this.institutionLocationRepository = institutionLocationRepository;
    }

    public void addListOfInstitutions(Model model){
        model.addAttribute("institutions",institutionRepository.findAll());
    }

    public void addListOfLocationInstitutions(Model model){
        model.addAttribute("locations",institutionLocationRepository.findAll());
        model.addAttribute("locationsEnableToDelete",institutionLocationRepository.findAllByInstitutionIsNull());
    }

    public void startAddLocation(Model model){ model.addAttribute("addLocation",new InstitutionLocation()); }
    public void startAgainToAddLocation(Model model,InstitutionLocation intitutionLocation){
        model.addAttribute("addLocation",intitutionLocation);
    }
    public void saveLocation(InstitutionLocation location){ institutionLocationRepository.save(location); }

    public void editLocation(Model model , Long id){
        model.addAttribute("editLocation",institutionLocationRepository.findOne(id));
    }

    public void startAddingInstitution(Model model , HttpServletRequest request){

        List<InstitutionLocation> locations = institutionLocationRepository.findAll();

        model.addAttribute("whomHelp" , Institution.listOfWhomHelp());
        model.addAttribute("institution", new Institution());
        model.addAttribute("locations", locations);
        model.addAttribute("formAction", request.getContextPath() + "/admin/institutions/create");

    }

}
