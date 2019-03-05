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

    public void startAgainAddingInstitution(Model model,HttpServletRequest request){
        List<InstitutionLocation> locations = institutionLocationRepository.findAll();

        model.addAttribute("locations",locations);
        model.addAttribute("whomHelp",Institution.listOfWhomHelp());
        model.addAttribute("formAction", request.getContextPath() + "/admin/institutions/create");
    }

    public boolean checkIfUnique(Institution institution,Model model) {
        Institution institutionToCheck = institutionRepository.findFirstByName(institution.getName());
        if(institutionToCheck != null){
            model.addAttribute("nameErr","Nazwa organizacji musi być unikalna!");
        }
        return institutionToCheck != null;
    }

    public void saveInstitution(Institution institution){ institutionRepository.save(institution); }


    public void editInstitution(Model model,Long id,HttpServletRequest request){
        List<InstitutionLocation> locations = institutionLocationRepository.findAll();
// todo - często powtarzajace sie dodawanie do modelu
        model.addAttribute("locations",locations);
        model.addAttribute("whomHelp",Institution.listOfWhomHelp());
        model.addAttribute("formAction", request.getContextPath() + "/admin/institutions/edit/"+id);

        model.addAttribute("institution",institutionRepository.findOne(id));
    }

    public void editInstitutionAgain(Model model,HttpServletRequest request,Institution institution){
        List<InstitutionLocation> locations = institutionLocationRepository.findAll();

        model.addAttribute("institution",institution);
        model.addAttribute("locations",locations);
        model.addAttribute("whomHelp",Institution.listOfWhomHelp());
        model.addAttribute("formAction", request.getContextPath() + "/admin/institutions/edit/"+institution.getId());

    }


    public boolean checkIfUniqueDuringEditing(Institution institution,Model model) {
        Institution institutionToCheck = institutionRepository.findFirstByName(institution.getName());
        if(institutionToCheck != null && institutionToCheck.getId()!=(institution.getId())){
            model.addAttribute("nameErr","Nazwa organizacji musi być unikalna!");
        }
        return (institutionToCheck != null && institutionToCheck.getId()!=(institution.getId()));
    }


}
