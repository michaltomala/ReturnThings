package pl.coderslab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.InstitutionListOfWhomHelp;
import pl.coderslab.model.Err;
import pl.coderslab.repository.InstitutionListOfWhomHelpRepository;
import pl.coderslab.repository.InstitutionLocationRepository;
import pl.coderslab.repository.InstitutionRepository;

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

    public void saveInstitution(Institution institution){ institutionRepository.save(institution); }

    public void deleteInstitution(Long id){ institutionRepository.delete(id); }


    public Institution findInstitution(Long id){ return institutionRepository.findOne(id); }

    public Institution findInstitutionByName(String name){
        return institutionRepository.findFirstByName(name); }


    public List<Institution> returnListOfInstitution(){ return institutionRepository.findAll(); }

    public List<InstitutionListOfWhomHelp> returnWhomHelpList(){
        return institutionListOfWhomHelpRepository.findAll(); }


    public void checkIfInstitutionIsUnique(Institution institution, Err modelErr) {

        Institution institutionToCheck = institutionRepository.findFirstByName(institution.getName());

        if(institutionToCheck != null){
            modelErr.addErr("Institution isn't unique!");
        }
    }

    public void checkIfInstitutionIsUniqueDuringEditing(Institution institution,Err modelErr) {

        Institution institutionToCheck = institutionRepository.findFirstByName(institution.getName());

        if(institutionToCheck != null && institutionToCheck.getId()!=(institution.getId())){
            modelErr.addErr("Institution isn't unique!");
        }
    }

    public boolean isEnableToDeleteInstitution(Long id){

        Institution institution = findInstitution(id);
        return institution.getBountyDetails().isEmpty();
    }



}

