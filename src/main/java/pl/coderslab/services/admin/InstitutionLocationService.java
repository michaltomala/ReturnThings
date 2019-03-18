package pl.coderslab.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.entity.InstitutionLocation;
import pl.coderslab.model.Err;
import pl.coderslab.repository.InstitutionLocationRepository;

import java.util.List;


@Service
public class InstitutionLocationService {

    private final InstitutionLocationRepository institutionLocationRepository;


    @Autowired
    public InstitutionLocationService(InstitutionLocationRepository institutionLocationRepository) {
        this.institutionLocationRepository = institutionLocationRepository;
    }

    public void saveLocation(InstitutionLocation location){ institutionLocationRepository.save(location); }

    public void deleteLocation(Long id){ institutionLocationRepository.delete(id); }



    public InstitutionLocation findLocation(Long id){ return institutionLocationRepository.findOne(id); }

    public List<InstitutionLocation> returnListOfLocations(){
        return institutionLocationRepository.findAll(); }

    public List<InstitutionLocation> returnListOfLocationsEnableToDelete(){
        return institutionLocationRepository.findAllByInstitutionIsNull(); }



    public void checkIfLocationIsNotEmpty(InstitutionLocation institutionLocation, Err modelErr){
        if(institutionLocation.getLocation().equals("")) {
            modelErr.addErr("Empty Location!");
        }
    }

    public void checkIfLocationIsUnique(InstitutionLocation institutionLocation, Err modelErr){
        InstitutionLocation location =
                institutionLocationRepository.findFirstByLocation(institutionLocation.getLocation());
        if(location!=null){
            modelErr.addErr("Location isn't unique!");
        }
    }

    public void checkIfLocationIsUniqueDuringEditing(InstitutionLocation institutionLocation, Err modelErr){
        InstitutionLocation location =
                institutionLocationRepository.findFirstByLocation(institutionLocation.getLocation());
        if(location!=null && location.getId()!=institutionLocation.getId()){
            modelErr.addErr("Location isn't unique!");
        }
    }

    public void checkIfDeleteLocationIsPossible(Long id, Err modelErr){
        InstitutionLocation location = institutionLocationRepository.findOne(id);
        if(location.getInstitution().size() != 0) {
            modelErr.addErr("Not enable to delete!");
        }
    }





}
