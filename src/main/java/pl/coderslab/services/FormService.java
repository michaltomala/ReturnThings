package pl.coderslab.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Bounty;
import pl.coderslab.entity.BountyType;
import pl.coderslab.entity.Institution;
import pl.coderslab.model.Err;
import pl.coderslab.repository.BountyTypeRepository;
import pl.coderslab.repository.InstitutionRepository;

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


    public List<Institution> findInstitutions(Institution institution){
//       todo warunek 1 - jeśli nazwa nie jest pusta to po nazwie
//        warunek 2 - jeżeli nazwa jest pusta to po jednym i drugim
        return institutionRepository.findAllByNameContainingIgnoreCase(institution.getName());
    }



}

