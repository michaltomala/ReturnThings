package pl.coderslab.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.entity.Bounty;
import pl.coderslab.entity.BountyType;
import pl.coderslab.entity.Institution;
import pl.coderslab.repository.BountyTypeRepository;
import pl.coderslab.services.admin.InstitutionLocationService;
import pl.coderslab.services.admin.InstitutionService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class BountyService {

    private final BountyTypeRepository bountyTypeRepository;
    private final InstitutionService institutionService;
    private final InstitutionLocationService institutionLocationService;
    @Autowired
    public BountyService(BountyTypeRepository bountyTypeRepository,
                         InstitutionService institutionService,
                         InstitutionLocationService institutionLocationService) {
        this.bountyTypeRepository = bountyTypeRepository;
        this.institutionService = institutionService;
        this.institutionLocationService = institutionLocationService;
    }

    public void prepareForm(Model model , HttpServletRequest request){

        model.addAttribute("bountyTypes",getAllBountyType());
        model.addAttribute("bounty",new Bounty());
        model.addAttribute("institution",new Institution());
        model.addAttribute("formAction", request.getContextPath() + "/bountyForm");

        model.addAttribute("whomHelp" , institutionService.returnWhomHelpList());
        model.addAttribute("locations", institutionLocationService.returnListOfLocations());

    }

    public void prepareFormSecondTime(){

    }

    public void saveForms(){

    }

//   todo - usunąć metodę i odwoływać się w widoku po obiekcie
    private List<String> getAllBountyType(){
        List<BountyType> bountyTypes = bountyTypeRepository.findAll();
        List<String> listToReturn = new ArrayList<>();
        for(BountyType bountyType: bountyTypes){
            listToReturn.add(bountyType.getType());
        }
        return listToReturn;
    }



}
