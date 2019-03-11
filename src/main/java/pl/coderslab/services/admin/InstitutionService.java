package pl.coderslab.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.InstitutionListOfWhomHelp;
import pl.coderslab.entity.InstitutionLocation;
import pl.coderslab.repository.InstitutionListOfWhomHelpRepository;
import pl.coderslab.repository.InstitutionLocationRepository;
import pl.coderslab.repository.InstitutionRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Service
public class InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final InstitutionLocationRepository institutionLocationRepository;
    private final InstitutionListOfWhomHelpRepository institutionListOfWhomHelpRepository;

    @Autowired
    public InstitutionService(InstitutionRepository institutionRepository,
                              InstitutionLocationRepository institutionLocationRepository,
                              InstitutionListOfWhomHelpRepository institutionListOfWhomHelpRepository) {
        this.institutionRepository = institutionRepository;
        this.institutionLocationRepository = institutionLocationRepository;
        this.institutionListOfWhomHelpRepository = institutionListOfWhomHelpRepository;
    }

    public void addListOfInstitutions(Model model){
        model.addAttribute("institutions",institutionRepository.findAll());
    }

    public void startAddingInstitution(Model model , HttpServletRequest request){
        addListOfWhomHelpAndLocations(model);
        model.addAttribute("tagHeader","Dodaj nową organizację");
        model.addAttribute("institution", new Institution());
        model.addAttribute("formAction", request.getContextPath() + "/admin/institutions/create");
    }



    public void startAgainAddingInstitution(Model model,HttpServletRequest request){
        addListOfWhomHelpAndLocations(model);
        model.addAttribute("tagHeader","Dodaj nową organizację");
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
        addListOfWhomHelpAndLocations(model);
        model.addAttribute("tagHeader","Edytuj organizację");
        model.addAttribute("formAction", request.getContextPath() + "/admin/institutions/edit/"+id);
        model.addAttribute("institution",institutionRepository.findOne(id));
    }

    public void editInstitutionAgain(Model model,HttpServletRequest request,Institution institution){
        addListOfWhomHelpAndLocations(model);
        model.addAttribute("tagHeader","Edytuj organizację");
        model.addAttribute("institution",institution);
        model.addAttribute("formAction", request.getContextPath() +
                "/admin/institutions/edit/"+institution.getId());
    }


    public boolean checkIfUniqueDuringEditing(Institution institution,Model model) {
        Institution institutionToCheck = institutionRepository.findFirstByName(institution.getName());
        if(institutionToCheck != null && institutionToCheck.getId()!=(institution.getId())){
            model.addAttribute("nameErr","Nazwa organizacji musi być unikalna!");
        }
        return (institutionToCheck != null && institutionToCheck.getId()!=(institution.getId()));
    }

    public void deleteInstitution(Long id){ institutionRepository.delete(id); }



    public void addListOfWhomHelpAndLocations(Model model) {
        List<InstitutionLocation> locations = institutionLocationRepository.findAll();
        model.addAttribute("whomHelp" , getListOfWhomHelp());
        model.addAttribute("locations", locations);
    }


    // todo jeśli się da - wyciągnąć bezpośrednio z repo
    private List<String> getListOfWhomHelp(){
        List<InstitutionListOfWhomHelp> listOfWhomHelp = institutionListOfWhomHelpRepository.findAll();
        List<String> listToReturn = new ArrayList<>();
        for(InstitutionListOfWhomHelp whomHelp : listOfWhomHelp){
            listToReturn.add(whomHelp.getWhomHelp());
        }
        return listToReturn;
    }




}

