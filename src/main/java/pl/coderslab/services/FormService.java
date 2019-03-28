package pl.coderslab.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.dto.BountyDetails;
import pl.coderslab.entity.*;
import pl.coderslab.model.Err;
import pl.coderslab.repository.BountyDetailsRepository;
import pl.coderslab.repository.BountyTypeRepository;
import pl.coderslab.repository.InstitutionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class FormService {

    private final BountyTypeRepository bountyTypeRepository;
    private final InstitutionRepository institutionRepository;
    private final BountyDetailsRepository bountyDetailsRepository;

    @Autowired
    public FormService(BountyTypeRepository bountyTypeRepository,
                       InstitutionRepository institutionRepository,
                       BountyDetailsRepository bountyDetailsRepository
    ) {
        this.bountyTypeRepository = bountyTypeRepository;
        this.institutionRepository = institutionRepository;
        this.bountyDetailsRepository = bountyDetailsRepository;
    }

    public List<BountyType> returnListOfBountyTypes(){ return bountyTypeRepository.findAll(); }


    public void checkIfNotEmptyBountyType(Bounty bounty, Err modelErr){
        if(bounty.getBountyType().isEmpty()){ modelErr.addErr("Empty BountyTypes!"); } }

    public void checkIfNotEmptyQuantityOfBags(Bounty bounty, Err modelErr){
        if(bounty.getQuantityOfBags()==null){ modelErr.addErr("Empty quantityOfBags!"); } }

    public void checkInstitution(Institution institution,Err modelErr){
        if(institution.getName().equals("") &&
                institution.getWhomHelp().isEmpty() &&
                institution.getInstitutionLocations().isEmpty()){
            modelErr.addErr("Nothing to find!");
        }
    }

    public Set<Institution> findInstitutions(Institution institution) throws NullPointerException{

        if(!institution.getName().equals("")){
            if(!institutionRepository.findAllByNameContainingIgnoreCase(institution.getName()).isEmpty()){
                 return institutionRepository.findAllByNameContainingIgnoreCase(institution.getName()); }
        }

        if(institution.getWhomHelp().isEmpty()){
            return institutionRepository.findAllByInstitutionLocations(
                    institution.getInstitutionLocations());
        }

        if(institution.getInstitutionLocations().isEmpty()){
            return institutionRepository.findAllByWhomHelpIn(institution.getWhomHelp());
        }

          return  institutionRepository.findAllByInstitutionLocationsAndWhomHelpIn(institution.getInstitutionLocations(),
                  institution.getWhomHelp());

    }

    public void setLocationsToEmptyStringWhenNull(Institution institution){
        if(institution.getInstitutionLocations().get(0)==null){
            List<InstitutionLocation> listToAdd = new ArrayList<>();
            institution.setInstitutionLocations(listToAdd);
        }
    }

    public void saveForm(Bounty bounty,Institution institution,Reception reception){

        BountyDetails bountyDetails = new BountyDetails();
        bountyDetails.setBounty(bounty);
        bountyDetails.setIntitution(institution);
        bountyDetails.setReception(reception);
        bountyDetailsRepository.save(bountyDetails);
    }

    public void setCorrectNumber(Reception reception){

        String phone = reception.getPhone();
        String result = phone.substring(0, 3) + "-" +phone.substring(3, 6) + "-" + phone.substring(6,9);
        reception.setPhone(result);
    }


}

