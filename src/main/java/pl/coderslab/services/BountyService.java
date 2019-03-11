package pl.coderslab.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.entity.Bounty;
import pl.coderslab.entity.BountyType;
import pl.coderslab.repository.BountyTypeRepository;
import pl.coderslab.services.admin.InstitutionService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class BountyService {

    private final BountyTypeRepository bountyTypeRepository;
    private final InstitutionService institutionService;

    @Autowired
    public BountyService(BountyTypeRepository bountyTypeRepository, InstitutionService institutionService) {
        this.bountyTypeRepository = bountyTypeRepository;
        this.institutionService = institutionService;
    }

    public void prepareForm(Model model , HttpServletRequest request){

        model.addAttribute("bountyTypes",getAllBountyType());
        model.addAttribute("bounty",new Bounty());
        model.addAttribute("formAction", request.getContextPath() + "/bountyForm");
        institutionService.addListOfWhomHelpAndLocations(model);

    }

    private List<String> getAllBountyType(){
        List<BountyType> bountyTypes = bountyTypeRepository.findAll();
        List<String> listToReturn = new ArrayList<>();
        for(BountyType bountyType: bountyTypes){
            listToReturn.add(bountyType.getType());
        }
        return listToReturn;
    }



}
