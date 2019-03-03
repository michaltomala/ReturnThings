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

    public void startAddingInstitution(Model model , HttpServletRequest request){

        List<InstitutionLocation> locations = institutionLocationRepository.findAll();

        model.addAttribute("whomHelp" , Institution.listOfWhomHelp());
        model.addAttribute("institution", new Institution());
        model.addAttribute("locations", locations);
        model.addAttribute("formAction", request.getContextPath() + "/admin/institutions/create");

    }

}
