package pl.coderslab.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.BountyType;
import pl.coderslab.repository.BountyTypeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BountyService {

    private final BountyTypeRepository bountyTypeRepository;


    @Autowired
    public BountyService(BountyTypeRepository bountyTypeRepository) {
        this.bountyTypeRepository = bountyTypeRepository;
    }

// todo jeśli się da - wyciągnąć bezpośrednio z repo
    public List<String> getAllBountyType(){
        List<BountyType> bountyTypes = bountyTypeRepository.findAll();
        List<String> listToReturn = new ArrayList<>();
        for(BountyType bountyType: bountyTypes){
            listToReturn.add(bountyType.getType());
        }
        return listToReturn;
    }



}
