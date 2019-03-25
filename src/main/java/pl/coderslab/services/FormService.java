package pl.coderslab.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Bounty;
import pl.coderslab.entity.BountyType;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.InstitutionLocation;
import pl.coderslab.model.Err;
import pl.coderslab.repository.BountyTypeRepository;
import pl.coderslab.repository.InstitutionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormService {

    private final BountyTypeRepository bountyTypeRepository;
    private final InstitutionService institutionService;
    private final InstitutionLocationService institutionLocationService;
    private final InstitutionRepository institutionRepository;

    @Autowired
    public FormService(BountyTypeRepository bountyTypeRepository,
                       InstitutionService institutionService,
                       InstitutionLocationService institutionLocationService,
                       InstitutionRepository institutionRepository) {
        this.bountyTypeRepository = bountyTypeRepository;
        this.institutionService = institutionService;
        this.institutionLocationService = institutionLocationService;
        this.institutionRepository = institutionRepository;
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

    public List<Institution> findInstitutions(Institution institution) throws NullPointerException{

        if(!institution.getName().equals("")){
            if(!institutionRepository.findAllByNameContainingIgnoreCase(institution.getName()).isEmpty()){
                 return institutionRepository.findAllByNameContainingIgnoreCase(institution.getName()); }
        }

        if(institution.getWhomHelp().isEmpty()){
            return institutionRepository.findAllByInstitutionLocations(
                    institution.getInstitutionLocations());
        }

        if(institution.getInstitutionLocations().isEmpty()){
            return institutionRepository.findAllByWhomHelp(institution.getWhomHelp());
        }

        return institutionRepository.findAllByWhomHelpAndInstitutionLocations(
                institution.getInstitutionLocations(), institution.getWhomHelp());
    }

    public void setLocationsToEmptyStringWhenNull(Institution institution){
        if(institution.getInstitutionLocations().get(0)==null){
            List<InstitutionLocation> listToAdd = new ArrayList<>();
            institution.setInstitutionLocations(listToAdd);
        }
    }

}

